package cdc;

public interface ProgramI {
    public boolean isQuiet();
    public boolean isReCDC();
    public boolean isReParse();
    public void print(String msg);
    public int getCid();
    public boolean isReadCodeFromFile();
    public DBHelper getDBhelperInstance();
}
