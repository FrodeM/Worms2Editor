package Worms2Editor.Indicators;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class BooleanIndicator  extends Indicator {
	
	public BooleanIndicator(int initstrength, int width, int height)
	{
		super(initstrength, 1, 1, width, height);
	}

	@Override
	public void paintComponent(Graphics g)
	{
		int w = getWidth()-1;
		int h = getHeight()-1;
		g.setColor(getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());

		if (strenght==0)
		{
			g.setColor(Color.red);
			int[] x = {0, w/6, w/2, (5*w)/6, w, (2*w)/3, w, (5*w)/6, w/2, w/6,0, w/3};
			int[] y = {h/3, 0, h/3, 0, h/3, h/2, (2*h)/3,h, (2*h)/3, h, (2*h)/3, h/2};
			g.fillPolygon(x, y, 12);
			g.setColor(Color.black);
			g.drawPolygon(x, y, 12);
		}
		else
		{
			g.setColor(Color.green);
			int[] x = {0, w/7, w/3, (5*w)/6, w, w/3};
			int[] y = {(5*h)/6, h/2, (2*h)/3, 0, h/3, h};
			g.fillPolygon(x, y, 6);
			g.setColor(Color.black);
			g.drawPolygon(x, y, 6);
		}

	}
	
}
