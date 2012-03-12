package com.bugfullabs.mapeditor;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;


public class TexturePackReader{
	
	@SuppressWarnings("unused")
	private XMLReader xr;
	private SAXParser sp; 
	private SAXParserFactory spf;
	
	private TexturePackHandler handler;
	private TexturePack texturePack;
	
	TexturePackReader(File file){
		
		spf = SAXParserFactory.newInstance();
		try {
			sp = spf.newSAXParser();
			xr = sp.getXMLReader();
			handler = new TexturePackHandler(file);	
			sp.parse(file, handler);
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.texturePack = handler.getTexturePack();
		
	}
	
	
	public TexturePack getTexturePack(){
		return this.texturePack;
	}
	
	
}

class TexturePackHandler extends DefaultHandler{
	
	private TexturePack pack;
	private File file;
	
	public TexturePackHandler(File pFile){
		super();
		file = pFile;
	}
	
	
	@Override
	public void startDocument() throws SAXException {

		pack = new TexturePack();	
		
	}


	@Override
	public void endDocument() throws SAXException {

	}

@Override
	public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
		if (localName.equals("texture")) {
			pack.setSize(Integer.parseInt(atts.getValue("width")), Integer.parseInt(atts.getValue("height")));
			pack.setFile(new File(file.getPath()+atts.getValue("file")));
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
			
		}else
			if (localName.equals("textureregion")) {
				
		}
	}
	
	@Override
    public void characters(char ch[], int start, int length) {

    	}	
	
	
	public TexturePack getTexturePack() {
		return this.pack;
	}
	
}