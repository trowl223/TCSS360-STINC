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
	
	public Contest()
	{
		this("", "");
		myEntries = new ArrayList<Entry>();
		myJudges = new ArrayList<User>();
	}
	
	public Contest(String theName, String theDescription)
	{
		myName = theName;
		myDescription = theDescription;
	}
	
	public Contest(String theName, List<Entry> theEntries, List<User> theJudges, String theDescription)
	{
		this(theName, theDescription);
		myEntries = theEntries;
		myJudges = theJudges;
	}
	
	public void addJudge(User theUser)
	{
		myJudges.add(theUser);
	}
	
	public boolean isWinner(User theUser)
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
	}
	
	public  List<Entry> getWinningEntries()
	{
		List<Entry> result = new ArrayList<Entry>();
		result.sort(new Comparator<Entry>() {

			@Override
			public int compare(Entry e, Entry e2) {
				
				return e.compareTo(e2);
			}
		});
		return result.subList(0, 3);
	}
	
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
	
	public boolean canJudge(User theUser)
	{
		if(myJudges.contains(theUser))
		{
			return true;
		}
		return false;
	}
	
	public int getID()
	{
		return myID;
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	
}
