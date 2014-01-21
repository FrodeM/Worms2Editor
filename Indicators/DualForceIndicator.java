package Worms2Editor.Indicators;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class DualForceIndicator  extends Indicator {

	public DualForceIndicator(int initstrength, int max, int amax, int width, int height)
	{
		super(initstrength, max, amax, width, height);
	}

	@Override
	public void paintComponent(Graphics g)
	{
		int w = getWidth()-1;
		int h = getHeight()-1;
		h = (10*h)/11;
		g.setColor(getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());

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

			for(int i = 0; i<w; i++)
			{
				if(i<(w*strenght)/m)
					if(i<=w/2)
						g.setColor(new Color(255*2*i/w,255,0));
					else
						g.setColor(new Color(255,255-255*(2*i-w)/w,0));
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

			g.drawLine(0, h, w, 0);

			g.drawLine(w*strenght/m, h-h*strenght/m, w*strenght/m, h);
		}
		else
		{
			for(int i = 0; i<w; i++)
			{
				if((i<=w*strenght/m && strenght >= m/2 && i>=w/2) || (i>=w*strenght/m && strenght <= m/2 && i<=w/2))
				{
					if(2*i==w || 2*i==w-1)
						g.setColor(new Color(0,0,0));						
					else
						g.setColor(getForceColor(Math.abs((i-((w-1)/2.0))/((w-1)/2.0))));
				}
				else
					g.setColor(new Color(220,220,220));
				if(i>=w/2)
					g.drawLine(i, h-(int)Math.round(h*i/(double)w), i, h);
				if(i<=w/2)
					g.drawLine(i, (int)Math.round(h*i/(double)w), i, h);
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

			g.drawLine(w/2, h/2, w, 0);
			g.drawLine(0, 0, w/2, h/2);
			g.drawLine(0, 0, 0, h);

			if(strenght >= m/2)
				g.drawLine((w*strenght)/m, h-(int)Math.round(h*strenght/(double)m), w*strenght/m, h);
			else
				g.drawLine((w*strenght)/m, (int)Math.round(h*strenght/(double)m), w*strenght/m, h);
		}
		
		g.drawLine(0, h, w, h);
		g.drawLine(w, 0, w, h);
		
		g.drawLine((w*maximum)/m, h-(h*maximum)/(m*2), (w*maximum)/m, h);
	}

}
