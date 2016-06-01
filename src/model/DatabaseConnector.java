package model;

/**
 * Created by root on 5/24/16.
 */
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.Key;
import java.util.ArrayList;
import java.util.Iterator;

public class DatabaseConnector {
    private static String myOperation;
    private int mState;
    public static final int IN_PROGRESS = 2; // now initiating an outgoing connection
    public static final int FAILURE = 3;//user input was incorrect
    public static final int SUCCESS = 1;//user input was correct
    public static final int BAD_CONNECTION = -1;//user input was correct
    private static final String login = "http://repos.insttech.washington.edu/~dejarc/library_login.php";
    private static final String contests = "http://repos.insttech.washington.edu/~dejarc/display_contests.php";
    private static final String createEntry = "http://repos.insttech.washington.edu/~dejarc/add_entry.php";
    private static final String getContestsEntered = "http://repos.insttech.washington.edu/~dejarc/user_contests.php";
    private static final String judgeActions = "http://repos.insttech.washington.edu/~dejarc/judge_actions.php";
    private static String URL;
    private ArrayList<String> myVals;
    HttpURLConnection conn = null;
    private OutputStreamWriter user_input;
    private InputStream is = null;
    private BufferedReader br = null;
    private JSONObject jObj = null;
    ArrayList<String> myKeys;
    URL url = null;
    private JSONArray jArr = null;
    public DatabaseConnector(String process, ArrayList<String> myVals) {
        myOperation = process;
        this.myVals = myVals;
        myKeys = new ArrayList<>();
        setState(IN_PROGRESS);
    }

    public void connect() {
        switch (myOperation) {
            case "login":
                URL = login;
                myKeys.add("username");
                myKeys.add("password");
                QueryDB(myKeys);
                break;
            case "getContests":
                URL = contests;
                FetchContests(myVals);
                break;
            case "createEntry":
                URL = createEntry;
                myKeys.add("url_link");
                myKeys.add("description");
                myKeys.add("entry_name");
                myKeys.add("date");
                myKeys.add("user_id");
                myKeys.add("contest_id");
                QueryDB(myKeys);
                break;
            case "userContests":
                URL = getContestsEntered;
                myKeys.add("query_type");
                myKeys.add("user_id");
                QueryDB(myKeys);
                break;
            case "updateJudges":
                URL = judgeActions;
                myKeys.add("query_type");
                myKeys.add("contest_id");
                myKeys.add("judge_id");
                QueryDB(myKeys);
                break;
        }

    }
    public void FetchContests(ArrayList<String> myContests) {
        try {
            url = new URL(URL);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.connect();
            int code = conn.getResponseCode();
            String msg = "Connection Code: " + code;
            is = conn.getInputStream();
            br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            try {

                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                jArr = new JSONArray(sb.toString());
                for(int i = 0; i < jArr.length(); i++) {
                    JSONObject objectInArray = jArr.getJSONObject(i);
                    String name = objectInArray.getString("name");
                    String description = objectInArray.getString("description");
                    myContests.add(name);
                    myContests.add(description);
                }
                is.close();
                setState(SUCCESS);
            } catch (Exception e) {
                setState(FAILURE);
            }
        } catch (IOException e) {
            setState(BAD_CONNECTION);
        }

    }
    public void QueryDB(ArrayList<String> myKeys) {
        String myQuery = "'";
        for (int i = 0; i < myVals.size(); i++) {
            myQuery += "&";
            try {
                myQuery += URLEncoder.encode(myKeys.get(i), "UTF-8") + "=" + URLEncoder.encode(myVals.get(i), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        try {
            url = new URL(URL);
            conn = (HttpURLConnection) url.openConnection();
            conn.setUseCaches(false);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            //conn.connect();
            user_input = new OutputStreamWriter(conn.getOutputStream());
            user_input.write(myQuery);
            user_input.flush();
            user_input.close();
            int code = conn.getResponseCode();
            String msg = "Connection Code: " + code;
            is = conn.getInputStream();
            //conn.connect();
            br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            try {
                if(URL.equals(getContestsEntered)) {
                    jArr = new JSONArray(sb.toString());
                    this.myVals.remove(1);
                    this.myVals.remove(0);
                    for(int i = 0; i < jArr.length(); i++) {
                        JSONObject temp = jArr.getJSONObject(i);
                        Iterator<?> allKeys =  temp.keys();
                        while(allKeys.hasNext()) {
                            String key = (String)allKeys.next();
                            String value = (String)temp.get(key);
                            this.myVals.add(value);
                        }

                    }
                    is.close();
                    setState(SUCCESS);
                } else {
                    jObj = new JSONObject(sb.toString());
                    if (jObj.getInt("status") == 1) {
                        setState(SUCCESS);
                    } else {
                        setState(FAILURE);
                    }
                }
            } catch (JSONException e) {
                setState(BAD_CONNECTION);
                e.printStackTrace();
            }
        } catch (IOException e) {
            setState(BAD_CONNECTION);
            e.printStackTrace();
        }
    }
    public synchronized int getState() {
        return mState;
    }
    public synchronized void setState(int myState) {
        mState = myState;
    }


}
