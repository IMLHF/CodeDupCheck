package cdc;

public class SubmissionBase {
    public int pid;
    public int cid;
    public int runid;
    public String name;
    public String code;
    public String result;
    public String languageType;
    public SubmissionBase(int cid,int pid,int runid,String name ,String code,String result,String languageType){
        this.cid=cid;
        this.pid=pid;
        this.runid=runid;
        this.name=name;
        this.code=code;
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
