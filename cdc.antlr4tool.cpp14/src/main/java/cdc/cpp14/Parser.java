package cdc.cpp14;

import cdc.Structure;
import cdc.cpp14.grammar.CPP14Lexer;
import cdc.cpp14.grammar.CPP14Parser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;


import java.io.*;

public class Parser extends cdc.Parser implements CPP14TokenConstants {


    private Structure struct = new Structure();
    private String currentFile;


    public cdc.Structure parse(String runidOrFileName, String code) {
        struct = new Structure();
        errors = 0;
        if (!parseFile(runidOrFileName, code)) {
            errors++;
        }
        System.gc();
        struct.addToken(new CPP14Token(FILE_END, runidOrFileName, -1, -1, -1));
        return struct;
    }

    public boolean parseFile(String runidOrFileName, String code) {
        ByteArrayInputStream fis;
        CharStream input;
        try {

            fis = new ByteArrayInputStream(code.getBytes("utf-8"));
            input = CharStreams.fromStream(fis);
            currentFile = runidOrFileName;
            CPP14Lexer lexer = new CPP14Lexer(input);

            // create a buffer of tokens pulled from the lexer
            CommonTokenStream tokens = new CommonTokenStream(lexer);

            // create a parser that feeds off the tokens buffer
            CPP14Parser parser = new CPP14Parser(tokens);
            CPP14Parser.TranslationunitContext in = parser.translationunit();

            ParseTreeWalker ptw = new ParseTreeWalker();
            for (int i = 0; i < in.getChildCount(); i++) {
                ParseTree pt = in.getChild(i);
                ptw.walk(new CodeDCCPP14Listener(this), pt);
            }

        } catch (IOException e) {
            program.print("      antlr4:Parsing Error in '" + runidOrFileName + "':\n" + e.getMessage() + "\n");
            return false;
        }catch (Exception e){
            e.printStackTrace();
        }

        return true;
    }


    public void add(int type, org.antlr.v4.runtime.Token tok) {
        struct.addToken(new CPP14Token(type, (currentFile == null ? "null" : currentFile), tok.getLine(), tok.getCharPositionInLine() + 1,
                tok.getText().length()));
        //TODO debug
//        System.out.println("cpp14 parser.java ling 76  :"+ CPP14Token.type2string(type)
//                +"\nline : "+tok.getLine()
//                +"\nCharPositionInLine : "+tok.getCharPositionInLine()
//                +"\nlength : "+tok.getText().length()
//                +"\ntoken text : "+tok.getText()+"\n\n"
//        );
    }

    public void addEnd(int type, org.antlr.v4.runtime.Token tok) {
        struct.addToken(new CPP14Token(type, (currentFile == null ? "null" : currentFile), tok.getLine(), struct.tokens[struct.tokenLength() - 1].getColumn() + 1, 0));
    }
}
