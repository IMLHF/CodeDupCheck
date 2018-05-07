package cdc;

import cdc.exceptions.ExitException;
import cdc.option.Options;
import javafx.scene.input.DataFormat;
import javafx.util.Pair;

import java.io.*;
import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Vector;

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
        if (this.options.isReadCodeFromFile()) {
            submissions = new Vector<Submission>();
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
                String codeString = null;
                try {
                    FileInputStream in = new FileInputStream(codefile);
                    Long filelength = codefile.length();
                    byte[] filecontent = new byte[filelength.intValue()];
                    in.read(filecontent);
                    in.close();
                    codeString = new String(filecontent, "utf-8");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                submissions.addElement(
                        new Submission(name, name, (codeString != null) ? codeString : "null",
                                this, this.options.language));
            }
        } else {
            //from DB
            Vector<SubmissionBase> base = options.dbHelper.getSubmissionListAndRemove();
            Iterator<SubmissionBase> iter = base.iterator();
            while (iter.hasNext()) {
                SubmissionBase tempBase = iter.next();
                submissions.addElement(
                        new Submission(tempBase.cid, tempBase.pid, tempBase.runid, tempBase.name, tempBase.code,
                                this, this.options.language)
                );
            }
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
            print("----Parsing submission:" + subm.name + "...");
            options.setProgress(count * 100 / totalcount);
            if (!(parseOk = subm.parse()))
                parseErrorsNum++;
            count++;
            if (subm.struct != null && subm.tokenLength() < options.min_token_match) {
                print("          Submission contains fewer tokens than minimum match " + get_min_token_match() + "\n");
                subm.struct = null;
                invalid++;
                removed = true;
            }
            if (parseOk && !removed)
                print("OK\n");
            else
                print("          ERROR -> Submission removed\n");

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
//            if(avgmatches.size()>Options.MAX_RESULT_PAIRS)
//                avgmatches.removeElementAt(Options.MAX_RESULT_PAIRS);
        }
//        if(options.clustering)
//            options.sim


    }

    private void writeResultsToMongo(int[] dist, SortedVector<PairSubmission> pairSubmissions
    ) throws ExitException {
        options.setState(Options.STATE_GENERATING_RESULT_TO_FILES);
        options.setProgress(0);

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
            FileOutputStream fos =new FileOutputStream(resultFile);
            PrintWriter pw = new PrintWriter(fos);
            Iterator<PairSubmission> iter=pairSubmissions.iterator();
            while(iter.hasNext()){
                PairSubmission pairTemp=iter.next();
                String line=pairTemp.subA.name+" , "+pairTemp.subB.name+"  similaration : "+pairTemp.percent()+"\n";
                pw.write(line);
            }
            pw.close();
            fos.close();
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

                print("----Compared " + s1.name + "-" + s2.name + " match percent: " + pairSubmission.percent()+"\n");

                registerMatch(pairSubmission, dist, avgmatches);
                options.setProgress(countAll * 100 / totalcomps);

            }
        }
        options.setProgress(100);
        long time = System.currentTimeMillis() - msec;
        print("Total time for comparing submissions: " + ((time / 3600000 > 0) ? (time / 3600000) + " h " : "")
                + ((time / 60000 > 0) ? ((time / 60000) % 60000) + " min " : "") + (time / 1000 % 60) + " sec\n" + "Time per comparison: "
                + (time / comOK) + " msec\n");

        //Cluster cluster = null;
        //if (options.clusterin g)
        //   cluster = this.clusters.calculateClustering(submissions);

        if (!options.isWriteResultToFile())
            writeResultsToMongo(dist, avgmatches);
        else
            writeResultToFile(dist, avgmatches);

    }

    private void run() throws ExitException {
        print("Language: " + options.language.name() + "\n\n");
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
        if (this.options.isReadCodeFromFile()) {//对文件夹中的代码查重
            run();
            System.gc();
        }
        else {//对比赛查重
            while (this.options.dbHelper.isHaveProblemNotCheck()) {
                run();
                System.gc();
            }
        }

    }

}
