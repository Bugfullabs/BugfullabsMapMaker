package com.bugfullabs.mapeditor;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class ItemsPanel extends JPanel{
	
	private Graphics2D g2d;
	private TexturePack tx;
	
	ItemsPanel(String name, TexturePack texture, int x, int y, int width, int height){
		super();
		this.setName(name);
		this.setBounds(x, y, width, height);
		this.setVisible(true);
		
		this.tx = texture;
	}
	
	@Override
	public void paintComponent(Graphics g){
		
		g2d = (Graphics2D) g;
		
		if(tx.getSize() > 0){
	
		int j = 0;
		int k = 0;
			
		for(int i = 0; i < tx.getSize(); i++){
			
		g2d.drawImage(tx.getTextureRegion(i), null, k*32, j*32);
		k++;
		if(k >= this.getWidth()/32){
			k = 0;
			j++;
		}
		
		}
		
		}
	}
	
}