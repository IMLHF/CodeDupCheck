package cdc.cpp14;

import cdc.Structure;
import cdc.ProgramI;


public class Language implements cdc.Language {

    private final Parser parser;

    public Language(ProgramI program) {
        this.parser = new cdc.cpp14.Parser();
        this.parser.setProgram(program);
    }

    @Override
    public String[] suffixes() {
        String[] res = {".cpp", ".CPP", ".cxx", ".CXX", ".c++", ".C++", ".c", ".C", ".cc", ".CC", ".h", ".H",
                ".hpp", ".HPP", ".hh", ".HH"};
        return res;
    }

    @Override
    public String[] languageType() {
        String[] res = {"c++","gcc","g++","C++","GCC","G++"};
        return res;
    }

    @Override
    public int errorsCount() {
        return this.parser.errorsCount();
    }

    @Override
    public String name() {
        return "CPP14 Parser";
    }

    @Override
    public String getShortName() {
        return "CPP14";
    }

    @Override
    public int min_token_match() {
        return 14;
    }

    @Override
    public Structure parse(String runidOrFileName, String code) {
        return this.parser.parse(runidOrFileName, code);
    }

    @Override
    public boolean errors() {
        return this.parser.getErrors();
    }

    @Override
    public boolean supportsColumns() {
        return true;
    }

    @Override
    public boolean isPreformated() {
        return true;
    }

    @Override
    public boolean usesIndex() {
        return false;
    }

    @Override
    public int numOfTokens() {
        return CPP14Token.numberOfTokens();
    }

    @Override
    public String type2string(int type) {
        return CPP14Token.type2string(type);
    }

}
