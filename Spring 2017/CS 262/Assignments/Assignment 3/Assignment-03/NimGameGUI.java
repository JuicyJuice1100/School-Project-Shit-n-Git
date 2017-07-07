/*
* GUI for Nimgame
*
*@ Justin Espiritu
*@ version 1.0
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class NimGameGUI extends JFrame
{

	private MatchGame matchGame;

	//Everything needed for matchGamePanel
	private JPanel matchGamePanel;
	private JButton matchGameButton;
	private JButton quitButton;

	//Everything needed for gameSelectionPanel
	private JPanel gameSelectionPanel;
	private JButton gameOneButton;
	private JButton gameTwoButton;
	private JButton cancelButton;
	
	//Everything needed for settingsPanel
	private JPanel settingsPanel;
	private JButton playGameButton;
	private JButton humanFirstButton;
	private JButton computerFirstButton;
	private JButton defaultButton;
	private JButton saveSettingsButton;
	//private JButton changeSticksButton
	private JTextField whoFirstText;
	private JTextField numberOfSticks;
	private JTextField inputSticksText;

	//Everything needed for playGamePanel
	private JPanel playGamePanel;
	private JButton removeOneStickButton;
	private JButton removeTwoSticksButton;
	private JButton removeThreeSticksButton;
	private JButton stopMatchButton;

	public NimGameGUI()
	{
		super("NimGame");
		setLayout(new FlowLayout());

		//Handlers
		ButtonHandler buttonHandler = new ButtonHandler();
		TextFieldHandler textHandler = new TextFieldHandler();

		//matchGamePanel
		matchGamePanel = new JPanel(new GridLayout(0, 1, 5, 5));
		matchGameButton = new JButton("New Match");
		quitButton = new JButton("Quit");

		matchGamePanel.add(matchGameButton, 0, 0);
		matchGamePanel.add(quitButton,0 , 1);

		matchGameButton.addActionListener(buttonHandler);
		quitButton.addActionListener(buttonHandler);

		//GameSelectionPanel
		gameSelectionPanel = new JPanel(new GridLayout(0, 2, 5, 5));
		gameOneButton = new JButton("Game 1");
		gameTwoButton = new JButton("Game 2");
		cancelButton = new JButton("Cancel");

		gameSelectionPanel.add(gameOneButton, 0, 0);
		gameSelectionPanel.add(gameTwoButton, 0, 1);
		gameSelectionPanel.add(cancelButton, 0, 2);

		gameOneButton.addActionListener(buttonHandler);
		gameTwoButton.addActionListener(buttonHandler);
		cancelButton.addActionListener(buttonHandler);

		gameSelectionPanel.setVisible(false);
		
		//Settings Panel
		settingsPanel = new JPanel(new GridLayout(2, 2, 5, 5));
		playGameButton = new JButton("Play Game");
		humanFirstButton = new JButton("Player");
		computerFirstButton = new JButton("Computer");
		defaultButton = new JButton("Change to Default Settings");
		saveSettingsButton = new JButton("Save Settings");
		//changeSticksButton = new JButton("Change Starting Sticks");
		whoFirstText = new JTextField("Who do you want to go First?");
		inputSticksText = new JTextField("Change Starting Sticks (Default: 15)");

		settingsPanel.add(playGameButton, 0, 0);
		settingsPanel.add(whoFirstText, 1, 0);
		settingsPanel.add(humanFirstButton, 1, 1);
		settingsPanel.add(computerFirstButton, 1, 2);
		settingsPanel.add(inputSticksText, 2, 0);
		settingsPanel.add(defaultButton, 2, 1);
		settingsPanel.add(saveSettingsButton, 2, 2);
	
		settingsPanel.setVisible(false);

		playGameButton.addActionListener(buttonHandler);
		humanFirstButton.addActionListener(buttonHandler);
		computerFirstButton.addActionListener(buttonHandler);
		defaultButton.addActionListener(buttonHandler);
		saveSettingsButton.addActionListener(buttonHandler);
		whoFirstText.addActionListener(textHandler);
		inputSticksText.addActionListener(textHandler);

		//playGame Panel
		playGamePanel = new JPanel(new GridLayout(0, 3, 5, 5));
		removeOneStickButton = new JButton("Remove 1");
		removeTwoSticksButton = new JButton("Remove 2");
		removeThreeSticksButton = new JButton("Remove 3");
		stopMatchButton = new JButton("Stop Match");

		playGamePanel.add(removeOneStickButton, 0, 0);
		playGamePanel.add(removeTwoSticksButton, 0, 1);
		playGamePanel.add(removeThreeSticksButton, 0, 2);
		playGamePanel.add(stopMatchButton, 0, 3);

		playGamePanel.setVisible(false);

		removeOneStickButton.addActionListener(buttonHandler);
		removeTwoSticksButton.addActionListener(buttonHandler);
		removeThreeSticksButton.addActionListener(buttonHandler);
		stopMatchButton.addActionListener(buttonHandler);
	}

	private class TextFieldHandler extends ButtonHandler
	{

	}

	private class ButtonHandler implements ActionListener
	{
		MatchGame game;

		@Override
		public void actionPerformed(ActionEvent event)
		{
			if(event.getSource() == matchGameButton)
			{
				matchGamePanel.setVisible(false);
				gameSelectionPanel.setVisible(true);
			}
			else if(event.getSource() == quitButton)
			{
				System.exit(0);
			}
			else if(event.getSource() == gameOneButton || event.getSource() == gameTwoButton)
			{
				gameSelectionPanel.setVisible(false);
				playGamePanel.setVisible(true);
			}
			else if(event.getSource() == cancelButton)
			{
				gameSelectionPanel.setVisible(false);
				matchGamePanel.setVisible(true);
			}
			else if(event.getSource() == playGameButton)
			{
				matchGamePanel.setVisible(false);
				game = new MatchGame();
			}
			//else if(event.getSource() == )
		}
	}
}