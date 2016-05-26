import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

import model.Contest;
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
	
	/**
	 * Constructs the Model
	 */
	public Model() {
		myContests = new ArrayList<Contest>();
	}
	
	/**
	 * Adds a contest
	 * @param theContest the Contest to add
	 * @return true if successful, false otherwise.
	 */
	public boolean addContest(Contest theContest)
	{
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
	
	public boolean addEntry(int theContestID, Entry theEntry)
	{
		Contest c = getContest(theContestID);
		
		boolean result = false;
		if (c != null)
		{
			result = c.addEntry(theEntry);
			setChanged();
			notifyObservers();
		}
		return result;
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

	public List<Contest> getElegibleContests(User theUser) {
		List<Contest> result = new ArrayList<Contest>();
		for (Iterator<Contest> iterator = myContests.iterator(); iterator.hasNext();)
		{
		    Contest contest = iterator.next();
		    if (contest.isEligible(theUser))
		    {
		    	result.add(contest);
		    }
		}
		return result;
	}
	
	public List<Contest> getContestsEntered(User theUser) {
		List<Contest> result = new ArrayList<Contest>();
		
		for (Iterator<Contest> iterator = myContests.iterator(); iterator.hasNext();)
		{
		    Contest contest = iterator.next();
		    if(contest.getUserEntries(theUser).isEmpty())
		    {		
		    	result.add(contest);
		    }
		}
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
	
}
