package personalTool;

import cdc.DBHelper;
import cdc.ProgramI;
import cdc.SubmissionBase;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.List;
import java.util.Vector;

public class MongoDBHeler implements DBHelper {
    ProgramI program;
    Document contestDoc;
    List problemList;
    public MongoDBHeler(ProgramI p){
        program=p;
    }
    public boolean isHaveProblemNotCheck(){
        if(problemList.size()>0)
            return true;
        return false;
    }
    public void prepareData() {
        List<Document> mongoSubmitList = null;
        try {
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            MongoDatabase mongoDB = ((MongoClient) mongoClient).getDatabase("testlhf");
            MongoCollection<Document> dbCollection_SDUTOJ = mongoDB.getCollection("SDUTOJ");

            contestDoc = dbCollection_SDUTOJ.find(
                    Filters.eq("contest_id", String.valueOf(this.program.getCid()))).first();


            //System.out.println("contest_id: " + contest_doc.get("problem"));
            problemList = ( List<Document>) contestDoc.get("problem");//TODO 查新所有时可以用array



        } catch (MongoException e) {
            e.printStackTrace();
        }
    }

    public Vector<SubmissionBase> getSubmissionListAndRemove() {
        Document problemDoc = (Document) problemList.get(0);
        problemList.remove(0);
        Vector<Document> mongoSubmitList = (Vector<Document>) problemDoc.get("all_submit_record");
        Vector<SubmissionBase> submissions=new Vector<SubmissionBase>();
        for (int i = 0; i < mongoSubmitList.size(); i++) {
            int cid = Integer.parseInt(mongoSubmitList.elementAt(i).get("cid").toString());
            int pid = Integer.parseInt(mongoSubmitList.elementAt(i).get("pid").toString());
            int runid=Integer.parseInt(mongoSubmitList.elementAt(i).get("runid").toString());
            String name = mongoSubmitList.get(i).get("username").toString()+"_Runid_"
                    +String.valueOf(runid);
            String code=mongoSubmitList.elementAt(i).get ("code").toString();
            submissions.addElement(new SubmissionBase(cid,pid,runid,name,code));
        }
        return submissions;
    }
}
