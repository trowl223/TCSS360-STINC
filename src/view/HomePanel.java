package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import model.Contest;
import stinc.Controller;

public class HomePanel extends JPanel {
	/**
	 * required serialVersionUID
	 */
	private static final long serialVersionUID = 1780272080173534812L;
	private ContestScroller myFeatCont;
	private ContestScroller myCont;
	private ContestScroller myMiscCont;
	
	private Controller myController;
	
	
	/**
	 * Create the panel with scrollers.
	 */
	public HomePanel(Controller aController, ContestScroller[] someScrollers) {
		myController = aController;
		myFeatCont = someScrollers[0];
		myCont = someScrollers[1];
		myMiscCont = someScrollers[2];
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
		JLabel fLabel = new JLabel("Featured Contests:", SwingConstants.LEFT);
		fLabel.setPreferredSize(new Dimension(160,15));
		JLabel mLabel = new JLabel("My Contests:", SwingConstants.LEFT);
		mLabel.setPreferredSize(new Dimension(120,15));
		JLabel oLabel = new JLabel("Other Contests:", SwingConstants.LEFT);
		oLabel.setPreferredSize(new Dimension(120,15));
		
		//Add stuff to the panel
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = .5;
		c.weighty =1;
		//c.anchor = GridBagConstraints.FIRST_LINE_START;
		cPane.add(fLabel, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = .5;
		c.weighty = 0;
		//c.anchor = GridBagConstraints.FIRST_LINE_END;
		cPane.add(myFeatCont,c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = .5;
		//c.anchor = GridBagConstraints.LINE_START;
		cPane.add(mLabel, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;
		c.weightx = .5;
		//c.anchor = GridBagConstraints.LINE_END;
		cPane.add(myCont, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 4;
		c.weightx = .5;
		//c.anchor = GridBagConstraints.LAST_LINE_START;
		cPane.add(oLabel, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 5;
		c.weightx = .5;
		//c.anchor = GridBagConstraints.LAST_LINE_END;
		cPane.add(myMiscCont, c);
		
		
		JScrollPane scrollPane = new JScrollPane(cPane);
		
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(1000, 500));
		
		add(scrollPane, BorderLayout.CENTER);
	}
//	public Contest getFeatCont(int anIndex) {
//		Contest toReturn = null;
//		if () {
//			toReturn =;
//		}
//		return toReturn;
//	}
//	
//	public Contest getMyCont(int anIndex) {
//		Contest toReturn = null;
//		if () {
//			toReturn =;
//		}
//		return toReturn;
//	}
//	
//	public Contest getMiscCont(int anIndex) {
//		Contest toReturn = null;
//		if () {
//			toReturn = myMiscCont.;
//		}
//		return toReturn;
//	}
}