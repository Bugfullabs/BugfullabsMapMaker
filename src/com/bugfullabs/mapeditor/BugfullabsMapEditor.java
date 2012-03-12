package com.bugfullabs.mapeditor;


import java.awt.event.ActionEvent;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;


/**
*
* @author Bugfullabs
* 
 */
 
public class BugfullabsMapEditor {

	   static JFrame mFrame;
	   
	   static JDesktopPane mDesktop;
	   
	   static Menu mMenu;
	   
	    public static void main(String[] args) {
	    	
	      mFrame = new JFrame("Bugfullabs Map Maker");
	      mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      
	      mFrame.setSize(1000, 600);

	      mFrame.setVisible(true);
	      
	      mDesktop = new JDesktopPane();
	      mDesktop.setVisible(true);
	      mFrame.add(mDesktop);
	      
	      
	      
	      mMenu = new Menu(mFrame){
	    	  @Override
	    	  public void onAction(ActionEvent e){
	    		  if (e.getSource().equals(this.menuNewFile)) {
	    			  new Editor(mDesktop);
	    		  }
	    	  }
	      };

	    }


		    	    
}