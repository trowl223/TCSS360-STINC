package stinc;


import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import model.Contest;
import stinc.view.UploadPanel;
import view.ContestScroller;
import view.HomePanel;
import view.LoginPanel;

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
//		System.out.print();
		List<Contest> contests = myController.getElegibleContests(myController.getCurrentUser());
		System.out.println(contests);
		getContentPane().removeAll();
		add(new ContestScroller(contests, myController));
		pack();
		setVisible(true);
	}
	
	/**
	 * 
	 */
	public void showHomePage() {
		ContestScroller[] theScrollers = new ContestScroller[3];
		theScrollers[0] = new ContestScroller(someContests(), myController);
		theScrollers[1] = new ContestScroller(someContests(), myController);
		theScrollers[2] = new ContestScroller(someContests(), myController);
		
		getContentPane().removeAll();
		//
		add(new HomePanel(myController, theScrollers));
		pack();
		setVisible(true);
		
	}
	
	private List<Contest> someContests() {
		List<Contest> retList = new ArrayList<Contest>();
		Contest c1 = new Contest(
				"Jurrasic Park", 
				"desc", 
				"https://upload.wikimedia.org/wikipedia/en/9/96/Jurassic_Park_logo.jpg"
		);
		retList.add(c1);
		
		Contest c2 = new Contest(
				"Ghost Busters", 
				"desc", 
				"http://www.thelogofactory.com/logo_blog/wp-content/uploads/2014/08/ghostbusters.png"
		);
		retList.add(c2);
		return retList;
	}

	public void showUploadPanel(Contest theContest) {
		getContentPane().removeAll();
		
		add(new UploadPanel(theContest, myController));
		
		pack();
		setVisible(true);
	}

	public void showAdminPanel(Contest theContest) {
		getContentPane().removeAll();
		
		add(new AdminPanel(theContest, myController));
		
		pack();
		setVisible(true);
	}

	public void showJudgePanel(Contest theContest) {
		getContentPane().removeAll();
		
		add(new JudgePanel(theContest, myController));
		
		pack();
		setVisible(true);
	}
}
