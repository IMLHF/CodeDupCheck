package cdc.personalTool;

import cdc.DBHelper;
import cdc.ProgramI;
import cdc.SubmissionBase;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class MongoDBHelper implements DBHelper {
    private ProgramI program;
    private Document contestDoc;
    private List problemList;
    public MongoDBHelper(ProgramI p){
        program=p;
    }
    public boolean isHaveProblemNotCheck(){
        if(problemList.size()>0)
            return true;
        return false;
    }
    public void prepareData() {
        try {
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            MongoDatabase mongoDB = (mongoClient).getDatabase("testlhf");
            MongoCollection<Document> dbCollection_SDUTOJ = mongoDB.getCollection("SDUTOJ");

            contestDoc = dbCollection_SDUTOJ.find(
                    Filters.eq("contest_id", String.valueOf(this.program.getCid()))).first();


            //System.out.println("contest_id: " + contest_doc.get("problem"));
            problemList = ( List<Document>) contestDoc.get("problem");//TODO 查新所有时可以用array
            mongoClient.close();
        } catch (MongoException e) {
            e.printStackTrace();
        }
    }

    public Vector<SubmissionBase> getSubmissionListAndRemove() {
        Document problemDoc = (Document) problemList.get(0);
        problemList.remove(0);
        ArrayList<Document> mongoSubmitList = (ArrayList<Document>) problemDoc.get("all_submit_record");
        Iterator<Document>iter=mongoSubmitList.iterator();
        Vector<SubmissionBase> submissions=new Vector<SubmissionBase>();
        while(iter.hasNext()){
            Document tmp=iter.next();
            int cid = Integer.parseInt(tmp.get("cid").toString());
            int pid = Integer.parseInt(tmp.get("pid").toString());
            int runid=Integer.parseInt(tmp.get("runid").toString());
            String name = tmp.get("username").toString()+"_Runid_"
                    +String.valueOf(runid);
            String code=tmp.get ("code").toString();
            submissions.addElement(new SubmissionBase(cid,pid,runid,name,code));
        }
        return submissions;
    }

    public void writeDocument(Document doc) {
        String cid= doc.get("cid").toString();
        try{
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            MongoDatabase mongoDB = (mongoClient).getDatabase("testlhf");
//            MongoCollection<Document> dbCollection_SSGSTANS = mongoDB.getCollection("SDUTOJ");
//            BasicDBObject filterCid=new BasicDBObject("cid",cid);
//            BasicDBObject update = new BasicDBObject("$setOnInsert",doc);
//            dbCollection_SSGSTANS.updateOne(filterCid,
//                    update);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        Document tmp=new Document("11",22);
        tmp.append("cid",233);
        MongoDBHelper mongoDBHelper =new MongoDBHelper(null);
        mongoDBHelper.writeDocument(tmp);
    }
}
