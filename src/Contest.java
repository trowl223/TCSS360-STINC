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
	private int myAgeLimit;
	private String myImageURL;
	
	/**
	 * Constructs a Contest
	 */
	public Contest()
	{
		this("", "");
		myAgeLimit = -1;
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
	public Contest(String theName, String theDescription, int theAgeLimit)
	{
		this(theName, theDescription);
		myAgeLimit = theAgeLimit;
	}
	
	/**
	 * Gets the Contest ID.
	 * @return the ID of the contest.
	 */
	public int getID()
	{
		return myID;
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
	 * Get the Image URL.
	 * @return the Image URL.
	 */
	public String getImageURL() {
		return myImageURL;
	}
	
	/**
	 * Get age limit.
	 * @return age limit.
	 */
	public int getAgeLimit() {
		return myAgeLimit;
	}
	
	/**
	 * Checks if a contest is equal to another contest.
	 * @param theOther a contest.
	 * @return true if equal, false otherwise.
	 */
	public boolean equals(Contest theOther) {
		return theOther.getID() == myID;
	}
	
}
