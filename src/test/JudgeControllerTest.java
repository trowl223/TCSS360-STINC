package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.Contest;
import model.Entry;
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
	
	/**
	 * Sets up the Judge Controller.
	 * @throws Exception if set up is unsuccessful.
	 */
	@Before
	public void setUp() throws Exception {
		Model myModel = new Model();
		myController = new Controller();
		myController.addModel(myModel);
		myController.login(myJudgeUser, myJudgePass);
	}
	/**
	 * Test judge login.
	 */
	@Test
	public void loginJudgeTest()
	{
		assertTrue("Judge was not able to login.", myController.login(myJudgeUser, myJudgePass));
		assertTrue("Judge login is not an judge", myController.getCurrentUser().isJudge());
	}
	/**
	 * Test the judge getting contests they are eligible for.
	 */
	@Test
	public void getJudgableContestsTest()
	{
		List<Contest> contests = myController.getJudgableContests(myController.getCurrentUser());
		List<Contest> same = myController.getJudgableContests(myController.getCurrentUser());
		assertTrue("Judgable Contest should be the same.", contests.equals(same));
	}
	
	/**
	 * Test judging a entry in a contest.
	 */
	@Test
	public void judgeEntryTest()
	{
		List<Contest> contests = myController.getJudgableContests(myController.getCurrentUser());
		if (contests.size() > 0)
		{
			List<Entry> contestEntries = myController.getUnjudgedEntries(contests.get(0).getID(), myController.getCurrentUser());
			if (contestEntries.size() > 0)
			{
				Entry testScore = contestEntries.get(0);
				assertTrue("The judge was unable to judge the contest.",
							myController.judgeEntry(myController.getCurrentUser(), testScore.getID(), 99, ""));
				
				contestEntries = myController.getContestEntries(contests.get(0));
				for (Entry e : contestEntries)
				{
					if(e.getID() == testScore.getID())
					{
						System.out.println(e.getScore());
						assertTrue(e.getScore() == 99);
					}
				}
			}
			else
			{
				fail("This contest has no Entries to judge.");
			}
		}
		else
		{
			fail("No contests to judge");
		}
	}
	
	

}
