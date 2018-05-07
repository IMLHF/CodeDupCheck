package cdc;

import org.bson.Document;

import java.util.Vector;

public interface DBHelper {
    public void writeDocument(Document doc);
    public boolean isHaveProblemNotCheck();//从数据库读入时，判断是否还有题目未进行查重
    public void prepareData(); //避免重复查询，提前将整个比赛的数据读入内存
    public Vector<SubmissionBase> getSubmissionListAndRemove();  //获取整个提交列表
}
