package cdc;

import cdc.exceptions.ExitException;
import cdc.option.Options;
import javafx.scene.input.DataFormat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Vector;

public class Program implements ProgramI {
    private DBHelper dbHelper;
    private Vector<Submission> submissions;

    public String currentSubmissionName = "<Unknown Submission>";

    public cdc.option.Options options;

    public int parseErrorsNum;

    public Program(Options options){
        this.options=options;
        if(!options.isReadCodeFromFile()){
            int i;
            for (i = 0; i < Options.dbs.length - 1; i += 2) {
                if (options.dbName.equals(Options.dbs[i])) {
                    try {
                        Constructor<?> con = Class.forName(options.dbName).getDeclaredConstructor();
                        dbHelper = (DBHelper) con.newInstance(this);
                        dbHelper.prepareData();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }

            }
            if (i >= Options.dbs.length)
                System.out.println("DB " + options.dbName + " not found!");
        }
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
            Vector<SubmissionBase> base=dbHelper.getSubmissionList();
            Iterator<SubmissionBase> iter=base.iterator();
            while (iter.hasNext()){
                SubmissionBase tempBase=iter.next();
                submissions.addElement(
                        new Submission(""+tempBase.runid,tempBase.name,tempBase.code,
                                this,this.options.language)
                );
            }
        }
    }

    private void parseAll() throws ExitException{
        if(submissions==null){
            print("Nothing to parse! Program.java");
            return;
        }
        int count = 0;
        int totalcount = submissions.size();
        options.setState(Options.STATE_PARSING);
        options.setProgress(0);
        long msec = System.currentTimeMillis();
        Iterator<Submission> iter = submissions.iterator();

        int invalid=0;
        while(iter.hasNext()){
            boolean parseOk=true;
            boolean removed=false;
            Submission subm=iter.next();
            print("----Parsing submission:"+subm.name+"...");
            options.setProgress(count * 100 / totalcount);
            if(!(parseOk=subm.parse()))
                parseErrorsNum++;
            count++;
            if (subm.struct != null && subm.tokenLength() < options.min_token_match) {
                print("          Submission contains fewer tokens than minimum match " + get_min_token_match()+"\n");
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
    private void compare(){
        int size=submissions.size();


    }
    private void run() throws ExitException {
        print("Language: " + options.language.name() + "\n\n");
        creatSubmission();
        parseAll();
        System.gc();
        if (validSubmissions() < 2) {
            throw new ExitException("Not enough valid submissions! (only " + validSubmissions() + " "
                    + (validSubmissions() != 1 ? "are" : "is") + " valid):\n" , ExitException.NOT_ENOUGH_SUBMISSIONS_ERROR);
        }
//        if (options.clustering) {
//            clusters = new Clusters(this);
//            options.similarity = new SimilarityMatrix(submissions.size());
//        }
        compare();
    }
    public void runCheckCodeFrom() throws cdc.exceptions.ExitException{
        if(this.options.isReadCodeFromFile())
            run();
        else{
            while(dbHelper.isHaveProblemNotCheck()){
                run();
                System.gc();
            }
        }

    }

}
