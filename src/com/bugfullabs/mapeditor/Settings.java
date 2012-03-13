package com.bugfullabs.mapeditor;

import javax.swing.JButton;
import javax.swing.JFrame;



public class Settings{
	
	private JFrame settings;
	private JButton textureButton;
	
	
	Settings(JFrame frame){
		
		settings = new JFrame();
		
		settings.setSize(200, 400);
		settings.setTitle("Settings");
		settings.setVisible(true);

		textureButton = new JButton("Set TexturePack Default Folder");
		textureButton.setBounds(50, 50, 100, 50);
		settings.add(textureButton);
		
		
	
	}
	
}