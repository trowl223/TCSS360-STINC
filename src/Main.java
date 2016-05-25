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

		// Create Controller. tell it about Model and View, initialize model
		Controller myController = new Controller();
		myController.addModel(myModel);
		myController.addView(myView);
		myController.initModel();
		
		// Tell View about Controller 
		myView.addController(myController);
		myView.test();
	}

}