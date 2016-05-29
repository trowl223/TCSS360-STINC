import model.Contest;

/**
 * Initializes MVC artitecture.
 * @author Nicholas Mousel
 * @version 5/24/2016
 */
public class Main
{
    /**
     * The main method.
     * @param args the Arguments to use.
     */
	public static void main(String[] args)
    {
	    // MVC artitecture based on http://www.austintek.com/mvc/
        // Create Model and View
		Model myModel = new Model();
		View myView = new View();

		// Tell Model about View. 
		myModel.addObserver(myView);
		
		// Create some dummy contests
		createDummyData(myModel);

		// Create Controller. tell it about Model and View, initialize model
		Controller myController = new Controller();
		myController.addModel(myModel);
		myController.addView(myView);
		myController.initModel();
		
		// Tell View about Controller 
		myView.addController(myController);
		
		// Start up the view
		myView.start();
	}

	/**
	 * Create two dummy contests for testing ContestScroller.
	 * 
	 * Delete this once we get some real data from the database.
	 * 
	 * @author stefan
	 * @param theModel The model to add the contests to
	 */
	private static void createDummyData(Model theModel) {
		Contest c1 = new Contest(
				"Jurrasic Park", 
				"desc", 
				"https://upload.wikimedia.org/wikipedia/en/9/96/Jurassic_Park_logo.jpg"
		);
		theModel.addContest(c1);
		
		Contest c2 = new Contest(
				"Ghost Busters", 
				"desc", 
				"http://www.thelogofactory.com/logo_blog/wp-content/uploads/2014/08/ghostbusters.png"
		);
		theModel.addContest(c2);
	}

}