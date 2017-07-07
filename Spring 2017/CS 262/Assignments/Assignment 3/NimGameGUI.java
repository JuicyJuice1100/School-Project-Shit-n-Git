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

	private NimGame game;

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
		setLayout(new BorderLayout());

		//Handlers
		ButtonHandler buttonHandler = new ButtonHandler();
		TextFieldHandler textHandler = new TextFieldHandler();

		//matchGamePanel
		//matchGamePanel = new JPanel(new GridLayout(0, 1, 5, 5));
		matchGamePanel = new JPanel(new FlowLayout());
		matchGamePanel.setSize(300, 300);
		add(matchGamePanel, BorderLayout.CENTER);
		matchGameButton = new JButton("New Match");
		quitButton = new JButton("Quit");

		matchGamePanel.add(matchGameButton);
		matchGamePanel.add(quitButton);

		matchGameButton.addActionListener(buttonHandler);
		quitButton.addActionListener(buttonHandler);

		//GameSelectionPanel
		//gameSelectionPanel = new JPanel(new GridLayout(0, 2, 5, 5));
		gameSelectionPanel = new JPanel(new FlowLayout());
		gameSelectionPanel.setSize(300, 300);
		add(gameSelectionPanel, BorderLayout.CENTER);
		gameOneButton = new JButton("Game 1");
		gameTwoButton = new JButton("Game 2");
		cancelButton = new JButton("Cancel");

		gameSelectionPanel.add(gameOneButton);
		gameSelectionPanel.add(gameTwoButton);
		gameSelectionPanel.add(cancelButton);

		gameOneButton.addActionListener(buttonHandler);
		gameTwoButton.addActionListener(buttonHandler);
		cancelButton.addActionListener(buttonHandler);

		gameSelectionPanel.setVisible(false);
		
		//Settings Panel
		//settingsPanel = new JPanel(new GridLayout(2, 2, 5, 5));
		settingsPanel = new JPanel(new FlowLayout());
		settingsPanel.setSize(300, 300);
		add(settingsPanel, BorderLayout.CENTER);
		playGameButton = new JButton("Play NimGame");
		humanFirstButton = new JButton("Player First");
		computerFirstButton = new JButton("Computer First");
		defaultButton = new JButton("Change to Default Settings");
		saveSettingsButton = new JButton("Save Settings");
		//whoFirstText = new JTextField("Who do you want to go First?");
		inputSticksText = new JTextField("Change Starting Sticks (Default: 15)");

		settingsPanel.add(playGameButton);
		//settingsPanel.add(whoFirstText);
		settingsPanel.add(humanFirstButton);
		settingsPanel.add(computerFirstButton);
		settingsPanel.add(inputSticksText);
		settingsPanel.add(defaultButton);
		settingsPanel.add(saveSettingsButton);
	
		settingsPanel.setVisible(false);

		playGameButton.addActionListener(buttonHandler);
		humanFirstButton.addActionListener(buttonHandler);
		computerFirstButton.addActionListener(buttonHandler);
		defaultButton.addActionListener(buttonHandler);
		saveSettingsButton.addActionListener(buttonHandler);
		//whoFirstText.addActionListener(textHandler);
		inputSticksText.addActionListener(textHandler);

		//playGame Panel
		playGamePanel = new JPanel(new GridLayout(0, 3, 5, 5));
		playGamePanel.setSize(300, 300);
		add(playGamePanel, BorderLayout.CENTER);
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

		game = new NimGame();
	}

	private class TextFieldHandler extends ButtonHandler
	{

	}

	private class ButtonHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			int numberOfSticks = 0;
			String str = "";
			boolean valid = true;

			if(event.getSource() == inputSticksText)
			{
				try
				{
					numberOfSticks = Integer.parseInt(inputSticksText.getText());
				}
				catch(Exception ex)
				{
					inputSticksText.setText("Please enter a Number");
					valid = false;
				}
				if(valid)
				{
					//error fix this
					Sticks(numberOfSticks);
					inputSticksText.setText("Sticks Updated");
				}
			}
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
				settingsPanel.setVisible(true);
			}
			else if(event.getSource() == cancelButton)
			{
				gameSelectionPanel.setVisible(false);
				matchGamePanel.setVisible(true);
			}
			else if(event.getSource() == playGameButton)
			{
				matchGamePanel.setVisible(false);
				playGamePanel.setVisible(true);
			}
			//else if(event.getSource() == )
		}
	}
}