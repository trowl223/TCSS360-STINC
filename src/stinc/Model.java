package stinc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

import model.Contest;
import model.DatabaseConnector;
import model.Entry;
import model.User;

/**
 * The model is the data model.
 * @author Nicholas Mousel
 * @version 5/24/2016
 *
 */
public class Model extends Observable
{
	/**
	 * The contests
	 */
	private List<Contest> myContests;
	private User myCurrentUser;
	
	/**
	 * Constructs the Model
	 */
	public Model() {
		myContests = new ArrayList<Contest>();
		myCurrentUser = null;
	}
	
	/**
	 * Adds a contest
	 * @param theContest the Contest to add
	 * @return true if successful, false otherwise.
	 */
	public boolean addContest(Contest theContest)
	{
		ArrayList<String> entryFields = new ArrayList<>();
		entryFields.add("createContest");
		entryFields.add(theContest.getName());
		entryFields.add(theContest.getDescription());
		entryFields.add("" + theContest.getAgeLimit());
		entryFields.add(theContest.getImageURL());
		DatabaseConnector myConnector = new DatabaseConnector("addContest", entryFields);//create an entry
        myConnector.connect();
		return myConnector.getState() == DatabaseConnector.SUCCESS;
	}
	
	/**
	 * Adds a Entry.
	 * @param theEntry the Entry to add.
	 * @return true if successful, false otherwise.
	 */
	public boolean addEntry(Entry theEntry)
	{
        ArrayList<String> entryFields = new ArrayList<>();
        entryFields.add(theEntry.getSubmissionPath());//this is the url link, a string
        entryFields.add(theEntry.getDescription());//this is the description, a string
        entryFields.add(theEntry.getName());//this is the entry name, a string
        entryFields.add(theEntry.getDateString());//this is the entry date, a string
        entryFields.add(String.valueOf(theEntry.getOwner().getID()));//this is the user id, an integer
        entryFields.add(String.valueOf(theEntry.getContestID()));//this is the contest id, an integer
        DatabaseConnector myConnector = new DatabaseConnector("createEntry", entryFields);//create an entry
        myConnector.connect();
		return myConnector.getState() == DatabaseConnector.SUCCESS;
	}
	
	/**
	 * Remove an Entry.
	 * @param theEntryID the ID of the Entry to remove.
	 * @return true if successful, false otherwise.
	 */
	public boolean removeEntry(int theEntryID)
	{
		ArrayList<String> entryFields = new ArrayList<>();
        entryFields.add("removeEntry");//this is the tag
        entryFields.add("" + theEntryID);//this should be a string
        DatabaseConnector myConnector = new DatabaseConnector("updateEntries", entryFields);//create an entry
        myConnector.connect();
        return myConnector.getState() == DatabaseConnector.SUCCESS;
	}
	
	/**
	 * Removes a contest with the specified ID.
	 * @param theContestID the Contest ID to remove.
	 * @return true if successful, false otherwise.
	 */
	public boolean removeContest(int theContestID)
	{
		ArrayList<String> vals = new ArrayList<>();
        vals.add("removeContest");
        vals.add("" + theContestID);//returns in order name,date,link,description
        DatabaseConnector myConnector = new DatabaseConnector("removeContest", vals);
        myConnector.connect();
        
		return myConnector.getState() == DatabaseConnector.SUCCESS;
	}
	
	/**
	 * Judge an Entry with a score
	 * @param theEntryID the Entry ID to judge
	 * @param theUser the User judging the Entry
	 * @param theScore the score of the Entry
	 * @param theComments the comments for the Entry
	 * @return true if successful, false otherwise.
	 */
	public boolean judgeEntry( User theUser, int theEntryID, int theScore, String theComments)
	{
		ArrayList<String> entryFields = new ArrayList<>();
	    entryFields.add("judgeEntry");//this is the tag
	    entryFields.add("" + theEntryID);//this should be a string
	    entryFields.add("" + theUser.getID());//this should be a string
	    entryFields.add("" + theScore);//this should be a string
	    entryFields.add(theComments);//this should be a string
	    DatabaseConnector myConnector = new DatabaseConnector("updateEntries", entryFields);//create an entry
	    myConnector.connect();
		return myConnector.getState() == DatabaseConnector.SUCCESS;
	}
	
	/**
	 * Gets the Contests the User is able to enter
	 * @param theUser to query for.
	 * @return List of Contests the User is able to enter.
	 */
	public List<Contest> getElegibleContests(User theUser) 
	{
		List<Contest> result = new ArrayList<Contest>();
		ArrayList<String> contests = new ArrayList<>();
		contests.add("eligible_contests");//all eligible contests for user_id 2
		contests.add(String.valueOf(theUser.getID()));//user_id
		DatabaseConnector myConnector = new DatabaseConnector("userContests", contests);
		//get all contests, these will populate the data structure passed in. The format that is returned is in arraylist is contest name, then description
		myConnector.connect();
		
		if (myConnector.getState() == myConnector.SUCCESS)
		{
			System.out.println(contests);
			for(int i = 0; i < contests.size(); i += 4)
			{
				result.add(new Contest(contests.get(i), contests.get(i + 1), Integer.valueOf(contests.get(i + 2)), contests.get(i + 3)));
			}
		}
		return result;
	}
	
