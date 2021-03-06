package cdc.option;

import cdc.DBHelper;
import cdc.Language;
import cdc.Program;
import cdc.exceptions.ExitException;

abstract public class Options {
    protected boolean min_token_match_setting=false;
    public int min_token_match;
    public static final int MIN_CLUSTER=1;
    public static final int MAX_CLUSTER=2;
    public static final int AVR_CLUSTER=3;

    public boolean msg_quiet=false;


    //默认从文件读取代码"file"; 可使用"mongo"从mongo数据库获取.
    private boolean codeSourceFromFile=true;
    public boolean isReadCodeFromFile(){
        return codeSourceFromFile;
    }
    public void setCodeSourceFromFile(boolean tmp){
        codeSourceFromFile=tmp;
    }

    //默认将结果写入文件
    private boolean resultToFile=true;
    public boolean isWriteResultToFile(){
        return resultToFile;
    }
    public void setResultToFile(boolean tmp){
        resultToFile=tmp;
    }

    public int cid;
    public int pid;
    public String nowProblemLabelAndName;
    public String root_dir = "./";
    public String result_dir = "result";
    public void setPidLabelAndName(String t){
        nowProblemLabelAndName=t;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }
    public int getPid(){
        return pid;
    }

    public String[] suffixes;
    public String[] languageTypes;

    public int store_percent=60; //默认保存相似度60%以上的配对

    //public static final int MAX_RESULT_PAIRS=1000;

//    public static final int COMPMODE_NORMAL = 0;
//    public static final int COMPMODE_REVISION = 1;
//    public int comparisonMode = COMPMODE_NORMAL;

    public String commandLine = "";

    public static final int STATE_SUBMITTING = 8;
    public static final int STATE_PARSING = 68;
    public static final int STATE_COMPARING = 88;
    public static final int STATE_GENERATING_RESULT_TO_FILES = 100;
    private int state=STATE_SUBMITTING;
    private int int_progress=0;
    private long time=-1;
    public long starttime=System.currentTimeMillis();


    public int getState(){return state;}

    //设置parse的进度
    public void setProgress(int progress) {
        long time=(System.currentTimeMillis()-starttime)/1000;
        if(progress!=int_progress || this.time<time){
            this.time=time;
            if(!isWriteResultToFile())
                dbHelper.setProgressToDB(cid,pid,nowProblemLabelAndName,
                        progress,state,task_id,time);
        }
        int_progress=progress;
    }
    //获取parse的进度
    public int getProgress(){
        return int_progress;
    }

    public void setState(int state){
        this.state=state;
    }

    public static void usage(){
        System.out.print("如何使用\n"
                +"\n"

        );
    }


    /**
     *语言设置
     *
     */
    protected static String[] languages={
            "python3", "cdc.python3.Language",
            "cpp14",   "cdc.cpp14.Language",
            "c#",    "cdc.csharp6.Language"
    };

    public Language language=null;
    public boolean languageIsFound=false;
    public String languageName="cpp14";
    abstract public void iniLanguage(Program program)throws ExitException;


    /**
     * 数据库设置
     */
    public static String[] dbs={
            "mongo","cdc.personalTool.MongoDBHelper"
    };
    public String dbName="mongo";
    public DBHelper dbHelper=null;
    abstract public void iniDBHelper(Program program)throws ExitException;

    /**
     * 输入输出方式
     */
    public static int FIFO=0; //读入文件 输出文件
    public static int DIFO=1; //从数据库读入 输出到文件
    public static int DIDO=2; //从数据库读入 输出到数据库
    public int IOMETHOD=FIFO;

    public boolean ifOnePersonOneCode=false;
    public boolean ifReCDC=false;//全新进行查重 仅DIDO生效
    public boolean ifReParse=false;//重新Parse,仅DIDO生效

    /**
     * celery task id
     */
    public String task_id=""+System.currentTimeMillis();


}
