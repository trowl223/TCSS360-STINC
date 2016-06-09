package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.Contest;
import stinc.Controller;
import stinc.Model;

/**
 * Tests the methods a Judge would use in the Controller.
 * @author Nicholas
 *
 */
public class JudgeControllerTest {

	private Controller myController;
	private String myJudgeUser = "judge";
	private String myJudgePass = "judge";

	@Before
	public void setUp() throws Exception {
		Model myModel = new Model();
		myController = new Controller();
		myController.addModel(myModel);
		myController.login(myJudgeUser, myJudgePass);
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
		List<Contest> contests = myController.getJudgableContests(myController.getCurrentUser());
		List<Contest> same = myController.getJudgableContests(myController.getCurrentUser());
		assertTrue("Judgable Contest should be the same.", contests.equals(same));
	}
	
	@Test
	public void judgeEntryTest()
	{
		List<Contest> contests = myController.getJudgableContests(myController.getCurrentUser());
		if (contests.size() > 0)
		{
//			myController
		}
		else
		{
			System.out.println("No contests to judge");
		}
	}
	
	

}
