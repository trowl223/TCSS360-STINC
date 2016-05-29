import java.util.ArrayList;

/**
 * Created by root on 5/24/16.
 */
public class Tester {
    public static void main(String[] args) {
        ArrayList<String> vals = new ArrayList<>();
        vals.add("eligible_contests");
        vals.add("2");
        DatabaseConnector myConnector = new DatabaseConnector("userContests", vals);
        myConnector.connect();
        if(myConnector.getState() == myConnector.FAILURE) {
            System.out.println("what the hell?");
        } else if(myConnector.getState() == myConnector.SUCCESS) {
            System.out.println("WOOOOOOOOOOO!!!");
            for(int i = 0; i < vals.size(); i++) {
                System.out.println(vals.get(i));
            }
        }
    }
}
