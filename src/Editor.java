import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

public class Editor {

	private JDesktopPane mDesktop;
	private JInternalFrame mItemsFrame;
	private JInternalFrame mLevelFrame;

	
	public Editor(JDesktopPane pDesktop){
		
		this.mDesktop = pDesktop;
		
		mLevelFrame = new JInternalFrame("Editor", false, false, false, false);
		mLevelFrame.setBounds(200, 0, 800, 480);
		mLevelFrame.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		mLevelFrame.setVisible(true);
		
		mDesktop.add(mLevelFrame);
		
		mItemsFrame = new JInternalFrame("Items", false, false, false, false);
		mItemsFrame.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		mItemsFrame.setBounds(0, 0, 200, 480);
		mItemsFrame.setVisible(true);
		
		mDesktop.add(mItemsFrame);
		
	}

	private void drawItems(){
		
		Rectangle rect = new Rectangle(0, 0, 10, 10);
		mItemsFrame.paint(rect);
		/*
		 * WYBACZ, NIC NIE ZROBI£EM, BO NIE MOGÊ SIÊ SKUPIÆ,
		 * ALE ZOBACZ TO:
		 * http://zetcode.com/tutorials/java2dtutorial/basicdrawing/
		 * WYGL¥DA CA£KIEM £ADNIE
		 * 
		 */
	}
	
	
}