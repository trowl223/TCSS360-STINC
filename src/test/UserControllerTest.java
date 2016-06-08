package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.Contest;
import model.Entry;
import stinc.Controller;
import stinc.Model;

public class UserControllerTest {

	private Controller myController;
	private String myUserUser;
	private String myUserPass;

	@Before
	public void setUp() throws Exception {
		Model myModel = new Model();
		myController = new Controller();
		myController.addModel(myModel);
//		myController.login("myUserUser", "myUserPass");
	}
	
	@Test
	public void loginUserTest()
	{
		assertTrue("User was not able to login.", myController.login(myUserUser, myUserPass));
		assertFalse("User login is a Judge", myController.getCurrentUser().isJudge());
		assertFalse("User login is an Admin", myController.getCurrentUser().isAdmin());
	}
	
	@Test
	public void getEligibleContestTest()
	{
		// TODO: Finish
		List<Contest> contests = myController.getContests();
		List<Contest> same = myController.getContests();
		assertTrue(contests.equals(same));
	}
	
	@Test
	public void addEntryTest()
	{
		// TODO: Finish
//		assertTrue("The contest was not able to be added", myController.addEntry(new Entry("test", "test")));
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
	public void removeEntryTest()
	{
		assertTrue("The contest was not able to be added", myController.addContest(TestUtilities.getDummyContest()));
		List<Contest> contests = myController.getContests();
		TestUtilities.checkForDummy(contests);
		assertTrue("The contest could not be removed.", myController.removeContest(TestUtilities.getDummyCID()));
		
		contests = myController.getContests();
		
		assertFalse("The Entry should be removed", false);
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
	
	@Test
	public void getEntriesTest ()
	{
		//TODO: get user entries tests
	}
	
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
