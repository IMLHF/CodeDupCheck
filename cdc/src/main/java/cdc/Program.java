package cdc;

import cdc.exceptions.ExitException;
import cdc.option.Options;
import org.bson.Document;
import java.io.*;
import java.util.*;

public class Program implements ProgramI {
    private Vector<Submission> submissions;

    public String currentSubmissionName = "<Unknown Submission>";

    public cdc.option.Options options;

    public int parseErrorsNum;

    private TokenMatchingGST tokenMatchingGST=new TokenMatchingGST(this);;

    public Program(Options options) throws ExitException {
        this.options = options;
        options.iniLanguage(this);
        options.iniDBHelper(this);
    }

    public DBHelper getDBhelperInstance() {
        return options.dbHelper;
    }

    public boolean isReadCodeFromFile() {
        return options.isReadCodeFromFile();
    }

    public int getCid() {
        return options.cid;
    }


    public void print(String msg) {
        if (options.msg_quiet)
            return;
        System.out.print(msg);
    }

    public int get_min_token_match() {
        return this.options.min_token_match;
    }

    protected int validSubmissions() {
        if (submissions == null)
            return 0;
        int size = 0;
        for (int i = submissions.size() - 1; i >= 0; i--) {
            if (!submissions.elementAt(i).error)
                size++;
        }
        return size;
    }

    private void creatSubmission() throws cdc.exceptions.ExitException {
        submissions = new Vector<Submission>();
        if (this.options.isReadCodeFromFile()) {
            File f = new File(options.root_dir);
            if (f == null || !f.isDirectory()) {
                throw new cdc.exceptions.ExitException("Dircector \"" + options.root_dir + "\" not found!");
            }
            String[] list = null;
            try {
                list = f.list();
            } catch (SecurityException e) {
                throw new cdc.exceptions.ExitException("Unable to retrieve directory: " + options.root_dir + " Cause : " + e.toString());
            }
            Arrays.sort(list);

            for (int i = 0; i < list.length; ++i) {
                File codefile = new File(f, list[i]);
                if (codefile.isDirectory())
                    continue;
                boolean suffixesOk = false;
                String name = codefile.getName();
                for (int j = 0; j < options.suffixes.length; ++j) {
                    if (name.endsWith(options.suffixes[j])) {
                        suffixesOk = true;
                        break;
                    }
                }
                if (!suffixesOk)
                    continue;

                submissions.addElement(
                        new Submission(codefile.toString(), name,
                                this, this.options.language));
            }
        } else {
            //from DB
            Vector<SubmissionBase> base = options.dbHelper.getSubmissionListAndRemove();
            Iterator<SubmissionBase> iter = base.iterator();
            Set<String>nameSet=new HashSet<String>();
            while (iter.hasNext()) {
                SubmissionBase tempBase = iter.next();

                //过滤代码语言
                String lanType=tempBase.languageType;
                boolean languageTypeOk = false;
                for (int j = 0; j < options.languageTypes.length; ++j) {
                    if (lanType.endsWith(options.languageTypes[j])) {
                        languageTypeOk = true;
                        break;
                    }
                }
                if (!languageTypeOk)
                    continue;

                //过滤ac的结果
                String subResult=tempBase.result;
                if(subResult.equals("Accepted") || subResult.equals("accepted") ||subResult.equals("accept")){
                    if(options.ifOnePersonOneCode){//一人只留一份ac的代码
                        if(nameSet.add(tempBase.name)) {
                            submissions.addElement(
                                    new Submission(tempBase.cid, tempBase.pid, tempBase.runid, tempBase.name,
                                            this, this.options.language)
                            );
                        }
                    }else{
                        submissions.addElement(
                                new Submission(tempBase.cid, tempBase.pid, tempBase.runid, tempBase.name,
                                        this, this.options.language)
                        );
                    }
                }

            }
            nameSet=null;
        }
    }

