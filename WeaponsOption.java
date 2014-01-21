package Worms2Editor;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.swing.*;
import javax.swing.event.ChangeListener;

import Worms2Editor.Indicators.BooleanIndicator;
import Worms2Editor.Indicators.DualForceIndicator;
import Worms2Editor.Indicators.ForceIndicator;
import Worms2Editor.Indicators.Indicator;
import Worms2Editor.Indicators.WeaponIndicator;

public class WeaponsOption extends JPanel implements ActionListener {

	private JButton info;
	private BufferedImage ikon;
	private JLabel l1;
	private JLabel l2;
	private JSpinner value;
	private int maxvalue;
	private int id;
	private Font labelFont;
	private double scale;
	private Weapons vindu;
	private Indicator force;

	public WeaponsOption(int optionNr, int val, BufferedImage ikoner, double iconsize, double sc, Weapons visning)
	{
		vindu = visning;
		id = optionNr;

		int ikonsize=(int)(iconsize);

		int[] pos = OptionGraphicsTable[id];
		ikon = ikoner.getSubimage((int)(pos[0]*iconsize), (int)(pos[1]*iconsize), ikonsize, ikonsize);

		setLayout(null);
		setPreferredSize(new Dimension((int)(220*sc), (int)(45*sc)));

		info = new JButton(new ImageIcon(ikon));

		info.addActionListener(this);
		l1 = new JLabel(WeaponsFuncDescTable[id][1]);
		maxvalue = Integer.parseInt(WeaponsFuncDescTable[id][3]);
		value = new JSpinner(new SpinnerNumberModel(val,0,maxvalue,1));
		l2 = new JLabel(WeaponsFuncDescTable[id][0]);

		JLabel l = new JLabel("");
		labelFont = l.getFont();
		if(id==0)
			force = new WeaponIndicator(val, 40, 20);
		else if(id==3)
			force = new BooleanIndicator(val, 40, 20);
		else if(id==17 || id==24 || id==29 || id == 34)
			force = new DualForceIndicator(val, Integer.parseInt(WeaponsFuncDescTable[id][4]), maxvalue, 40, 20);
		else
			force = new ForceIndicator(val, Integer.parseInt(WeaponsFuncDescTable[id][4]), maxvalue, 40, 20);

		buildGUI(sc);
	}


	private void scaleJlabelFont(Container label, double sc)
	{		
		label.setFont(new Font(labelFont.getName(), Font.PLAIN, (int)(labelFont.getSize()*sc)));
	}

	public void buildGUI(double sc)
	{
		scale = sc;
		removeAll();

		BufferedImage scaledikon = new BufferedImage((int)(ikon.getWidth()*scale*0.53), (int)(ikon.getHeight()*scale*0.53), BufferedImage.TYPE_INT_ARGB);
		AffineTransform at = new AffineTransform();
		at.scale(scale*0.53, scale*0.53);
		AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
		scaledikon = scaleOp.filter(ikon, scaledikon);
		info.setIcon(new ImageIcon(scaledikon));

		setScaledBounds(info, 5, 0, 45, 45, scale);
		add(info);
		scaleJlabelFont(l1, scale);
		setScaledBounds(l1, 55, 0, 100, 22, scale);
		add(l1);
		if(id != 3)
			setScaledBounds(force, 160, 3, 55, 22, scale);
		else
			setScaledBounds(force, 135, 3, 75, 39, scale);
		add(force);
		while(value.getChangeListeners().length>0)
			value.removeChangeListener(value.getChangeListeners()[0]);
		value = new JSpinner(new SpinnerNumberModel((int)value.getValue(),0,maxvalue,1));
		value.addChangeListener(vindu);
		value.addChangeListener(force);
		value.setFont(new Font(labelFont.getName(), Font.PLAIN, (int)(labelFont.getSize()*scale)));
		setScaledBounds(value, 55, 23, 70, 22, scale);
		add(value);
		scaleJlabelFont(l2, scale);
		setScaledBounds(l2, 130, 23, 85, 22, scale);
		add(l2);
		repaint();
	}

	private void setScaledBounds(Container c, int x, int y, int h, int b, double scale)
	{
		c.setBounds((int)(x*scale),(int)(y*scale),(int)(h*scale),(int)(b*scale));
	}

	public int getOptionNr()
	{
		return id;
	}

	public int getValue()
	{
		return (int)value.getValue();
	}

