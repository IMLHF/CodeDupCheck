package cdc.cpp14;

public class CPP14Token extends cdc.Token implements CPP14TokenConstants{
    private int line, column, length;

    public CPP14Token(int type, String fileOrName, int line, int column, int length) {
        super(type, fileOrName, line, column, length);
    }

    @Override
    public int getLine() {
        return line;
    }

    @Override
    public int getColumn() {
        return column;
    }

    @Override
    public int getLength() {
        return length;
    }

    @Override
    public void setLine(int line) {
        this.line = line;
    }

    @Override
    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public void setLength(int length) {
        this.length = length;
    }

    public static String type2string(int type) {
        switch (type) {
            case CPP14TokenConstants.FILE_END:            // FILE_END
                return "********";
            case CPP14TokenConstants.SEPARATOR_TOKEN:
                return "METHOD_SEPARATOR";
            case CPP_NAMESPACE_DEFINITION:
                return "NAMESPACE ";
            case CPP_USING_DIRECTIVE:
                return "USING     ";
            case CPP_CLASS_SPECIFIER_BEGIN:
                return "CLASS{    ";
            case CPP_CLASS_SPECIFIER_END:
                return "}CLASS    ";
            case CPP_FUNCTION_DEFINITION_BEGIN:
                return "FUNCTION{   ";
            case CPP_FUNCTION_DEFINITION_END:
                return "}FUNCTION   ";
            case CPP_ATTRIBUTE_DECLARATION:
                return "VAR_CONST ";
            case CPP_DO_WHILE_BEGIN:
                return "DO_WHILE{ ";
            case CPP_DO_WHILE_END:
                return "}DO_WHILE ";                        //
            case CPP_WHILE_BEGIN:                               // J_WHILE_BEGIN
                return "WHILE{    ";                        //
            case CPP_WHILE_END:                                 // J_WHILE_END
                return "}WHILE    ";                        //
            case CPP_FOR_BEGIN:                                 // J_FOR_BEGIN
                return "FOR{      ";                        //
            case CPP_FOR_END:                                   // J_FOR_END
                return "}FOR      ";                        //
            case CPP_FOREACH_BEGIN:                             // ***added***
                return "FOREACH{  ";                        //
            case CPP_FOREACH_END:                               // ***added***
                return "}FOREACH  ";                        //
            case CPP_SWITCH_BEGIN:                              // J_SWITCH_BEGIN
                return "SWITCH{   ";                        //
            case CPP_SWITCH_END:                                // J_SWITCH_END
                return "}SWITCH   ";                        //
            case CPP_TRY_BEGIN:                                 // J_TRY_BEGIN
                return "TRY{      ";                        //
            case CPP_TRY_END:                                   // ***added***
                return "}TRY      ";                        //
            case CPP_CATCH_BEGIN:                               // J_CATCH_BEGIN
                return "CATCH{    ";                        //
            case CPP_CATCH_END:                                 // J_CATCH_END
                return "}CATCH    ";                        //
            case CPP_IF_BEGIN:                                  // J_IF_BEGIN
                return "IF{       ";                        //
            case CPP_IF_END:                                    // J_IF_END
                return "}IF       ";                        //
            case CPP_IFELSE_BEGIN:                                // J_ELSE
                return "ELSE{     ";                        //
            case CPP_IFELSE_END:                                  // ***added***
                return "}ELSE     ";                        //
            case CPP_BREAK:                                     // J_BREAK
                return "BREAK     ";                        //
            case CPP_CONTINUE:                                  // J_CONTINUE
                return "CONTINUE  ";                        //
            case CPP_RETURN:                                    // J_RETURN
                return "RETURN    ";                        //
            case CPP_THROW:                                     // J_THROW
                return "THROW     ";                        //
            case CPP_ASSIGNMENT:                                // J_ASSIGN
                return "ASSIGNMENT";
            case CPP_INITIALIZER_BEGIN:                   // J_ARRAY_INIT_BEGIN
                return "ARRAY_INI{";                        //
            case CPP_INITIALIZER_END:                     // J_ARRAY_INIT_END
                return "}ARRAY_INI";                        //
            case CPP_ENUM_SPECIFIER_BEGIN:                     // J_ENUM_BEGIN
                return "ENUM{     ";                        //
            case CPP_ENUM_SPECIFIER_END:                       // J_ENUM_END
                return "}ENUM     ";                        //
            case CPP_GOTO:
                return "GOTO      ";
            case CPP_ASSIGN_OP:
                return "ASSIGNOP  ";
            case CPP_BLOCK_BEGIN:
                return "BLOCK{    ";
            case CPP_BLOCK_END:
                return "}BLOCK    ";
            case CPP_SCOPE:
                return "SCOPE     ";
            case CPP_CONDITION_BEGIN:
                return "CONDITION_BEGIN{";
            case CPP_CONDITION_END:
                return "}CONDITION_END";
            case CPP_PTR_OP:
                return "PTR_OP";
            case CPP_OPERATOR_FUN:
                return "OPERATOR_FUN";
            case CPP_UNARY_OP:
                return "UNARY_OP";




            default:
                System.err.println("*UNKNOWN: " + type);
                return "*UNKNOWN" + type;
        }
    }

    public static int numberOfTokens() {
        return NUM_DIFF_TOKENS;
    }
}
