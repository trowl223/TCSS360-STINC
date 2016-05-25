package model;

public class User
{
	private int myLibraryID;
	private boolean myAdmin;
	private int myAge;
	
	public User(int theLibraryID, boolean theAdmin)
	{
		myLibraryID = theLibraryID;
		myAdmin = theAdmin;
	}
	
	public User(int theLibraryID, boolean theAdmin, int theAge)
	{
		this(theLibraryID, theAdmin);
		myAge = theAge;
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
}
