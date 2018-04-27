package cdc;

import java.util.Vector;

public interface DBHelper {
    public boolean isHaveProblemNotCheck();
    public void prepareData();
    public Vector<SubmissionBase> getSubmissionList();
}
