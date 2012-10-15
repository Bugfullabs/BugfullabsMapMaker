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
 * 
 */

@SuppressWarnings("serial")
public class EditorPanel extends JPanel implements MouseListener, MouseMotionListener {
	
	private Graphics2D g2d;
	private TexturePack tx;
	private static int item_id;
	public PlayerEntity player;
	
	public static int EMPTY = 0;
	public static int SOLID = 1;
	public static int FINISH = 2;
	public static int ARROW = 3;
	public static int LET_PASS = 4;
	public static int LET_PASS_ONE_DIR = 5;
	public static int STAR = 6;

	public static int DIR_SWITCH = 9;
	public static int DIR_SWITCH_TURN = 10;
	
	public static int LOCK_LOCKED = 7;
	public static int LOCK_KEY = 8;
	public static int LOCK_UNLOCKED = 18;

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

				if (level.getItem(k, j) != EMPTY && level.getItem(k, j) != FINISH && level.getItem(k, j) != ARROW && level.getItem(k, j) != DIR_SWITCH && level.getItem(k, j) != DIR_SWITCH_TURN && level.getItem(k, j) != LET_PASS && level.getItem(k, j) != LET_PASS_ONE_DIR && level.getItem(k, j) != LOCK_LOCKED) {
					g2d.drawImage(tx.getTextureRegion(level.getItem(k, j)), null, k*32, j*32);
				}
				else if (level.getItem(k, j) == FINISH || level.getItem(k, j) == ARROW || level.getItem(k, j) == DIR_SWITCH || level.getItem(k, j) == DIR_SWITCH_TURN || level.getItem(k, j) == LET_PASS || level.getItem(k, j) == LET_PASS_ONE_DIR) {
					AffineTransform xform = new AffineTransform();
					xform.translate(k*32, j*32);
					xform.rotate(level.getItemAtts(k, j)*(Math.PI/2), tx.getTextureRegion(level.getItem(k, j)).getWidth()/2, tx.getTextureRegion(level.getItem(k, j)).getHeight()/2);
					g2d.drawImage(tx.getTextureRegion(level.getItem(k, j)), xform, null);
				}
				else if (level.getItem(k, j) == LOCK_LOCKED) {
					if (level.getItemAtts(k, j) == 0)
						g2d.drawImage(tx.getTextureRegion(LOCK_LOCKED), null, k*32, j*32);
					else
						g2d.drawImage(tx.getTextureRegion(LOCK_UNLOCKED), null, k*32, j*32);
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

	public void setPlayerDir(int dir) {
		player.setDir(dir);
	}

	public int getPlayerDir() {
		return player.getDir();
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
		
		if (e.getButton() == MouseEvent.BUTTON1 && !(level.getItem(e.getX()/32, e.getY()/32) == FINISH && ItemsPanel.finishSelected()) && !(level.getItem(e.getX()/32, e.getY()/32) == ARROW && ItemsPanel.arrowSelected()) && !(level.getItem(e.getX()/32, e.getY()/32) == DIR_SWITCH && ItemsPanel.dirSwitchSelected()) && !(level.getItem(e.getX()/32, e.getY()/32) == DIR_SWITCH_TURN && ItemsPanel.dirSwitchTurnSelected()) && !(level.getItem(e.getX()/32, e.getY()/32) == LET_PASS && ItemsPanel.letPassSelected()) && !(level.getItem(e.getX()/32, e.getY()/32) == LET_PASS_ONE_DIR && ItemsPanel.letPassOneDirSelected()) && !(item_id == LOCK_LOCKED) && !(level.getItem(e.getX()/32, e.getY()/32) == LOCK_LOCKED && ItemsPanel.lockLockedSelected()))
			level.setItem(item_id, e.getX()/32, e.getY()/32, 0);
		else if (e.getButton() == MouseEvent.BUTTON3)
			level.setItem(EMPTY, e.getX()/32, e.getY()/32, 0);
		else if (item_id == LOCK_LOCKED && !(level.getItem(e.getX()/32, e.getY()/32) == LOCK_LOCKED && ItemsPanel.lockLockedSelected()))
			level.setItem(item_id, e.getX()/32, e.getY()/32, 1);

		if (level.getItem(e.getX()/32, e.getY()/32) == EMPTY && ItemsPanel.playerSelected() && e.getButton() == MouseEvent.BUTTON1) {
			player.setColumn(e.getX()/32);
			player.setRow(e.getY()/32);
			level.setItem(EMPTY, e.getX()/32, e.getY()/32, 0);
		}
		else if (((level.getItem(e.getX()/32, e.getY()/32) == FINISH && ItemsPanel.finishSelected()) || (level.getItem(e.getX()/32, e.getY()/32) == ARROW && ItemsPanel.arrowSelected()) || (level.getItem(e.getX()/32, e.getY()/32) == DIR_SWITCH && ItemsPanel.dirSwitchSelected()) || (level.getItem(e.getX()/32, e.getY()/32) == DIR_SWITCH_TURN && ItemsPanel.dirSwitchTurnSelected()) || (level.getItem(e.getX()/32, e.getY()/32) == LET_PASS && ItemsPanel.letPassSelected()) || (level.getItem(e.getX()/32, e.getY()/32) == LET_PASS_ONE_DIR && ItemsPanel.letPassOneDirSelected())) && e.getButton() == MouseEvent.BUTTON1) {
			if (level.getItemAtts(e.getX()/32, e.getY()/32) < 3) {
				System.out.println(level.getItemAtts(e.getX()/32, e.getY()/32));
				level.setItemAtts(e.getX()/32, e.getY()/32, level.getItemAtts(e.getX()/32, e.getY()/32) + 1);
				System.out.println(level.getItemAtts(e.getX()/32, e.getY()/32));
			}
			else
				level.setItemAtts(e.getX()/32, e.getY()/32, 0);
			BugfullabsMapEditor.mEditor.mEditorPanel.repaintIt();
		}
		else if (level.getItem(e.getX()/32, e.getY()/32) == LOCK_LOCKED && ItemsPanel.lockLockedSelected() && e.getButton() == MouseEvent.BUTTON1) {
			if (level.getItemAtts(e.getX()/32, e.getY()/32) == 0) {
				level.setItemAtts(e.getX()/32, e.getY()/32, 1);
			}
			else {
				level.setItemAtts(e.getX()/32, e.getY()/32, 0);
			}
		}
		
		System.out.println("position_editor: " + e.getX() + ", " + e.getY() + ", item id: " + item_id + ", item atts: " + level.getItemAtts(e.getX()/32, e.getY()/32) + " " + (ItemsPanel.dirSwitchSelected() ? "true" : "false"));
		repaint();
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	public void setLevel(Level l) {
		this.level = l;
		this.level.pattern = l.pattern;
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