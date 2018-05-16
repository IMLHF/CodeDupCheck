package cdc;

public class Structure implements TokenConstants {
    public Token[] tokens;
    Table table = null;
    int hash_length = -1;

    int fileEndToken_num;
    private int tokenNum; //size

    public Structure() {
        tokens = new Token[400];
        fileEndToken_num = tokenNum = 0;
    }

    public final int tokenLength() {
        return tokenNum;
    }

    public final void ensureCapacity(int minCapacity) {
        int oldCapacity = tokens.length;
        if (minCapacity > oldCapacity) {
            Token[] oldTokens = tokens;
            int newCapacity = oldCapacity * 2;
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            tokens = new Token[newCapacity];
            System.arraycopy(oldTokens, 0, tokens, 0, tokenNum);
        }
    }

    public final void addToken(Token token) {
        ensureCapacity(tokenNum + 1);
        if (tokenNum > 0 && tokens[tokenNum - 1].runidOrFileName.equals(token.runidOrFileName))
            token.runidOrFileName = tokens[tokenNum - 1].runidOrFileName; //节省内存
        if ((tokenNum > 0) && (token.getLine() < tokens[tokenNum - 1].getLine()) &&
                (token.runidOrFileName.equals(tokens[tokenNum - 1].runidOrFileName)))
            token.setLine(tokens[tokenNum - 1].getLine());


        tokens[tokenNum++] = token;
        if (token.type == FILE_END)
            fileEndToken_num++;
    }

    public final String toString() {
        StringBuffer buf = new StringBuffer();
        try {
            for (int i = 0; i < tokenNum; ++i) {
                String s = tokens[i].toString();
                buf.append(i);
                buf.append("\t");
                buf.append(s);
                buf.append("\n");
            }
        } catch (OutOfMemoryError e) {
            return "Tokenlist too large for output: " + (tokenNum) + "Tokens";
        }
        return buf.toString();
    }

}
