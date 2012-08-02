package com.bugfullabs.mapeditor;


import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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

	   static JFileChooser mSelectLeveLPack;

	   static JFileChooser mOpen;

	   public static Editor mEditor;
	   
	   public static void main(String[] args) {

	      mFrame = new JFrame("Bugfullabs Map Maker");
	      mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      mFrame.setSize(1050, 570);

	      mDesktop = new JDesktopPane();
	      mDesktop.setVisible(true);
	      mFrame.add(mDesktop);

	      mSelectLeveLPack = new JFileChooser();
	      mSelectLeveLPack.setCurrentDirectory(new File(Values.GFX_FOLDER));

		  mSelectLeveLPack.setFileFilter(new FileFilter(){

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

		  mOpen = new JFileChooser();
	      mOpen.setCurrentDirectory(new File(Values.LEVELS_FOLDER));

	      mMenu = new Menu(mFrame){
	    	  @Override
	    	  public void onAction(ActionEvent e){
	    		  if (e.getSource().equals(this.menuNewFile)) {

	    			  if (mEditor == null) {
		    			  int returnVal = mSelectLeveLPack.showOpenDialog(null);
		    			  if (returnVal == JFileChooser.APPROVE_OPTION) {
		    				  mEditor = new Editor(mDesktop, mSelectLeveLPack.getSelectedFile());
		    				  System.out.println("First Editor");
		    	          }
	    			  }
	    			  else if (mEditor != null) {


	    				  int a = JOptionPane.showConfirmDialog(null,
	    						  "Do you want to save level, before creating new one?\n"
	  						    + "All unsaved changes will be lost.\n",
	  						    "Do you want to save level?",
	  						    JOptionPane.YES_NO_CANCEL_OPTION);
	    				  if (a == 0) {
	    					  new SaveMenu(mFrame, true);
	    				  }
	    				  else if (a == 1) {
	    					  openBrandNewEditor();
	    				  }
	    			  }

	    		  }
	    		  else if (e.getSource().equals(this.menuOpen)) {

		   			  if (mEditor == null) {
		    			  int returnValOpen = mOpen.showOpenDialog(null);
		    			  int returnValLP = mSelectLeveLPack.showOpenDialog(null);
		    			  if (returnValOpen == JFileChooser.APPROVE_OPTION && returnValLP == JFileChooser.APPROVE_OPTION) {
		    				  clear();
		    				  mEditor = new Editor(mDesktop, mSelectLeveLPack.getSelectedFile());
		    				  mEditor.mEditorPanel.setLevel(LevelFileReader.getFromFile(mOpen.getSelectedFile()));
		    				  System.out.println("Level id: " + mEditor.mEditorPanel.level.getId() + ", LevelPackId: " + mEditor.mEditorPanel.level.getLevelPack());
		    				  System.out.println("First Editor, Open");
		    				  mEditor.mEditorPanel.repaintIt();
		    	          }
		   			  }
		    		  else if (mEditor != null) {


		    				  int a = JOptionPane.showConfirmDialog(null,
		    						  "Do you want to save level, before creating new one?\n"
		  						    + "All unsaved changes will be lost.\n",
		  						    "Do you want to save level?",
		  						    JOptionPane.YES_NO_CANCEL_OPTION);
		    				  if (a == 0) {
		    					  new SaveMenu(mFrame, true);
		    				  }
		    				  else if (a == 1) {
		    					  int returnValOpen = mOpen.showOpenDialog(null);
				    			  int returnValLP = mSelectLeveLPack.showOpenDialog(null);
				    			  if (returnValOpen == JFileChooser.APPROVE_OPTION && returnValLP == JFileChooser.APPROVE_OPTION) {
				    				  mEditor.setTexturePack(mSelectLeveLPack.getSelectedFile());
				    				  mEditor.mEditorPanel.setLevel(LevelFileReader.getFromFile(mOpen.getSelectedFile()));
				    				  System.out.println("Level id: " + mEditor.mEditorPanel.level.getId() + ", LevelPackId: " + mEditor.mEditorPanel.level.getLevelPack());
				    				  System.out.println("First Editor, Open");
				    				  mEditor.mEditorPanel.repaintIt();
				    	          }
		    				  }
		    			  }

	    		  }
	    		  else if(e.getSource().equals(this.menuSettings)){

	    			  new Settings(mFrame);

	    		  }
	    		  else if(e.getSource().equals(this.menuSave)){

	    			  new SaveMenu(mFrame, false);

	    		  }
	    	  }
	      };

	      mFrame.setVisible(true);


	    }


	    public static void openBrandNewEditor() {
	    	int returnVal = mSelectLeveLPack.showOpenDialog(null);
			  if (returnVal == JFileChooser.APPROVE_OPTION) {
				  clear();
				  mEditor = new Editor(mDesktop, mSelectLeveLPack.getSelectedFile());
				  System.out.println("openBrandNewEditor();");
			  }
	    }

	    public static void clear() {
	    	
	    	if(mEditor == null)
	    		return;
	    		
	    	mEditor.clear();
			mEditor = null;
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