package cdc;

import cdc.exceptions.ExitException;
import cdc.option.CommandOptions;

public class CDCMain {
    Program program;

    public int getCid() {
        return program.getCid();
    }
    public int getProgress(){
        return program.options.getProgress();
    }

    public void run(String[] args){
        long msec = System.currentTimeMillis();//开始时间
        try {
            CommandOptions commandOptions = new CommandOptions(args);
            program = new Program(commandOptions);
            program.print("Initialize OK");
            program.runcodeChecker();
        } catch (ExitException e) {
            e.printStackTrace();
            System.out.println("ERROR: " + e.getReport());
            System.exit(e.getState());
        }
        long time = System.currentTimeMillis() - msec;
        program.print("\n\n\nTotal time for Program: " + ((time / 3600000 > 0) ? (time / 3600000) + " h " : "")
                + ((time / 60000 > 0) ? ((time / 60000) % 60) + " min " : "") + (time / 1000 % 60) + " sec\n");
    }
    public static void main(String[] args) {
        new CDCMain().run(args);
    }

    public CDCMain(){};
    public int test=-1;
    public String name;
    public CDCMain(String name){
        this.name=name;
    }
    public void testprint(){
        System.out.println(name+" test print: "+test);
    }
    public void testrun() throws Exception{
        test++;
        Thread.sleep(2000);
        test++;
        Thread.sleep(2000);
        test++;
        Thread.sleep(2000);
    }
    public void testplus(){
        test++;
        System.out.println(name+"test plus: " +test);
    }
    public void testset(int t){
        test=t;
    }

}
