import java.util.Observable;
import java.util.Observer;
/**
 * The View is the GUI model.
 * @author Nicholas
 * @version 5/24/2016
 */
public class View implements Observer
{
	/**
	 * The controller instance.
	 */
	private Controller myController;
	/**
	 * Constructs the view.
	 */
	public View() 
	{
		myController = null;
	}
	
	@Override
	public void update(Observable o, Object arg)
	{
		 System.out.println("View Update called");
	}
	
	/**
	 * Ignore this for now just a test
	 */
	public void test()
	{
		myController.addContest(new Contest());
	}
	
	/**
	 * Adds the controller to the view.
	 * @param theController the Controller to add.
	 */
	public void addController(Controller theController)
	{
		myController = theController;
	}
}
