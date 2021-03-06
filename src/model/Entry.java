package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * An Entry contains information from a user submission for a Contest.
 * @author Nicholas Mousel
 * @version 5/24/2016
 */
public class Entry
{
	private User myUser;
	private String myName;
	private int myID;
	private String myDescription;
	private String mySubmissionPath;
	private Date myDate;
	private int myScore;
	private int myContestID;
	private boolean myIsRejected;
	private String myComment;
	
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
		myIsRejected = false;
	}

	/**
	 * Constructs an Entry.
	 * @param theName the name of the Entry.
	 * @param theDescription the description of the Entry.
	 * @param theSubmissionPath the path the submitted Entry.
	 */
	public Entry(String theName, String theDescription, String theSubmissionPath)
	{
		myName = theName;
		myDescription = theDescription;
		mySubmissionPath = theSubmissionPath;
		myIsRejected = false;
	}
	
	/**
	 * Constructs an Entry.
	 * @param theName the name of the Entry.
	 * @param theDescription the description of the Entry.
	 * @param theSubmissionPath the path the submitted Entry.
	 * @param theEntryID the entry ID.
	 */
	public Entry(int theEntryID, String theSubmissionPath, String theName, String theDescription)
	{
		myID = theEntryID;
		myName = theName;
		myDescription = theDescription;
		mySubmissionPath = theSubmissionPath;
		myIsRejected = false;
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
	 * Set the contest score
	 * @param theScore to set.
	 */
	public void setScore(int theScore) {
		myScore = theScore;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(obj instanceof Entry)
		{
			Entry temp = ((Entry) obj);
			if (temp.myID == myID)
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Gets the contest description.
	 * @return the Contest description.
	 */
	public String getDescription() {
		return myDescription;
	}
	
	/**
	 * Gets the submission path.
	 * @return the submission path.
	 */
	public String getSubmissionPath() {
		return mySubmissionPath;
	}
	
	/**
	 * Gets the entry name.
	 * @return the entry name.
	 */
	public String getName() {
		return myName;
	}
	
	public String getDateString() 
	{
		if (myDate == null)
			return "00-00-0000";
		DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
		return df.format(myDate);
	}
	
	public void setUser(User theUser) {
		myUser = theUser;
	}
	
	public void setID(int theID) {
		myID = theID;
	}
	
	public void setDate(Date theDate) {
		myDate = theDate;
	}

	public void setContestID(int theContestID) {
		myContestID = theContestID;
	}

	public int getContestID() {
		return myContestID;
	}
	
	public void setRejected(boolean aval)
	{
		myIsRejected = aval;
	}
	
	public boolean getRejected()
	{
		return myIsRejected;
	}
	
	public String getComment()
	{
		return myComment;
	}
	
	public void setComment(String comment)
	{
		myComment = comment;
	}
}
