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

import model.Entry;
import stinc.Controller;

@SuppressWarnings("serial")
/**
 * The ContestScroller is a horizontal scroll box for displaying contests.
 * @author taylor
 * Majority of code copied over from original author Stefan Titus' ContestScroller class.
 * 
 */
public class EntryScroller extends JPanel {
	
	private static final int IMAGE_WIDTH = 200;
	private static final int IMAGE_HEIGHT = 200;
	private static final int DEFAULT_HEIGHT = IMAGE_HEIGHT + 55;
	private static final int DEFAULT_WIDTH = 700;
	private List<Entry> myEntries;
	private final HashMap<Entry,JPanel> myEntryBoxes = new HashMap<Entry, JPanel>();

	/**
	 * Construct an EntryScroller from a list of Entries
	 * @param theContests The list of entries.
	 * @param theController The controller of the View.
	 */
	public EntryScroller(List<Entry> theEntries, Controller theController) {
		myEntries = theEntries;
		setLayout(new BorderLayout());
		
		JPanel contents = new JPanel();
		
		for(final Entry c : myEntries) {
			JPanel contestBox = new JPanel();
			contestBox.setLayout(new BorderLayout());
			contents.add(contestBox);
			
			JLabel nameLabel = new JLabel(c.getName());
			nameLabel.setHorizontalAlignment(JLabel.CENTER);
			contestBox.add(nameLabel, BorderLayout.SOUTH);
			
			Image image = ImageFetcher.fetchImage(c.getSubmissionPath(), IMAGE_WIDTH, IMAGE_HEIGHT);
			
			// image will only be null if the real image and the placeholder image both fail to load
			if (image == null) {
				JButton notImageButton = new JButton(c.getName());

				notImageButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						showContestDetails(c);
					}
					
				});
				contestBox.add(notImageButton, BorderLayout.CENTER);
			}
			else {
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
			
			myEntryBoxes.put(c, contestBox);
		}
		
		
		JScrollPane scrollPane = new JScrollPane(contents);
		
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		
		add(scrollPane, BorderLayout.CENTER);
		
		
	}
	
	private Entry mySelectedEntry = null;
	
	/**
	 * Shows a context box with information about the entry, including title
	 * and any admin or judge comments, including weather the entry was
	 * rejected from the contest.
	 * @param theEntry The entry clicked on.
	 */
	private void showContestDetails(Entry theEntry) {
		// If the user clicked the same contest again, do nothing.
		if (theEntry == mySelectedEntry)
			return;
		
		// If a contest was previously clicked, remove its details panel.
		if (mySelectedEntry != null) {
			JPanel oldContestBox = myEntryBoxes.get(mySelectedEntry);
			for(Component c : oldContestBox.getComponents()) {
				if (c instanceof JPanel) {
					oldContestBox.remove(c);
					break;
				}
			}
		}
		
		// Add the details panel for the contest which was clicked.
		JPanel contestBox = myEntryBoxes.get(theEntry);
		
		JPanel detailsPanel = new JPanel(new BorderLayout());
		detailsPanel.setPreferredSize(new Dimension(150, 200));
		
		detailsPanel.add(new JLabel("Details"), BorderLayout.NORTH);
		
		
		JTextArea details = new JTextArea(theEntry.getComment());
		detailsPanel.add(details, BorderLayout.CENTER);
		
		contestBox.add(detailsPanel, BorderLayout.EAST);
		
		mySelectedEntry = theEntry;
		
		revalidate();
	}

}

















