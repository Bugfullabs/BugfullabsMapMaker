package com.bugfullabs.mapeditor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;


@SuppressWarnings("serial")
public class ItemsPanel extends JPanel{
	
	private Graphics2D g2d;
	
	ItemsPanel(String name, int x, int y, int width, int height){
		super();
		this.setName(name);
		this.setBounds(x, y, width, height);
		this.setBackground(Color.YELLOW);
	}
	
	@Override
	public void paintComponent(Graphics g){
		
		g2d = (Graphics2D) g;
		
		g2d.drawLine(0, 0, 200, 200);
		
	}
	
}