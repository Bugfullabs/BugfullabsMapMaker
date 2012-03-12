package com.bugfullabs.mapeditor;


import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;


/**
*
* @author Bugfullabs
* 
 */
 
public class BugfullabsMapEditor {

	   static JFrame mFrame;
	   
	   static JDesktopPane mDesktop;
	   
	   static Menu mMenu;
	   
	   static JFileChooser mFiles;
	   
	    public static void main(String[] args) {
	    	
	      mFrame = new JFrame("Bugfullabs Map Maker");
	      mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      
	      mFrame.setSize(1000, 600);

	      mFrame.setVisible(true);
	      
	      mDesktop = new JDesktopPane();
	      mDesktop.setVisible(true);
	      mFrame.add(mDesktop);
	      
	      mFiles = new JFileChooser();
	      
	      
	      
		  mFiles.setFileFilter(new FileFilter(){

			@Override
		    	  public boolean accept(File f){
		    		  
		    		  if (f.isDirectory()) {
		    		        return true;
		    		  }

		    		    String extension = getFileExtension(f);
		    		    if (extension != null) {
		    		        if (extension.equals("xml")){
		    		                return true;
		    		        }
		    		    }

		    		    return false;  
		    	  }
			

			@Override
			public String getDescription() {
				return null;
			}});
		  
	      
	      mMenu = new Menu(mFrame){
	    	  @Override
	    	  public void onAction(ActionEvent e){
	    		  if (e.getSource().equals(this.menuNewFile)) {
	    			
	    			  mFiles.showOpenDialog(null);
	    			  
	    			  
	    			  
	    			  new Editor(mDesktop);
	    		  }
	    	  }
	      };

	    }


	    private static String getFileExtension(File f) {
	        String ext = null;
	        String s = f.getName();
	        int i = s.lastIndexOf('.');

	        if (i > 0 &&  i < s.length() - 1) {
	            ext = s.substring(i+1).toLowerCase();
	        }
	        return ext;
	    }
	    
		    	    
}