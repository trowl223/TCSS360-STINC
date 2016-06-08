package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.Contest;
import stinc.Controller;
import stinc.Model;

public class AdminControllerTest {

	private Controller myController;

	@Before
	public void setUp() throws Exception {
		Model myModel = new Model();
		myController = new Controller();
		myController.addModel(myModel);
		myController.login("test", "test");
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void getContestsTest()
	{
		List<Contest> contests = myController.getContests();
		List<Contest> same = myController.getContests();
		assertTrue(contests.equals(same));
//		assertTrue("The contest was unable to be removed", myController.removeContest(theContestID))
//		System.out.println(myController.getContests());
	}
	
	@Test
	public void addContestTest()
	{
		assertTrue("The contest was not able to be added", myController.addContest(new Contest("test", "test")));
		List<Contest> contests = myController.getContests();
		List<Contest> nameTest = new ArrayList<>();
		for (Contest c : contests)
		{
			if (c.getName().equals("test"))
			{
				if (c.getDescription().equals("test"))
				{
					nameTest.add(c);
				}
			}
		}
		assertFalse("There should be a contest named test.", nameTest.size() == 0);
		assertTrue("There should only be one contest named test.", nameTest.size() == 1);
	}
	
	@Test
	public void removeContestTest()
	{
		assertTrue("The contest was not able to be added", myController.addContest(new Contest("jTest", "test")));
		List<Contest> contests = myController.getContests();
		List<Contest> nameTest = new ArrayList<>();
		int id = -1;
		for (Contest c : contests)
		{
			if (c.getName().equals("jTest"))
			{
				if (c.getDescription().equals("test"))
				{
					nameTest.add(c);
					id = c.getID();
					
				}
			}
		}
		assertFalse("There should be a contest named jTest.", nameTest.size() == 0);
		assertTrue("There should only be one contest named jTest.", nameTest.size() == 1);
		assertTrue("The contest could not be removed.", myController.removeContest(id));
		
		contests = myController.getContests();
		assertFalse("The dummy should not be removed.", TestUtilities.checkForDummy(contests));
	}
	
	@Test
	public void removeEntryTest()
	{
		//TODO: Finish
		assertTrue("The contest was not able to be added", myController.addContest(new Contest("jTest", "test")));
		List<Contest> contests = myController.getContests();
		List<Contest> nameTest = new ArrayList<>();
		int id = -1;
		for (Contest c : contests)
		{
			if (c.getName().equals("jTest"))
			{
				if (c.getDescription().equals("test"))
				{
					nameTest.add(c);
					id = c.getID();
					
				}
			}
		}
		assertFalse("There should be a contest named jTest.", nameTest.size() == 0);
		assertTrue("There should only be one contest named jTest.", nameTest.size() == 1);
		assertTrue("The contest could not be removed.", myController.removeContest(id));
		
		contests = myController.getContests();
		for (Contest c : contests)
		{
			if (c.getName().equals("jTest"))
			{
				if (c.getDescription().equals("test"))
				{
					fail("jTest contest was not removed");
				}
			}
		}
	}

}
