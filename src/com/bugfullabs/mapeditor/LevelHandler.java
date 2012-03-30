package com.bugfullabs.mapeditor;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;



public class LevelHandler extends DefaultHandler{

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

		/** Gets be called on opening tags like: 
		 * <tag> 
		 * Can provide attribute(s), when xml was like:
		 * <tag attribute="attributeValue">
		 */
		@Override
		public void startElement(String namespaceURI, String localName,
				String qName, Attributes atts) throws SAXException {

//			System.out.println("Entered startElement(" + namespaceURI + ", " + localName + ", " + qName + ", " + atts + ")");
			
			if (qName.equals("level")) {
				this.in_leveltag = true;

				System.out.println("Entered 'level' tag");
				
				level = new Level(Integer.parseInt(atts.getValue("id")), Integer.parseInt(atts.getValue("levelpackid")), atts.getValue("texture"), Integer.parseInt(atts.getValue("columns"))*32, Integer.parseInt(atts.getValue("rows"))*32, -1, -1, 1);
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

				System.out.println("Entered 'player' tag, params: " + Integer.parseInt(atts.getValue("column")) + ", " + Integer.parseInt(atts.getValue("row")) + ", " + Integer.parseInt(atts.getValue("color")) + ", " + Integer.parseInt(atts.getValue("dir")));
				
				//level.setPlayer(new PlayerEntity(-1, -1, 0, 1));
				BugfullabsMapEditor.mEditor.mEditorPanel.player.setColumn(Integer.parseInt(atts.getValue("column")));
				BugfullabsMapEditor.mEditor.mEditorPanel.player.setRow(Integer.parseInt(atts.getValue("row")));
				BugfullabsMapEditor.mEditor.mEditorPanel.player.setDir(Integer.parseInt(atts.getValue("dir")));
				BugfullabsMapEditor.mEditor.mEditorPanel.player.setColor(Integer.parseInt(atts.getValue("color")));
				
			}
			else if (qName.equals("item")) {
				if(in_rowtag == true) {
					level.setItem(Integer.parseInt(atts.getValue("id")), Integer.parseInt(atts.getValue("column")), current_row);
					// TODO: delete
					System.out.println("Entered 'item' tag, params: " + Integer.parseInt(atts.getValue("id")) + ", " + Integer.parseInt(atts.getValue("column")) + ", " + current_row);
				}
			}
			}
		
		/** Gets be called on closing tags like: 
		 * </tag> */
		@Override
		public void endElement(String namespaceURI, String localName, String qName)
				throws SAXException {

//			System.out.println("Entered endElement(" + namespaceURI + ", " + localName + ", " + qName + ")");
			
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