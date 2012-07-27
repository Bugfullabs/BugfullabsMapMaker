package com.bugfullabs.mapeditor;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

/*
 * Nowe blocki tutaj w jednym miejscu oraz w ItemsPanel w kilku miejscach
 * 
 */

@SuppressWarnings("serial")
public class ItemsPanel extends JPanel implements MouseListener {
	
	private Graphics2D g2d;
	private TexturePack tx;
	boolean drawing = false;
	static boolean player_selected = false;

	
	ItemsPanel(String name, TexturePack texture, int x, int y, int width, int height){
		super();
		this.setName(name);
		this.setBounds(x, y, width, height);
		this.setVisible(true);
		
		this.tx = texture;
		
		addMouseListener(this);

	}
	
	public void setTexturePack(TexturePack texture) {
		this.tx = texture;
	}
	
	@Override
	public void paintComponent(Graphics g){
		
		g2d = (Graphics2D) g;
		
		if(tx.getSize() > 0){
	
			int j = 0;
			int k = 0;
			
			for(int i = 0; i < tx.getSize(); i++){
			
				if(tx.getTextureRegion(i).getWidth() < 100 || tx.getTextureRegion(i).getHeight() < 100)
						g2d.drawImage(tx.getTextureRegion(i), null, k*32, j*32);
				k++;
		
				if(k >= this.getWidth()/32){
					k = 0;
					j++;
				}
		
			}

		}
	}
	

	
	public static boolean playerSelected() {
		return player_selected;
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {

		drawing = true;

		if ((((this.getWidth()/32) * (e.getY()/32)) + (e.getX()/32)) == 0 && !player_selected) {
			player_selected = true;
		}
		else if ((((this.getWidth()/32) * (e.getY()/32)) + (e.getX()/32)) == 0 && player_selected) {
			player_selected = false;
		}
		
		
		System.out.println("position_items: " + e.getX() + ", " + e.getY() + ", id: " + ((((this.getWidth()/32) * (e.getY()/32)) + (e.getX()/32)) <= tx.getSize() ? (((this.getWidth()/32) * (e.getY()/32)) + (e.getX()/32)) : 0));
		EditorPanel.selectItem((((this.getWidth()/32) * (e.getY()/32)) + (e.getX()/32)) <= tx.getSize() ? (((this.getWidth()/32) * (e.getY()/32)) + (e.getX()/32)) : 0);
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		drawing = false;
		
	}
	
}