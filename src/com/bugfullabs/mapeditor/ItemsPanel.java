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
	static boolean finish_selected = false;
	static boolean arrow_selected = false;
	static boolean dir_switch_selected = false;
	static boolean dir_switch_turn_selected = false;
	static boolean player_selected = false;
	static boolean let_pass_selected = false;
	static boolean let_pass_one_dir_selected = false;
	static boolean lock_locked_selected = false;
	
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

	public static boolean finishSelected() {
		return finish_selected;
	}

	public static boolean arrowSelected() {
		return arrow_selected;
	}

	public static boolean dirSwitchSelected() {
		return dir_switch_selected;
	}
	
	public static boolean dirSwitchTurnSelected() {
		return dir_switch_turn_selected;
	}
	
	public static boolean playerSelected() {
		return player_selected;
	}
	
	public static boolean letPassSelected() {
		return let_pass_selected;
	}
	
	public static boolean letPassOneDirSelected() {
		return let_pass_one_dir_selected;
	}
	
	public static boolean lockLockedSelected() {
		return lock_locked_selected;
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
			finish_selected = false;
			arrow_selected = false;
			dir_switch_selected = false;
			dir_switch_turn_selected = false;
			player_selected = true;
			let_pass_selected = false;
			let_pass_one_dir_selected = false;
			lock_locked_selected = false;
		}
		else if ((((this.getWidth()/32) * (e.getY()/32)) + (e.getX()/32)) == EditorPanel.EMPTY && player_selected) {
			player_selected = false;
		}
		else if ((((this.getWidth()/32) * (e.getY()/32)) + (e.getX()/32)) == EditorPanel.FINISH) {
			finish_selected = true;
			arrow_selected = false;
			dir_switch_selected = false;
			dir_switch_turn_selected = false;
			player_selected = false;
			let_pass_selected = false;
			let_pass_one_dir_selected = false;
			lock_locked_selected = false;
		}
		else if ((((this.getWidth()/32) * (e.getY()/32)) + (e.getX()/32)) == EditorPanel.ARROW) {
			finish_selected = false;
			arrow_selected = true;
			dir_switch_selected = false;
			dir_switch_turn_selected = false;
			player_selected = false;
			let_pass_selected = false;
			let_pass_one_dir_selected = false;
			lock_locked_selected = false;
		}
		else if ((((this.getWidth()/32) * (e.getY()/32)) + (e.getX()/32)) == EditorPanel.DIR_SWITCH) {
			finish_selected = false;
			arrow_selected = false;
			dir_switch_selected = true;
			dir_switch_turn_selected = false;
			player_selected = false;
			let_pass_selected = false;
			let_pass_one_dir_selected = false;
			lock_locked_selected = false;
		}
		else if ((((this.getWidth()/32) * (e.getY()/32)) + (e.getX()/32)) == EditorPanel.DIR_SWITCH_TURN) {
			finish_selected = false;
			arrow_selected = false;
			dir_switch_selected = false;
			dir_switch_turn_selected = true;
			player_selected = false;
			let_pass_selected = false;
			let_pass_one_dir_selected = false;
			lock_locked_selected = false;
		}
		else if ((((this.getWidth()/32) * (e.getY()/32)) + (e.getX()/32)) == EditorPanel.LET_PASS) {
			finish_selected = false;
			arrow_selected = false;
			dir_switch_selected = false;
			dir_switch_turn_selected = false;
			player_selected = false;
			let_pass_selected = true;
			let_pass_one_dir_selected = false;
			lock_locked_selected = false;
		}
		else if ((((this.getWidth()/32) * (e.getY()/32)) + (e.getX()/32)) == EditorPanel.LET_PASS_ONE_DIR) {
			finish_selected = false;
			arrow_selected = false;
			dir_switch_selected = false;
			dir_switch_turn_selected = false;
			player_selected = false;
			let_pass_selected = false;
			let_pass_one_dir_selected = true;
			lock_locked_selected = false;
		}
		else if ((((this.getWidth()/32) * (e.getY()/32)) + (e.getX()/32)) == EditorPanel.LOCK_LOCKED) {
			finish_selected = false;
			arrow_selected = false;
			dir_switch_selected = false;
			dir_switch_turn_selected = false;
			player_selected = false;
			let_pass_selected = false;
			let_pass_one_dir_selected = false;
			lock_locked_selected = true;
			System.out.println("LOCK_LOCKED SELECTED");
		}
		else {
			finish_selected = false;
			arrow_selected = false;
			dir_switch_selected = false;
			dir_switch_turn_selected = false;
			player_selected = false;
			let_pass_selected = false;
			let_pass_one_dir_selected = false;
			lock_locked_selected = false;
		}
		
		System.out.println("position_items: " + e.getX() + ", " + e.getY() + ", id: " + ((((this.getWidth()/32) * (e.getY()/32)) + (e.getX()/32)) <= tx.getSize() ? (((this.getWidth()/32) * (e.getY()/32)) + (e.getX()/32)) : 0));
		EditorPanel.selectItem((((this.getWidth()/32) * (e.getY()/32)) + (e.getX()/32)) <= tx.getSize() ? (((this.getWidth()/32) * (e.getY()/32)) + (e.getX()/32)) : 0);
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		drawing = false;
		
	}
	
}