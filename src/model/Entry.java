import java.util.Date;
import java.util.List;

public class Entry implements Comparable<Entry>
{
	private User myUser;
	private int myID;
	private String myDescription;
	private String mySubmissionPath;
	private Date myDate;
	private int myScore;
	private List<User> myJudgedBy;
	private List<User> myAllowedJudges;
	
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
	
	public User getOwner()
	{
		return myUser;
	}
	
	public int getID()
	{
		return myID;
	}
	
	public int getScore()
	{
		return myScore;
	}
	
	public boolean wasJudged(User theUser)
	{
		if (myJudgedBy.contains(theUser))
		{
			return true;
		}
		return false;
	}
	
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
