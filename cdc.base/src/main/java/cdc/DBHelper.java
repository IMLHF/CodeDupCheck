package cdc;

import org.bson.Document;

import java.util.Vector;

public interface DBHelper {


    /**
     * 将一个submission的代码的parse结果根据对应runid写入mongo
     */
    public void wirteParseAnsToMongo(int runid,Structure struc);


    /**
     * 将查重结果写入数据库
     * @param doc {cid:?, pid:?, comparisonPairs:? }
     *
     */
    public void writeDocument(Document doc);

    /**
     * 根据runid获取代码
     */
    public String getCodeByRunid(int runid);

    /**
     * 从数据库读入时，判断是否还有题目未进行查重
     * @return
     */
    public boolean isHaveProblemNotCheck();

    /**
     * 避免重复查询，提前将整个比赛的数据读入内存
     */
    public void prepareData();

    /**
     * //获取整个提交列表
     * @return
     */
    public Vector<SubmissionBase> getSubmissionListAndRemove();
}
