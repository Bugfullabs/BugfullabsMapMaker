package com.bugfullabs.mapeditor;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;




public class Menu implements ActionListener{
	
	JFrame mFrame;
	
	JMenuBar menuBar;
	JMenu menu;

	public JMenuItem menuNewFile;
	public JMenuItem menuSave;
	public JMenuItem menuAbout;
	public JMenuItem menuSettings;
	
	public Menu(JFrame pFrame){
		
		this.mFrame = pFrame;
		this.createMenu();
	}
	
    private void createMenu(){
	      menuBar = new JMenuBar();
	      menu = new JMenu("File");
	      menu.setMnemonic(KeyEvent.VK_F);
	      menuBar.add(menu);
	      
	      menuNewFile = new JMenuItem("New File...", KeyEvent.VK_N);
	      menuNewFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.ALT_MASK));
	      menuNewFile.addActionListener(this);
	      menu.add(menuNewFile);
	      
	      menuSave = new JMenuItem("Save...", KeyEvent.VK_S);
	      menuSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK));
	      menuSave.addActionListener(this);
	      menu.add(menuSave);
	      
	      
	      
	      menu = new JMenu("Settings");
	      menu.setMnemonic(KeyEvent.VK_S);
	      menuBar.add(menu);	 
	      
	      menuSettings = new JMenuItem("Settings", KeyEvent.VK_T);
	      menuSettings.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.ALT_MASK));
	      menuSettings.addActionListener(this);
	      menu.add(menuSettings);
	      
	      
	      menu = new JMenu("Help");
	      menu.setMnemonic(KeyEvent.VK_H);
	      menuBar.add(menu);

	      menuAbout = new JMenuItem("About...", KeyEvent.VK_A);
	      menuAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.ALT_MASK));
	      menuAbout.addActionListener(this);
	      menu.add(menuAbout);     
	      
	      
	      
	      menuBar.setVisible(true);
	      menu.setVisible(true);
	      
	      mFrame.setJMenuBar(menuBar);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Menu.this.onAction(e);
	}
	

	public void onAction(ActionEvent e){
	
	}
	
}