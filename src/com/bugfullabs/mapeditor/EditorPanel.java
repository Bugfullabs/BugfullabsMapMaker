package com.bugfullabs.mapeditor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

/*
 * Nowe blocki tutaj w kilku miejscach oraz w ItemsPanel w jednym miejscu
 * Dziêki
 * 
 */

@SuppressWarnings("serial")
public class EditorPanel extends JPanel implements MouseListener, MouseMotionListener {
	
	private Graphics2D g2d;
	private TexturePack tx;
	private static int item_id;
	public PlayerEntity player;
	
	public Level level;
	
	EditorPanel(String name, TexturePack texture, int x, int y, int width, int height){
		super();
		
		level = new Level(1, 1, texture.getFileName(), width/32, height/32, -1, -1);
		this.player = level.player;
		level.setPlayer(player);

		addMouseListener(this);
		addMouseMotionListener(this);

		this.tx = texture;
		
		this.setName(name);
		this.setBounds(x, y, width, height);
		this.setBackground(Color.DARK_GRAY);
	}
	
	@Override
	public void paintComponent(Graphics g){
		
		g2d = (Graphics2D) g;
		g2d.clearRect(0, 0, this.getWidth(), this.getHeight());

		drawGrid(32, 32);
		
		
			for(int i = 0; i < level.getWidth(); i++){
				for(int j = 0; j < level.getHeight(); j++){

				g2d.drawImage(tx.getTextureRegion(level.getItem(i, j)), null, i*32, j*32);

				System.out.printf("\nITEM ATTS i: " + Integer.toString(i) + " j: " + Integer.toString(j) + " ATTS: " + Integer.toString(level.getItemAtts(i, j)));
				
				if(level.getItemAtts(i, j) != 0)
					g2d.drawImage(tx.getTextureRegion(level.getItemAtts(i, j)), null, i*32, j*32);
			}

		}
		

		if (player.getRow() >= 0 && player.getColumn() >= 0)
			g2d.drawImage(tx.getTextureRegion(Values.PLAYER_ID), null, player.getColumn()*32, player.getRow()*32);

	
	}

	
	private void drawGrid(int OffX, int OffY){
	
		Rectangle rect = this.getBounds();
		
		g2d.setColor(Color.gray);
		
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
	
	public void repaintIt() {
		repaint();
	}
	
	public void setLevel(Level l) {
		this.level = l;
		this.level.setPlayer(l.player);
		System.out.println("Level id: " + this.level.getId() + ", LevelPackId: " + this.level.getLevelPack());
	}
	

	public void setTexturePack(TexturePack texture) {
		this.tx = texture;
	}
	


	@Override
	public void mousePressed(MouseEvent e) {

		if (level == null) {
			System.out.println("LEVEL NULL!");
		}
		
		System.out.println("position_editor: " + e.getX() + ", " + e.getY() + ", item id: " + item_id + ", item atts: " + level.getItemAtts(e.getX()/32, e.getY()/32));
		//PLAYER
		if (e.getButton() == MouseEvent.BUTTON2) {
			player.setColumn(e.getX()/32);
			player.setRow(e.getY()/32);
			
		}else if(e.getButton() == MouseEvent.BUTTON1){
		//OTHER	
			
		switch(item_id){
		
		case Values.FLAME_ID: //FLAME
			level.setItemAtts(e.getX()/32, e.getY()/32, Values.FLAME_ID);
			System.out.printf("KEY\n");
			System.out.print(level.getItemAtts(e.getX()/32, e.getY()/32));
		break;
		
		case Values.KEY_ID:
			level.setItemAtts(e.getX()/32, e.getY()/32, Values.KEY_ID);
			System.out.printf("KEY\n");
			System.out.print(level.getItemAtts(e.getX()/32, e.getY()/32));
		break;
		
		case Values.LOCK_ID:
			level.setItemAtts(e.getX()/32, e.getY()/32, Values.LOCK_ID);
			System.out.printf("LOCK\n");
			System.out.print(level.getItemAtts(e.getX()/32, e.getY()/32));
			break;
		
		
		default:
		level.setItem(item_id, e.getX()/32, e.getY()/32);
		break;
		}
		
		repaint();
		
		}
		
		
		
		if (e.getButton() == MouseEvent.BUTTON3)
		{
				
			if(item_id == Values.LOCK_ID || item_id == Values.KEY_ID || item_id == Values.FLAME_ID)
				level.setItemAtts(e.getX()/32, e.getY()/32, 0);
			else
				level.setItem(Values.SOLID_ID, e.getX()/32, e.getY()/32);//SOLID
			
		}
		
		repaint();
	}


	@Override
	public void mouseDragged(MouseEvent e) {

		level.setItem(item_id, e.getX()/32, e.getY()/32);

		
		repaint();
		
	}
	
	
	
	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
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
	
	
}