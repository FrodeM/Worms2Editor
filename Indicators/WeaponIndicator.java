package Worms2Editor.Indicators;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class WeaponIndicator  extends Indicator {

	public WeaponIndicator(int initstrength, int width, int height)
	{
		super(initstrength, 10, 127, width, height);
	}

	@Override
	public void paintComponent(Graphics g)
	{
		int w = getWidth()-1;
		int h = getHeight()-1;
		g.setColor(getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());

		if(strenght == 0)
		{
			g.setColor(Color.red);
			int[] x = {0, w/6, w/2, (5*w)/6, w, (2*w)/3, w, (5*w)/6, w/2, w/6,0, w/3};
			int[] y = {h/3, 0, h/3, 0, h/3, h/2, (2*h)/3,h, (2*h)/3, h, (2*h)/3, h/2};
			g.fillPolygon(x, y, 12);
			g.setColor(Color.black);
			g.drawPolygon(x, y, 12);
		}
		else if(strenght<10)
			for(int i = 0; i<strenght; i++)
			{
				g.setColor(new Color(200,200,0));
				g.fillOval((w/5)*(i%5), (h/2)*(i/5), w/5, h/2);
				g.setColor(Color.black);
				g.drawOval((w/5)*(i%5), (h/2)*(i/5), w/5, h/2);
			}
		else if(strenght == 10)
		{
			g.setColor(Color.gray);
			g.fillOval((3*w)/18, 0, (7*w)/18, h);
			g.fillOval((8*w)/18, 0, (7*w)/18, h);
			g.setColor(getBackground());
			g.fillOval((5*w)/18, h/4, (3*w)/18, h/2);
			g.fillOval((10*w)/18, h/4, (3*w)/18, h/2);
			g.setColor(Color.black);
			g.drawOval((3*w)/18, 0, (7*w)/18, h);
			g.drawOval((8*w)/18, 0, (7*w)/18, h);
			g.setColor(Color.gray);
			int x = (int)Math.round((2*3.5*w*Math.sin(Math.acos(5/7.0))/18));
			g.fillRect((8*w)/18, 1+(h-x)/2, 1+(2*w)/18, x-1);
			g.setColor(Color.black);
			g.drawOval((5*w)/18, h/4, (3*w)/18, h/2);
			g.drawOval((10*w)/18, h/4, (3*w)/18, h/2);
		}
		else
		{
			h = (10*h)/11;

			int m = maximum;
			if (strenght>m)
			{
				g.setColor(Color.red);
				int[] x = {0, w/10, w/5};
				int[] y = {h/3, 0, h/3};
				g.fillPolygon(x, y, 3);
				g.setColor(Color.black);
				g.drawPolygon(x, y, 3);
				m = absolutemaximum;
			}

			for(int i = 0; i<w; i++)
			{
				if(i<(w*strenght)/m)
					g.setColor(getForceColor(i/(w-1.0)));
				else
					g.setColor(new Color(220,220,220));
				g.drawLine(i, h-(h*i)/w, i, h);
			}
			g.setColor(Color.black);
			for(int i = 0; i<m;)
			{
				if(m/30000 > 0)
					i = i+10000;
				else if(m/3000 > 0)
					i = i+1000;
				else if(m/300 > 0)
					i = i+100;
				else if(m/30 > 0)
					i = i+10;
				else
					i++;

				g.drawLine(w*i/m, h, w*i/m, h + h/10);
			}

			g.drawLine(0, h, w, h);
			g.drawLine(w, 0, w, h);
			g.drawLine(0, h, w, 0);
			g.drawLine((w*strenght)/m, h-(h*strenght)/m, (w*strenght)/m, h);
			g.drawLine((w*maximum)/m, h-(h*maximum)/(m*2), (w*maximum)/m, h);
		}

	}

}
