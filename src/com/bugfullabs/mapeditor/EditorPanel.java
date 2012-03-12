package com.bugfullabs.mapeditor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JPanel;


@SuppressWarnings("serial")
public class EditorPanel extends JPanel{
	
	private Graphics2D g2d;
	
	EditorPanel(String name, int x, int y, int width, int height){
		super();
		this.setName(name);
		this.setBounds(x, y, width, height);
		this.setBackground(Color.DARK_GRAY);
	}
	
	@Override
	public void paintComponent(Graphics g){
		
		g2d = (Graphics2D) g;
		drawGrid(32, 32);
		
	}

	
	private void drawGrid(int OffX, int OffY){
	
		Rectangle rect = this.getBounds();
		
		for(int i = 0; i < (int)rect.getWidth(); i = i + OffX){
			g2d.drawLine(i, 0, i, (int) rect.getHeight());
		}
		
		for(int i = 0; i < (int)rect.getHeight(); i = i + OffY){
			g2d.drawLine(0, i, (int) rect.getWidth(), i);
		}
		
		
	}
	
}
