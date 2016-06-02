package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * This class stores information and methods that deal with Contests
 * @author Nicholas Mousel
 * @version 5/24/2016
 */
public class Contest
{
	private int myID;
	private String myName;
	private String myDescription;
	private List<Entry> myEntries;
	private List<User> myJudges;
	private int myAgeLimit;
	private String myImageURL;
	
	/**
	 * Constructs a Contest
	 */
	public Contest()
	{
		this("", "");
		myAgeLimit = -1;
		myEntries = new ArrayList<Entry>();
		myJudges = new ArrayList<User>();
	}
	
	/**
	 * Constructs a Contest with the specified parameters
	 * @param theName the Contest Name
	 * @param theDescription the Contest Description
	 */
	public Contest(String theName, String theDescription, int theID, String theImageURL)
	{
		myName = theName;
		myID = theID;
		myDescription = theDescription;
		myImageURL = theImageURL;
	}
	
	/**
	 * Constructs a Contest with the specified parameters
	 * @param theName the Contest Name
	 * @param theDescription the Contest Description
	 */
	public Contest(String theName, String theDescription)
	{
		myName = theName;
		myDescription = theDescription;
	}
	
	/**
	 * Constructs a Contest with a name, description, and image URL.
	 * @param theName the Contest Name.
	 * @param theDescription the Contest Description.
	 * @param theImageURL the URL to fetch the image from.
	 */
	public Contest(String theName, String theDescription, String theImageURL) {
		this(theName, theDescription);
		myImageURL = theImageURL;
	}
	
	/**
	 * Constructs a Contest with the specified parameters.
	 * @param theName the Contest Name.
	 * @param theDescription the Contest Description.
	 * @param theEntries the Contest Entries.
	 * @param theJudges the Judges for the Contest.
	 */
	public Contest(String theName, List<Entry> theEntries, List<User> theJudges, String theDescription, int theAgeLimit)
	{
		this(theName, theDescription);
		myAgeLimit = theAgeLimit;
		myEntries = theEntries;
		myJudges = theJudges;
	}
	
	/**
	 * Add the specified User as a Judge to the Contest
	 * @param theUser the User allowed to Judge
	 */
	public boolean addJudge(User theUser)
	{
		return myJudges.add(theUser);
	}
	
	/**
	 * Check if the User is a winner.
	 * @param theUser the User to check.
	 * @return true if user is winner, false otherwise
	 */
	/*public boolean isWinner(User theUser)
	{
		List<Entry> winners = getWinningEntries();
		for(Entry e : winners)
		{
			if(e.getOwner().equals(theUser))
			{
				return true;
			}
		}
		return false;
	}*/
	/**
	 * Get the winning Entries for the Contest.
	 * @return list of winning entries.
	 */
	/*public  List<Entry> getWinningEntries()
	{
		List<Entry> result = new ArrayList<Entry>();
		result.sort(new Comparator<Entry>() {

			@Override
			public int compare(Entry e, Entry e2) {
				
				return e.compareTo(e2);
			}
		});
		return result.subList(0, 3);
	}*/
	/**
	 * Remove Entry with specified ID.
	 * @param theEntryID the entry to remove.
	 * @return true if successful, false otherwise.
	 */
	public boolean removeEntry(int theEntryID)
	{
		for (Iterator<Entry> iterator = myEntries.iterator(); iterator.hasNext();)
		{
		    Entry e = iterator.next();
		    if (e.getID() == theEntryID)
		    {
		        // Remove the current element from the iterator and the list.
		        iterator.remove();
		        return true;
		    }
		}
		return false;
	}
	
	public Entry getEntry(int theEntryID)
	{
		for (Entry e: myEntries)
		{
			if (e.getID() == theEntryID)
			{
				return e;
			}
		}
		return null;
	}
	
	public Entry getEntries(User theUser, int theEntryID)
	{
		List<Entry> es = getUserEntries(theUser);
		for (Entry e: es)
		{
			if(e.getID() == theEntryID)
			{
				return e;
			}
		}
		return null;
	}
	
	/**
	 * Get the entries for a specified User.
	 * @param theUser to get Entries for.
	 * @return the User entries.
	 */
	public List<Entry> getUserEntries(User theUser)
	{
		List<Entry> result = new ArrayList<Entry>();
		for(Entry e : myEntries)
		{
			if(e.getOwner().equals(theUser))
			{
				result.add(e);
			}
		}
		return result;
	}
	
	/**
	 * Checks to see if the User can judge the contest.
	 * @param theUser the User to check.
	 * @return true if the User can judge the Entry, false otherwise
	 */
	public boolean canJudge(User theUser)
	{
		if(myJudges.contains(theUser))
		{
			return true;
		}
		return false;
	}
	
	/**
	 * Gets the Contest ID.
	 * @return the ID of the contest.
	 */
	public int getID()
	{
		return myID;
	}

	public boolean addEntry(Entry theEntry) {
		if (getUserEntries(theEntry.getOwner()).isEmpty())
		{
			return myEntries.add(theEntry);
		}
		return false;
	}

	public boolean removeJudge(User theUser) {
		return myJudges.remove(theUser);
	}
	
	/**
	 * Get the name of the contest.
	 * @return the name of the contest.
	 */
	public String getName() {
		return myName;
	}
	
	/**
	 * Get the description of the contest.
	 * @return the contest's description.
	 */
	public String getDescription() {
		return myDescription;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getImageURL() {
		return myImageURL;
	}
	
}