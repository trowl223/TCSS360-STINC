import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * @author igorgonchar
 * The login Panel allows a user to login to the system. 
 * It will check if the user has valid credentials and allow/deny access accordingly.
 */

@SuppressWarnings("serial")
public class LoginPanel extends JPanel {
	
	private JTextField username;		//Field for username
	private JPasswordField password;	//Field for password
	private JButton submit;				//Login submit button
	
	/** 
	 * Creates a new Login Panel
	 * @return 
	 */
	public LoginPanel() {
		super();
		username = new JTextField();
		password = new JPasswordField();
		submit = new JButton("Login");
		create();
	}
	
	/**
	 * Setups the Login Panel
	 */
	private void create() {
		GridLayout grid = new GridLayout(3,2);
		this.setLayout(grid);
		this.setBackground(Color.WHITE);
		
		add(new JLabel("Username/Library Card #:", SwingConstants.LEFT), grid);
		username.setPreferredSize(new Dimension(1, 12));
		add(username,grid);
		
		add(new JLabel("Password:", SwingConstants.LEFT), grid);
		add(password, grid);
		
		LoginAction();
		add(submit, grid);
	}

	/**
	 * Action Listener method for login 
	 */
	private void LoginAction() {
		submit.addActionListener(new ActionListener() {


			public void actionPerformed(ActionEvent e) {
				if (username.getText().isEmpty() || password.getPassword().length == 0) {
					JOptionPane.showMessageDialog(null, "All fields have not been entered.", "Oops! Please Try Again", JOptionPane.ERROR_MESSAGE);
				
				} else if (username.getText().equalsIgnoreCase("User") && Arrays.equals(password.getPassword(), new char[]{'u','s','e','r','p','w','d'})) {
					JOptionPane.showMessageDialog(null, "Logged in as a USER!", "Success!", JOptionPane.PLAIN_MESSAGE);
					
				} else if (username.getText().equalsIgnoreCase("Judge") && Arrays.equals(password.getPassword(), new char[]{'j','u','d','g','e','p','w','d'})) {
					JOptionPane.showMessageDialog(null, "Logged in as a Judge!", "Success!", JOptionPane.PLAIN_MESSAGE);
				
				} else if (username.getText().equalsIgnoreCase("Admin") && Arrays.equals(password.getPassword(), new char[]{'a','d','m','i','n','p','w','d'})) {
					JOptionPane.showMessageDialog(null, "Logged in as an Admin!", "Success!", JOptionPane.PLAIN_MESSAGE);
				
				} else {
					JOptionPane.showMessageDialog(null, "Incorrect Login.", "Oops! Please Try Again", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
}
