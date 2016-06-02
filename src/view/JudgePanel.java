package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import model.Contest;
import model.Entry;
import stinc.Controller;
import sun.swing.SwingAccessor;

@SuppressWarnings("serial")
public class JudgePanel extends JPanel
{
	private static final int IMAGE_WIDTH = 200;
	private static final int IMAGE_HEIGHT = 200;
	private static final int DEFAULT_HEIGHT = IMAGE_HEIGHT + 55;
	private static final int DEFAULT_WIDTH = 700;
	private final List<Contest> myContests;
	private final Controller myController;
	private Contest mySelectedContest = null;
	
	private final HashMap<Contest,JPanel> myContestBoxes = new HashMap<Contest, JPanel>();

	private JPanel myContent;
	
	public JudgePanel(Controller theController) 
	{
		super();
		myController = theController;
		myContent = new JPanel();
		myContent.setLayout(new BoxLayout(myContent, BoxLayout.Y_AXIS));
		myContests = null;
		setLayout(new BorderLayout());
		
		add(judgeScroll(myContent), BorderLayout.WEST);	
	}
	
	public void populate(List<Entry> theEntries)
	{
		for (Entry e : theEntries)
		{
			myContent.add(judgeBox(e));
		}
	}
	
	public JPanel judgeBox (Entry theEntry)
	{
		JPanel judgeBox = new JPanel();
		judgeBox.setLayout(new FlowLayout(FlowLayout.LEFT));
		Image image = ImageFetcher.fetchImage("https://upload.wikimedia.org/wikipedia/en/9/96/Jurassic_Park_logo.jpg", IMAGE_WIDTH, IMAGE_HEIGHT);
		judgeBox.add(new JLabel(new ImageIcon(image)));
		judgeBox.add(createJudgeInfo(theEntry));
		return judgeBox;
	}
	
	public JPanel createJudgeInfo (Entry theEntry)
	{
		JPanel judgeInfo = new JPanel();
		judgeInfo.setLayout(new BoxLayout(judgeInfo, BoxLayout.Y_AXIS));
		
		JLabel entryName = new JLabel(theEntry.getName());
		JLabel entryDesc = new JLabel(theEntry.getDescription());
		judgeInfo.add(new JLabel("Name: "));
		judgeInfo.add(entryName);
		
		judgeInfo.add(new JLabel("Description: "));
		judgeInfo.add(entryDesc);
		judgeInfo.add(createScorePanel());
		return judgeInfo;
	}
	
	public JPanel createScorePanel ()
	{
		JPanel scorePanel = new JPanel();
		scorePanel.setLayout(new FlowLayout());
		
		JLabel scoreLabel = new JLabel("Score: ");
		JTextField score = new JTextField(2);
		scorePanel.add(scoreLabel);
		scorePanel.add(score);
		
		return scorePanel;
	}
	
	public JScrollPane judgeScroll(JPanel theContent)
	{
		JScrollPane scrollPane = new JScrollPane(theContent);
		
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		return scrollPane;
	}
	
//	private void showContestDetails(Contest theContest) 
//	{
//		// If the user clicked the same contest again, do nothing.
//		if (theContest == mySelectedContest)
//			return;
//		
//		// If a contest was previously clicked, remove its details panel.
//		if (mySelectedContest != null) {
//			JPanel oldContestBox = myContestBoxes.get(mySelectedContest);
//			for(Component c : oldContestBox.getComponents()) {
//				if (c instanceof JPanel) {
//					oldContestBox.remove(c);
//					break;
//				}
//			}
//		}
//		
//		// Add the details panel for the contest which was clicked.
//		JPanel contestBox = myContestBoxes.get(theContest);
//		
//		JPanel detailsPanel = new JPanel(new BorderLayout());
//		detailsPanel.setPreferredSize(new Dimension(150, 200));
//		
//		detailsPanel.add(new JLabel("Details"), BorderLayout.NORTH);
//		
//		
//		JTextArea details = new JTextArea(theContest.getDescription());
//		detailsPanel.add(details, BorderLayout.CENTER);
//		
//		JButton openButton = new JButton("Open");
//		openButton.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				myController.showContest(mySelectedContest);
//			}
//			
//		});
//		detailsPanel.add(openButton, BorderLayout.SOUTH);
//		
//		contestBox.add(detailsPanel, BorderLayout.EAST);
//		
//		mySelectedContest = theContest;
//		
//		revalidate();
//	}
	
}
