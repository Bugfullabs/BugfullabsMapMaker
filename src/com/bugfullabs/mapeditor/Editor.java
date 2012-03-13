package com.bugfullabs.mapeditor;

import java.awt.Color;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JDesktopPane;

public class Editor {

	private JDesktopPane mDesktop;
	private ItemsPanel mItemsPanel;
	private EditorPanel mEditorPanel;

	
	public Editor(JDesktopPane pDesktop, File file){
		
		this.mDesktop = pDesktop;
		
		mEditorPanel = new EditorPanel("Editor", 210, 10, 800, 480);
		mEditorPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		mEditorPanel.setVisible(true);
		
		mDesktop.add(mEditorPanel);
		
		TexturePack tx = TexturePackLoader.createFromFile(file);
		
		mItemsPanel = new ItemsPanel("Items", tx, 10, 10, 200, 480);
		mItemsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		mItemsPanel.setVisible(true);
		
		mDesktop.add(mItemsPanel);
		
		mDesktop.repaint();
	}


	
	
}