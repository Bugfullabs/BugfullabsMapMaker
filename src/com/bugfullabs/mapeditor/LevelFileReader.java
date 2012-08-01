package com.bugfullabs.mapeditor;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class LevelFileReader{
	


	public static Level getFromFile(File file){
		
		SAXParser sp; 
		SAXParserFactory spf;
		
		if(file.exists()){
		
		try {
			LevelHandler handler = new LevelHandler();	
			spf = SAXParserFactory.newInstance();
			sp = spf.newSAXParser();
			sp.parse(file, handler);
			Level level;
			level = handler.getLevel();
			return level;
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		}else{
		System.exit(0);	
		}
		
		return null;
		
	}
	
}





class LevelHandler extends DefaultHandler{

	// ===========================================================
	// Fields
	// ===========================================================
	
	private boolean in_rowtag = false;
	@SuppressWarnings("unused")
	private boolean in_leveltag = false;
	@SuppressWarnings("unused")
	private boolean in_playerstag = false;
	
	private int current_row = 0;
	
	
	private Level level;		

	public Level getLevel(){
		return this.level;
	}
	

	@Override
	public void startDocument() throws SAXException {

	}

	@Override
	public void endDocument() throws SAXException {

	}

	
	@Override
	public void startElement(String namespaceURI, String localName,
			String qName, Attributes atts) throws SAXException {

	
		if (qName.equals("level")) {
			this.in_leveltag = true;

			System.out.println("Entered 'level' tag");
			
			level = new Level(Integer.parseInt(atts.getValue("id")), Integer.parseInt(atts.getValue("levelpackid")), atts.getValue("texture"), Integer.parseInt(atts.getValue("columns")), Integer.parseInt(atts.getValue("rows")), -1, -1);
			System.out.println("Level id: " + level.getId() + ", LevelPackId: " + level.getLevelPack());
			
		}
		else if (qName.equals("row")) {

			System.out.println("Entered 'row' tag, row number: " + Integer.parseInt(atts.getValue("number")));
			
			current_row = Integer.parseInt(atts.getValue("number"));
			this.in_rowtag = true;

		}
		else if (qName.equals("players")) {

			System.out.println("Entered 'players' tag");
			
			in_playerstag = true;
		}
		else if(qName.equals("player")) {

			BugfullabsMapEditor.mEditor.mEditorPanel.player.setColumn(Integer.parseInt(atts.getValue("column")));
			BugfullabsMapEditor.mEditor.mEditorPanel.player.setRow(Integer.parseInt(atts.getValue("row")));
			BugfullabsMapEditor.mEditor.mEditorPanel.player.setColor(Integer.parseInt(atts.getValue("color")));
			
		}
		else if (qName.equals("item")) {
			if(in_rowtag == true) {
				level.setItem(Integer.parseInt(atts.getValue("id")), Integer.parseInt(atts.getValue("column")), current_row, atts.getValue("attribute") != null ? Integer.parseInt(atts.getValue("attribute")) : 0);
				// TODO: delete
				System.out.println("Entered 'item' tag, params: " + Integer.parseInt(atts.getValue("id")) + ", " + Integer.parseInt(atts.getValue("column")) + ", " + current_row);
			}
		}
		
		}
	

	
	
	@Override
	public void endElement(String namespaceURI, String localName, String qName)
			throws SAXException {

//		System.out.println("Entered endElement(" + namespaceURI + ", " + localName + ", " + qName + ")");
		
		if (qName.equals("level")) {
			
			System.out.println("Quitted 'level' tag");
			
			this.in_leveltag = false;
		}else if (qName.equals("row")) {
			
			System.out.println("Quitted 'row' tag");
			
			this.in_rowtag = false;
		}else if (qName.equals("players")) {
			
			System.out.println("Quitted 'players' tag");
			
			this.in_playerstag = false;
		}
		
		
	}
	
	/** Gets be called on the following structure: 
	 * <tag>characters</tag> */
	@Override
    public void characters(char ch[], int start, int length) {

    }	
	
}