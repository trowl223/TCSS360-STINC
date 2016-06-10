package test;

import java.util.List;

import model.Contest;
import model.Entry;

public class TestUtilities 
{
	private static String dummyCName = "I am a Test.";
	private static String dummyCDesc = "I am test description.";
	private static int dummyCID = -1;
	private static String dummyEName = "I am a Test Entry.";
	private static String dummyEDesc = "I am test entry.";
	private static String dummyEPath = "www.google.com";
	private static int dummyEID = -1;
	
	public static Contest getDummyContest()
	{
		return new Contest(dummyCName, dummyCDesc);
	}
	
	public static boolean checkForCDummy(List<Contest> theContests)
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

	public static int getDummyCID() 
	{
		return dummyCID;
	}
	
	public static int getDummyEID()
	{
		return dummyEID;
	}
	
	public static Entry getDummyEntry()
	{
		return new Entry(dummyEName, dummyEDesc, dummyEPath);
	}
	
	public static Contest getDummyCFrom(List<Contest> theContests)
	{
		for (Contest c : theContests)
		{
			if(c.getName().equals(dummyCName))
			{
				if (c.getDescription().equals(dummyCDesc))
				{				
					return c;
				}
			}
		}
		return null;
	}
	public static Entry getDummyEFrom(List<Entry> theEntries)
	{
		for (Entry c : theEntries)
		{
			if(c.getName().equals(dummyEName))
			{
				if (c.getDescription().equals(dummyEDesc))
				{
					
					return c;
				}
			}
		}
		return null;
	}
	
	public static boolean checkForEDummy(List<Entry> theEntries) 
	{
		for (Entry c : theEntries)
		{
			if(c.getName().equals(dummyEName))
			{
				if (c.getDescription().equals(dummyEDesc))
				{
					dummyEID = c.getID();
					return true;
				}
			}
		}
		return false;
	}
}
