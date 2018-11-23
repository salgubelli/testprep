package ee.testprep;

public class DBRow {
    //Column names
    public static final String KEY_EXAM = "examName";
    public static final String KEY_YEAR = "year";
    public static final String KEY_QNO = "qno";
    public static final String KEY_QUESTION = "question";
    public static final String KEY_OPTA = "optionA";
    public static final String KEY_OPTB = "optionB";
    public static final String KEY_OPTC = "optionC";
    public static final String KEY_OPTD = "optionD";
    public static final String KEY_ANSWER = "answer";
    public static final String KEY_IPC = "ipc";
    public static final String KEY_SUBJECT = "subject";
    public static final String KEY_USER_STATUS = "userstatus";

    public String exam;
    public String year;
    public Integer qNo;
    public String question;
    public String optionA;
    public String optionB;
    public String optionC;
    public String optionD;
    public String answer;
    public String ipc;
    public String subject;
    //0 not read, 1 completed, -1 to_review_later
    public Integer userStatus = 0;

    @Override
    public String toString() {
        return exam + " " +  year + " " + ipc + " " + subject + " " + userStatus;
    }
}
