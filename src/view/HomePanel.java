package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import stinc.Controller;

public class HomePanel extends JPanel {
	/**
	 * required serialVersionUID
	 */
	private static final long serialVersionUID = 1780272080173534812L;
	private ContestScroller myCont1;
	private EntryScroller myEnt1;
	
	private Controller myController;
	
	
	/**
	 * Create the panel with scrollers.
	 */
	public HomePanel(Controller aController, ContestScroller someContests, EntryScroller someEntries) {
		myController = aController;
		myCont1 = someContests;
		myEnt1 = someEntries;
		create();
	}
	
	
	/**
	 * Setups the HomePanel
	 */
	private void create() {
		JPanel cPane = new JPanel();
		//GridLayout grid = new GridLayout(6,1);
		GridBagConstraints c = new GridBagConstraints();
		cPane.setLayout(new GridBagLayout());
		//this.setLayout(grid);
		c.gridheight = 1;
		c.gridwidth = 6;
		cPane.setBackground(Color.WHITE);
		
		//Make labels
		JLabel tLabel;
		JLabel bLabel;
		//top label
		if (myController.getCurrentUser().isAdmin()) {
			tLabel = new JLabel("Open Contests:", SwingConstants.LEFT);
		} else if(myController.getCurrentUser().isJudge()) {
			tLabel = new JLabel("Judgeable Contests:", SwingConstants.LEFT);
		} else {//user
			tLabel = new JLabel("My Contests:", SwingConstants.LEFT);
		}
		tLabel.setPreferredSize(new Dimension(160,15));
		
		//bottom label
		if (myController.getCurrentUser().isAdmin()) {
			bLabel = new JLabel("", SwingConstants.LEFT);
		} else if(myController.getCurrentUser().isJudge()) {
			bLabel = new JLabel("", SwingConstants.LEFT);
		} else {//user
			bLabel = new JLabel("My Entries:", SwingConstants.LEFT);
			bLabel.setPreferredSize(new Dimension(120,15));
		}
		
		//Add stuff to the panel
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = .5;
		c.weighty =1;
		//c.anchor = GridBagConstraints.FIRST_LINE_START;
		cPane.add(tLabel, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = .5;
		c.weighty = 0;
		//c.anchor = GridBagConstraints.FIRST_LINE_END;
		cPane.add(myCont1,c);
		
		if (!myController.getCurrentUser().isAdmin() && !myController.getCurrentUser().isJudge()) {
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 0;
			c.gridy = 2;
			c.weightx = .5;
			//c.anchor = GridBagConstraints.LINE_START;
			cPane.add(bLabel, c);
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 0;
			c.gridy = 3;
			c.weightx = .5;
			//c.anchor = GridBagConstraints.LINE_END;
			cPane.add(myEnt1, c);
		}
		
		JScrollPane scrollPane = new JScrollPane(cPane);
		
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(1000, 500));
		
		add(scrollPane, BorderLayout.CENTER);
	}
}