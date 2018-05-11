package cdc;

import org.bson.Document;

import java.util.Vector;

public interface DBHelper {

    /**
     * 根据比赛id，移除该比赛的查重结果（结果的写入使用增量方式，写入前先清理）
     * @param cid 比赛id
     */
    public void removeCDCANS(int cid);

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
