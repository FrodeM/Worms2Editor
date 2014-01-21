package Worms2Editor;

import java.awt.Dimension;

import Worms2Editor.EditorFrame.EditorPanels;

public class Options extends EditorPanel {

	public Options(EditorFrame vindu)
	{
		vindu.setTitle("Worms 2 - Scheme Configuration Editor");
		setPreferredSize(new Dimension(640, 400));
		vindu.changeTo(EditorPanels.MENU);
	}

	@Override
	public boolean closeFunc() {
		// TODO Auto-generated method stub
		return true;
	}
	
}
