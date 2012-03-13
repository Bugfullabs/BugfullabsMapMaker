package com.bugfullabs.mapeditor;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;


public class TexturePackLoader{
	
	
	
	public static TexturePack createFromFile(File file){
		
		TexturePackHandler handler;

		SAXParser sp; 
		SAXParserFactory spf;
		
		if(file.exists()){
		
		handler = new TexturePackHandler(file);	
		
		try {
			
			spf = SAXParserFactory.newInstance();
			sp = spf.newSAXParser();
			sp.parse(file, handler);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		TexturePack tx =  handler.getTexturePack();
		return tx;
		}else{
			System.exit(0);
			
		}
		
		return null;
		
	}
	
}

class TexturePackHandler extends DefaultHandler{
	
	TexturePack pack;
	File file;
	
	public TexturePackHandler(File pFile){
		super();
		file = pFile;
	}
	
	
	public TexturePack getTexturePack() {
		return this.pack;
	}
	
	@Override
	public void startDocument() throws SAXException {

	}


	@Override
	public void endDocument() throws SAXException {
	
	}

@Override
	public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
		if (localName.equals("texture")) {
			
			
			try {
				pack = new TexturePack(ImageIO.read(new File(file.getParent()+File.separator+atts.getValue("file"))), Integer.parseInt(atts.getValue("width")), Integer.parseInt(atts.getValue("height")));
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(-1);
				
			}
			
		}else
		if (localName.equals("textureregion")) {
			pack.addTextureRegion(Integer.parseInt(atts.getValue("id")),
								  Integer.parseInt(atts.getValue("x")),
								  Integer.parseInt(atts.getValue("y")),
								  Integer.parseInt(atts.getValue("width")),
								  Integer.parseInt(atts.getValue("height")));
		}
		
	}
	
	@Override
	public void endElement(String namespaceURI, String localName, String qName)
			throws SAXException {
		if (localName.equals("texture")) {
			
		}
	}
	
	@Override
    public void characters(char ch[], int start, int length) {

    	}	
	
}