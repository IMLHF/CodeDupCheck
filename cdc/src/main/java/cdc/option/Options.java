package cdc.option;

import cdc.Language;
import cdc.exceptions.ExitException;

abstract public class Options {
    protected boolean min_token_match_setting=false;
    public int min_token_match;
    public static String[] dbs={
            "mongo","cdc.personalTool.MongoDBHelper"
    };
    protected static String[] languages={
            "python3", "jplag.python3.Language",
            "cdc/cpp14",   "jplag.Language",
            "c#",    "jplag.csharp6.Language"
    };

    public String dbName;
    public String languageName;
    public static final int MIN_CLUSTER=1;
    public static final int MAX_CLUSTER=2;
    public static final int AVR_CLUSTER=3;

    public boolean msg_quiet=false;

    public boolean languageIsFound=false;

    //默认从文件读取代码"file"; 可使用"mongo"从mongo数据库获取.
    private boolean codeSourceFromFile=true;
    public boolean isReadCodeFromFile(){
        return codeSourceFromFile;
    }

    public int cid;
    public String root_dir = "";
    public String result_dir = "result";

    public String[] suffixes;

    public int store_matches = 30;
    public boolean store_percent=false; //保存30个还是保存相似度30%以上的结果

    public static final int MAX_RESULT_PAIRS=1000;

    public static final int COMPMODE_NORMAL = 0;
    public static final int COMPMODE_REVISION = 1;
    public int comparisonMode = COMPMODE_NORMAL;

    public String commandLine = "";

    public static final int STATE_PARSING = 100;
    public static final int STATE_PARSING_WARNINGS = 101;
    public static final int STATE_COMPARING = 200;
    public static final int STATE_GENERATING_RESULT_FILES = 250;
    public static final int STATE_SUBMISSION_ABORTED = 405;
    private int state=50;
    private int int_progress=0;
    private boolean forceStop=false;

    public void forceProgramToStop(){
        forceStop=true;
    }

    public boolean isForceStop() {
        return forceStop;
    }

    public int getState(){return state;}

    //设置parse的进度
    public void setProgress(int progress) throws cdc.exceptions.ExitException{
        int_progress=progress;
        if(forceStop)
            throw new cdc.exceptions.ExitException("Sbumission aborted",ExitException.SUBMISSION_ABORTED);
    }
    //获取parse的进度
    public int getProgress(){
        return int_progress;
    }

    public void setState(int state){
        this.state=state;
    }

    public Language language=null;
    public static void usage(){
        System.out.print("Start -->\n"
                +"\n"

        );
    }

}