    private void parseAll() throws ExitException {
        if (submissions == null) {
            print("Nothing to parse! Program.java");
            return;
        }
        int count = 0;
        int totalcount = submissions.size();
        long msec = System.currentTimeMillis();
        Iterator<Submission> iter = submissions.iterator();

        options.setState(Options.STATE_PARSING);
        options.setProgress(0);

        int invalid = 0;
        while (iter.hasNext()) {
            boolean parseOk = true;
            boolean removed = false;
            Submission subm = iter.next();
            print("|----Parsing submission:" + subm.name + "...\n");
            options.setProgress(count * 100 / totalcount);
            if (!(parseOk = subm.parse()))
                parseErrorsNum++;
            count++;
            if (subm.struct != null && subm.tokenLength() < options.min_token_match) {
                print("          |----Submission contains fewer tokens than minimum match " + get_min_token_match() + "\n");
                subm.struct = null;
                invalid++;
                removed = true;
            }
            if (parseOk && !removed)
                print("                                    |----OK\n");
            else
                print("          |----ERROR -> Submission removed\n");

        }

        options.setProgress(100);

        print("\n" + (count - parseErrorsNum - invalid) + " submissions parsed successfully!\n" + parseErrorsNum + " parsing error"
                + (parseErrorsNum != 1 ? "s!\n" : "!\n"));
        long time = System.currentTimeMillis() - msec;
        print("\nTotal time for parsing: " + ((time / 3600000 > 0) ? (time / 3600000) + " h " : "")
                + ((time / 60000 > 0) ? ((time / 60000) % 60000) + " min " : "") + (time / 1000 % 60) + " sec\n"
                + "Time per parsed submission: " + (count > 0 ? (time / count) : "n/a") + " msec\n\n");

    }

    private void registerMatch(PairSubmission pairSubmission, int[] dist,
                               SortedVector<PairSubmission> avgmatches) {
        float avgpercent = pairSubmission.percent();
        dist[(((int) avgpercent) / 10) == 10 ? 9 : ((int) avgpercent) / 10]++;
        if (avgpercent > options.store_percent) {
            avgmatches.insert(pairSubmission);
        }
//        if(options.clustering)
//            options.sim
    }

    private void writeResultsToMongo(int[] dist, SortedVector<PairSubmission> pairSubmissions
    ) throws ExitException {
        options.setState(Options.STATE_GENERATING_RESULT_TO_FILES);
        options.setProgress(0);
        if(pairSubmissions.size()<=0){
            System.out.println("未检查到相似代码！ similar code not found!");
            return;
        }
        Iterator<PairSubmission>iter=pairSubmissions.iterator();
        List<Document> comparePairList=new ArrayList<Document>();
        while(iter.hasNext()){
            PairSubmission tmpPair=iter.next();
            Document tmpDocument=new Document();
            tmpDocument.append("cid",tmpPair.subA.getCid());

            tmpDocument.append("runidA",tmpPair.subA.getRunid());
            tmpDocument.append("codeNameA",tmpPair.subA.name);
            tmpDocument.append("pidA",tmpPair.subA.getPid());
            tmpDocument.append("runidB",tmpPair.subB.getRunid());
            tmpDocument.append("codeNameB",tmpPair.subB.name);
            tmpDocument.append("pidB",tmpPair.subB.getPid());

            List<Document> matchesList=new ArrayList<Document>();
            Match[] matches=tmpPair.matches;
            //System.out.println("_____________num    "+tmpPair.matchesNum());
            for(int i=0;i<tmpPair.matchesNum();++i){
                Document tmpDoc=new Document();
                tmpDoc.append("startA",matches[i].startA);
                tmpDoc.append("startB",matches[i].startB);
                tmpDoc.append("length",matches[i].length);
                matchesList.add(tmpDoc);
            }
            tmpDocument.append("matches",matchesList);
            tmpDocument.append("matchesPercent",tmpPair.percent());

            comparePairList.add(tmpDocument);
        }
        Document doc=new Document("cid",pairSubmissions.elementAt(0).subA.getCid());
        doc.append("pid",pairSubmissions.elementAt(0).subA.getPid());
        doc.append("comparisonPairs",comparePairList);
        this.options.dbHelper.writeDocument(doc);


    }

