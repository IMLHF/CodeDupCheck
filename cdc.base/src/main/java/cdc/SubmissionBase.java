package cdc;

public class SubmissionBase {
    public int pid;
    public int cid;
    public int runid;
    public String name;
    /**
     * submit result 映射
     * 0 => Waiting
     * 1 => Accepted
     * 2 => Time Limit Exceeded
     * 3 => Memory Limit Exceeded
     * 4 => Wrong Answer
     * 5 => Runtime Error
     * 6 => Output Limit Exceeded
     * 7 => Compile Error
     * 8 => Presentation Error
     * 11 => System Error
     * 12 => Judging
     */
    public static int ACCEPTED=1;
    public int result;
    public String languageType;
    public SubmissionBase(int cid,int pid,int runid,String name ,int result,String languageType){
        this.cid=cid;
        this.pid=pid;
        this.runid=runid;
        this.name=name;
        this.result=result;
        this.languageType=languageType;
    }
    public final String toString(){
        return ""+cid
                +" "+pid
                +" "+runid
                +" "+name
                +" "+result;
    }
}
