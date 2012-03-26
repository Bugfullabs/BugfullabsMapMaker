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
			if (localName.equals("level")) {
				this.in_leveltag = true;
				
				level = new Level(Integer.parseInt(atts.getValue("id")), Integer.parseInt(atts.getValue("levelpackid")), atts.getValue("texture"), Integer.parseInt(atts.getValue("columns")), Integer.parseInt(atts.getValue("rows")));
				
			}
			else if (localName.equals("row")) {
				
				current_row = Integer.parseInt(atts.getValue("number"));
				this.in_rowtag = true;

			}
			else if (localName.equals("players")) {
				in_playerstag = true;
			}
			else if(localName.equals("player")) {
				level.setPlayer(new PlayerEntity(Integer.parseInt(atts.getValue("column")), Integer.parseInt(atts.getValue("row")), Integer.parseInt(atts.getValue("color")), Integer.parseInt(atts.getValue("dir"))));	
			}
			else if (localName.equals("item")) {
				if(in_rowtag == true) {
					level.setItem(Integer.parseInt(atts.getValue("column")), current_row,  Integer.parseInt(atts.getValue("id")));
				}
			}
			}
		
		/** Gets be called on closing tags like: 
		 * </tag> */
		@Override
		public void endElement(String namespaceURI, String localName, String qName)
				throws SAXException {
			if (localName.equals("level")) {
				this.in_leveltag = false;
			}else if (localName.equals("row")) {
				this.in_rowtag = false;
			}else if (localName.equals("players")) {
				this.in_playerstag = false;
			}
			
			
		}
		
		/** Gets be called on the following structure: 
		 * <tag>characters</tag> */
		@Override
	    public void characters(char ch[], int start, int length) {

	    }	
		
}