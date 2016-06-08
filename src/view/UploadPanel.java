package view;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import model.Contest;
import model.Entry;
import stinc.Controller;

@SuppressWarnings("serial")
public class UploadPanel extends JPanel {

	private final Contest myContest;
	private final Controller myController;
	private JTextField subTitleBox;
	private JTextArea subDescBox;
	private JTextField subUrlBox;
	
	public UploadPanel(Contest theContest, Controller theController) {
		myContest = theContest;
		myController = theController;
	}
	
	public void setup() {
		setLayout(new GridBagLayout());
		
		GridBagConstraints left = new GridBagConstraints();
		left.fill = GridBagConstraints.HORIZONTAL;
		left.gridx = 0;
		left.gridy = 0;
		left.insets = new Insets(3, 3, 3, 3);
		left.anchor = GridBagConstraints.NORTHEAST;
		
		GridBagConstraints right = new GridBagConstraints();
		right.fill = GridBagConstraints.HORIZONTAL;
		right.gridx = 1;
		right.gridy = 0;
		right.insets = new Insets(3, 3, 3, 3);
		
		// Row 0 Contest Name
		add(new JLabel(myContest.getName()), left);
		
		
		// Row 1 Contest Description
		left.gridy++;
		right.gridy++;
		add(new JLabel("Contest Description:", JLabel.RIGHT), left);
		JTextArea contestDesc = new JTextArea(myContest.getDescription());
		contestDesc.setEditable(false);
		contestDesc.setWrapStyleWord(true);
		contestDesc.setLineWrap(true);
		contestDesc.setSize(300, 1);
		add(contestDesc, right);
		
		// Row 2 Contest Rules
		left.gridy++;
		right.gridy++;
		add(new JLabel("Contest Rules:", JLabel.RIGHT), left);
		JTextArea contestRules = new JTextArea("[Contest rules go here]");
		contestRules.setEditable(false);
		contestRules.setWrapStyleWord(true);
		contestRules.setLineWrap(true);
		contestRules.setSize(300, 1);
		add(contestRules, right);
		
		// Row 3 Submission title
		left.gridy++;
		right.gridy++;
		add(new JLabel("Submission Title:", JLabel.RIGHT), left);
		subTitleBox = new JTextField();
		add(subTitleBox, right);
		
		// Row 4 Sumbission description
		left.gridy++;
		right.gridy++;
		add(new JLabel("Submission Description:", JLabel.RIGHT), left);
		subDescBox = new JTextArea();
		makeTabChangeFocus(subDescBox);
		// TODO prevent tabs from making characters
		// TODO make multiple lines
		add(subDescBox, right);
		
		// Row 4 URL
		left.gridy++;
		right.gridy++;
		add(new JLabel("Submission URL:", JLabel.RIGHT), left);
		subUrlBox = new JTextField();
		add(subUrlBox, right);
		
		// TODO Ommiting "I agree" button for later sprint 
		
		// Row 5 Submission button
		left.gridy++;
		right.gridy++;
		JButton submitButton = new JButton("Submit");
		submitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("User clicked Submit");
				submit();
			}
			
		});
		add(submitButton, right);
	}
	
	private void submit() {
		String errorText = "";
		
		String name = subTitleBox.getText();
		if (name.length() == 0) 
			errorText += "Please give your submission a title.\n";
		
		String desc = subDescBox.getText();
		if (desc.length() == 0)
			errorText += "Please write a description for your submission.\n";
		
		String url = subUrlBox.getText();
		if (url.length() == 0)
			errorText += "Please enter a URL for your submission.\n";
		else {
			try {
				URL testingUrl = new URL(url);
			} catch (MalformedURLException e) {
				errorText += "The URL for your submission has something wrong.\n";
			} 
		}
		
		
		if (errorText.length() == 0) {
			Entry entry = new Entry(
					name,
					desc, 
					url
			);
			
			if (myController.addEntry(myContest, entry)) {
				JOptionPane.showMessageDialog(null,  "Your entry has been submitted.", "Submission successful", JOptionPane.PLAIN_MESSAGE);
				myController.showContentFrame();
			}
			else {
				JOptionPane.showMessageDialog(null, "An error prevented the submission from completing.\nPlease talk to the database administrator.", "Database Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		else {
			JOptionPane.showMessageDialog(null, errorText, "Submission Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
     * Patch the behaviour of a component. 
     * TAB transfers focus to the next focusable component,
     * SHIFT+TAB transfers focus to the previous focusable component.
     * 
     * @param c The component to be patched.
     */
	private static void makeTabChangeFocus(Component c) {
		Set<KeyStroke> 
        strokes = new HashSet<KeyStroke>(Arrays.asList(KeyStroke.getKeyStroke("pressed TAB")));
        c.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, strokes);
        strokes = new HashSet<KeyStroke>(Arrays.asList(KeyStroke.getKeyStroke("shift pressed TAB")));
        c.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, strokes);
	}

}
