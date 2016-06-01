import java.util.ArrayList;

/**
 * Created by root on 5/24/16.
 */
public class Tester {
    public static void main(String[] args) {
        ArrayList<String> entryFields = new ArrayList<>();
        entryFields.add("http://images.upload.example?folder/blahblah");//this is the url link, a string
        entryFields.add("the best entry ever");//this is the description, a string
        entryFields.add("my entry");//this is the entry name, a string
        entryFields.add("11-19-15");//this is the entry date, a string
        entryFields.add("1");//this is the user id, an integer
        entryFields.add("1");//this is the contest id, an integer
        DatabaseConnector myConnector = new DatabaseConnector("createEntry", entryFields);//create an entry
        myConnector.connect();
        ArrayList<String> loginFields = new ArrayList<>();
        loginFields.add("testUser123");//this is the username, a string
        loginFields.add("testPass456");//this is the password, a string
        DatabaseConnector myConnector = new DatabaseConnector("login", loginFields);//login and validate the user
        myConnector.connect();
        ArrayList<String> contests = new ArrayList<>();
        DatabaseConnector myConnector = new DatabaseConnector("getContests", contests);
        //get all contests, these will populate the data structure passed in. The format that is returned is in arraylist is contest name, then description
        myConnector.connect();
        ArrayList<String> contests = new ArrayList<>();
        contests.add("contests_entered");//all entered contests for user_id 2
        contests.add("2");//user_id
        DatabaseConnector myConnector = new DatabaseConnector("userContests", contests);
        //get all contests, these will populate the data structure passed in. The format that is returned is in arraylist is contest name, then description
        myConnector.connect();
         ArrayList<String> contests = new ArrayList<>();
        contests.add("eligible_contests");//all eligible contests for user_id 2
        contests.add("2");//user_id
        DatabaseConnector myConnector = new DatabaseConnector("userContests", contests);
        //get all contests, these will populate the data structure passed in. The format that is returned is in arraylist is contest name, then description
        myConnector.connect();
        ArrayList<String> removeVals = new ArrayList<>();
        removeVals.add("removeJudge");//keyword to remove a judge
        removeVals.add("1");//this is the contest id
        removeVals.add("1");//this is the judge id
        DatabaseConnector myConnector = new DatabaseConnector("updateJudges", removeVals);
        ArrayList<String> addVals = new ArrayList<>();
        addVals.add("addJudge");//keyword to remove a judge
        addVals.add("1");//this is the contest id
        addVals.add("1");//this is the judge id
        DatabaseConnector myConnector = new DatabaseConnector("updateJudges", addVals);
        /*if(myConnector.getState() == myConnector.FAILURE) {//displays the success or failure of the query attempted
            System.out.println("what the hell?");
        } else if(myConnector.getState() == myConnector.SUCCESS) {
            System.out.println("WOOOOOOOOOOO!!!");
            for(int i = 0; i < vals.size(); i++) {
                System.out.println(vals.get(i));
            }
        }*/
    }
}
