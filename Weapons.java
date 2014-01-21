package Worms2Editor;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import Worms2Editor.EditorFrame.EditorPanels;

public class Weapons extends EditorPanel implements MouseListener, ActionListener, ChangeListener{

	private int[][] data = new int[38][35];
	private int[][] databak = new int[0][0];
	private BufferedImage weaponimg = null;
	private BufferedImage ikoner = null;
	private WeaponsOptionList options = null;
	private JScrollPane scrollpane;
	private EditorFrame vindu;
	private Bildepanel meny;
	private WeaponsHeader header;
	private JButton load = new JButton("Load");
	private JButton save = new JButton("Save");
	private JButton clear = new JButton("Clear");
	private JButton toMenu = new JButton("Menu");
	private File lastfile = null;
	private boolean constructordone = false;
	private double xscale = 1;
	private double yscale = 1;
	private Timer resizetimer = new Timer(50, this);
	private Dimension windowsize = new Dimension(0,0);
	private Font buttonFont;
	private boolean resizeready = true;
	
	public Weapons(EditorFrame v)
	{
		vindu = v;
		vindu.setTitle("Worms 2 SuperConfig - Weapons Configuration Editor");

		setLayout(null);
		setPreferredSize(new Dimension(640, 400));

		try {
			ikoner = ImageIO.read(getClass().getResource("WepOpt.gif"));
			weaponimg = ImageIO.read(getClass().getResource("W2Wep.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		header = new WeaponsHeader(weaponimg, 30, 1);
		meny = new Bildepanel(weaponimg, 30, 1);
		meny.addMouseListener(this);

		load.addActionListener(this);
		save.addActionListener(this);
		clear.addActionListener(this);
		toMenu.addActionListener(this);
		//toMenu.setEnabled(false);
		
		JButton b = new JButton("");
		buttonFont = b.getFont();

		resetData();
		resizetimer.start();
		constructordone = true;
	}
	
	private void scaleJbuttonFont(Container label)
	{		
		label.setFont(new Font(buttonFont.getName(), Font.PLAIN, (int)(buttonFont.getSize()*Math.min(xscale, yscale))));
	}

	private void resetData()
	{
		
		for(int i = 0; i<data.length; i++)
			for(int j = 0; j<data[i].length; j++)
				data[i][j] = 0;
		options = null;
		for(int i = 0; i<data.length; i++)
			data[i] = WeaponsOptionList.getDefault(i);
		databak = data.clone();
		loadWeapon(0);
	}

	private void setScaledBounds(Container c, int x, int y, int h, int b)
	{
		c.setBounds((int)(x*xscale),(int)(y*yscale),(int)(h*xscale),(int)(b*yscale));
	}

	private void buildGUI()
	{
		removeAll();

		scaleJbuttonFont(toMenu);
		setScaledBounds(toMenu,3,3,70,40);
		add(toMenu);

		scaleJbuttonFont(load);
		setScaledBounds(load,76,3,70,40);
		add(load);

		scaleJbuttonFont(clear);
		setScaledBounds(clear,3,46,70,40);
		add(clear);

		scaleJbuttonFont(save);
		setScaledBounds(save,76,46,70,40);
		add(save);

		setScaledBounds(meny,0,90,150,310);
		add(meny);

		setScaledBounds(header,150,0,490,90);
		add(header);

		setScaledBounds(scrollpane,150,90,490,310);
		add(scrollpane);

		vindu.pack();
		vindu.setFocusable(true);
		vindu.requestFocus();
	}



	private int[][] WeaponsTableGraph =
		{{0, 1, 33, 35},
			{2, 3, 4, 6},
			{8, 9, 10, 11},
			{12, 13, 14, 25},
			{15, 16, 30, 36},
			{18, 19, 20, 21},
			{34, 23, 22, 24},
			{27, 28, 29, 26},
			{37, 5, -1, -1},
			{7, 17, -1, -1},
			{31, 32, -1, -1}};

	private int[][] WeaponsGraphTable =
		{{0, 0}, {0, 1}, {1, 0}, {1, 1}, {1, 2}, {8, 1}, {1, 3}, {9, 0}, {2, 0}, {2, 1},
			{2, 2}, {2, 3}, {3, 0}, {3, 1}, {3, 2}, {4, 0}, {4, 1}, {9, 1}, {5, 0}, {5, 1},
			{5, 2}, {5, 3}, {6, 2}, {6, 1}, {6, 3}, {3, 3}, {7, 3}, {7, 0}, {7, 1}, {7, 2},
			{4, 2}, {10, 0}, {10, 1}, {0, 2}, {6, 0}, {0, 3}, {4, 3}, {8, 0}};
	

	@Override
	public boolean closeFunc() {
		resizetimer.stop();
		return true;
	}

	@Override
	public void paintComponent(Graphics g)
	{
		g.setColor(new Color(220,220,220));
		g.fillRect(0, 0, getWidth(), getHeight());
	}

	private void loadWeapon(int nr)
	{
		if(nr >= 0)
		{
			updateData();
			if(constructordone)
				options = new WeaponsOptionList(nr,data[nr], scrollpane.getWidth()-16, Math.min(xscale, yscale), ikoner, this);
			else
				options = new WeaponsOptionList(nr,data[nr], 474, 1, ikoner, this);
			scrollpane =  new JScrollPane(options);
			scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			header.setWeapon(nr, Math.min(xscale, yscale));
			buildGUI();
		}
	}




	@Override
	public void mouseClicked(MouseEvent arg0) {

	}






	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}






	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}






	@Override
	public void mousePressed(MouseEvent arg0) {
		int[] pos = meny.tile(arg0.getX(), arg0.getY());

		int x = pos[0];
		int y = pos[1];
		if(y < WeaponsTableGraph.length && x < WeaponsTableGraph[y].length && y >= 0 && x >= 0)
			loadWeapon(WeaponsTableGraph[y][x]);
	}






	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	private void updateData(){
		if(options != null)
			data[options.getWeaponNr()] = options.getValues();
		int[][] dat = new int[44][2];
		for(int i = 0; i<dat.length; i++)
			dat[i][0] = -1;
		for(int i = 0; i<data.length; i++)
		{
			int j = WeaponsGraphTable[i][0]*4 + WeaponsGraphTable[i][1];
			dat[j][0] = data[i][0];
			dat[j][1] = data[i][1];
		}
		meny.showNumbers(dat);
	}

	private boolean unsavedWarning(){
		boolean equal = true;

		updateData();
		if(databak.length == data.length)
		{
			for(int i=0; i<data.length; i++)
				for(int j=0; j<data[i].length; j++)
					if(data[i][j] != databak[i][j])
						equal = false;
		}
		else
			equal = false;

		boolean goQuit = true;
		if(!equal)
		{
			JDialog meldingboks = new JDialog();
			meldingboks.setModal(true);
			meldingboks.setDefaultCloseOperation (JDialog.DISPOSE_ON_CLOSE);

			int selection = JOptionPane.showConfirmDialog(meldingboks, "This will delete all unsaved data. Do you want to save your work?", "Warning!", JOptionPane.YES_NO_CANCEL_OPTION);

			if(selection == JOptionPane.OK_OPTION)
				goQuit = savedata();
			if(selection == JOptionPane.CANCEL_OPTION)
				goQuit = false;
		}

		return goQuit;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		if(arg0.getSource() == toMenu)
		{
			if(unsavedWarning())
				vindu.changeTo(EditorPanels.MENU);
		}
		if(arg0.getSource() == clear)
		{
			if(unsavedWarning())
				resetData();
		}
		if(arg0.getSource() == save)
		{
			savedata();
		}
		if(arg0.getSource() == load)
		{
			unsavedWarning();
			loaddata();
		}
		if(arg0.getSource() == resizetimer && resizeready && !vindu.getSize().equals(windowsize) && vindu.isVisible())
		{
			resizeready = false;
			windowsize = vindu.getSize();
			xscale = getWidth()/640.0;
			yscale = getHeight()/400.0;
			setPreferredSize(new Dimension(getWidth(), getHeight()));

			header.setScale(Math.min(xscale, yscale));

			options.buildGUI((int)(490*xscale)-16, Math.min(xscale, yscale));
			buildGUI();
			repaint();
			resizeready = true;
		}

	}

	private void loaddata() {
		try {
			JFileChooser inp = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Worms 2 Weapons File", "wep");
			inp.removeChoosableFileFilter(inp.getAcceptAllFileFilter());
			inp.setFileFilter(filter);

			if(lastfile != null)
				inp.setSelectedFile(lastfile);

			int valg = inp.showOpenDialog(null);
			File f = inp.getSelectedFile();
			if(valg == JFileChooser.APPROVE_OPTION)
			{
				lastfile = f;
				FileInputStream a = new FileInputStream(f);
				byte[] dat = new byte[a.available()];
				a.read(dat);
				a.close();
				if(dat[16] == 'W' && dat[17] == 'E' && dat[18] == 'P' && dat[19] == 'F' && dat[20] == 'I' && dat[21] == 'L' && dat[22] == 'E')
				{
					for(int i=0; i<data.length; i++)
						for(int j=0; j<data[i].length; j++)
							data[i][j] = readSInt32(dat, 24+j*4+i*data[i].length*4);
					databak = data.clone();
					options = null;
					loadWeapon(0);	
				}
			}
			else
				throw new NullPointerException();
		} catch (IOException e) {
			System.out.println("Kunne ikke lese fra fil!");
		} catch (NullPointerException e) {
			System.out.println("Ingen fil valgt!");
		}
	}

	private boolean savedata() {

		updateData();

		byte[] dat = new byte[5344];
		for(int i = 0; i<16; i++)
			dat[i] = (byte)(Math.random()*256);
		dat[16] = 'W';
		dat[17] = 'E';
		dat[18] = 'P';
		dat[19] = 'F';
		dat[20] = 'I';
		dat[21] = 'L';
		dat[22] = 'E';
		dat[23] = 0;
		for(int i=0; i<data.length; i++)
			for(int j=0; j<data[i].length; j++)
				WriteSInt32(dat, 24+j*4+i*data[i].length*4, data[i][j]);

		boolean success = false;

		try {	
			JFileChooser inp = new JFileChooser();

			FileNameExtensionFilter filter = new FileNameExtensionFilter("Worms 2 Weapons File", "wep");
			inp.removeChoosableFileFilter(inp.getAcceptAllFileFilter());
			inp.setFileFilter(filter);

			if(lastfile != null)
				inp.setSelectedFile(lastfile);

			int valg = inp.showSaveDialog(null);
			if(valg == JFileChooser.APPROVE_OPTION)
			{
				File f = inp.getSelectedFile();
				if(!f.getName().endsWith(".wep"))
					f = new File(f.getAbsolutePath() + ".wep");
				FileOutputStream a = new FileOutputStream(f);
				a.write(dat);
				a.close();
				databak = data.clone();
				success = true;
			}
			else
				throw new NullPointerException();
		} catch (IOException ex) {
			System.out.println("Kunne ikke skrive til fil!");
		} catch (NullPointerException e) {
			System.out.println("Ingen fil valgt!");
		}

		return success;
	}

	private void WriteSInt32(byte[] list, int pos, int val) {
		list[pos] = (byte)((val)&0xFF);
		list[pos+1] = (byte)((val>>8)&0xFF);
		list[pos+2] = (byte)((val>>16)&0xFF);
		list[pos+3] = (byte)((val>>24)&0xFF);
	}

	private int readSInt32(byte[] list, int pos) {
		int a = (list[pos]+256)%256;
		int b = ((list[pos+1]+256)%256<<8);
		int c = ((list[pos+2]+256)%256<<16);
		int d = ((list[pos+3]+256)%256<<24);
		return a+b+c+d;
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		updateData();
	}

}
