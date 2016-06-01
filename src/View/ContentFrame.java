import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ContentFrame extends JFrame {

	private JPanel myContentPane;
	
	private Controller myController;
	
	private int myContestID;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ContentFrame frame = new ContentFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ContentFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}
	
	public Contest getContest(int aContestID) {
		myController.getContest(aContestID);
		
		return new Contest();//some contest info
		
	}
	
	public void addController(Controller aController) {
		if (myController == null) {
			myController = aController;
		}
	}
	
	public void loadUtility(int aContestID, int aUserType) {
		myContestID = aContestID;
		if (aUserType == 0) {//User:upload
			myContentPane = new UploadPanel(aContestID);
		} else if(aUserType == 1) {//Judge:score
			myContentPane = new JudgePanel(aContestID);
		} else {//Admin:administrate
			myContentPane = new AdminPanel(aContestID);
		}
	}
	
	public void loadHome() {
		myContentPane = new HomePanel();
	}
	
	public void loadLogin() {
		myContentPane = new loginPanel();
	}
}
