/*
@Justin Espirtu
@Version 1
Creating a temperature converter using GUI
*/
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class TemperatureConverter extends JFrame
{
	private final JTextField fText, fLabel;
	private final JTextField cText, cLabel;
	private final JButton fButton;
	private final JButton cButton;

	//TemperatureConverter add Jbuttons and Jtextfield to JFrame
	public TemperatureConverter()
	{	
		super("TemperatureConverter");
		setLayout(new FlowLayout());


		//add textField with 10 columns for inserting degrees Fahrenheit
		fText = new JTextField(10);
		add(fText);

		//add another textField with 10 columns for inserting degrees Celcius
		cText = new JTextField(10);
		add(cText);

		//create new text handler events
		TextFieldHandler textHandler = new TextFieldHandler();
		fText.addActionListener(textHandler);
		cText.addActionListener(textHandler);

		//create a button for converting to Celsius
		fButton = new JButton("Fahrenheit to Celsius");
		add(fButton);

		//create a button for converting to Fahrenheit
		cButton = new JButton("Celsius to Fahrenheit");
		add(cButton);

		//create new button handler for events
		ButtonHandler buttHandler = new ButtonHandler();
		fButton.addActionListener(buttHandler);
		cButton.addActionListener(buttHandler);
	}

	private class ButtonHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			String str = "";
			double degree, conversion;
			if(event.getSource() == fButton)
			{
				str = fText.getText();
				degree = 1.0 * Integer.parseInt(str);
				conversion = (5.0 * (degree - 32.0)/9.0);
				str = Double.toString(conversion);
			}

			else if(event.getSource() == cButton)
			{
				str = cText.getText();
				degree = 1.0 * Integer.parseInt(str);
				conversion = (9.0 * degree/5.0 + 32.0);
				str = Double.toString(conversion);
			} 

			JOptionPane.showMessageDialog(null, str);
		}
	}

	private class TextFieldHandler implements ActionListener 
	{	
		@Override
		public void actionPerformed(ActionEvent event)
		{	
			String str = "";
			double degree, conversion;

			if(event.getSource() == fText)
			{
				str = String.format("%s", event.getActionCommand());
				degree = 1.0 * Integer.parseInt(str);
				conversion = (5.0 * (degree - 32.0)/9.0);
				str = Double.toString(conversion);
			}
			else if(event.getSource() == cText)
			{
				str = String.format("%s", event.getActionCommand());
				degree = 1.0 * Integer.parseInt(str);
				conversion = (9.0 * degree/5.0 + 32.0);
				str = Double.toString(conversion);
			} 

			JOptionPane.showMessageDialog(null, str);
		}
	}
}
