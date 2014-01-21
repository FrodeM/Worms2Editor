package Worms2Editor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.*;

public class WeaponsOptionList extends JPanel {

	private WeaponsOption[] options;
	private int id;
	
	@Override
	public void paintComponent(Graphics g)
	{
		int w = getWidth();
		int h = getHeight();
		g.setColor(getBackground());
		g.fillRect(0, 0, w, h);
		w = getPreferredSize().width;
		h = getPreferredSize().height;
		g.setColor(Color.lightGray);
		g.fillRect(0, 0, w, h);
	}
	
	public WeaponsOptionList(int weaponNr, int[] values, int parentw, double scale, BufferedImage ikoner, Weapons panel)
	{
		id = weaponNr;	
		setLayout(null);
		
		options = new WeaponsOption[WeaponsFuncTable[id].length];
		for(int i = 0; i< WeaponsFuncTable[id].length; i++)
		{
			options[i] = new WeaponsOption(WeaponsFuncTable[id][i], values[WeaponsFuncTable[id][i]], ikoner, 85.4, scale, panel);
		}
		buildGUI(parentw, scale);
	}
	
	public void buildGUI(int parentw, double scale)
	{
		
		if(scale<0.8)
			scale = 0.8;
		int space = (int)((parentw-3*scale)/(226*scale));
		if(space == 0)
			space = 1;
		if (scale > 0.8 && space<2)
			space = 2;
		removeAll();
		setPreferredSize(new Dimension((int)((3+226*space)*scale), (int)((3+((WeaponsFuncTable[id].length+(space-1))/space)*51)*scale)));
		
		for(int i = 0; i< WeaponsFuncTable[id].length; i++)
		{
			options[i].buildGUI(scale);
			options[i].setBounds((int)((3+226*(i%space))*scale), (int)((3+(i/space)*51)*scale), (int)(220*scale), (int)(45*scale));
			add(options[i]);
		}
		repaint();
	}

	public int getWeaponNr()
	{
		return id;
	}
	
	public int[] getValues()
	{
		int[] values = new int[35];
		for(int i = 0; i<35; i++)
			values[i] = -522133280;
		for(int i = 0; i< options.length; i++)
			values[options[i].getOptionNr()] = options[i].getValue();
		return values;
	}

	
	private static final int[][] WeaponsFuncTable =
		{{0, 1, 2, 3, 5, 7, 8, 9, 10, 13},
			{0, 1, 2, 3, 5, 7, 8, 9, 10, 11, 12},
			{0, 1, 2, 3, 5, 7, 8, 9, 10, 13, 19},
			{0, 1, 2, 3, 5, 7, 8, 9, 10, 13, 15, 16, 17, 18, 19},
			{0, 1, 2, 3, 5, 7, 8, 9, 10, 13, 15, 16, 17, 18, 19},
			{0, 1, 2, 3, 5, 7, 8, 9, 10, 13, 19},
			{0, 1, 2, 3, 5, 7, 8, 9, 10, 11, 12, 15, 16, 17, 18, 19},
			{0, 1, 2, 3, 5, 7, 8, 9, 10, 20, 21, 22},
			{0, 1, 2, 3, 5, 6, 7, 8, 9, 10, 34},
			{0, 1, 2, 3, 5, 6, 7, 8, 9, 10, 34},
			{0, 1, 2, 3, 5, 6, 7, 8, 9, 10, 34},
			{0, 1, 2, 3, 5, 6, 7, 8, 9, 10, 34},
			{0, 1, 2, 3, 5, 7, 23, 24, 25, 26},
			{0, 1, 2, 3, 5, 7, 27, 28, 29, 30},
			{0, 1, 3, 5, 7, 8, 23, 24, 25, 31},
			{0, 1, 2, 3, 5, 7, 8, 9, 10, 19},
			{0, 1, 2, 3, 5, 7, 8, 9, 10},
			{0, 1, 2, 3, 5, 7, 8, 9, 10, 15, 16, 17, 18, 19},
			{0, 1, 2, 3, 5, 7, 9, 10, 32, 33},
			{0, 1, 2, 3, 5, 7, 9, 10, 11, 12, 32, 33},
			{0, 1, 2, 3, 5, 7, 9, 10, 20, 21, 22, 32, 33},
			{0, 1, 2, 3, 5, 7, 9, 10, 32, 33},
			{0, 1, 2, 3, 5, 7},
			{0, 1, 2, 3, 5, 7, 23, 24, 25, 31},
			{0, 1, 2, 3, 5, 7, 23, 25},
			{0, 1, 2, 3, 5, 7, 23, 24, 25},
			{0, 1, 2, 3, 5, 7},
			{0, 1, 2, 3, 5, 7},
			{0, 1, 2, 3, 5, 7},
			{0, 1, 2, 3, 5, 7, 13},
			{0, 1, 2, 3, 5, 7, 8, 9, 10, 19},
			{0, 1, 2, 3, 5, 7, 8, 9, 10},
			{0, 1, 2, 3, 5, 7, 8, 9, 10, 19},
			{0, 1, 2, 3, 5, 7, 8, 9, 10, 13, 15, 16, 17, 18},
			{0, 1, 2, 3, 5, 7, 23, 24, 25, 31},
			{0, 1, 2, 3, 5, 7, 8, 9, 10, 11, 12},
			{0, 1, 2, 3, 5, 7, 8, 9, 10, 19},
			{0, 1, 2, 3, 5, 7, 8, 9, 10, 13, 15, 16, 17, 18}};

	public static int[] getDefault(int nr) {
		int[] values = new int[35];
		for(int i = 0; i<35; i++)
			values[i] = -522133280;
		for(int i = 0; i< WeaponsFuncTable[nr].length; i++)
			values[WeaponsFuncTable[nr][i]] = 0;
		return values;
	}

}
