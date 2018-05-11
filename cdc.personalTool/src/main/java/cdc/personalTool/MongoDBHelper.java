package cdc.personalTool;

import cdc.DBHelper;
import cdc.ProgramI;
import cdc.SubmissionBase;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MongoDBHelper implements DBHelper {
    private ProgramI program;
    private List problemList;
    private static String DBNAME="testlhf";
    private static String HOST="localhost";
    private static int PORT=27017;
    public MongoDBHelper(ProgramI p){
        program=p;
        Logger log = Logger.getLogger("org.mongodb.driver");
        log.setLevel(Level.OFF);
    }

    public String getCodeByRunid(int runid) {
        MongoClient mongoClient = new MongoClient(HOST, PORT);
        MongoDatabase mongoDB = (mongoClient).getDatabase(DBNAME);
        MongoCollection<Document> dbCollection_SSGSTANS = mongoDB.getCollection("SDUTOJ_STATUS");
        BasicDBObject filterRunid=new BasicDBObject("runid",runid);
        String code =dbCollection_SSGSTANS.find(filterRunid).first().get("code").toString();
        mongoClient.close();
        return code;
    }

    public void removeCDCANS(int cid) {
        MongoClient mongoClient = new MongoClient(HOST, PORT);
        MongoDatabase mongoDB = (mongoClient).getDatabase(DBNAME);
        MongoCollection<Document> dbCollection_SSGSTANS = mongoDB.getCollection("SDUTOJ_CDC_ANS");
        BasicDBObject filterCid=new BasicDBObject("cid",cid);
        dbCollection_SSGSTANS.deleteOne(filterCid);
        mongoClient.close();
    }

    public boolean isHaveProblemNotCheck(){
         return (problemList.size()>0);
    }
    public void prepareData() {
        try {
            MongoClient mongoClient = new MongoClient(HOST, PORT);
            MongoDatabase mongoDB = (mongoClient).getDatabase(DBNAME);

            MongoCollection<Document> dbCollection_SDUTOJ = mongoDB.getCollection("SDUTOJ");
            Document contestDoc;
            contestDoc = dbCollection_SDUTOJ.find(
                    Filters.eq("contest_id", String.valueOf(this.program.getCid()))).first();

            //System.out.println("contest_id: " + contest_doc.get("problem"));
            problemList = ( List<Document>) contestDoc.get("problem");
            mongoClient.close();

        } catch (MongoException e) {
            e.printStackTrace();
        }
    }

    public Vector<SubmissionBase> getSubmissionListAndRemove() {
        Document problemDoc = (Document) problemList.get(0);
        problemList.remove(0);
        if(problemDoc.containsKey("all_submit_record")){
            ArrayList<Document> mongoSubmitList = (ArrayList<Document>) problemDoc.get("all_submit_record");
            Iterator<Document>iter=mongoSubmitList.iterator();
            Vector<SubmissionBase> submissions=new Vector<SubmissionBase>();
            while(iter.hasNext()){
                Document tmp=iter.next();
                int cid = Integer.parseInt(tmp.get("cid").toString());
                int pid = Integer.parseInt(tmp.get("pid").toString());
                int runid=Integer.parseInt(tmp.get("runid").toString());
                String name = tmp.get("username").toString();
                //String code=tmp.get ("code").toString();
                //code不再存在contest的信息文档中
                String result=tmp.get("result").toString();
                String languageType=tmp.get("language").toString();
                submissions.addElement(new SubmissionBase(cid,pid,runid,name,result,languageType));
            }
            return submissions;
        }
        return new Vector<SubmissionBase>();
    }

    /**
     * @param doc
     *  {cid:?, pid:?, comparisonPairs:? }
     */
    public void writeDocument(Document doc) {
        int cid= Integer.parseInt(doc.get("cid").toString());
        try{
            MongoClient mongoClient = new MongoClient(HOST, PORT);
            MongoDatabase mongoDB = (mongoClient).getDatabase(DBNAME);
            MongoCollection<Document> dbCollection_SSGSTANS = mongoDB.getCollection("SDUTOJ_CDC_ANS");

            /**
             * 下面三行代码相当于Mongo语句：SDUTOJ.update({'cid':cid}, {'$push':{'problem':doc}},true)
             * 用来往集合SDUTOJ中'cid'的值为cid的文档中的‘problem’字段的列表中添加一个元素
             * 第三个参数为true，当cid找到时更新，找不到时插入新的文档
             * MongoCollection的对象的函数大多可以这样使用，
             * 如果要更改嵌套在内部的文档，可以使用.进行定位，
             * 例如修改集合SDUTOJ中key为'cid'的文档中的key为'pid'的文档中的key为'title'的内容为2333333：
             * SDUTOJ.update({'cid':cid}, {'$set':{'pid.title':'2333333'},true)
             * 详细见博客MongoDB操作：
             */
            BasicDBObject filterCid=new BasicDBObject("cid",cid);
            BasicDBObject update = new BasicDBObject("$push",new BasicDBObject("problem",doc));
            dbCollection_SSGSTANS.updateOne(filterCid,update,new UpdateOptions().upsert(true));
            mongoClient.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        Document tmp=new Document("11",666666);
        tmp.append("cid",233);
        MongoDBHelper mongoDBHelper =new MongoDBHelper(null);
//        mongoDBHelper.writeDocument(tmp);
        mongoDBHelper.removeCDCANS(2015);
    }
}
