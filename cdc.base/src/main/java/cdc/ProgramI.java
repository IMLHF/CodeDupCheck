package cdc;

public interface ProgramI {
    public void print(String msg);
    public int getCid();
    public boolean isReadCodeFromFile();
    public DBHelper getDBhelperInstance();
}
