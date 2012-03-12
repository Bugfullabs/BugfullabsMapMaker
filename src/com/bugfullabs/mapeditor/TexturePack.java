package com.bugfullabs.mapeditor;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;



public class TexturePack{
	
	private ArrayList<BufferedImage> textures;
	
	private BufferedImage mainImage;
	
	TexturePack(){
		
		textures = new ArrayList<BufferedImage>();
		
	}

	public void setTexture(BufferedImage img){
	
		this.mainImage = img;
		
	}
	
	public void addTextureRegion(int id, int x, int y, int width, int height){
		textures.add(id, mainImage.getSubimage(x, y, width, height));
	}
	
	public BufferedImage getTextureRegion(int id){
		return this.textures.get(id);
	}
	
	
	public void setFile(File file){
		
		try {
			this.mainImage = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void setSize(int width, int height){
		
	}

	
	
}