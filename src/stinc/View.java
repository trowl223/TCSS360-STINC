package stinc;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Contest;
import model.Entry;
import view.AdminPanel;
import view.ContestScroller;
import view.EntryScroller;
import view.HomePanel;
import view.JudgePanel;
import view.LoginPanel;
import view.UploadPanel;

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
	
	/*public void testContentScroller()
	{
//		System.out.print();
		List<Contest> contests = myController.getElegibleContests(myController.getCurrentUser());
		System.out.println(contests);
		getContentPane().removeAll();
		add(new ContestScroller(contests, myController));
		pack();
		setVisible(true);
	}*/
	
	/**
	 * Instantiates a HomePanel and adds it to the view's primary content panel.
	 */
	public void showHomePage() {
		Container pane = getContentPane();
		
		pane.removeAll();
		
		// Add the back button
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				showHomePage();
			}
		});
		backButton.setEnabled(false);
		pane.add(backButton, BorderLayout.NORTH);
		
		// Add ContestScroller
		ContestScroller someContests;
		EntryScroller someEntries = null;
		
		if (myController.getCurrentUser().isJudge()) {
			List<Contest> judgableContests = myController.getJudgableContests(myController.getCurrentUser()); 
			someContests = new ContestScroller(judgableContests, myController);
		} 
		else if (myController.getCurrentUser().isAdmin()) {
			List<Contest> contests = myController.getContests();
			someContests = new ContestScroller(contests, myController);
		}
		else {
			List<Contest> elegibleContests = myController.getElegibleContests(myController.getCurrentUser());
			List<Entry> entries = myController.getEntries(myController.getCurrentUser());
			
			someContests = new ContestScroller(elegibleContests, myController);
			someEntries = new EntryScroller(entries, myController);
		}
		//TODO add third scroller with "featured" contests

		
		pane.add(new HomePanel(myController, someContests, someEntries), BorderLayout.CENTER);
		setVisible(true);
//		setPreferredSize(new Dimension(500,300));
		
		pack();
		setVisible(true);
	}
	
	/**
	 * Instantiates an UploadPanel and adds it to the View's primary content panel.
	 * @param theContest The contest that will be uploaded to.
	 */
	public void showUploadPanel(Contest theContest) {
		Container pane = getContentPane();
		pane.removeAll();

		// Add the back button
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				showHomePage();
			}
		});
		pane.add(backButton, BorderLayout.NORTH);
		
		
		UploadPanel upload = new UploadPanel(theContest, myController);
		upload.setup();
		add(upload, BorderLayout.CENTER);
		
		pack();
		setVisible(true);
	}
	
	/**
	 * Instantiates an Admin Panel and adds it to the View's primary content panel.
	 * @param theContest The contest to be administrated.
	 */
	public void showAdminPanel(Contest theContest) {
		Container pane = getContentPane();
		pane.removeAll();

		// Add the back button
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				showHomePage();
			}
		});
		pane.add(backButton, BorderLayout.NORTH);
		 
		
		add(new AdminPanel(theContest, myController), BorderLayout.CENTER);
		
		pack();
		setVisible(true);
	}

	/**
	 * Instantiates a JudgePanel and adds it to the View's primary content panel.
	 * @param theContest The Contest to be judged.
	 */
	public void showJudgePanel(Contest theContest) {
		Container pane = getContentPane();
		pane.removeAll();
		

		// Add the back button
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				showHomePage();
			}
		});
		pane.add(backButton, BorderLayout.NORTH);
		
		add(new JudgePanel(myController, theContest));
		
		pack();
		setVisible(true);
	}
}
