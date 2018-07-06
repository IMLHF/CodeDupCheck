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
    private static String DBNAME = "ojans";
    private static String HOST = "localhost";
    private static int PORT = 27017;
    private Document currentProblemDoc;
    private int problem_num;


    public MongoDBHelper(ProgramI p) {
        program = p;
        Logger log = Logger.getLogger("org.mongodb.driver");
        log.setLevel(Level.OFF);
    }

    @Override
    public int getStructLengthByRunid(int runid) {
        return getTokensByRunid(runid).size();
    }

    @Override
    public Structure getStructByRunid(int runid) {
        ArrayList<Document> tokens=getTokensByRunid(runid);
        Iterator<Document>iter=tokens.iterator();
        Structure struct=new Structure();
        while (iter.hasNext()){
            Document tmpdoc = iter.next();
            int type=Integer.parseInt(tmpdoc.get("type").toString());
            String runidOrFileName=tmpdoc.get("runidOrFileName").toString();
            int line=Integer.parseInt(tmpdoc.get("line").toString());
            int column=Integer.parseInt(tmpdoc.get("column").toString());
            int length=Integer.parseInt(tmpdoc.get("length").toString());
            struct.addToken(new Token(type,runidOrFileName,line,column,length));
        }
        return struct;
    }

    @Override
    public void removeTaskID() {
        MongoClient mongoClient = new MongoClient(HOST, PORT);
        MongoDatabase mongoDB = (mongoClient).getDatabase(DBNAME);
        MongoCollection<Document> colSCONTEST = mongoDB.getCollection("SDUTOJ_CONTEST");
        colSCONTEST.updateOne(Filters.eq("cid", program.getCid()),
                Updates.unset("task_id"));
        mongoClient.close();
    }

    @Override
    public void setTaskID() {
        MongoClient mongoClient = new MongoClient(HOST, PORT);
        MongoDatabase mongoDB = (mongoClient).getDatabase(DBNAME);
        MongoCollection<Document> colSCONTEST = mongoDB.getCollection("SDUTOJ_CONTEST");
        colSCONTEST.updateOne(Filters.eq("cid", program.getCid()),
                Updates.set("task_id", program.getTaskID()));
        mongoClient.close();
    }

    @Override
    public void setProgressToDB(int cid, int pid, String labelAndName, int state_progress, int state, String task_id, long time) {
        BasicDBObject progress = new BasicDBObject();
        int problem_progress;
        if (state == STATE_SUBMITTING) {
            progress.put("state", "submitting_code");
            problem_progress = STATE_SUBMITTING * state_progress / 100;
        } else if (state == STATE_PARSING) {
            progress.put("state", "parsing_code");
            problem_progress = STATE_SUBMITTING + (STATE_PARSING - STATE_SUBMITTING) * state_progress / 100;
        } else if (state == STATE_COMPARING) {
            progress.put("state", "comparing_code");
            problem_progress = STATE_PARSING + (STATE_COMPARING - STATE_PARSING) * state_progress / 100;
        } else {
            progress.put("state", "generating_answer");
            problem_progress = STATE_COMPARING + (STATE_GENERATING_RESULT_TO_FILES - STATE_COMPARING) * state_progress / 100;
        }
        progress.put("state_progress", state_progress);


        progress.put("pid", pid);
        progress.put("problemLabelAndName", labelAndName);
        progress.put("problem_progress", problem_progress);

        progress.put("cid", cid);
        int leftProblemNum = problemList.size();
        int nowProblemNo = problem_num - leftProblemNum;
        int contest_progress = ((nowProblemNo == 0 ? nowProblemNo : nowProblemNo - 1) * 100
                + problem_progress) / problem_num;
        progress.put("contest_progress",
                contest_progress);
        progress.put("time", time);

//        progress.put("task_id", task_id);

//        System.out.println("\nstate: " + state);
//        System.out.println("state_progress: " + state_progress);
//        System.out.println("problem_progress: " + problem_progress);
//        System.out.println("contest_progress: " + contest_progress);

        MongoClient mongoClient = new MongoClient(HOST, PORT);
        MongoDatabase mongoDB = (mongoClient).getDatabase(DBNAME);
        MongoCollection<Document> colSCONTEST = mongoDB.getCollection("SDUTOJ_CDC_STATUS");
        colSCONTEST.updateOne(Filters.eq("_id", task_id),
                new BasicDBObject("$set", progress), new UpdateOptions().upsert(true));
        mongoClient.close();


    }

    @Override
    public String getLabelAndName() {
        return currentProblemDoc.getString("label") + " "
                + currentProblemDoc.getString("title");
    }

    @Override
    public int getPid() {
        return Integer.parseInt(currentProblemDoc.get("pid").toString());
    }


    @Override
    public void setHasCDC(int cid, boolean isCDC) {
        /**
         * 在SDUTOJ_CONTEST集合文档中添加hasCDC字段，表示已经查重过该比赛
         */
        MongoClient mongoClient = new MongoClient(HOST, PORT);
        MongoDatabase mongoDB = (mongoClient).getDatabase(DBNAME);
        MongoCollection<Document> colSCONTEST = mongoDB.getCollection("SDUTOJ_CONTEST");
        colSCONTEST.updateOne(Filters.eq("cid", cid),
                Updates.set("hasCDC", isCDC));
        mongoClient.close();
    }

    @Override
    public ArrayList<Document> getTokensByRunid(int runid) {
        MongoClient mongoClient = new MongoClient(HOST, PORT);
        MongoDatabase mongoDB = (mongoClient).getDatabase(DBNAME);
        MongoCollection<Document> dbCollection_SSTATUS = mongoDB.getCollection("SDUTOJ_STATUS");
        ArrayList<Document> tokens = null;
        Iterator<Document> iter = dbCollection_SSTATUS.find(
                Filters.eq("runid", runid)).projection(
                new BasicDBObject("tokens", true)).iterator();
        if (iter.hasNext()) {
            tokens = (ArrayList<Document>) iter.next().get("tokens");
        }
        mongoClient.close();
        return tokens;
    }

    @Override
    public void wirteParseAnsToMongo(int runid, Structure struc) {
        ArrayList<BasicDBObject> tokens = new ArrayList<>();
        for (int i = 0; i < struc.tokenLength(); ++i) {
            BasicDBObject tmpObj = new BasicDBObject();
            tmpObj.put("type", struc.tokens[i].getType());
            tmpObj.put("line", struc.tokens[i].getLine());
            tmpObj.put("column", struc.tokens[i].getColumn());
            tmpObj.put("length", struc.tokens[i].getLength());
            tmpObj.put("runidOrFileName", struc.tokens[i].getRunidOrFileName());
            tokens.add(tmpObj);
        }
//        BasicDBObject parseAns=new BasicDBObject("tokens",tokens);
//        BasicDBObject setParseAns=new BasicDBObject("$setOnInsert",parseAns);

        MongoClient mongoClient = new MongoClient(HOST, PORT);
        MongoDatabase mongoDB = (mongoClient).getDatabase(DBNAME);
        MongoCollection<Document> dbCollection_SSTATUS = mongoDB.getCollection("SDUTOJ_STATUS");

        if (program.isReParse())
            dbCollection_SSTATUS.updateOne(Filters.eq("runid", runid),
                    Updates.set("tokens", tokens));
        else
            dbCollection_SSTATUS.updateOne(Filters.and(Filters.eq("runid", runid), Filters.exists("tokens", false)),
                    Updates.set("tokens", tokens));
        mongoClient.close();
    }

    @Override
    public String getCodeByRunid(int runid) throws cdc.exceptions.ExitException {
        MongoClient mongoClient = new MongoClient(HOST, PORT);
        MongoDatabase mongoDB = (mongoClient).getDatabase(DBNAME);
        MongoCollection<Document> dbCollection_SSGSTANS = mongoDB.getCollection("SDUTOJ_STATUS");
        BasicDBObject filterRunid = new BasicDBObject("runid", runid);
        Iterator<Document> iter = dbCollection_SSGSTANS.find(filterRunid).iterator();
        String code;
        if (iter.hasNext()) {
            code = iter.next().getString("code");
        } else
            throw new cdc.exceptions.ExitException("Error in MongoDBHelper.java::getCodeByRunid()::"
                    + "runid " + runid + " not found!\n");
        mongoClient.close();
        return code;
    }


    @Override
    public boolean isHaveProblemNotCheck() {
        return (problemList.size() > 0);
    }

    @Override
    public void prepareData() throws cdc.exceptions.ExitException {
        try {
            MongoClient mongoClient = new MongoClient(HOST, PORT);
            MongoDatabase mongoDB = (mongoClient).getDatabase(DBNAME);

            MongoCollection<Document> dbCollection_SDUTOJ = mongoDB.getCollection("SDUTOJ_CONTEST");
            Document currentontestDoc;
            /**
             * 指定查找返回的键
             */
            Iterator<Document> iter = dbCollection_SDUTOJ.find(
                    Filters.eq("cid", this.program.getCid())).projection(
                    new BasicDBObject(
                    ).append("cid", 1
                    ).append("name", 1
                    ).append("problem.all_submit", 1
                    ).append("problem.title", 1
                    ).append("problem.cid", 1
                    ).append("problem.pid", 1
                    ).append("problem.label", 1)).iterator();
//            Iterator<Document> iter = dbCollection_SDUTOJ.find(
//                    Filters.eq("cid", this.program.getCid())).projection(
//                    new BasicDBObject(
//                    ).append("problem.comparisonPairs", 0
//                    ).append("ranklist", 0)).iterator();
            if (!iter.hasNext())
                throw new cdc.exceptions.ExitException("Error in MongoDBHelper.java::prepareData()::contest_"
                        + this.program.getCid() + " not found!\n");
            else
                currentontestDoc = iter.next();
//            System.out.println(contestDoc.size());
//
//            System.out.println(contestDoc.get("cid").toString());
//            System.out.println(contestDoc.keySet());
            problemList = (List<Document>) currentontestDoc.get("problem");
//            for(int j=0;j<5;++j){//跳过题目
//                problemList.remove(0);
//            }
            problem_num = problemList.size();
//            System.out.println(problemList.get(0).keySet());
            mongoClient.close();

        } catch (MongoException e) {
            e.printStackTrace();
            throw new cdc.exceptions.ExitException("Error in MongoDBHelper.java::prepareData()\n");
        }
    }

    @Override
    public Vector<SubmissionBase> getSubmissionListAndRemove() {
        currentProblemDoc = (Document) problemList.get(0);
        problemList.remove(0);
        if (currentProblemDoc.containsKey("all_submit")) {
            ArrayList<Document> mongoSubmitList = (ArrayList<Document>) currentProblemDoc.get("all_submit");
            Iterator<Document> iter = mongoSubmitList.iterator();
            Vector<SubmissionBase> submissions = new Vector<SubmissionBase>();
            while (iter.hasNext()) {
                Document tmp = iter.next();
                int result = Integer.parseInt(tmp.get("result").toString());
                if (result != SubmissionBase.ACCEPTED)//只添加结果为ac的submission
                    continue;
                int cid = Integer.parseInt(tmp.get("cid").toString());
                int pid = Integer.parseInt(tmp.get("pid").toString());
                int runid = Integer.parseInt(tmp.get("runid").toString());
                int uid = Integer.parseInt(tmp.get("uid").toString());
                String name;
                if (program.isQuiet()) {
                    name = tmp.getString("user_name");
                } else {
                    name = tmp.getString("user_name") + "_Runid_" + runid;
                }
                //String code=tmp.get ("code").toString();
                //code不再存在contest的信息文档中
                String languageType = tmp.get("language").toString();
                submissions.addElement(new SubmissionBase(cid, pid, runid, uid, name, result, languageType));
            }
            return submissions;
        }
        return new Vector<SubmissionBase>();
    }

    /**
     * 将查重结果的重复配对写入mongo
     *
     * @param doc {cid:?, pid:?, comparisonPairs:? }
     */

    @Override
    public void writeDocument(Document doc) throws cdc.exceptions.ExitException {
        long msec = System.currentTimeMillis();//开始时间
        try {
            MongoClient mongoClient = new MongoClient(HOST, PORT);
            MongoDatabase mongoDB = (mongoClient).getDatabase(DBNAME);
            MongoCollection<Document> colSCONTEST_CDC_ANS = mongoDB.getCollection(
                    "SDUTOJ_CONTEST_CDC_ANS");
            MongoCollection<Document> colSCONTEST = mongoDB.getCollection(
                    "SDUTOJ_CONTEST");
            MongoCollection<Document> colUSER = mongoDB.getCollection(
                    "SDUTOJ_USER");
            MongoCollection<Document> colCONTESTUSER = mongoDB.getCollection(
                    "SDUTOJ_CONTEST_USER");

            /**
             * 用来往集合中匹配cid和problem.$.pid的文档中的‘problem’列表中指定位置中添加一个键值对
             * 第三个参数为true，当cid找到时更新，找不到时插入新的文档
             * MongoCollection的对象的函数大多可以这样使用，
             * 如果要更改嵌套在内部的文档，可以使用.进行定位，
             * 例如修改集合SDUTOJ中key为'cid'的文档中的key为'pid'的文档中的key为'title'的内容为2333333：
             * SDUTOJ.update({'cid':cid}, {'$set':{'pid.title':'2333333'},true)
             * 详细见博客MongoDB操作：
             */
//            BasicDBObject filters = new BasicDBObject("cid", cid);
//            filters.put("pid", pid);//也可以使用Filters.and()
//            if (program.isReCDC())
//                colSCONTEST_CDC_ANS.updateOne(filters,
//                        Updates.set("comparisonPairs", doc.get("comparisonPairs")),
//                        new UpdateOptions().upsert(true));
//            else {
//                colSCONTEST_CDC_ANS.updateOne(filters,
//                        Updates.addEachToSet("comparisonPairs", (List) doc.get("comparisonPairs")),
//                        new UpdateOptions().upsert(true));
//            }
            /**
             * 更改CDC ANS结构
             */
            List<BasicDBObject> comparePairList = (List<BasicDBObject>) doc.get("comparisonPairs");

            int cid_ = Integer.parseInt(doc.get("cid").toString());
            int pid_ = Integer.parseInt(doc.get("pid").toString());
            BasicDBObject filters = new BasicDBObject("cid", cid_);
//            System.out.println(pid);
            filters.put("problem.pid", pid_);
            colSCONTEST.updateOne(filters, Updates.set("problem.$.dupnum", comparePairList.size()));

            Iterator<Document> tmpiter = colSCONTEST.find(new BasicDBObject("cid", cid_)).projection(
                    new BasicDBObject("type", 1)
            ).iterator();
            //1 => Private
            //2 => Register
            //3 => Public
            int _type = 1;
            if (tmpiter.hasNext()) {
                _type = Integer.parseInt(((Document) tmpiter.next()).get("type").toString());
            }

            Iterator<BasicDBObject> iter = comparePairList.iterator();
            while (iter.hasNext()) {
                BasicDBObject tmpdoc = iter.next();
                int cid = Integer.parseInt(tmpdoc.get("cid").toString());
                int pid = Integer.parseInt(tmpdoc.get("pid").toString());
                int runidA = Integer.parseInt(tmpdoc.get("runidA").toString());
                int runidB = Integer.parseInt(tmpdoc.get("runidB").toString());
                int uidA = Integer.parseInt(tmpdoc.get("uidA").toString());
                int uidB = Integer.parseInt(tmpdoc.get("uidB").toString());
                String usernameA,usernameB,nicknameA,nicknameB;
                if (_type != 2) {
//                    System.out.println(uidA+" "+cid+" "+pid+" "+runidA);
                    Iterator<Document> useriter=colUSER.find(new BasicDBObject("uid", uidA)).iterator();
                    Document userdict;
                    if(useriter.hasNext()){
                        userdict=useriter.next();
                    }else{
                        userdict=colUSER.find(new BasicDBObject("uid", 1)).iterator().next();
                    }
                    usernameA = userdict.get("user_name").toString();
                    nicknameA = userdict.get("nick_name").toString();

                    useriter=colUSER.find(new BasicDBObject("uid", uidB)).iterator();
                    if(useriter.hasNext()){
                        userdict=useriter.next();
                    }else{
                        userdict=colUSER.find(new BasicDBObject("uid", 10)).iterator().next();
                    }
                    usernameB = userdict.get("nick_name").toString();
                    nicknameB = userdict.get("nick_name").toString();
                }else{
//                    System.out.println(uidA+" "+runidA);
                    Iterator<Document> useriter=colCONTESTUSER.find(new BasicDBObject("cuid", uidA)).iterator();
                    Document userdict;
                    if(useriter.hasNext()){
                        userdict=useriter.next();
                    }else{
                        userdict=colCONTESTUSER.find(new BasicDBObject("cuid", 1)).iterator().next();
                    }
                    usernameA = userdict.get("user_name").toString();
                    nicknameA = userdict.get("nick_name").toString();

                    useriter=colCONTESTUSER.find(new BasicDBObject("cuid", uidB)).iterator();
                    if(useriter.hasNext()){
                        userdict=useriter.next();
                    }else{
                        userdict=colCONTESTUSER.find(new BasicDBObject("cuid", 10)).iterator().next();
                    }
                    usernameB = userdict.get("nick_name").toString();
                    nicknameB = userdict.get("nick_name").toString();
                }
                tmpdoc.put("usernameA",usernameA);
                tmpdoc.put("usernameB",usernameB);
                tmpdoc.put("nicknameA",nicknameA);
                tmpdoc.put("nicknameB",nicknameB);

                BasicDBObject _filters = new BasicDBObject("cid", cid);
                _filters.put("pid", pid);
                _filters.put("runidA", runidA);
                _filters.put("runidB", runidB);
//                System.out.println(_filters);
//                System.out.println(tmpdoc);
                colSCONTEST_CDC_ANS.updateOne(_filters,
                        new BasicDBObject("$set", tmpdoc),
                        new UpdateOptions().upsert(true));
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
        } finally {
            long time = System.currentTimeMillis() - msec;
            program.print("\n\n\nTotal time for write CDC_ANS to Mongo: " + ((time / 3600000 > 0) ? (time / 3600000) + " h " : "")
                    + ((time / 60000 > 0) ? ((time / 60000) % 60) + " min " : "") + (time / 1000 % 60) + " sec\n");
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