	private String[][] WeaponsFuncDescTable =
		{{"Initial Ammo", "Weapon Stock", "Determines the initial stock of the weapon.", "127","10"},
			{"Turns", "Weapon Delay", "Determines the number of turns before the weapon can be used.","255","9"},
			{"s 0=def, 10=n/a", "Retreat Time", "Determines the retreat time after using the weapon.","127","10"},
			{"", "Remember", "Determines if weapon is automatically equipped the next time that worm takes a turn.","1","1"},
			{"", "", "", "", ""},
			{"Ammo", "Ammo in Crates", "Determines the amount of ammo which is found of this weapon in crates.","255","5"},
			{"Bullets", "Bullets per Shot", "Determines the number of bullets per shot.","255","50"},
			{"%", "Crate Probability", "Determines the probability of weapon appearing in a crate.","255","100"},
			{"HitPoints", "Weapon Damage", "Determines the damage dealt by a direct hit of the blast. Also determines blast radius.","255","100"}, 
			{"%", "Blast Power", "Determines the pushing power of the weapon.","255","200"},
			{"%", "Explosion Bias", "Determines how far below point of impact explosion happens.","255","100"},
			{"milliSeconds", "Homing Delay", "Determines the time before homing activates.","65535","2000"},
			{"milliSeconds", "Homing Time", "Determines how long homing will last for after activation","65535","8000"},
			{"%", "Wind Response", "Determines to what degree the projectile is affected by wind","255","100"},
			{"", "", "", "", ""},
			{"Clusters", "Clusters", "Determines how many clusters the bomb will shell.","255","10"},
			{"%", "Cluster Power", "Determines the blast power the clusters are ejected with.","255","200"},
			{"Degrees", "Cluster Angle", "Determines which angle the clusters are ejected at.","255","90"},
			{"HitPoints", "Cluster Damage", "Determines the amount of damage a direct hit from a cluster will take. Also determines blast radius.","255","100"},
			{"Sec. 0=Default", "Override Fuse", "Determines the time delay before explosion.","255","20"},
			{"%", "Amount of Fire", "Determines how much fire will be spread.","255","100"},
			{"%", "Fire Speed", "Determines how fast the fire will spread.","255","200"},
			{"%", "Fire period", "Determines how much time the fire will burn for.","255","200"},
			{"%", "Melee Power", "Determines the force of the impact.","255", "200"},
			{"Degrees", "Melee Angle", "Determines the angle of the impact force.","255","90"},
			{"HitPoints", "Melee Damage", "Determines the damage of the impact.","255","100"},
			{"%", "Jump Height", "Determines how far the worm will jump.","255","200"},
			{"HitPoints", "Ball Damage", "Determines the damage of the ball.","255","100"},
			{"%", "Ball Power", "Determines the force of the ball.","255","200"},
			{"Degrees", "Ball Angle", "Determines the angle of the ball force.","255","90"}, 
			{"milliSeconds", "Ball Time", "Determines how long the ball will go for.","65535","2500"},
			{"milliSeconds", "Digging Time", "Determines how long digging will be performed.","65535","5000"}, 
			{"Bomblets", "Bomblets", "Determines how many bomblets will be fiered.","255","8"},
			{"HitPoints", "Bomblet Damage", "Determines how much damage a direct bomblet hit will take. Also determines the blast radius.","255","100"}, 
			{"Degrees", "Bullet Spread", "Determines the maximum spread of the bullets fiered.","255","90"}};

	private int[][] OptionGraphicsTable =
		{{0, 1}, {1, 1}, {2, 1}, {3, 1}, {4, 0},
			{4, 1}, {5, 1}, {6, 1}, {0, 2}, {1, 2},
			{3, 2}, {4, 2}, {5, 2}, {6, 2}, {4, 0},
			{0, 3}, {0, 0}, {2, 0}, {1, 3}, {4, 3},
			{5, 3}, {6, 3}, {0, 4}, {2, 4}, {3, 4},
			{1, 4}, {4, 4}, {5, 4}, {6, 4}, {2, 3},
			{3, 3}, {1, 0}, {3, 0}, {5, 0}, {6, 0}};

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JDialog meldingboks = new JDialog();
		meldingboks.setModal(true);
		meldingboks.setDefaultCloseOperation (JDialog.DISPOSE_ON_CLOSE);
		JOptionPane.showMessageDialog(meldingboks, WeaponsFuncDescTable[id][2] + " Max: " + WeaponsFuncDescTable[id][3]);
	}

}
