import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
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
	private List<Contest> contests;
	
	/**
	 * Constructs the Model
	 */
	public Model() {
		contests = new ArrayList<Contest>();
	}
	
	/**
	 * Adds a contest
	 * @param theContest the Contest to add
	 * @return true if successful, false otherwise.
	 */
	public boolean addContest(Contest theContest)
	{
		if (!contestExists(theContest))
		{
			contests.add(theContest);
			System.out.println("Add Contest");
			setChanged();
			notifyObservers();
		}
		return false;
	}
	
	/**
	 * Checks if the Contest already exists.
	 * @param theContest the Contest to check for
	 * @return true if Contest exists, false otherwise.
	 */
	public boolean contestExists(Contest theContest)
	{
		return contests.contains(theContest);
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
		return c.removeEntry(theEntryID);
	}
	
	/**
	 * Remove an Entry without knowing the contest it came from.
	 * @param theEntryID the ID of the Entry to remove.
	 * @return true if successful, false otherwise.
	 */
	public boolean removeEntry(int theEntryID)
	{
		for (Iterator<Contest> iterator = contests.iterator(); iterator.hasNext();)
		{
		    Contest contest = iterator.next();
		    if(contest.removeEntry(theEntryID))
		    {
		    	return true;
		    }
		}
		return false;
	}
	
	/**
	 * Gets the Contest with the specified  ID.
	 * @param theContestID the Contest ID to get.
	 * @return if successful the Contest with the specified ID, null otherwise.
	 */
	public Contest getContest(int theContestID)
	{
		for (Iterator<Contest> iterator = contests.iterator(); iterator.hasNext();)
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
		for (Iterator<Contest> iterator = contests.iterator(); iterator.hasNext();)
		{
		    Contest contest = iterator.next();
		    if (contest.getID() == theContestID)
		    {
		        // Remove the current element from the iterator and the list.
		        iterator.remove();
		        return true;
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
	
}
