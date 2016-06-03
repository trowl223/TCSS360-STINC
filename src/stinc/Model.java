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
		entryFields.add(theContest.getName());
		entryFields.add(theContest.getDescription());
		entryFields.add("" + theContest.getAgeLimit() + "");
		entryFields.add(theContest.getImageURL());
		DatabaseConnector myConnector = new DatabaseConnector("addContest", entryFields);//create an entry
        	myConnector.connect();
		boolean result = false;
		if (!contestExists(theContest))
		{
			result = myContests.add(theContest);
			System.out.println("Add Contest");
			setChanged();
			notifyObservers();
		}
		return result;
	}
	
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
//		Contest c = getContest(theContestID);
//		
//		boolean result = false;
//		if (c != null)
//		{
//			result = c.addEntry(theEntry);
//			setChanged();
//			notifyObservers();
//		}
		return myConnector.getState() == DatabaseConnector.SUCCESS;
	}
	
	public Entry getEntry(int theContestID, int theEntryID)
	{
		
		Contest c = getContest(theContestID);
		boolean result = false;
		if (c != null)
		{
			return c.getEntry(theEntryID);
		}
		return null;
	}
	
	/**
	 * Checks if the Contest already exists.
	 * @param theContest the Contest to check for
	 * @return true if Contest exists, false otherwise.
	 */
	public boolean contestExists(Contest theContest)
	{
		return myContests.contains(theContest);
	}
	
	/**
	 * Remove an Entry.
	 * @param theEntryID the ID of the Entry to remove.
	 * @param theContestID the ID of the Contest to remove.
	 * @return true if successful, false otherwise.
	 */
	public boolean removeEntry(int theEntryID, int theContestID)
	{
		Contest c = getContest(theContestID);
		boolean result = false;
		if (c != null)
		{
			result = c.removeEntry(theEntryID);
			setChanged();
			notifyObservers();
		}
		return result;
	}
	
	/**
	 * Remove an Entry without knowing the contest it came from.
	 * @param theEntryID the ID of the Entry to remove.
	 * @return true if successful, false otherwise.
	 */
	public boolean removeEntry(int theEntryID)
	{
		/*ArrayList<String> entryFields = new ArrayList<>();
	        entryFields.add("removeEntry");//this is the tag
	        entryFields.add("" + theEntryID + "");//this should be a string
	        DatabaseConnector myConnector = new DatabaseConnector("updateEntries", entryFields);//create an entry
	        myConnector.connect();*/
		boolean result = false;
		for (Iterator<Contest> iterator = myContests.iterator(); iterator.hasNext();)
		{
		    Contest contest = iterator.next();
		    
		    if(contest.getID() == theEntryID)
		    {
		    	result = contest.removeEntry(theEntryID);
				setChanged();
				notifyObservers();
		    }
		}
		return result;
	}
	
	/**
	 * Gets the Contest with the specified  ID.
	 * @param theContestID the Contest ID to get.
	 * @return if successful the Contest with the specified ID, null otherwise.
	 */
	public Contest getContest(int theContestID)
	{
		for (Iterator<Contest> iterator = myContests.iterator(); iterator.hasNext();)
		{
		    Contest contest = iterator.next();
		    if (contest.getID() == theContestID)
		    {
		    	return contest;
		    }
		}
		return null;
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
        	vals.add("" + theContestID + "");//returns in order name,date,link,description
        	DatabaseConnector myConnector = new DatabaseConnector("removeContest", vals);
        	myConnector.connect();
		for (Iterator<Contest> iterator = myContests.iterator(); iterator.hasNext();)
		{
		    Contest contest = iterator.next();
		    
		    if (contest.getID() == theContestID)
		    {
		        // Remove the current element from the iterator and the list.
		        iterator.remove();
				setChanged();
				notifyObservers();
		        return true;
		    }
		}
		return false;
	}
	
	public boolean judgeEntry(int theContestID, int theEntryID, User theUser, int theScore)
	{
		/*ArrayList<String> entryFields = new ArrayList<>();
	        entryFields.add("judgeEntry");//this is the tag
	        entryFields.add("" + theEntryID + "");//this should be a string
	        entryFields.add("" + theUser.getID() + "");//this should be a string
	        entryFields.add("" + theScore + "");//this should be a string
	        entryFields.add("comments for the user");//this should be a string
	        DatabaseConnector myConnector = new DatabaseConnector("updateEntries", entryFields);//create an entry
	        myConnector.connect();*/
		Contest c = getContest(theContestID);
		if(c != null)
		{
			if(c.canJudge(theUser))
			{
				Entry e = c.getEntry(theEntryID);
				if (e != null)
				{
					e.judge(theUser, theScore);
					setChanged();
					notifyObservers();
					return true;
				}
				
			}
		}
		return false;
	}
	
	/**
	 * Saves the data to the database.
	 */
	public void save()
	{
		//TODO: When I know what I am saving.
	}

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
//		List<Contest> result = new ArrayList<Contest>();
//		for (Iterator<Contest> iterator = myContests.iterator(); iterator.hasNext();)
//		{
//		    Contest contest = iterator.next();
//		    if (contest.isEligible(theUser))
//		    {
//		    	result.add(contest);
//		    }
//		}
//		return result;
	}
	
	public List<Entry> getContestsEntered(User theUser) {
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
				// Name of entry, Date, URL, Description
//				result.add(new Contest(contests.get(i), contests.get(i + 1), Integer.valueOf(contests.get(i + 2)), contests.get(i + 3)));
			}
		}
		
