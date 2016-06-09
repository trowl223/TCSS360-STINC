package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.Contest;
import model.Entry;
import model.User;
import stinc.Controller;

/**
 * @author igorgonchar
 * This panel will allow the 'Admin' to accept/reject contest entries
 */
@SuppressWarnings("serial")

public class AdminPanel extends JPanel {
	
	private static final int IMAGE_WIDTH = 100;
	private static final int IMAGE_HEIGHT = 100;
	private static final int DEFAULT_HEIGHT = IMAGE_HEIGHT + 200;
	private static final int DEFAULT_WIDTH = 800;
	private final Contest myContest;
	private final Controller myController;

	
	/**
	 * Creates a new Admin Panel
	 */
	public AdminPanel(Contest theContest, Controller theController) {
		super();
		myController = theController;
		myContest = theContest;
		
		JPanel container = new JPanel();
		container.setSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		create(container);
	}

	/**
	 * Create the ScrollPanel 
	 */
	private void create(JPanel container) {
		
		JScrollPane scrPane = new JScrollPane(container);
		add(scrPane, BorderLayout.CENTER); 
		
		scrPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrPane.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		
		container.setBackground(Color.WHITE);
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		
		container.add(new JLabel("This panel allows you to Accept/Reject entries."), BorderLayout.NORTH);
		
		if (myController.getContestEntries(myContest).isEmpty()) {
			container.add(new JLabel("This contest does not contain any entries."), BorderLayout.CENTER);
		} else {
			for (Entry e : myController.getContestEntries(myContest)) {
				if(!e.getRejected())
					container.add(populate(e));
			}
		}
		
		JButton Submit = new JButton("Submit");
		container.add(Submit, BorderLayout.AFTER_LAST_LINE);
		Submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent action){
				
				//TODO Update Database when SUMBIT is pressed
				myController.showHomePage();
			}
		});
	
	}
	
	/**
	 * Fills in the ScrollPanel with Entries/GUI
	 * @return myContent
	 */
	public Component populate(final Entry e) {
		
			final JTextArea Comment = new JTextArea("Enter Rejection Comments Here...");
			final JButton Reject = new JButton("Reject");
			final JButton Accept = new JButton("Accept");
			
			Accept.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent action) {
					Reject.setEnabled(false);
					e.setRejected(false);
				}
			});
			
			Reject.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent action) {
					if (Comment.getText().equals("Enter Rejection Comments Here...") || Comment.getText().equals("Please Enter Rejection Comments\n AND\n Press REJECT Again\n") || Comment.getText().isEmpty())
						Comment.setText("Please Enter Rejection Comments\n AND\n Press REJECT Again\n");
					
					else{
						Accept.setEnabled(false);
						e.setComment(Comment.getText());
						e.setRejected(true);
					}	
				}
			});
			
			JPanel myContent = new JPanel();
			Image image = ImageFetcher.fetchImage((e.getSubmissionPath()), IMAGE_WIDTH, IMAGE_HEIGHT);
			myContent.add(new JLabel(new ImageIcon(image)), BorderLayout.LINE_START);
			
			Comment.setPreferredSize(new Dimension(300, 75));
			myContent.add(Comment, BorderLayout.CENTER);

			myContent.add(Accept, BorderLayout.LINE_END);
			myContent.add(Reject, BorderLayout.PAGE_END);
		
		return myContent;
	}
}
