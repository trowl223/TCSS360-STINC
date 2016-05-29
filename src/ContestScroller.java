

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.Contest;

@SuppressWarnings("serial")
/**
 * The ContestScroller is a horizontal scroll box for displaying contests.
 * @author stefan
 * 
 */
public class ContestScroller extends JPanel {
	
	private static final int DEFAULT_HEIGHT = 400;
	private static final int DEFAULT_WIDTH = 700;
	private final List<Contest> myContests;

	/**
	 * Construct a ContestScroller from a list of Contests.
	 * @param theContests the list of Contests.
	 */
	public ContestScroller(List<Contest> theContests) {
		myContests = theContests;
		
		JPanel contents = new JPanel();
		
		for(Contest c : myContests) {
			JPanel contestBox = new JPanel();
			contestBox.setLayout(new BorderLayout());
			contents.add(contestBox);
			
			JLabel nameLabel = new JLabel(c.getName());
			nameLabel.setHorizontalAlignment(JLabel.CENTER);
			contestBox.add(nameLabel, BorderLayout.SOUTH);
			
			BufferedImage image = ImageFetcher.fetchImage(c.getImageURL());
			if (image != null) {
				JLabel imageLabel = new JLabel(new ImageIcon(image));
				contestBox.add(imageLabel, BorderLayout.CENTER);
			}
		}
		
		
		JScrollPane scrollPane = new JScrollPane(contents);
		
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		
		add(scrollPane);
	}

}
