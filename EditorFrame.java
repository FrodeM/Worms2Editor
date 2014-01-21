package Worms2Editor;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class EditorFrame extends JFrame {


	public static void main(String[] args) {
		new EditorFrame();
	}

	public EditorFrame()
	{
		BufferedImage ikon = null;
		try {
			ikon = ImageIO.read(getClass().getResource("Icon.gif"));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		if(ikon != null)
			setIconImage(ikon);
		setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		changeTo(EditorPanels.WEAPONS);
		setVisible(true);
	}

	
	public enum EditorPanels {
		MENU,
		OPTIONS,
		WEAPONS
	}
	
	private EditorPanel vises = null;
	
	public void changeTo(EditorPanels panel){
		boolean b = true;
		if(vises != null)
			b = vises.closeFunc();
		if(b)
		{
			if(panel == EditorPanels.MENU)
				vises = new Meny(this);
			if(panel == EditorPanels.OPTIONS)
				vises = new Options(this);
			if(panel == EditorPanels.WEAPONS)
				vises = new Weapons(this);
			setContentPane(vises);
			pack();
		}
	}
}
