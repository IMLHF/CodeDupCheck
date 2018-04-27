package cdc.exceptions;

public class ExitException extends Exception {
    public static final int COMPARE_SOURCE_DONE = 300;
    public static final int UNKNOWN_ERROR_OCCURRED = 400;
    public static final int BAD_LANGUAGE_ERROR = 401;
    public static final int NOT_ENOUGH_SUBMISSIONS_ERROR = 402;
    public static final int BAD_PARAMETER = 403;
    public static final int BAD_SENSITIVITY_OF_COMPARISON = 404;
    public static final int SUBMISSION_ABORTED = 405;

    private int state =0;
    private String report="";

    public ExitException(String msg) {
        super(msg);
        this.state=UNKNOWN_ERROR_OCCURRED;
        this.report=msg;
    }

    public ExitException(String msg, int errorcode) {
        super(msg);
        this.state=errorcode;
        this.report=msg;
    }

    public ExitException(String msg, Throwable cause) {
        super(msg,cause);
    }

    public int getState() { return this.state; }
    public String getReport() { return this.report; }
}