//		for (Iterator<Contest> iterator = myContests.iterator(); iterator.hasNext();)
//		{
//		    Contest contest = iterator.next();
//		    if(contest.getUserEntries(theUser).isEmpty())
//		    {		
//		    	result.add(contest);
//		    }
//		}
		return result;
	}
	
	public List<Contest> getJudgableContests(User theUser) {
		List<Contest> result = new ArrayList<Contest>();
		
//		DatabaseConnector myQuery = new DatabaseConnector("getJudgables", myCurrentUser.getID(), result);
		ArrayList<String> dumb = new ArrayList<String>();
		dumb.add("judgeContests");
		dumb.add("" + theUser.getID());
		

        DatabaseConnector myConnector = new DatabaseConnector(
        		"userContests", dumb);
		myConnector.connect();
		
		if (myConnector.getState() == DatabaseConnector.SUCCESS) {
			
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
		
//		myQuery.connect();
		
		return result;
	}

	public List<Contest> getContests() {
		/*DatabaseConnector myQuery = new DatabaseConnector("getContests",myContests);*/
		return myContests;
	}

	public Entry getEntry(int theEntryID) {
		for (Contest c : myContests)
		{
			Entry e = c.getEntry(theEntryID);
			if (e != null)
			{
				return e;
			}
		}
		return null;
	}

	public boolean login(String theUsername, String thePassword) {
        ArrayList<String> loginFields = new ArrayList<>();
        loginFields.add(theUsername);//this is the username, a string
        loginFields.add(thePassword);//this is the password, a string
        DatabaseConnector myConnector = new DatabaseConnector("login", loginFields);//login and validate the user
        myConnector.connect();
        if (myConnector.getState() == myConnector.SUCCESS)
        {
        	System.out.println(loginFields);
        	
        	int id = Integer.valueOf(loginFields.get(0));
        	boolean admin = false;
        	if (loginFields.get(1).equals("1"))
        		admin = true;
        	boolean judge = false;
        	if (loginFields.get(2).equals("1"))
        		judge = true;
        	
        	myCurrentUser = new User(id, admin, judge);
        	return true;
        }
		return false;
	}

	public User getCurrentUser() {
		return myCurrentUser;
	}
	
}
