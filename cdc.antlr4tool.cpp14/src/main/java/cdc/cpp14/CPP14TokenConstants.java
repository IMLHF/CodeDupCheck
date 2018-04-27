package cdc.cpp14;

public interface CPP14TokenConstants extends cdc.TokenConstants {

    final static int CPP_NAMESPACE_DEFINITION = 2;
    final static int CPP_USING_DIRECTIVE = 3;

    final static int CPP_CLASS_SPECIFIER_BEGIN = 4;
    final static int CPP_CLASS_SPECIFIER_END = 5;
    final static int CPP_FUNCTION_DEFINITION_BEGIN = 6;
    final static int CPP_FUNCTION_DEFINITION_END = 7;
    //cpp14EXatt
    final static int CPP_ATTRIBUTE_DECLARATION = 8;

    // 修改CPP14.g4
    final static int CPP_DO_WHILE_BEGIN = 9;
    final static int CPP_DO_WHILE_END = 10;
    final static int CPP_WHILE_BEGIN = 11;
    final static int CPP_WHILE_END = 12;
    final static int CPP_FOR_BEGIN = 13;
    final static int CPP_FOR_END = 14;
    final static int CPP_FOREACH_BEGIN = 15;
    final static int CPP_FOREACH_END = 16;

    // 修改CPP14.g4
    final static int CPP_SWITCH_BEGIN = 17;
    final static int CPP_SWITCH_END = 18;
    final static int CPP_IF_BEGIN = 19;
    final static int CPP_IF_END = 20;
    final static int CPP_IFELSE_BEGIN = 21;
    final static int CPP_IFELSE_END = 22;

    // 修改CPP14.g4
    final static int CPP_TRY_BEGIN = 23;
    final static int CPP_TRY_END = 24;
    final static int CPP_CATCH_BEGIN = 25;
    final static int CPP_CATCH_END = 26;
    final static int CPP_BREAK = 27;
    final static int CPP_CONTINUE = 28;
    final static int CPP_RETURN = 29;
    final static int CPP_GOTO = 30;

    final static int CPP_THROW = 31;


    final static int CPP_ASSIGNMENT = 32;// not use
    final static int CPP_ASSIGN_OP = 33;

    //cpp14EXinit
    final static int CPP_INITIALIZER_BEGIN = 34;
    final static int CPP_INITIALIZER_END = 35;

    final static int CPP_ENUM_SPECIFIER_BEGIN = 36;
    final static int CPP_ENUM_SPECIFIER_END = 37;


    final static int CPP_BLOCK_BEGIN = 38;
    final static int CPP_BLOCK_END = 39; //not use
    final static int CPP_SCOPE = 40;

    final static int CPP_CONDITION_BEGIN=41;//not use
    final static int CPP_CONDITION_END=42;//括号 not use

    final static int CPP_PTR_OP=43;
    final static int CPP_OPERATOR_FUN=44;
    final static int CPP_UNARY_OP=45;

    final static int NUM_DIFF_TOKENS = 46;


}
