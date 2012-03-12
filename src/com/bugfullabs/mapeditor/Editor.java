package com.bugfullabs.mapeditor;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

public class Editor {

	private JDesktopPane mDesktop;
	private ItemsPanel mItemsPanel;
	private EditorPanel mEditorPanel;

	
	public Editor(JDesktopPane pDesktop){
		
		this.mDesktop = pDesktop;
		
		mEditorPanel = new EditorPanel("Editor", 200, 0, 800, 480);
		mEditorPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		mEditorPanel.setVisible(true);
		
		mDesktop.add(mEditorPanel);
		
		mItemsPanel = new ItemsPanel("Items", 0, 0, 200, 480);
		mItemsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		mItemsPanel.setVisible(true);
		
		mDesktop.add(mItemsPanel);
		
	}


	
	
}