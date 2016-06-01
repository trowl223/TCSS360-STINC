package stinc;

import java.awt.BorderLayout;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import model.Contest;
import view.ContentFrame;
import view.ContestScroller;
import view.LoginPanel;
import stinc.Controller;

/**
 * The View is the GUI model.
 * @author Nicholas
 * @version 5/24/2016
 */
@SuppressWarnings("serial")
public class View extends JFrame implements Observer
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
	 * Adds the controller to the view.
	 * @param theController the Controller to add.
	 */
	public void addController(Controller theController)
	{
		myController = theController;
	}
	
	/**
	 * Show the View to the user.
	 */
	public void start() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
//		setLayout(new BorderLayout());

//		ContestScroller scroller = new ContestScroller(myController.getCurrentContests(), myController);
//		add(new LoginPanel(myController));
//		testContentScroller();
		add(new LoginPanel(myController));
//		add(new ContentFrame(myController));
		pack();
//		
		setVisible(true);
	}
	

	public void showContentFrame() {
		removeAll();
//		add(new ContentFrame());	
	}
	
	public void testContentScroller()
	{
		List<Contest> contests = myController.getElegibleContests(myController.getCurrentUser());
		add(new ContestScroller(contests, myController));
	}
}
