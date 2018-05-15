package cdc;

import cdc.exceptions.ExitException;
import cdc.option.CommandOptions;

public class CDCMain {
    public static void main(String[] args) {
        long msec = System.currentTimeMillis();//开始时间
        try {
            CommandOptions commandOptions = new CommandOptions(args);
            Program program = new Program(commandOptions);
            System.out.println("Initialize OK");
            program.runcodeChecker();
        } catch (ExitException e) {
            System.out.println("ERROR: " + e.getReport());
            System.exit(e.getState());
        }
        long time = System.currentTimeMillis() - msec;
        System.out.println("\n\n\nTotal time for Program: " + ((time / 3600000 > 0) ? (time / 3600000) + " h " : "")
                + ((time / 60000 > 0) ? ((time / 60000) % 60000) + " min " : "") + (time / 1000 % 60) + " sec\n");
    }
}
