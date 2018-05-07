package cdc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Vector;

public class Submission implements Comparable<Submission>{
    private Program program;
    private Language language;
    public boolean error=false;

    private int cid;
    private int pid;
    private int runid;//from DB

    public int getCid(){return cid;}
    public int getPid(){return pid;}
    public int getRunid(){return runid;}
    private String file;//from file

    public String name;
    private String code;


    public Structure struct;

    public Submission(int cid,int pid,int runid, String name, String code, Program p, Language language){
        this.cid=cid;
        this.pid=pid;
        this.runid=runid;
        this.name=name;
        this.code=code;
        this.program=p;
        this.language=language;
    }

    public Submission(String file, String name, String code, Program p, Language language){
        this.file=file;
        this.name=name;
        this.code=code;
        this.program=p;
        this.language=language;
    }
    public boolean parse()throws cdc.exceptions.ExitException{
        if(this.program.options.isReadCodeFromFile())
            struct=this.language.parse(file,code);
        else
            struct=this.language.parse(String.valueOf(runid),code);
        if(!language.errors())
            return true;
        struct=null;
        return false;
    }
    public int tokenLength(){
        if(struct!=null)
            return struct.tokenLength();
        return 0;
    }

    public int compareTo(Submission other) {
        return name.compareTo(other.name);
    }
    public String toString(){return name;}
}
