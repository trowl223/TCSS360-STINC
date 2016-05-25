package model;

import java.util.Date;
import java.util.List;
/**
 * An Entry contains information from a user submission for a Contest.
 * @author Nicholas Mousel
 * @version 5/24/2016
 */
public class Entry implements Comparable<Entry>
{
	private User myUser;
	private int myID;
	private String myDescription;
	private String mySubmissionPath;
	private Date myDate;
	private int myScore;
	/**
	 * Judges that have judged this Entry.
	 */
	private List<User> myJudgedBy;
	/**
	 * Judges that are allowed to judge this Entry.
	 */
	private List<User> myAllowedJudges;
	/**
	 * Constructs an Entry.
	 * @param theUser the user that submitted this Entry.
	 * @param theDescription the description of the Entry.
	 * @param theSubmissionPath the path the submitted Entry.
	 * @param theDate the Date the Entry was submitted.
	 * @param theScore the Score the Entry has.
	 * @param theID the Entry ID.
	 */
	public Entry(User theUser, String theDescription, 
				 String theSubmissionPath, Date theDate, int theScore, int theID)
	{
		myUser = theUser;
		myDescription = theDescription;
		mySubmissionPath = theSubmissionPath;
		myDate = theDate;
		myScore = theScore;
		myID = theID;
	}
	/**
	 * Get the User that owns the Entry.
	 * @return the User that owns the Entry.
	 */
	public User getOwner()
	{
		return myUser;
	}
	
	/**
	 * Gets the Entry ID.
	 * @return the Entry ID.
	 */
	public int getID()
	{
		return myID;
	}
	
	/**
	 * Gets the score of the Entry.
	 * @return score of the entry.
	 */
	public int getScore()
	{
		return myScore;
	}
	
	/**
	 * Checks if the specified User has judged the Entry.
	 * @param theUser the User judging the Entry.
	 * @return true if the Entry was judged, false otherwise.
	 */
	public boolean wasJudged(User theUser)
	{
		if (myJudgedBy.contains(theUser))
		{
			return true;
		}
		return false;
	}
	
	/**
	 * Reward the Entry with the specified amount of points by the specified User.
	 * @param theUser the User scoring the Entry.
	 * @param theScore the Score rewarded to that entry.
	 */
	public void judge(User theUser, int theScore)
	{
		if(!wasJudged(theUser))
		{
			myScore += theScore;
			myJudgedBy.add(theUser);
		}
		System.out.println("This User has already judged the submission");
	}

	@Override
	public int compareTo(Entry theOther) {
		return theOther.myScore - myScore;
	}
}
