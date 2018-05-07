package cdc.option;

import cdc.DBHelper;
import cdc.Language;
import cdc.Program;
import cdc.exceptions.ExitException;

import java.lang.reflect.Constructor;

public class CommandOptions extends Options {


    private String[] args;

    private int scanOption(int i) throws ExitException {
        String arg = args[i];
        if (arg.equals("-l") && i + 1 < args.length) {
            languageIsFound = true;
            languageName = args[++i].toLowerCase();
        } else if (arg.equals("-r") && i + 1 < args.length) {
            result_dir = args[++i];
        } else if (arg.equals("-m") && i + 1 < args.length) {
            try {
                store_percent = Integer.parseInt(args[++i]);
            } catch (NumberFormatException e) {
                System.out.println("-m parameter error");
                throw new ExitException("Parameter of option -m error", ExitException.BAD_PARAMETER);
            }
        } else if (arg.equals("-db") && i + 1 < args.length) {
            dbName = args[++i];
        } else if (arg.equals("-s") && i + 1 < args.length) {
            root_dir = args[++i];
        } else if (arg.equals("-command") && i + 1 < args.length) {
            i++;
            if (args[i] .equals("FIFO")){
                this.IOMETHOD = Options.FIFO;
                setCodeSourceFromFile(true);
                setResultToFile(true);
            }
            else if (args[i].equals("DIFO")){
                this.IOMETHOD = Options.DIFO;
                setCodeSourceFromFile(false);
                setResultToFile(true);
            }
            else if (args[i].equals("DIDO")){
                this.IOMETHOD = Options.DIDO;
                setCodeSourceFromFile(false);
                setResultToFile(false);
            }
            else
                throw new ExitException("Parameter of option -command error", ExitException.BAD_PARAMETER);
        } else if (arg.equals("-mt") && i + 1 < args.length) {
            try {
                min_token_match = Integer.parseInt(args[++i]);
            } catch (NumberFormatException e) {
                System.out.println("-mt parameter error");
                throw new ExitException("Parameter of option -mt error", ExitException.BAD_PARAMETER);
            }
            if (min_token_match < 1)
                throw new cdc.exceptions.ExitException(
                        "Illegal value: Minimum token length is less or " +
                                "equal zero!", ExitException.BAD_SENSITIVITY_OF_COMPARISON);
            min_token_match_setting = true;
        }else if(arg.equals("--quiet")){
            msg_quiet=true;
        }
        return i;
    }

    public CommandOptions(String[] args) throws cdc.exceptions.ExitException {
        this.args = args;
        int i;
        for (i = 0; i < args.length; ++i) {
            if (args[i].startsWith("-"))
                i = scanOption(i);
        }
        if(args.length==0)
            usage();
        else{
            for (i = 0; i < args.length; i++)
                this.commandLine += args[i] + " ";
        }
    }


    @Override
    public void iniLanguage(Program program) throws cdc.exceptions.ExitException {
        int i;
        for (i = 0; i < languages.length - 1; i += 2) {
            if (languageName.equals(languages[i])) {
                try {
                    Constructor<?>[] languageConstructors = Class.forName(languages[i + 1]).getDeclaredConstructors();
                    Constructor<?> cons = languageConstructors[0];
                    Object[] ob = {program};
                    this.language = (Language) cons.newInstance(ob);
                    System.out.println("Language received ----------------  " + language.name());
                    this.min_token_match = this.language.min_token_match();
                    this.suffixes = language.suffixes();
                } catch (ClassNotFoundException e) {
                    System.out.println(e.getMessage() + "  Language initial error");
                } catch (InstantiationException e) {
                    System.out.println(e.getMessage() + " Instantialtion eror");
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new cdc.exceptions.ExitException("Language instantiation failed!");
                }
                break;
            }
        }
        if (i >= languages.length)
            throw new ExitException("Unknow language : " + languageName);
    }

    @Override
    public void iniDBHelper(Program program) throws cdc.exceptions.ExitException {
        if (!this.isReadCodeFromFile()) {
            int i;
            for (i = 0; i < Options.dbs.length - 1; i += 2) {
                if (this.dbName.equals(Options.dbs[i])) {
                    try {
                        Constructor<?> con = Class.forName(this.dbName).getDeclaredConstructor();
                        this.dbHelper = (DBHelper) con.newInstance(this);
                        this.dbHelper.prepareData();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new cdc.exceptions.ExitException("DBHelper initial failed!");
                    }
                    break;
                }
            }
            if (i >= Options.dbs.length)
                throw new ExitException("Unknown database : " + this.dbName);
        }
    }
}
