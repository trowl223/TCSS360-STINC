package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import stinc.Controller;
import stinc.Model;

public class JudgeControllerTest {

	private Controller myController;
	private String myJudgeUser = "judge";
	private String myJudgePass = "judge";

	@Before
	public void setUp() throws Exception {
		Model myModel = new Model();
		myController = new Controller();
		myController.addModel(myModel);
		myController.login("test", "test");
	}
	
	@Test
	public void loginJudgeTest()
	{
		assertTrue("Judge was not able to login.", myController.login(myJudgeUser, myJudgePass));
		assertTrue("Judge login is not an judge", myController.getCurrentUser().isJudge());
	}
	
	@Test
	public void getJudgableContestsTest()
	{
		fail("Not yet implemented");
	}
	
	@Test
	public void getJudgeEntryTest()
	{
		fail("Not yet implemented");
	}
	
	

}
