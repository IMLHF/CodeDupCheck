package cdc;

import com.mongodb.BasicDBObject;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Vector;

public interface DBHelper {


    /**
     * 通过runid获取代码parse结果的tokens列表
     * @param runid
     * @return
     */
    public ArrayList<Document> getTokensByRunid(int runid);


    /**
     * 将一个submission的代码的parse结果根据对应runid写入mongo
     */
    public void wirteParseAnsToMongo(int runid,Structure struc);


    /**
     * 将查重结果写入数据库
     * @param doc {cid:?, pid:?, comparisonPairs:? }
     *
     */
    public void writeDocument(Document doc) throws cdc.exceptions.ExitException;

    /**
     * 根据runid获取代码
     */
    public String getCodeByRunid(int runid) throws cdc.exceptions.ExitException;

    /**
     * 从数据库读入时，判断是否还有题目未进行查重
     * @return
     */
    public boolean isHaveProblemNotCheck();

    /**
     * 避免重复查询，提前将整个比赛的数据读入内存
     */
    public void prepareData() throws cdc.exceptions.ExitException;

    /**
     * //获取整个提交列表
     * @return
     */
    public Vector<SubmissionBase> getSubmissionListAndRemove();
}
