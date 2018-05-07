package cdc;

import cdc.exceptions.ExitException;
import cdc.option.CommandOptions;

public class CDCMain {
    public static void main(String[] args) {
        try {
            CommandOptions commandOptions = new CommandOptions(args);
            Program program = new Program(commandOptions);
            System.out.println("Initialize OK");
            program.runcodeChecker();
        } catch (ExitException e) {
            System.out.println("ERROR: " + e.getReport());
            System.exit(e.getState());
        }
    }
}
