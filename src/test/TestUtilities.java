package test;

import java.util.List;

import model.Contest;

public class TestUtilities 
{
	private static String dummyCName = "jTestNew";
	private static String dummyCDesc = "test";
	private static int dummyCID = -1;
	
	public static Contest getDummyContest()
	{
		return new Contest(dummyCName, dummyCDesc);
	}
	
	public static boolean checkForDummy(List<Contest> theContests)
	{
		for (Contest c : theContests)
		{
			if(c.getName().equals(dummyCName))
			{
				if (c.getDescription().equals(dummyCDesc))
				{
					dummyCID = c.getID();
					return true;
				}
			}
		}
		return false;
	}

	public static int getDummyCID() {
		return dummyCID;
	}
}
