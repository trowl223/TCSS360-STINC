package stinc;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import model.Contest;
import model.Entry;
import stinc.view.UploadPanel;
import view.ContestScroller;
import view.JudgePanel;
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

	public void showUploadPanel(Contest theContest) {
		getContentPane().removeAll();
		
		add(new UploadPanel(theContest, myController));
		
		pack();
		setVisible(true);
	}
}
