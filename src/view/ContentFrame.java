package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import stinc.Controller;

public class ContentFrame extends JFrame {

	private JPanel myContentPane;
	
	private Controller myController;
	
	private int myContestID;
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ContentFrame frame = new ContentFrame();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public ContentFrame(Controller theController) {
		super();
		myController = theController;

	}
	

	
	public void loadUtility(int aContestID, int aUserType) {
		myContestID = aContestID;
		if (aUserType == 0) {//User:upload
//			myContentPane = new UploadPanel(aContestID);
		} else if(aUserType == 1) {//Judge:score
//			myContentPane = new JudgePanel(aContestID);
		} else {//Admin:administrate
//			myContentPane = new AdminPanel(aContestID);
		}
	}
	
	public void loadHome() {
//		myContentPane = new HomePanel();
	}
	
	public void loadLogin() {
//		myContentPane = new loginPanel();
	}
}
