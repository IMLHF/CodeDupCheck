package cdc;

import java.io.File;

public interface Language {
    public String[] suffixes();//该语言源文件后缀名
    public String[] languageType();//从数据库读入时，过滤语言类型

    public String name();//语言分析插件名称
    public String getShortName();

    public int min_token_match();//最小配对Token

    public Structure parse(String runidOrFileName ,String code);

    public boolean errors();
    public int errorsCount();

    public boolean supportsColumns();

    public boolean isPreformated();

    public boolean usesIndex();

    public int numOfTokens();

    public String type2string(int type);


}
