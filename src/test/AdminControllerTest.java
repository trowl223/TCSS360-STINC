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
	private String myAdminUser = "admin";
	private String myAdminPass = "admin";

	@Before
	public void setUp() throws Exception {
		Model myModel = new Model();
		myController = new Controller();
		myController.addModel(myModel);
	}
	
	@Test
	public void loginAdminTest()
	{
		assertTrue("Admin was not able to login.", myController.login(myAdminUser, myAdminPass));
		assertTrue("Admin login is not an admin", myController.getCurrentUser().isAdmin());
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
		assertTrue("The contest was not able to be added", myController.addContest(TestUtilities.getDummyContest()));
		List<Contest> contests = myController.getContests();
		assertTrue("There should be a contest named jTest.", TestUtilities.checkForDummy(contests));
		assertTrue("The contest could not be removed.", myController.removeContest(TestUtilities.getDummyCID()));
		
		contests = myController.getContests();
		assertFalse("The dummy should not be removed.", TestUtilities.checkForDummy(contests));
	}
	
	@Test
	public void removeContestTest()
	{
		assertTrue("The contest was not able to be added", myController.addContest(TestUtilities.getDummyContest()));
		List<Contest> contests = myController.getContests();
		assertTrue("There should be a contest named jTest.", TestUtilities.checkForDummy(contests));
		assertTrue("The contest could not be removed.", myController.removeContest(TestUtilities.getDummyCID()));
		
		contests = myController.getContests();
		assertFalse("The dummy should not be removed.", TestUtilities.checkForDummy(contests));
	}
	
//	@Test
//	public void removeEntryTest()
//	{
//		//TODO: Finish
//		assertTrue("The contest was not able to be added", myController.addContest(new Contest("jTest", "test")));
//		List<Contest> contests = myController.getContests();
//		List<Contest> nameTest = new ArrayList<>();
//		int id = -1;
//		for (Contest c : contests)
//		{
//			if (c.getName().equals("jTest"))
//			{
//				if (c.getDescription().equals("test"))
//				{
//					nameTest.add(c);
//					id = c.getID();
//					
//				}
//			}
//		}
//		assertFalse("There should be a contest named jTest.", nameTest.size() == 0);
//		assertTrue("There should only be one contest named jTest.", nameTest.size() == 1);
//		assertTrue("The contest could not be removed.", myController.removeContest(id));
//		
//		contests = myController.getContests();
//		for (Contest c : contests)
//		{
//			if (c.getName().equals("jTest"))
//			{
//				if (c.getDescription().equals("test"))
//				{
//					fail("jTest contest was not removed");
//				}
//			}
//		}
//	}
}
