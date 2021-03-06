package cdc;

import com.mongodb.BasicDBObject;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Vector;

public interface DBHelper {
    public static final int STATE_SUBMITTING = 8;
    public static final int STATE_PARSING = 68;
    public static final int STATE_COMPARING = 88;
    public static final int STATE_GENERATING_RESULT_TO_FILES = 100;

    public int getStructLengthByRunid(int runid);
    public Structure getStructByRunid(int runid);

    /**
     *
     */
    public void removeTaskID();
    /**
     *
     */
    public  void setTaskID();
    /**
     * 将进度写入mongoDB
     *
     */
    public void setProgressToDB(int cid,int pid,String labelAndName,int state_progress,int state,String task_id,long time);
    /**
     * 获取当前题目的Label 和名称
     */
    public String getLabelAndName();
    /**
     * 为查重后的contest设置字段hasCDC
     */
    public void setHasCDC(int cid,boolean isCDC);

    /**
     * 获取当前提交的pid
     */
    public int getPid();

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
