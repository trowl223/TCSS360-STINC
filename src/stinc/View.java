package stinc;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLabel;

import model.Contest;
import model.Entry;
import stinc.view.UploadPanel;
import view.ContestScroller;
import view.EntryScroller;
import view.JudgePanel;
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
//		add(new ContentFrame(myController));

//		
		add(new LoginPanel(myController));
//		myController.addEntry(new Entry(theName, theDescription, theSubmissionPath))
//		List<Entry> es = new ArrayList<>();
//		es.add(new Entry("BANANAS", "ARE CRAZY", "http://www.thelogofactory.com/logo_blog/wp-content/uploads/2014/08/ghostbusters.png"));
//		es.add(new Entry("BANANAS", "ARE CRAZY", "http://www.thelogofactory.com/logo_blog/wp-content/uploads/2014/08/ghostbusters.png"));
//		es.add(new Entry("BANANAS", "ARE CRAZY", "http://www.thelogofactory.com/logo_blog/wp-content/uploads/2014/08/ghostbusters.png"));
//
//		JudgePanel jp = new JudgePanel(myController, new Contest("a", "a"));
//		add(jp);
//		jp.populate(es);
		pack();
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
		ContestScroller someContests;
		EntryScroller someEntries = null;
		
		if (myController.getCurrentUser().isJudge()) {
			//theScrollers[0] = new ContestScroller(myController.getJudgableContests(myController.getCurrentUser()), myController);
			someContests = new ContestScroller(someContests(), myController);
		} else {
			someContests = new ContestScroller(myController.getElegibleContests(myController.getCurrentUser()), myController);
			someEntries = new EntryScroller(myController.getContestsEntered(myController.getCurrentUser()), myController);
		}
		//TODO add third scroller with "featured" contests

		
		
		
		
		
		getContentPane().removeAll();
		//
		add(new HomePanel(myController, someContests, someEntries));
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
		
		UploadPanel upload = new UploadPanel(theContest, myController);
		upload.setup();
		add(upload);
		
		pack();
		setVisible(true);
	}

	public void showAdminPanel(Contest theContest) {
		getContentPane().removeAll();

		// TODO Add AdminPanel
		add(new JLabel("Admin Panel"));
//		add(new AdminPanel(theContest, myController));
		
		pack();
		setVisible(true);
	}

	public void showJudgePanel(Contest theContest) {
		getContentPane().removeAll();
		// TODO Add JudgePanel
		add(new JLabel("Judge Panel"));
//		add(new JudgePanel(theContest, myController));
		
		pack();
		setVisible(true);
	}
}
