package model;
/**
 * Information used to identify a User
 * @author Nicholas Mousel
 * @version 5/24/2016
 *
 */
public class User
{
	private int myLibraryID;
	private boolean myAdmin;
	private boolean myJudge;
	private int myAge;
	
	/**
	 * Constructs the User.
	 * @param theLibraryID ID of the User.
	 * @param theAdmin Flag for denoting if the user is an Admin.
	 * @param theJudge Flag for denoting if the user is a Judge.
	 */
	public User(int theLibraryID, boolean theAdmin, boolean theJudge)
	{
		myLibraryID = theLibraryID;
		myAdmin = theAdmin;
		myJudge = theJudge;
	}
	
	/**
	 * Constructs the User.
	 * @param theLibraryID ID of the User.
	 * @param theAdmin Flag for denoting if the user is an Admin.
	 * @param theJudge Flag for denoting if the user is a Judge.
	 * @param theAge How old the user is.
	 */
	public User(int theLibraryID, boolean theAdmin, boolean theJudge, int theAge)
	{
		this(theLibraryID, theAdmin, theJudge);
		myAge = theAge;
	}
	
	public int getAge()
	{
		return myAge;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(obj instanceof User)
		{
			User temp = ((User) obj);
			if (temp.myLibraryID == myLibraryID)
			{
				return true;
			}
		}
		return false;
	}

	public int getID() {
		return myLibraryID;
	}

	public boolean isJudge() {
		return myJudge;
	}

	public boolean isAdmin() {
		return myAdmin;
	}
}
