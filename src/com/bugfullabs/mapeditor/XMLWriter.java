package com.bugfullabs.mapeditor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;


public class XMLWriter{
	
	XMLWriter(){	
	}
	
	public static File writeXML(File file, Level level){
		
		
		try {
			
			
			FileOutputStream fos = new FileOutputStream(file);
			XMLStreamWriter out = XMLOutputFactory.newInstance().createXMLStreamWriter(new OutputStreamWriter(fos, "utf-8"));
			
			out.writeStartDocument();

			out.writeCharacters("\n");
			
			out.writeStartElement("level");

			out.writeAttribute("id", "");
			out.writeAttribute("rows", "");
			out.writeAttribute("columns", "");
			out.writeAttribute("levelpackid", "");
			out.writeAttribute("texture", "");
			
				out.writeCharacters("\n");
			
				out.writeStartElement("row");
				out.writeAttribute("number", "");
				
				out.writeCharacters("\n");
				
					out.writeStartElement("item");
					out.writeAttribute("column", "");
					out.writeAttribute("id", "");
					out.writeEndElement();//</item>
				
					out.writeCharacters("\n");
					
				out.writeEndElement();//</row>
			
				out.writeCharacters("\n");
				
				out.writeStartElement("players");
				out.writeAttribute("number", "");
				
				out.writeCharacters("\n");
				
					out.writeStartElement("player");
					out.writeAttribute("color", "");
					out.writeAttribute("column", "");
					out.writeAttribute("row", "");
					out.writeAttribute("id", "");
					out.writeAttribute("dir", "");	
					out.writeEndElement();
				
					out.writeCharacters("\n");
					
			out.writeEndElement();//</level>
			
			out.writeCharacters("\n");
			
			out.writeEndDocument();
			
		out.close();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return file;
	}
	
	
}
