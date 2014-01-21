package Worms2Editor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Worms2Editor.EditorFrame.EditorPanels;

public class Meny extends EditorPanel implements ActionListener {

	private JButton wepbutton;
	private EditorFrame vindu;

	public Meny(EditorFrame v)
	{
		vindu = v;
		vindu.setTitle("Worms 2 SuperConfig - Main Menu");
		setPreferredSize(new Dimension(640, 400));
		wepbutton = new JButton("Back To Weapons Editor");
		wepbutton.addActionListener(this);
		add(wepbutton);
	}

	public void paintComponent (Graphics side)
	{		
		side.setColor(Color.lightGray);
		side.fillRect(0, 0, getWidth(), getHeight());
		side.setColor(Color.black);
		Font font = side.getFont();
		side.setFont(new Font("Arial", Font.BOLD, 40));
		side.drawString("Main Menu, Under Construction...",10,75);
		side.setFont(new Font("Arial", Font.PLAIN, 25));
		side.drawString("Worms 2 SuperConfig",50,250);
		side.drawString("Code and Design by Frode van der Meeren.",50,310);
		side.drawString("Version Beta - 20 December 2013",50,280);
		side.setFont(new Font("Arial", Font.BOLD, 75));
		String st = "Credits:";
		side.drawString(st,50,175);
		side.setColor(Color.darkGray);
		side.drawString(st,55,180);
		side.setColor(Color.gray);
		side.drawString(st,60,185);
		side.setColor(Color.lightGray);
		side.drawString(st,65,190);
		side.setColor(Color.white);
		side.drawString(st,70,195);
		side.setColor(Color.black);
		side.setFont(font);
	}

	@Override
	public boolean closeFunc() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == wepbutton)
			vindu.changeTo(EditorPanels.WEAPONS);

	}

}
