package cdc.personalTool;

import cdc.DBHelper;
import cdc.ProgramI;
import cdc.Structure;
import cdc.SubmissionBase;
import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.util.JSON;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.logging.Filter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MongoDBHelper implements DBHelper {
    private ProgramI program;
    private List<Document> problemList;
    private static String DBNAME = "testlhf";
    private static String HOST = "localhost";
    private static int PORT = 27017;

    public MongoDBHelper(ProgramI p) {
        program = p;
        Logger log = Logger.getLogger("org.mongodb.driver");
        log.setLevel(Level.OFF);
    }

    @Override
    public ArrayList<Document> getTokensByRunid(int runid) {
        MongoClient mongoClient = new MongoClient(HOST, PORT);
        MongoDatabase mongoDB = (mongoClient).getDatabase(DBNAME);
        MongoCollection<Document> dbCollection_SSTATUS = mongoDB.getCollection("SDUTOJ_STATUS");
        ArrayList<Document>tokens=null;
        Iterator<Document>iter= dbCollection_SSTATUS.find(
                Filters.eq("runid",runid)).projection(
                        new BasicDBObject("tokens",true)).iterator();
        if(iter.hasNext()){
            tokens=(ArrayList<Document>)iter.next().get("tokens");
        }
        mongoClient.close();
        return tokens;
    }

    @Override
    public void wirteParseAnsToMongo(int runid,Structure struc) {
        ArrayList<BasicDBObject> tokens=new ArrayList<>();
        for(int i=0;i<struc.tokenLength();++i){
            BasicDBObject tmpObj=new BasicDBObject();
            tmpObj.put("type",struc.tokens[i].getType());
            tmpObj.put("line",struc.tokens[i].getLine());
            tmpObj.put("column",struc.tokens[i].getColumn());
            tmpObj.put("length",struc.tokens[i].getLength());
            tmpObj.put("runidOrFileName",struc.tokens[i].getRunidOrFileName());
            tokens.add(tmpObj);
        }
//        BasicDBObject parseAns=new BasicDBObject("tokens",tokens);
//        BasicDBObject setParseAns=new BasicDBObject("$setOnInsert",parseAns);

        MongoClient mongoClient = new MongoClient(HOST, PORT);
        MongoDatabase mongoDB = (mongoClient).getDatabase(DBNAME);
        MongoCollection<Document> dbCollection_SSTATUS = mongoDB.getCollection("SDUTOJ_STATUS");

        if(program.isReParse())
            dbCollection_SSTATUS.updateOne(Filters.eq("runid",runid),
                    Updates.set("tokens",tokens));
        else
            dbCollection_SSTATUS.updateOne(Filters.and(Filters.eq("runid",runid),Filters.exists("tokens",false)),
                Updates.set("tokens",tokens));
        mongoClient.close();
    }

    @Override
    public String getCodeByRunid(int runid) throws cdc.exceptions.ExitException{
        MongoClient mongoClient = new MongoClient(HOST, PORT);
        MongoDatabase mongoDB = (mongoClient).getDatabase(DBNAME);
        MongoCollection<Document> dbCollection_SSGSTANS = mongoDB.getCollection("SDUTOJ_STATUS");
        BasicDBObject filterRunid = new BasicDBObject("runid", runid);
        Iterator<Document>iter=dbCollection_SSGSTANS.find(filterRunid).iterator();
        String code;
        if(iter.hasNext()){
            code=iter.next().getString("code");
        }
        else
            throw new cdc.exceptions.ExitException("Error in MongoDBHelper.java::getCodeByRunid()::"
                    +"runid "+runid+" not found!\n");
        mongoClient.close();
        return code;
    }


    @Override
    public boolean isHaveProblemNotCheck() {
        return (problemList.size() > 0);
    }

    @Override
    public void prepareData() throws cdc.exceptions.ExitException{
        try {
            MongoClient mongoClient = new MongoClient(HOST, PORT);
            MongoDatabase mongoDB = (mongoClient).getDatabase(DBNAME);

            MongoCollection<Document> dbCollection_SDUTOJ = mongoDB.getCollection("SDUTOJ_CONTEST");
            Document contestDoc;

            /**
             * 指定查找返回的键
             */
            Iterator<Document> iter= dbCollection_SDUTOJ.find(
                    Filters.eq("cid",
                            this.program.getCid())).projection(
                                    new BasicDBObject("problem.comparisonPairs",0)).iterator();
            if(!iter.hasNext())
                throw new cdc.exceptions.ExitException("Error in MongoDBHelper.java::prepareData()::contest_"
                        +this.program.getCid()+" not found!\n");
            else
                contestDoc=iter.next();
//            System.out.println(contestDoc.size());
//
//            System.out.println(contestDoc.get("cid").toString());
            problemList = (List<Document>) contestDoc.get("problem");
            mongoClient.close();

        } catch (MongoException e) {
            e.printStackTrace();
            throw new cdc.exceptions.ExitException("Error in MongoDBHelper.java::prepareData()\n");
        }
    }

    @Override
    public Vector<SubmissionBase> getSubmissionListAndRemove() {
        Document problemDoc = (Document) problemList.get(0);
        problemList.remove(0);
        if (problemDoc.containsKey("all_submit")) {
            ArrayList<Document> mongoSubmitList = (ArrayList<Document>) problemDoc.get("all_submit");
            Iterator<Document> iter = mongoSubmitList.iterator();
            Vector<SubmissionBase> submissions = new Vector<SubmissionBase>();
            while (iter.hasNext()) {
                Document tmp = iter.next();
                int result = Integer.parseInt(tmp.get("result").toString());
                if(result!= SubmissionBase.ACCEPTED)//只添加结果为ac的submission
                    continue;
                int cid = Integer.parseInt(tmp.get("cid").toString());
                int pid = Integer.parseInt(tmp.get("pid").toString());
                int runid = Integer.parseInt(tmp.get("runid").toString());
                String name = tmp.get("user_name").toString()+"_Runid_"+runid;
                //String code=tmp.get ("code").toString();
                //code不再存在contest的信息文档中
                String languageType = tmp.get("language").toString();
                submissions.addElement(new SubmissionBase(cid, pid, runid, name, result, languageType));
            }
            return submissions;
        }
        return new Vector<SubmissionBase>();
    }

    /**
     * 将查重结果的重复配对写入mongo
     * @param doc {cid:?, pid:?, comparisonPairs:? }
     */

    @Override
    public void writeDocument(Document doc) throws cdc.exceptions.ExitException{
        long msec = System.currentTimeMillis();//开始时间
        int cid = Integer.parseInt(doc.get("cid").toString());
        int pid = Integer.parseInt(doc.get("pid").toString());
        try {
            MongoClient mongoClient = new MongoClient(HOST, PORT);
            MongoDatabase mongoDB = (mongoClient).getDatabase(DBNAME);
            MongoCollection<Document> dbCollection_SCONTEST = mongoDB.getCollection("SDUTOJ_CONTEST");

            /**
             * 用来往集合中匹配cid和problem.$.pid的文档中的‘problem’列表中指定位置中添加一个键值对
             * 第三个参数为true，当cid找到时更新，找不到时插入新的文档
             * MongoCollection的对象的函数大多可以这样使用，
             * 如果要更改嵌套在内部的文档，可以使用.进行定位，
             * 例如修改集合SDUTOJ中key为'cid'的文档中的key为'pid'的文档中的key为'title'的内容为2333333：
             * SDUTOJ.update({'cid':cid}, {'$set':{'pid.title':'2333333'},true)
             * 详细见博客MongoDB操作：
             */
            BasicDBObject filters = new BasicDBObject("cid", cid);
            filters.put("problem.pid", pid);//也可以使用Filters.and()

            if(program.isReCDC())
                dbCollection_SCONTEST.updateOne(filters,
                        Updates.set("problem.$.comparisonPairs", doc.get("comparisonPairs")));
            else{
                dbCollection_SCONTEST.updateOne(filters,
                        Updates.addEachToSet("problem.$.comparisonPairs", (List) doc.get("comparisonPairs")));
            }
//            BasicDBObject update=new BasicDBObject("$push",new BasicDBObject("problem.$.num",
//                    new BasicDBObject("$each",doc.get("comparisonPairs"))));
//            dbCollection_SSGSTANS.updateOne(filterCid,
//                    update,new UpdateOptions().upsert(true));
            /**
             * 运行原生mongo语句
             */
//            DBObject filterCid=(DBObject) JSON.parse("{'cid':"+cid+"," +
//                    "'problem.id':"+pid+"}");
//            DBObject update=(DBObject)JSON.parse("{'$push':{'problem.$.num':[555,666,777]}}");
//            DB db = new MongoClient().getDB("testlhf");
//            DBCollection coll = db.getCollection("test");
//            coll.update(filterCid,update,true,false);
            mongoClient.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new cdc.exceptions.ExitException("Error in MongoDBHelper.java::writeDocument()\n");
        }finally {
            long time = System.currentTimeMillis() - msec;
            System.out.println("\n\n\nTotal time for write CDC_ANS to Mongo: " + ((time / 3600000 > 0) ? (time / 3600000) + " h " : "")
                    + ((time / 60000 > 0) ? ((time / 60000) % 60000) + " min " : "") + (time / 1000 % 60) + " sec\n");
        }
    }

    public static void main(String[] args) throws Exception {
        Document tmp = new Document();
        tmp.append("cid", 233);
        tmp.append("pid", 2);
        ArrayList<Integer> temp = new ArrayList<Integer>();
        temp.add(123);
        temp.add(456);
        tmp.append("comparisonPairs", temp);
        MongoDBHelper mongoDBHelper = new MongoDBHelper(null);
        //mongoDBHelper.writeDocument(tmp);
        //mongoDBHelper.removeCDCANS(2015);
        System.out.println(mongoDBHelper.getCodeByRunid(2305636));
    }
}
