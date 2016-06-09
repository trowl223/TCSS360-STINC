package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.Contest;
import stinc.Controller;
import stinc.Model;

/**
 * Tests the methods an Admin would use in the Controller.
 * @author Nicholas
 *
 */
public class AdminControllerTest {

	private Controller myController;
	private String myAdminUser = "admin";
	private String myAdminPass = "admin";
	
	/**
	 * Set up the admin controller test.
	 * @throws Exception if setup is unsecussful.
	 */
	@Before
	public void setUp() throws Exception {
		Model myModel = new Model();
		myController = new Controller();
		myController.addModel(myModel);
	}
	
	/**
	 * Test admin login
	 */
	@Test
	public void loginAdminTest()
	{
		assertTrue("Admin was not able to login.", myController.login(myAdminUser, myAdminPass));
		assertTrue("Admin login is not an admin", myController.getCurrentUser().isAdmin());
	}
	
	/**
	 * Gets all the Contests.
	 */
	@Test
	public void getContestsTest()
	{
		List<Contest> contests = myController.getContests();
		List<Contest> same = myController.getContests();
		assertTrue(contests.equals(same));
	}
	
	/**
	 * Tests if adding a Contest is successful.
	 */
	@Test
	public void addContestTest()
	{
		assertTrue("The contest was not able to be added", myController.addContest(TestUtilities.getDummyContest()));
		List<Contest> contests = myController.getContests();
		assertTrue("There should be a contest named jTest.", TestUtilities.checkForCDummy(contests));
		assertTrue("The contest could not be removed.", myController.removeContest(TestUtilities.getDummyCID()));
		
		contests = myController.getContests();
		assertFalse("The dummy should not be removed.", TestUtilities.checkForCDummy(contests));
	}
	
//	/**
//	 * Tests if removing a Contest is successful.
//	 */
//	@Test
//	public void removeContestTest()
//	{
//		assertTrue("The contest was not able to be added", myController.addContest(TestUtilities.getDummyContest()));
//		List<Contest> contests = myController.getContests();
//		assertTrue("There should be a contest named jTest.", TestUtilities.checkForCDummy(contests));
//		assertTrue("The contest could not be removed.", myController.removeContest(TestUtilities.getDummyCID()));
//		
//		contests = myController.getContests();
//		assertFalse("The dummy should not be removed.", TestUtilities.checkForCDummy(contests));
//	}
}
