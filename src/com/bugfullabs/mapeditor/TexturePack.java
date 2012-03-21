package com.bugfullabs.mapeditor;

import java.awt.image.BufferedImage;
import java.util.ArrayList;



public class TexturePack{
	
	private ArrayList<BufferedImage> textures;
	
	private BufferedImage mainImage;
	
	TexturePack(BufferedImage img, int width, int height){

		this.mainImage = img;
	
		textures = new ArrayList<BufferedImage>(20);
		
	}

	public TexturePack() {
		textures = new ArrayList<BufferedImage>(1);
	}

	public void addTextureRegion(int id, int x, int y, int width, int height){
		textures.add(id, mainImage.getSubimage(x, y, width, height));
	}
	
	public BufferedImage getTextureRegion(int id){
		return this.textures.get(id);
	}	

	public int getSize(){
		return this.textures.size();
	}

}
