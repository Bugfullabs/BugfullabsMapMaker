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
	      mFrame.setSize(1050, 570);
	      
	      
	      
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
	    			
	    			  int returnVal = mFiles.showOpenDialog(null);
	    			  
	    	            if (returnVal == JFileChooser.APPROVE_OPTION) {
	    	            
	    			  new Editor(mDesktop, mFiles.getSelectedFile());
	    	            }
	    		  }else if(e.getSource().equals(this.menuSettings)){
	    			  
	    			  new Settings(mFrame);
	    			  
	    		  }else if(e.getSource().equals(this.menuSave)){
	    			  
	    			  XMLWriter.writeXML(new File("C:/Users/dlt/git/TheGame/TheGame/assets/levels/level_1_7.xml"), Editor.mEditorPanel.level);
	    			  
	    		  }
	    	  }
	      };

	      mFrame.setVisible(true);
	      
	      
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