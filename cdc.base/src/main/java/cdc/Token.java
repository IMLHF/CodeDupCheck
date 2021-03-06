package cdc;


abstract public class Token implements TokenConstants {
    public int type;
    public String runidOrFileName;

    protected boolean marked; //GST算法使用的标记位
    protected int hash = -1;

    public Token(int type, String runidOrFileName, int line, int column, int length){
        this.type=type;
        this.runidOrFileName = runidOrFileName;
        setLine(line>0 ? line : 1);
        setColumn(column);
        setLength(length);

    }
    public Token(int type, String runidOrFileName, int line) {
        this(type, runidOrFileName, line, -1, -1);
    }

    abstract public int getLine();
    abstract public int getColumn();
    abstract public int getLength();
    abstract public int getType();
    abstract public String getRunidOrFileName();
    protected void setLine(int line){}
    protected void setColumn(int column){}
    protected void setLength(int Length){}

    protected int getIndex(){
        return -1;
    }

    public static String type2string(int type){
        return "<abstract>";
    }
    public String toString(){
        return type2string(type);
    }
    public static int numberOfTokens() {
        return -1;
    }



}