	/**
	 * Gets the List of Entry the User has submitted.
	 * @param theUser to query Entry for.
	 * @return list of User submitted Entry.
	 */
	public List<Entry> getEntries(User theUser)
	{
		ArrayList<String> entries = new ArrayList<>();
		entries.add("contests_entered");
		entries.add(String.valueOf(theUser.getID()));
	    DatabaseConnector myConnector = new DatabaseConnector("userContests", entries);
	    myConnector.connect();
		List<Entry> result = new ArrayList<Entry>();
		
		if (myConnector.getState() == myConnector.SUCCESS)
		{
			System.out.println(entries);
			for(int i = 0; i < entries.size(); i += 4)
			{
				
				int id = Integer.valueOf(entries.get(i + 3));
				String path = entries.get(i + 1);
				String name = entries.get(i + 0);
				String desc = entries.get(i + 2);

				result.add( new Entry(
						id,	// ID
						path,					// Path
						name, 				// Name
						desc)  				// Description
				);
		   }
		}
		return result;
	}
	
	public List<Entry> getContestEntries(Contest theContest) {
		int STRIDE = 5;
		
		ArrayList<String> entries = new ArrayList<String>();
		// TODO fix this
		entries.add("allEntries");
		entries.add("" + theContest.getID());
		DatabaseConnector myConnector = new DatabaseConnector("contestEntries", entries);
		myConnector.connect();
		
		List<Entry> retList = new ArrayList<Entry>();
		
		if (myConnector.getState() == DatabaseConnector.SUCCESS) {
			for (int i = 0; i < entries.size(); i += STRIDE) {
				
				int entryID = Integer.valueOf(entries.get(i + 4));
				String entryName = entries.get(i + 0);
				String entryDesc = entries.get(i + 3);
				String entryPath = entries.get(i + 2);
				int entryScore = Integer.valueOf(entries.get(i + 1));
				
				Entry e = new Entry(entryID, entryPath, entryName, entryDesc);
				e.setScore(entryScore);
				
				retList.add(e);
			}
		}
		
		return retList;
	}
	
	/**
	 * Gets the Contests a User can judge.
	 * @param theUser to query contests for.
	 * @return Contests the User can judge.
	 */
	public List<Contest> getJudgableContests(User theUser)
	{
		List<Contest> result = new ArrayList<Contest>();
		ArrayList<String> dumb = new ArrayList<String>();
		dumb.add("judgeContests");
		dumb.add("" + theUser.getID());
		
        DatabaseConnector myConnector = new DatabaseConnector(
        		"userContests", dumb);
		myConnector.connect();
		
		if (myConnector.getState() == DatabaseConnector.SUCCESS)
		{		
			for (int i = 0; i < dumb.size(); i += 4) {
				String name = dumb.get(i + 0);
				String desc = dumb.get(i + 1);
				int id = Integer.valueOf(dumb.get(i + 2));
				String url = dumb.get(i + 3);
				
				result.add(new Contest(
						name,
						desc, 
						id, 
						url
				));
			}	
		}
		
		return result;
	}
	
	/**
	 * Gets a list of all the Contests
	 * @return a list of all Contests
	 */
	public List<Contest> getContests() {
		List<Contest> result = new ArrayList<>();
		ArrayList<String> contests = new ArrayList<>();
		DatabaseConnector myQuery = new DatabaseConnector("getContests", contests);
		myQuery.connect();
		if (myQuery.getState() == DatabaseConnector.SUCCESS) {
			for (int i = 0; i < contests.size(); i += 4) {
				int id = Integer.valueOf(contests.get(i + 0));
				String name = contests.get(i + 1);
				String desc = contests.get(i + 2);
				String url = contests.get(i + 3);
				
				result.add(new Contest(
						name,
						desc, 
						id, 
						url
				));
			}	
		}
		System.out.print(myQuery.getState());
		return result;
	}
	
	/**
	 * Logins the user into the program.
	 * @param theUsername for the user.
	 * @param thePassword for the user.
	 * @return true if success, false otherwise
	 */
	public boolean login(String theUsername, String thePassword)
	{
        ArrayList<String> loginFields = new ArrayList<>();
        loginFields.add(theUsername);//this is the username, a string
        loginFields.add(thePassword);//this is the password, a string
        DatabaseConnector myConnector = new DatabaseConnector("login", loginFields);//login and validate the user
        myConnector.connect();
        if (myConnector.getState() == myConnector.SUCCESS)
        {
        	System.out.println(loginFields);
        	
        	int id = Integer.valueOf(loginFields.get(2));
        	myCurrentUser = new User(id, loginFields.get(1).equals("1"), loginFields.get(0).equals("1"));
        	return true;
        }
		return false;
	}
	
	/**
	 * Gets the logged in user.
	 * @return current logged in user.
	 */
	public User getCurrentUser() 
	{
		return myCurrentUser;
	}
	
	public boolean updateRejected(Entry anEntry) {
		ArrayList<String> entryFields = new ArrayList<>();
	    entryFields.add("updateRejected");//this is the tag
	    entryFields.add(""+ anEntry.getID());//this should be a string
	    entryFields.add(""+ anEntry.getComment());//this should be a string, admin comment
	    entryFields.add(""+ 1);//this should be a string, was rejected.
	    
	    DatabaseConnector myConnector = new DatabaseConnector("adminReject", entryFields);//create an entry
	    myConnector.connect();
		return myConnector.getState() == DatabaseConnector.SUCCESS;
	}
}
