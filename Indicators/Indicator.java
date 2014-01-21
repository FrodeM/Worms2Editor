package Worms2Editor.Indicators;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Indicator extends JPanel implements ChangeListener{

	protected int strenght;
	protected int maximum;
	protected int absolutemaximum;

	Indicator(int initstrength, int max, int amax, int width, int height)
	{
		setLayout(null);
		strenght = initstrength;
		maximum = max;
		absolutemaximum = amax;
		repaint();
	}

	public void setPanelSize(int width, int height)
	{
		setPreferredSize(new Dimension(width, height));
		repaint();
	}
	
	protected static Color getForceColor(double force)
	{
		if (force<0.5)
			return new Color((int)Math.round(510*force),255,0);
		if (force>0.5)
			return new Color(255, 510-(int)Math.round(510*force),0);
		return new Color(255,255,0);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		strenght = (int)((JSpinner)e.getSource()).getValue();
		repaint();
	}

}
