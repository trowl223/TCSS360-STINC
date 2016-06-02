package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import model.Contest;
import model.Entry;
import model.User;
import stinc.Controller;
import sun.swing.SwingAccessor;

/**
 * @author igorgonchar
 * This panel will allow the 'Admin' to reject contest entries
 */
@SuppressWarnings("serial")

public class AdminPanel extends JPanel {
	
	private static final int IMAGE_WIDTH = 100;
	private static final int IMAGE_HEIGHT = 100;
	private static final int DEFAULT_HEIGHT = IMAGE_HEIGHT + 200;
	private static final int DEFAULT_WIDTH = 800;
	private final List<Contest> myContests;
	private final Controller myController;

	
	/**
	 * Creates a new Admin Panel
	 */
	public AdminPanel(Controller theController) {
		super();
		myController = theController;
		myContests = null;
		
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
		
		/**
		 * Generate dummy entries
		 */
		List<Entry> theEntries = new ArrayList<Entry>();
		theEntries.add(new Entry(new User(01, false, false), "Description_I", "https://cdn2.iconfinder.com/data/icons/ballicons-2-free/100/wrench-512.png", new Date(02,04,2015), 02, 03));
		theEntries.add(new Entry(new User(02, false, false), "Description_II", "http://gapcode.com/atom-editor-icon/2/atom-icon.png", new Date(02,05,2015), 03, 04));
		theEntries.add(new Entry(new User(03, false, false), "Description_III", "https://cdn4.iconfinder.com/data/icons/flat-icon-set/2133/flat_icons-graficheria.it-01.png", new Date(02,06,2015), 04, 05));
		
		/**
		 * For each entry, generate an ACCEPT/REJECT strip in the AdminPanel
		 */
		container.add(populate(theEntries.get(0)));
		container.add(populate(theEntries.get(1)));
		container.add(populate(theEntries.get(2)));
		
//		for (Entry e : theEntries) {
//			container.add(populate(e));
//			container.revalidate();
//            container.repaint();
//		}
	}
	
	/**
	 * Fills in the ScrollPanel with Entries/GUI
	 * @return myContent
	 */
	public Component populate(Entry e) {
		
			final JTextField Comment = new JTextField("Enter Comments Here...");
			
			JButton Accept = new JButton("Accept");
			Accept.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					//Currently does nothing.
				}
			});
			
			JButton Reject = new JButton("Reject");
			Reject.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					Comment.setText("###\nENTRY REJECTED\n###");
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
	
//	/**
//	 * Add an icon to each user entry for review
//	 * @param theEntry
//	 * @return adminBox
//	 */
//	public JPanel adminBox (Entry theEntry) {
//		JPanel adminBox = new JPanel();
//		adminBox.setLayout(new FlowLayout(FlowLayout.CENTER));
//		Image image = ImageFetcher.fetchImage((theEntry.getSubmissionPath()), IMAGE_WIDTH, IMAGE_HEIGHT);
//		adminBox.add(new JLabel(new ImageIcon(image)));
//		
//		
//		return adminBox;
//	}
}
