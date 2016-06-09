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
	 * @param theContestID the ID of the Contest to remove.
	 * @return true if succeeds, false otherwise.
	 */
	public boolean removeContest(int theContestID)
	{
		return myModel.removeContest(theContestID);
	}
	
	/**
	 * Gets a list of all the Contests
	 * @return a list of all Contests
	 */
	public List<Contest> getContests()
	{
		return myModel.getContests();
	}
	
	/**
	 * Gets the Entries a User can judge.
	 * @param theUser to query entries for.
	 * @return Contests the User can judge.
	 */
	public List<Entry> getNotJudgedEntries(int theContest, User theUser)
	{
		return myModel.getUnjudgedEntries(theContest, theUser);
	}
	
	/**
	 * Gets all the entries for a Contest
	 * @param theContest to search for Entries.
	 * @return list of Entry.
	 */
	public List<Entry> getContestEntries(Contest theContest) {
		return myModel.getContestEntries(theContest);
	}
	/**
	 * Gets the Contests the User is able to enter
	 * @param theUser to query for.
	 * @return List of Contests the User is able to enter.
	 */
	public List<Contest> getElegibleContests(User theUser)
	{
		return myModel.getElegibleContests(theUser);
	}
	
	/**
	 * Gets the List of Entry the User has submitted.
	 * @param theUser to query Entry for.
	 * @return list of User submitted Entry.
	 */
	public List<Entry> getEntries(User theUser)
	{
		return myModel.getEntries(theUser);
	}
	
	/**
	 * Gets the Contests a User can judge.
	 * @param theUser to query contests for.
	 * @return Contests the User can judge.
	 */
	public List<Contest> getJudgableContests(User theUser) {
		
		return myModel.getJudgableContests(theUser);
	}
	/**
	 * Adds an Entry to a Contest.
	 * @param theContest to add Entry to.
	 * @param theEntry to add.
	 * @return true if successful, false otherwise.
	 */
	public boolean addEntry(int theContestID, Entry theEntry)
	{
		theEntry.setUser(getCurrentUser());
		theEntry.setDate(new Date());
		theEntry.setContestID(theContestID);
		
		return myModel.addEntry(theEntry);
	}
	/**
	 * Remove an Entry.
	 * @param theEntryID the ID of the Entry to remove.
	 * @return true if successful, false otherwise.
	 */
	public boolean removeEntry(int theEntryID)
	{
		return myModel.removeEntry(theEntryID);
	}
	
	/**
	 * Judge an Entry with a score
	 * @param theEntryID the Entry ID to judge
	 * @param theUser the User judging the Entry
	 * @param theScore the score of the Entry
	 * @param theComments the comments for the Entry
	 * @return true if successful, false otherwise.
	 */
	public boolean judgeEntry(User theUser, int theEntryID, int theScore, String theComments)
	{
		return myModel.judgeEntry(theUser, theEntryID, theScore, theComments);
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
	 * Show the contests for the respective panel
	 * @param theContest to be shown.
	 */
	public void showContest(Contest theContest) {
		// TODO Tell the view to load this contest
		System.out.println("Stub: Load ContestView for " + theContest.getName());
		//myView.showUploadPanel(theContest);
		if (myModel.getCurrentUser().isAdmin())
		{
			myView.showAdminPanel(theContest);
		} 
		else if(myModel.getCurrentUser().isJudge())
		{
			myView.showJudgePanel(theContest);
		} 
		else 
		{
			myView.showUploadPanel(theContest);
		}
	}
	
	/**
	 * Display the HomePanel on the contentFrame
	 */
	public void showHomePage()
	{
		myView.showHomePage();
	}
	
	/**
	 * Logins the user into the program.
	 * @param theUsername for the user.
	 * @param thePassword for the user.
	 * @return true if success, false otherwise
	 */
	public boolean login(String theUsername, String thePassword) 
	{
		return myModel.login(theUsername, thePassword);
	}
	
	/**
	 * Gets the logged in user.
	 * @return current logged in user.
	 */
	public User getCurrentUser() {
		return myModel.getCurrentUser();
	}
	
	/**
	 * Updates the specified rejected entry.
	 * @param anEntry A rejected entry whose rejected status must
	 *  be updated in the database.
	 */
	public void updateRejected(Entry anEntry) {
		myModel.updateRejected(anEntry);
	}

}
