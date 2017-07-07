/*
@Justin Espiritu
@version 1
Drive to test Temperature converter
*/

import javax.swing.JFrame;

public class TemperatureConverterTest
{
	public static void main(String[] args)
	{
		TemperatureConverter tmpConvert = new TemperatureConverter();
		tmpConvert.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tmpConvert.setSize(350, 100);
		tmpConvert.setVisible(true);
	}
}