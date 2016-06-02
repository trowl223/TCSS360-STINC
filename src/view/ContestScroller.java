package view;



import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import model.Contest;
import stinc.Controller;

@SuppressWarnings("serial")
/**
 * The ContestScroller is a horizontal scroll box for displaying contests.
 * @author stefan
 * 
 */
public class ContestScroller extends JPanel {
	
	private static final int IMAGE_WIDTH = 200;
	private static final int IMAGE_HEIGHT = 200;
	private static final int DEFAULT_HEIGHT = IMAGE_HEIGHT + 55;
	private static final int DEFAULT_WIDTH = 700;
	private final List<Contest> myContests;
	private final Controller myController;
	
	private final HashMap<Contest,JPanel> myContestBoxes = new HashMap<Contest, JPanel>();

	/**
	 * Construct a ContestScroller from a list of Contests.
	 * @param theContests the list of Contests.
	 */
	public ContestScroller(List<Contest> theContests, Controller theController) {
		myContests = theContests;
		myController = theController;
		
		setLayout(new BorderLayout());
		
		JPanel contents = new JPanel();
		
		for(final Contest c : myContests) {
			JPanel contestBox = new JPanel();
			contestBox.setLayout(new BorderLayout());
			contents.add(contestBox);
			
			JLabel nameLabel = new JLabel(c.getName());
			nameLabel.setHorizontalAlignment(JLabel.CENTER);
			contestBox.add(nameLabel, BorderLayout.SOUTH);
			
			Image image = ImageFetcher.fetchImage(c.getImageURL(), IMAGE_WIDTH, IMAGE_HEIGHT);
			if (image != null) {
				JButton imageButton = new JButton(new ImageIcon(image));
				imageButton.setBorderPainted(false);
				imageButton.setContentAreaFilled(false);
				imageButton.setFocusPainted(false);
//				imageButton.setOpaque(false);
				
				imageButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						showContestDetails(c);
					}
					
				});
				
				contestBox.add(imageButton, BorderLayout.CENTER);
			}
			
			myContestBoxes.put(c, contestBox);
		}
		
		
		JScrollPane scrollPane = new JScrollPane(contents);
		
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		
		add(scrollPane, BorderLayout.CENTER);
		
		
	}
	
	private Contest mySelectedContest = null;
	
	private void showContestDetails(Contest theContest) {
		// If the user clicked the same contest again, do nothing.
		if (theContest == mySelectedContest)
			return;
		
		// If a contest was previously clicked, remove its details panel.
		if (mySelectedContest != null) {
			JPanel oldContestBox = myContestBoxes.get(mySelectedContest);
			for(Component c : oldContestBox.getComponents()) {
				if (c instanceof JPanel) {
					oldContestBox.remove(c);
					break;
				}
			}
		}
		
		// Add the details panel for the contest which was clicked.
		JPanel contestBox = myContestBoxes.get(theContest);
		
		JPanel detailsPanel = new JPanel(new BorderLayout());
		detailsPanel.setPreferredSize(new Dimension(150, 200));
		
		detailsPanel.add(new JLabel("Details"), BorderLayout.NORTH);
		
		
		JTextArea details = new JTextArea(theContest.getDescription());
		detailsPanel.add(details, BorderLayout.CENTER);
		
		JButton openButton = new JButton("Open");
		openButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				myController.showContest(mySelectedContest);
			}
			
		});
		detailsPanel.add(openButton, BorderLayout.SOUTH);
		
		contestBox.add(detailsPanel, BorderLayout.EAST);
		
		mySelectedContest = theContest;
		
		revalidate();
	}

}

















