package cdc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Vector;

public class Submission implements Comparable<Submission>{
    private Program program;
    private Language language;
    public boolean error=false;

    private int runid;//from DB

    private String file;//from file

    public String name;
    private String code;


    public Structure struct;

    public Submission(int runid, String name, String code, Program p, Language language){
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
    public String[][] readCode() throws cdc.exceptions.ExitException {
        String[][] result = new String[1][];
        String help;

        Vector<String> text = new Vector<String>();
        try {
            BufferedReader in = new BufferedReader(new StringReader(code));
            while ((help = in.readLine()) != null) {
                help = help.replaceAll("&", "&amp;");
                help = help.replaceAll("<", "&lt;");
                help = help.replaceAll(">", "&gt;");
                help = help.replaceAll("\"", "&quot;");
                text.addElement(help);
            }
            in.close();
        } catch (IOException e) {
            throw new cdc.exceptions.ExitException("I/O exception!");
        }
        result[0] = new String[text.size()];
        text.copyInto(result[0]);
        return result;
    }
    public void readFilesChar(String[] files) throws cdc.exceptions.ExitException {

    }

    public int compareTo(Submission other) {
        return name.compareTo(other.name);
    }
    public String toString(){return name;}
}
