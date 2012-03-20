package com.bugfullabs.mapeditor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;


@SuppressWarnings("serial")
public class EditorPanel extends JPanel implements MouseListener{
	
	private Graphics2D g2d;
	private boolean drawing = false;
	private TexturePack tx;
	private static int item_id;
	
	private Level level;
	
	EditorPanel(String name, TexturePack texture, int x, int y, int width, int height){
		super();
		
		level = new Level(1, 1, "texturepack_02", width, height);
		
		addMouseListener(this);
		
		this.tx = texture;
		
		this.setName(name);
		this.setBounds(x, y, width, height);
		this.setBackground(Color.DARK_GRAY);
	}
	
	@Override
	public void paintComponent(Graphics g){
		
		g2d = (Graphics2D) g;
		drawGrid(32, 32);

		if (drawing)
			if(level.getWidth() > 0){
				
				int j = 0;
				int k = 0;
				
				for(int i = 0; i < level.getWidth(); i++){
	
					if(level.getItem(k, j) != 0)
						g2d.drawImage(tx.getTextureRegion(level.getItem(k, j)), null, k*32, j*32);
					
					k++;
			
					if(k >= this.getWidth()/32){
						k = 0;
						j++;
					}
			
				}
	
			}

		
	}

	
	private void drawGrid(int OffX, int OffY){
	
		Rectangle rect = this.getBounds();
		
		g2d.setColor(Color.blue);
		
		for(int i = 0; i < (int)rect.getWidth(); i = i + OffX){
			g2d.drawLine(i, 0, i, (int) rect.getHeight());
		}
		
		for(int i = 0; i < (int)rect.getHeight(); i = i + OffY){
			g2d.drawLine(0, i, (int) rect.getWidth(), i);
		}
		
		
	}
	
	public static void selectItem(int id) {
		item_id = id;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {

		drawing = true;
		System.out.println("position_editor: " + e.getX() + ", " + e.getY() + ", item id: " + item_id);
		level.setItem(item_id, e.getX()/32, e.getY()/32);
		repaint();
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		drawing = false;
		
	}
	
}
