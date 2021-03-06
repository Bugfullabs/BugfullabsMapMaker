package com.bugfullabs.mapeditor;

import java.awt.Color;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JDesktopPane;

public class Editor {

	private JDesktopPane mDesktop;
	private ItemsPanel mItemsPanel;
	public EditorPanel mEditorPanel;
	
	public static int selected_item_id;

	
	public Editor(JDesktopPane pDesktop, File file){
		
		this.mDesktop = pDesktop;
		
		TexturePack tx = TexturePackLoader.createFromFile(file);
		
		mEditorPanel = new EditorPanel("Editor", tx, 210, 10, 800, 480);
		mEditorPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		mEditorPanel.setVisible(true);
		
		mDesktop.add(mEditorPanel);
		
		mItemsPanel = new ItemsPanel("Items", tx, 10, 10, 200, 480);
		mItemsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		mItemsPanel.setVisible(true);
		
		mDesktop.add(mItemsPanel);
		
		mDesktop.repaint();
	}

	public void setSelectedItemId(int id) {
		selected_item_id = id;
	}

	public void setTexturePack(File texture) {
		TexturePack tx = TexturePackLoader.createFromFile(texture);
		mItemsPanel.setTexturePack(tx);
		mEditorPanel.setTexturePack(tx);
		
	}
	
	public int getSelectedItemId() {
		return selected_item_id;
	}

	public void clear() {
			mDesktop.removeAll();
			mItemsPanel.removeAll();
			mEditorPanel.removeAll();
			selected_item_id = 1;
			mEditorPanel.level = null;
	
			System.out.println("mEditor.clear();");
	}

}