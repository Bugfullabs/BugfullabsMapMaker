package com.bugfullabs.mapeditor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;

/*
 * Nowe blocki tutaj w kilku miejscach oraz w ItemsPanel w jednym miejscu
 * Dzi�ki
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
		
		level = new Level(1, 1, texture.getFileName(), width, height, -1, -1, 1);
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
		g2d.drawImage(tx.getTextureRegion(16), null, 0, 0);

		drawGrid(32, 32);
		
		if (player.getRow() >= 0 && player.getColumn() >= 0)
			g2d.drawImage(tx.getTextureRegion(0), null, player.getColumn()*32, player.getRow()*32);

		if (level.getWidth() > 0) {
			
			int j = 0;
			int k = 0;

			for(int i = 0; i < level.getWidth()/32 * level.getHeight()/32; i++) {

//				System.out.print("(" + k + "," + j + ") ");

				if (level.getItem(k, j) != 0 && level.getItem(k, j) != 2 && level.getItem(k, j) != 7 && level.getItem(k, j) != 8) {
					g2d.drawImage(tx.getTextureRegion(level.getItem(k, j)), null, k*32, j*32);
				}
				else if (level.getItem(k, j) == 2 || level.getItem(k, j) == 7 || level.getItem(k, j) == 8) {
					AffineTransform xform = new AffineTransform();
					xform.translate(k*32, j*32);
					xform.rotate(level.getItemAtts(k, j)*(Math.PI/2), tx.getTextureRegion(level.getItem(k, j)).getWidth()/2, tx.getTextureRegion(level.getItem(k, j)).getHeight()/2);
					g2d.drawImage(tx.getTextureRegion(level.getItem(k, j)), xform, null);
				}
				
				k++;
		
				if (k >= this.getWidth()/32) {
					k = 0;
					j++;
				}

				if (j >= this.getHeight()/32) {
					break;
				}
		
			}
	
		}
	
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

		if (level == null) {
			System.out.println("LEVEL NULL!");
		}

		if (level.getItem(e.getX()/32, e.getY()/32) == 0 && ItemsPanel.playerSelected() && e.getButton() == MouseEvent.BUTTON1) {
			player.setColumn(e.getX()/32);
			player.setRow(e.getY()/32);
			level.setItem(0, e.getX()/32, e.getY()/32, 0);
		}
		else if (((level.getItem(e.getX()/32, e.getY()/32) == 2 && ItemsPanel.finishSelected()) || (level.getItem(e.getX()/32, e.getY()/32) == 7 && ItemsPanel.letPassSelected()) || (level.getItem(e.getX()/32, e.getY()/32) == 8 && ItemsPanel.letPassOneDirSelected())) && e.getButton() == MouseEvent.BUTTON1) {
			if (level.getItemAtts(e.getX()/32, e.getY()/32) < 3) {
				System.out.println(level.getItemAtts(e.getX()/32, e.getY()/32));
				level.setItemAtts(e.getX()/32, e.getY()/32, level.getItemAtts(e.getX()/32, e.getY()/32) + 1);
				System.out.println(level.getItemAtts(e.getX()/32, e.getY()/32));
			}
			else
				level.setItemAtts(e.getX()/32, e.getY()/32, 0);
			BugfullabsMapEditor.mEditor.mEditorPanel.repaintIt();
		}
		
		System.out.println("position_editor: " + e.getX() + ", " + e.getY() + ", item id: " + item_id + ", item atts: " + level.getItemAtts(e.getX()/32, e.getY()/32));
		if (e.getButton() == MouseEvent.BUTTON1 && !(level.getItem(e.getX()/32, e.getY()/32) == 2 && ItemsPanel.finishSelected()) && !(level.getItem(e.getX()/32, e.getY()/32) == 7 && ItemsPanel.letPassSelected()) && !(level.getItem(e.getX()/32, e.getY()/32) == 8 && ItemsPanel.letPassOneDirSelected()))
			level.setItem(item_id, e.getX()/32, e.getY()/32, 0);
		else if (e.getButton() == MouseEvent.BUTTON3)
			level.setItem(0, e.getX()/32, e.getY()/32, 0);
		repaint();
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {

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
	public void mouseDragged(MouseEvent e) {

		level.setItem(item_id, e.getX()/32, e.getY()/32, 0);
		repaint();
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}