    private void writeResultToFile(int[] dist, SortedVector<PairSubmission> pairSubmissions
    ) throws ExitException {
        options.setState(Options.STATE_GENERATING_RESULT_TO_FILES);
        options.setProgress(0);
        File f = new File(options.result_dir);
        if (!f.exists())
            if (!f.mkdirs())
                throw new ExitException("Writing result, cannot create directory " + options.result_dir);
        if (!f.isDirectory())
            throw new ExitException(options.result_dir + " is not a directory!");
        if (!f.canWrite())
            throw new ExitException("Cannot write directory " + options.result_dir);

        File resultFile = new File(f, "result.text");
        try {
            if (!resultFile.exists())
                resultFile.createNewFile();
            FileWriter fw=new FileWriter(resultFile,true);
            PrintWriter pw = new PrintWriter(fw);
            Iterator<PairSubmission> iter=pairSubmissions.iterator();
            while(iter.hasNext()){
                PairSubmission pairTemp=iter.next();
                String line=pairTemp.subA.name+" , "+pairTemp.subB.name+"  similaration : "+pairTemp.percent()+"\n";
                pw.write(line);
            }
            pw.close();
            fw.close();
        }catch(IOException e){
            e.printStackTrace();
        }


    }

    private void compare() throws ExitException {
        int size = submissions.size();

        SortedVector<PairSubmission> avgmatches, maxmatches;
        int dist[] = new int[10];

        avgmatches = new SortedVector<PairSubmission>(new PairSubmission.AvgComparator());
        maxmatches = new SortedVector<PairSubmission>(new PairSubmission.MaxComparator());

        long msec = System.currentTimeMillis();//开始时间
        Submission s1, s2;

        options.setState(Options.STATE_COMPARING);
        options.setProgress(0);

        int totalcomps = (size - 1) * size / 2;
        int i, j, comOK = 0, countAll = 0;

        PairSubmission pairSubmission;
        for (i = 0; i < (size - 1); ++i) {
            s1 = submissions.elementAt(i);
            if (s1.struct == null) {
                countAll++;
                continue;
            }
            for (j = i + 1; j < size; j++) {
                s2 = submissions.elementAt(j);
                if (s2.struct == null) {
                    countAll++;
                    continue;
                }
                pairSubmission = this.tokenMatchingGST.compare(s1, s2);

                comOK++;

                print("|----Compared " + s1.name + "-" + s2.name + " match percent: " + pairSubmission.percent()+"\n");

                registerMatch(pairSubmission, dist, avgmatches);
                options.setProgress(countAll * 100 / totalcomps);

            }
        }
        options.setProgress(100);
        long time = System.currentTimeMillis() - msec;
        print("Total time for comparing submissions: " + ((time / 3600000 > 0) ? (time / 3600000) + " h " : "")
                + ((time / 60000 > 0) ? ((time / 60000) % 60000) + " min " : "") + (time / 1000 % 60) + " sec\n" + "Time per comparison: "
                + (comOK==0?0 : (time / comOK)) + " msec\n");

        //Cluster cluster = null;
        //if (options.clusterin g)
        //   cluster = this.clusters.calculateClustering(submissions);

        if (!options.isWriteResultToFile())
            writeResultsToMongo(dist, avgmatches);
        else
            writeResultToFile(dist, avgmatches);

    }

    private void run() throws ExitException {
        creatSubmission();
        parseAll();
        System.gc();
        if (validSubmissions() < 2) {
            throw new ExitException("Not enough valid submissions! (only " + validSubmissions() + " "
                    + (validSubmissions() != 1 ? "are" : "is") + " valid):\n", ExitException.NOT_ENOUGH_SUBMISSIONS_ERROR);
        }
//        if (options.clustering) {
//            clusters = new Clusters(this);
//            options.similarity = new SimilarityMatrix(submissions.size());
//        }
        compare();
    }

    public void runcodeChecker() throws cdc.exceptions.ExitException {
        print("Language: " + options.language.name() + "\n\n");
        if (this.options.isReadCodeFromFile()) {//对文件夹中的代码查重
            run();
            System.gc();
        }
        else {//对比赛查重
            this.options.dbHelper.removeCDCANS(this.options.cid);
            while (this.options.dbHelper.isHaveProblemNotCheck()) {
                try {
                    run();
                }catch (ExitException e){
                    if(e.getState()==ExitException.NOT_ENOUGH_SUBMISSIONS_ERROR){
                        System.out.println(e.getReport());
                    }else
                        throw e;
                }
                System.gc();
            }
        }

    }

}
