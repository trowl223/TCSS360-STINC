import java.util.ArrayList;
import java.util.List;

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

}
