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
	private String myUserUser = "user";
	private String myUserPass = "user";

	@Before
	public void setUp() throws Exception {
		Model myModel = new Model();
		myController = new Controller();
		myController.addModel(myModel);
		myController.login(myUserUser, myUserPass);
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
		myController.addContest(TestUtilities.getDummyContest());
		List<Contest> contests = myController.getElegibleContests(myController.getCurrentUser());
		assertTrue("The User should be able to enter the eligible contest.", TestUtilities.checkForCDummy(contests));
		myController.removeContest(TestUtilities.getDummyCID());
	}
	
	@Test
	public void addEntryTest()
	{
		assertTrue("The contest was not able to be added", myController.addContest(TestUtilities.getDummyContest()));
		assertTrue("The dummy contest should be an eligible contest", TestUtilities.checkForCDummy(myController.getContests()));
		assertTrue("The entry was not able to be added.", myController.addEntry(TestUtilities.getDummyCID(), TestUtilities.getDummyEntry()));
		assertTrue("The dummy entry should be inside the User Entries",TestUtilities.checkForEDummy(myController.getEntries(myController.getCurrentUser())));
		assertTrue("The contest was unable to be removed.", myController.removeContest(TestUtilities.getDummyCID()));
	}
	
	@Test
	public void removeEntryTest()
	{
		assertTrue("The contest was not able to be added", myController.addContest(TestUtilities.getDummyContest()));
		assertTrue("The dummy contest should be an eligible contest", TestUtilities.checkForCDummy(myController.getContests()));
		assertTrue("The entry was not able to be added.", myController.addEntry(TestUtilities.getDummyCID(), TestUtilities.getDummyEntry()));
		assertTrue("The dummy entry should be inside the User Entries",TestUtilities.checkForEDummy(myController.getEntries(myController.getCurrentUser())));
		assertTrue("The contest was unable to be removed.", myController.removeContest(TestUtilities.getDummyCID()));
	}
	
	@Test
	public void getEntriesTest ()
	{
		assertTrue("The contest was not able to be added", myController.addContest(TestUtilities.getDummyContest()));
		assertTrue("The dummy contest should be an eligible contest", TestUtilities.checkForCDummy(myController.getContests()));
		assertTrue("The entry was not able to be added.", myController.addEntry(TestUtilities.getDummyCID(), TestUtilities.getDummyEntry()));
		assertTrue("The dummy entry should be inside the User Entries",TestUtilities.checkForEDummy(myController.getEntries(myController.getCurrentUser())));
		assertTrue("The contest was unable to be removed.", myController.removeContest(TestUtilities.getDummyCID()));
	}

}
