package Worms2Editor;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;

import javax.swing.*;

public class WeaponsHeader extends JPanel {

	private int id;
	private BufferedImage graphics;
	private int iconsize;
	private int icongap;

	public WeaponsHeader(BufferedImage graph, int gsize, int gbord)
	{
		setLayout(new GridBagLayout());
		graphics = graph;
		iconsize = gsize;
		icongap = gbord;
		
		setWeapon(0, 1);

	}

	private Container scaleJlabelFont(Container label, double scale)
	{		
		Font labelFont = label.getFont();
		label.setFont(new Font(labelFont.getName(), Font.PLAIN, (int)(labelFont.getSize()*scale)));
		return label;
	}
	
	public void setWeapon(int weaponNr, double scale){
		id = weaponNr;
		removeAll();
		GridBagConstraints k = new GridBagConstraints();
		k.gridheight = 5;
		k.anchor = GridBagConstraints.LINE_START;
		k.insets = new Insets(0,20,0,20);
		k.weightx = 0;
		k.gridx = 0;
		k.gridy = 0;
		add(new Bildepanel(graphics.getSubimage(WeaponsGraphTable[id][1]*(iconsize-icongap), WeaponsGraphTable[id][0]*(iconsize-icongap), iconsize, iconsize), iconsize, icongap, scale), k);
		k.gridheight = 1;
		k.insets = new Insets(0,0,0,0);
		k.weightx = 1;
		k.gridx = 1;
		k.gridy = 0;
		k.weighty = 1;
		add(scaleJlabelFont(new JLabel(WeaponDescTable[id][0]), scale), k);
		k.weighty = 0;
		for(int i = 1; i<4; i++)
		{
			k.gridy = i+1;
			if(i < WeaponDescTable[id].length)
				add(scaleJlabelFont(new JLabel(WeaponDescTable[id][i]), scale), k);
			else
				add(scaleJlabelFont(new JLabel(" "), scale), k);
		}
		repaint();
	}

	@Override
	public void paintComponent(Graphics g)
	{
		g.setColor(new Color(220,220,220));
		g.fillRect(0, 0, getWidth(), getHeight());
	}
	
	private int[][] WeaponsGraphTable =
		{{0, 0}, {0, 1}, {1, 0}, {1, 1}, {1, 2}, {8, 1}, {1, 3}, {9, 0}, {2, 0}, {2, 1},
			{2, 2}, {2, 3}, {3, 0}, {3, 1}, {3, 2}, {4, 0}, {4, 1}, {9, 1}, {5, 0}, {5, 1},
			{5, 2}, {5, 3}, {6, 2}, {6, 1}, {6, 3}, {3, 3}, {7, 3}, {7, 0}, {7, 1}, {7, 2},
			{4, 2}, {10, 0}, {10, 1}, {0, 2}, {6, 0}, {0, 3}, {4, 3}, {8, 0}};


	private String[][] WeaponDescTable = 
		{{"Bazooka", "Launches a single projectile which explodes on impact."},
			{"Homing Missile", "Launches a single homing projectile which explodes on impact."},
			{"Grenade", "Launches a bomb which explodes after a set amount of time."},
			{"Cluster Bomb", "Launches a bomb which explodes and spreads exploding clusters","after a set amount of time."},
			{"Banana Bomb", "Launches a bounching bomb which explodes and spreads","exploding clusters after an amount of time."},
			{"Holy Hand Grenade", "Launches a bomb which explodes a set amount of time after it has","landed."},
			{"Homing Cluster Bomb", "Launches a bomb which explodes and spreads homing exploding","clusters after a set amount of time."},
			{"Petrol Bomb", "Launches a bottle of flamable liquid which spreads fire over an area."},
			{"Shotgun", "Fires two separate gunshots."},
			{"Handgun", "Fires one round of bullets, one at a time in succession."},
			{"Uzi", "Fires a lot of bullets in moderate succession."},
			{"Minigun", "Rapidly fires a bunch of bullets."},
			{"Fire Punch", "Jumps high and packs a solid punch to all surroundings."},
			{"Dragon Ball", "Fires a ball of energy vertically, which hits anything in it's way."},
			{"Kamikaze", "Worm soars through anything in a given direction before exploding","after a short time."},
			{"Dynamite", "Places a stick of dynamite which explodes after a fixed time."},
			{"Mine", "Places a mine which explodes whenever surrounding motion is","detected."},
			{"Ming Vase", "Places a vase which explodes into exploding bomblets after a fixed","amount of time."},
			{"Air Strike", "Drops exploding bomblets form the sky at a target."},
			{"Homing Strike", "Drops homing and exploding bomblets from the sky at a target."},
			{"Napalm Strike", "Drops an amount of burning fluid from the sky at a target."},
			{"Mail Strike", "Drops several explosive letters from the sky towards a target."},
			{"Girder", "Places a platform into the envroniment."},
			{"Drill", "Digs right down for a moment, damaging anything in the way."},
			{"Baseball Bat", "Packs a solid aimed punch toward a target."},
			{"Prod", "Pushes a worm in a fixed angle."},
			{"Teleport", "Teleports the worm to a selected location."},
			{"Ninja Rope", "The worm can use it to swing through the air to reach different","locations."},
			{"Bungee", "The worm can use it to bounce and then launch itself to a different","location."},
			{"Parachute", "Used to prevent fall damage when jumping off tall cliffs."},
			{"Sheep", "Releases a sheep which bounches around explodes on command","or after a fixed time."},
			{"Mad Cow", "Releases a hoard of cows which explodes on the first hinder in their","way."},
			{"Old Woman", "Releases an old woman which explodes on command or after a","fixed amount of time."},
			{"Mortar", "Launches a projectile which explodes on impact, while dropping off","explosive clusters."},
			{"Blowtorch", "Digs to either side for a moment, damaging anything in the way."},
			{"Homing Pigeon", "Releases a homing pigeon which will do wathever it can to find and","follow a path to a target. It explodes on target, impact or after a fixed","time."},
			{"Super Sheep", "Releases a sheep which can at will be commanded anywhere it can","fly. Explodes on impact or after a fixed time."},
			{"Super Banana Bomb", "Launches a bomb which explodes on command, spreading","exploding clusters."}};

	public void setScale(double scale) {
		setWeapon(id, scale);
	}
}
