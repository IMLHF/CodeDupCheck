package cdc.personalTool;

public class Token extends cdc.Token {
    private int line, column, length;
    public Token(int type, String runidOrFileName, int line, int column, int length) {
        super(type, runidOrFileName, line, column, length);
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
    public int getType() {
        return this.type;
    }

    @Override
    public String getRunidOrFileName() {
        return this.runidOrFileName;
    }
}
