package com.bugfullabs.mapeditor;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;

public class LevelFileReader{
	
	XMLReader xr;
	SAXParser sp; 
	SAXParserFactory spf;
	
	File mFile;
	
	LevelHandler handler;
	
	
	public LevelFileReader(File file){
		mFile = file;
	}
	
	public Level getLevel(){
		
		try {
			readLevel();
		} catch (SAXNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Level level = handler.getLevel();
		return level;
	}
	
	private void readLevel() throws  SAXNotSupportedException{
		
	try {
		spf = SAXParserFactory.newInstance();
		sp = spf.newSAXParser();
		xr = sp.getXMLReader();

		handler = new LevelHandler();
		
		System.out.println(mFile);
		sp.parse(mFile, handler);
		
		
		} catch (Exception e) {
		System.out.println("XML Pasing Excpetion = " + e);
		}
	
	}
	
}