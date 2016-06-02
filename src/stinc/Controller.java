package stinc;

import java.util.Date;
import java.util.List;

import model.Contest;
import model.Entry;
import model.User;

/**
 * The controller for the View and Model
 * @author Nicholas Mousel
 * @version 5/24/2016
 */
public class Controller {

	private Model myModel;
	private View myView;
	
	public Controller() 
	{
	}
	
	/**
	 * Adds a Contest to the model.
	 * @param theContest the Contest to add.
	 * @return true if succeeds, false otherwise
	 */
	public boolean addContest(Contest theContest)
	{
		return myModel.addContest(theContest);
	}
	
	/**
	 * Removes a Contest from the model.
	 * @param contestID the ID of the Contest to remove.
	 * @return true if succeeds, false otherwise.
	 */
	public boolean removeContest(int theContestID)
	{
		return myModel.removeContest(theContestID);
	}
	
	public Contest getContest(int theContestID)
	{
		return myModel.getContest(theContestID);
	}
	
	public List<Contest> getCurrentContests()
	{
		return myModel.getContests();
	}
	
	public List<Contest> getElegibleContests(User theUser)
	{
		return myModel.getElegibleContests(theUser);
	}
	
	public List<Contest> getContestsEntered(User theUser)
	{
		return myModel.getContestsEntered(theUser);
	}
	
	public boolean addEntry(Contest theContest, Entry theEntry)
	{
		theEntry.setUser(getCurrentUser());
		theEntry.setDate(new Date());
		theEntry.setContestID(theContest.getID());
		return myModel.addEntry(theEntry);
	}
	
	public boolean removeEntry(int theEntryID)
	{
		return myModel.removeEntry(theEntryID);
	}
	
	public Entry getEntry(int theEntryID)
	{
		return myModel.getEntry(theEntryID);
	}
	
	public boolean addJudge(int theContestID, User theUser)
	{
		if (theUser != null)
		{
			Contest c = myModel.getContest(theContestID);
			if (c != null)
			{
				return c.addJudge(theUser);
			}
		}
		return false;
	}
	
	public boolean removeJudge(int theContestID, User theUser)
	{
		if (theUser != null)
		{
			Contest c = myModel.getContest(theContestID);
			if (c != null)
			{
				if (c.canJudge(theUser))
				{
					return c.removeJudge(theUser);
				}
			}
		}
		return false;
	}
	
	public boolean judgeEntry(int theContestID, int theEntryID, User theUser, int theScore)//change this to judge id, entry id and score
	{
		return myModel.judgeEntry(theContestID, theEntryID, theUser, theScore);
	}
	
	/**
	 * Adds the model to the controller.
	 * @param theModel the Model to add.
	 */
	public void addModel(Model theModel) {
		myModel = theModel;
		
	}
	
	/**
	 * Adds the view to the controller.
	 * @param theView the View to add.
	 */
	public void addView(View theView) {
		myView = theView;
		
	}
	/**
	 * Initializes the model.
	 */
	public void initModel() {
		// TODO CHRIS' SHIT GOES HERE
		
	}
	
	public void showContest(Contest theContest) {
		// TODO Tell the view to load this contest
		System.out.println("Stub: Load ContestView for " + theContest.getName());
		myView.showUploadPanel(theContest);
	}
	
	public void showContentFrame()
	{
		myView.testContentScroller();
		
	}
	
	/**
	 * displays the HomePanel on the contentFrame
	 */
	public void showHomePage() {
		myView.setupHomePage();
	}

	public boolean login(String theUsername, String thePassword) 
	{
		return myModel.login(theUsername, thePassword);
	}

	public User getCurrentUser() {
		return myModel.getCurrentUser();
	}

}
