package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import stinc.Controller;

@SuppressWarnings("serial")
public class JudgePanel extends JPanel
{
	private static final int IMAGE_WIDTH = 200;
	private static final int IMAGE_HEIGHT = 200;
	private static final int DEFAULT_HEIGHT = IMAGE_HEIGHT + 55;
	private static final int DEFAULT_WIDTH = 700;
	private final Controller myController;
	
	private final HashMap<Entry, JTextField> myEntryBoxes = new HashMap<Entry, JTextField>();
	
	private JPanel myContent;
	private Contest myContest;
	
	public JudgePanel(Controller theController, Contest theContest) 
	{
		super();
		setLayout(new BorderLayout());
		myController = theController;
		myContest = theContest;
		myContent = new JPanel();
		myContent.setLayout(new BoxLayout(myContent, BoxLayout.Y_AXIS));
		add(contestInfo(), BorderLayout.NORTH);
		add(judgeScroll(myContent), BorderLayout.CENTER);	
		add(createSubmit(), BorderLayout.SOUTH);
	}
	
	public void populate(List<Entry> theEntries)
	{
		for (Entry e : theEntries)
		{
			myContent.add(judgeBox(e));
		}
	}
	
	private JPanel contestInfo()
	{
		JPanel contest = new JPanel();
		contest.setLayout(new BorderLayout());
		JLabel contestName = new JLabel(myContest.getName(), SwingConstants.CENTER);
		JLabel contestDes = new JLabel(myContest.getDescription(), SwingConstants.CENTER);
		contestName.setFont(new Font("Courier", Font.BOLD, 32));
		contestDes.setFont(new Font("Courier", Font.BOLD, 24));
		contest.add(contestName, BorderLayout.NORTH);
		contest.add(contestDes, BorderLayout.CENTER);
		return contest;
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
		judgeInfo.setLayout(new GridBagLayout());
		GridBagConstraints left = new GridBagConstraints();
		left.fill = GridBagConstraints.HORIZONTAL;
		left.gridx = 0;
		left.gridy = 0;
		left.insets = new Insets(3, 3, 3, 3);
		left.anchor = GridBagConstraints.NORTHEAST;
		
		GridBagConstraints right = new GridBagConstraints();
		right.fill = GridBagConstraints.HORIZONTAL;
		right.gridx = 0;
		right.gridy = 0;
		right.insets = new Insets(3, 3, 3, 3);
		
		// Row 3 Submission title
		left.gridy++;
		right.gridy++;
		judgeInfo.add(new JLabel("Name: ", JLabel.LEFT), left);
		left.gridy++;
		right.gridy++;
		judgeInfo.add(new JLabel(theEntry.getName()), left);
		
		// Row 4 Sumbission description
		left.gridy++;
		right.gridy++;
		judgeInfo.add(new JLabel("Description: ", JLabel.LEFT), left);
		left.gridy++;
		right.gridy++;
		judgeInfo.add(new JLabel(theEntry.getDescription()), left);
		
		// Row 3 Submission title
		left.gridy++;
		right.gridy++;
		judgeInfo.add(new JLabel("Score: ", JLabel.LEFT), left);
//		left.gridy++;
		left.gridx++;
		right.gridy++;
		JTextField score = new JTextField(2);
		myEntryBoxes.put(theEntry, score);
		judgeInfo.add(score, left);
		
//		judgeInfo.add(submitButton, right);
//		judgeInfo.setLayout(new BoxLayout(judgeInfo, BoxLayout.Y_AXIS));
//		
//		JLabel entryName = new JLabel(theEntry.getName());
//		JLabel entryDesc = new JLabel(theEntry.getDescription());
//		judgeInfo.add(new JLabel("Name: "));
//		judgeInfo.add(entryName);
//		
//		judgeInfo.add(new JLabel("Description: "));
//		judgeInfo.add(entryDesc);
//		judgeInfo.add(createScorePanel());
		return judgeInfo;
	}
	
	public JButton createSubmit ()
	{
		JButton submitButton = new JButton("Submit");
		submitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("User clicked Submit");
				submit();
			}
			
		});
		return submitButton;
	}
	
	private void submit() 
	{
		for (Entry e : myEntryBoxes.keySet())
		{
			JTextField jt = myEntryBoxes.get(e);
			try 
			{
			    Integer in = Integer.parseInt(jt.getText());
			    if (in != null)
			    {
						System.out.println(in);
//						myController.judgeEntry(myContest.getID(), e.getID(), myController.getCurrentUser(), in.intValue());
				}
			}
			catch (NumberFormatException e1) {
			     //Not an integer
				System.out.print("balls");
			}
		}		
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
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
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
