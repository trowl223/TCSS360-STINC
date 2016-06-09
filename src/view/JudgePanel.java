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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import model.Contest;
import model.Entry;
import stinc.Controller;

/**
 * The page displayed when a judge clicks on a contest. Allows the judge to
 * assign scores and add comments to entries and submit these to the database.
 * @author Nicholas
 *
 */
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
	
	/**
	 * Creates a panel for judges to judge entries of a contest.
	 * @param theController reference.
	 * @param theContest that is being judged.
	 */
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
	
	/**
	 * Populate the box with entries
	 * @param theEntries to populate with.
	 */
	public void populate(List<Entry> theEntries)
	{
		for (Entry e : theEntries)
		{
			myContent.add(judgeBox(e));
		}
	}
	
	/**
	 * JPanel containing the Contest info.
	 * @return the Contest info.
	 */
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
	
	/**
	 * The box the judge uses to judge entries.
	 * @param theEntry to judge.
	 * @return JPanel containing the entry info.
	 */
	public JPanel judgeBox (Entry theEntry)
	{
		JPanel judgeBox = new JPanel();
		judgeBox.setLayout(new FlowLayout(FlowLayout.LEFT));
		Image image = ImageFetcher.fetchImage(theEntry.getSubmissionPath(), IMAGE_WIDTH, IMAGE_HEIGHT);
		judgeBox.add(new JLabel(new ImageIcon(image)));
		judgeBox.add(createJudgeInfo(theEntry));
		return judgeBox;
	}
	
	/**
	 * Shows the judge info.
	 * @param theEntry info  to show.
	 * @return the judge info panel.
	 */
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
		left.gridx++;
		right.gridy++;
		JTextField score = new JTextField(2);
		myEntryBoxes.put(theEntry, score);
		judgeInfo.add(score, left);
		
		return judgeInfo;
	}
	
	/**
	 * Creates the submit button.
	 * @return the submit button.
	 */
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
	
	/**
	 * Submits the entries scored.
	 */
	private void submit() 
	{
		if(!myEntryBoxes.isEmpty())
		{
			for (Entry e : myEntryBoxes.keySet())
			{
				JTextField jt = myEntryBoxes.get(e);
				if (jt.getText().length() > 0)
				{
					try 
					{
					    Integer in = Integer.parseInt(jt.getText());
					    if (in != null)
					    {
								//System.out.println(in);
							myController.judgeEntry(myController.getCurrentUser(), e.getID(), in, "");
						}
					}
					catch (NumberFormatException e1) {
						 JOptionPane.showMessageDialog(null, "You must input a valid number for the entry called: " + e.getName(), "Submission Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Nothing to sumbit", "Submission Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Creates the score panel.
	 * @return the score panel.
	 */
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
	
	/**
	 * Creates the scroll pane the Content will be placed in.
	 * @param theContent the content to scroll.
	 * @return the scroll pane.
	 */
	public JScrollPane judgeScroll(JPanel theContent)
	{
		JScrollPane scrollPane = new JScrollPane(theContent);
		
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		return scrollPane;
	}
}
