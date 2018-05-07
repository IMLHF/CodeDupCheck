package cdc;

public class SubmissionBase {
    public int pid;
    public int cid;
    public int runid;
    public String name;
    public String code;
    public SubmissionBase(int cid,int pid,int runid,String name ,String code){
        this.cid=cid;
        this.pid=pid;
        this.runid=runid;
        this.name=name;
        this.code=code;
    }
    public final String toString(){
        return ""+cid
                +" "+pid
                +" "+runid
                +" "+name;
    }
}
