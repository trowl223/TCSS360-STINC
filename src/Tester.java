import java.util.ArrayList;

/**
 * Created by root on 5/24/16.
 */
public class Tester {
    public static void main(String[] args) {
        ArrayList<String> vals = new ArrayList<>();
        vals.add("difdkdiidji");
        vals.add("good stuff");
        vals.add("great");
        vals.add("11-19-15");
        vals.add("1");
        vals.add("1");
        DatabaseConnector myConnector = new DatabaseConnector("createEntry", vals);
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
