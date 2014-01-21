package Worms2Editor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class Bildepanel  extends JPanel{

	private BufferedImage bilde;
	private int tilesize;
	private int tilegap;
	private boolean vistall = false;
	private int[][] tall;

	Bildepanel(BufferedImage bi, int tz, int tg)
	{
		setLayout(null);
		bilde = bi;
		setPreferredSize(new Dimension(bilde.getWidth(), bilde.getHeight()));
		tilesize = tz;
		tilegap = tg;
		repaint();
	}

	Bildepanel(BufferedImage bi, int tz, int tg, double scale)
	{
		setLayout(null);
		bilde = bi;
		setPreferredSize(new Dimension((int)(bilde.getWidth()*scale), (int)(bilde.getHeight()*scale)));
		tilesize = tz;
		tilegap = tg;
		repaint();
	}

	public int[] tile(int x,int y)
	{
		double dx = x;
		double dy = y;
		int tilex = -1;
		int tiley = -1;
		double scale = 1;

		if(bilde!=null)
			if(bilde.getHeight()*getWidth() > getHeight()*bilde.getWidth())
			{
				scale = getHeight()/(double)bilde.getHeight();
				dx = x-(getWidth()-bilde.getWidth()*scale)/2.0;
			}	
			else
			{
				scale = getWidth()/(double)bilde.getWidth();
				dy = y-(getHeight()-bilde.getHeight()*scale)/2.0;
			}
		tilex = (int)(((dx/scale)-tilegap/2.0)/(tilesize-tilegap));
		tiley = (int)(((dy/scale)-tilegap/2.0)/(tilesize-tilegap));

		int[] ret = {tilex, tiley};
		return ret;
	}


	@Override
	public void paintComponent(Graphics g)
	{
		g.setColor(new Color(220,220,220));
		g.fillRect(0, 0, getWidth(), getHeight());
		if(bilde!=null)
		{
			int x = 0;
			int y = 0;
			int w = getWidth();
			int h = getHeight();
			double scale = 1;
			double scaley = w/(double)bilde.getWidth();
			double scalex = h/(double)bilde.getHeight();

			if(scaley > scalex)
			{
				scale = scalex;
				x = (int)((w-bilde.getWidth()*scale)/2.0);
				w = (int)(bilde.getWidth()*scale);
			}
			else
			{
				scale = scaley;
				y = (int)((h-bilde.getHeight()*scale)/2.0);
				h = (int)(bilde.getHeight()*scale);
			}
			g.drawImage(bilde, x, y, w, h, this);
			if(vistall)
			{
				g.setFont(new Font(g.getFont().getFontName(), Font.BOLD, (int)(g.getFont().getSize()*scale*0.8)));

				int tilesy = bilde.getHeight()/(tilesize-tilegap);
				int tilesx = bilde.getWidth()/(tilesize-tilegap);

				for(int i = 0; i<tilesy; i++)
				{
					for(int j = 0; j<tilesx; j++)
					{
						if(tall[i*4+j][0] != -1)
						{
							String t;
							int xf;
							int yf;

							if(tall[i*4+j][0] != 10)
								t = tall[i*4+j][0]+"";
							else
								t = "Inf.";
							xf = x+(int)(((double)j+0.10)*(tilesize-tilegap)*scale);
							yf = y+(int)(((double)i+0.93)*(tilesize-tilegap)*scale);
							g.setColor(Color.YELLOW);
							if(tall[i*4+j][0] > 0)
								DrawTextBox(t, g, xf, yf);

							t = tall[i*4+j][1]+"";
							xf = x+(int)(((double)j+0.95)*(tilesize-tilegap)*scale)-size(t,g)[0];
							yf = y+(int)(((double)i+0.03)*(tilesize-tilegap)*scale)+size(t,g)[1];

							g.setColor(Color.GREEN);
							if(tall[i*4+j][1] > 0)
								DrawTextBox(t, g, xf, yf);
						}
					}
				}
			}
		}
	}

	private void DrawTextBox(String s, Graphics g, int x, int y)
	{
		Color c = g.getColor();
		g.setColor(Color.BLACK);
		g.fillRect(x-2, y+2-size(s,g)[1], size(s,g)[0]+2, size(s,g)[1]-1);
		g.setColor(Color.DARK_GRAY);
		g.drawRect(x-2, y+2-size(s,g)[1], size(s,g)[0]+2, size(s,g)[1]-1);
		g.setColor(c);
		g.drawString(s, x, y);
	}

	private int[] size(String s, Graphics g)
	{
		int[] temp = {g.getFontMetrics().charsWidth(s.toCharArray(), 0, s.length()), g.getFontMetrics().getAscent()};
		return temp;
	}

	public void showNumbers(int[][] dat) {
		tall = dat;
		vistall = true;
		repaint();
	}
}
