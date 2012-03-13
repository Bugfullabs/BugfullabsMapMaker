package com.bugfullabs.mapeditor;

import java.util.ArrayList;



public class Level{
	
	private int width;
	private int height;
	private int id;
	private int levelpack;
	private String texture;
	
	
	private ArrayList<PlayerEntity> players;
	private int pattern[][];
	
	Level(int w, int h){
		
		this.width = w;
		this.height = h;
		
		this.players = new ArrayList<PlayerEntity>();
		
		this.pattern = new int[w][h];
		
	}
	
	
	public int getItem(int column, int row){
		return pattern[column][row];
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public int getId(){
		return id;
	}
	
	public int getLevelPack(){
		return levelpack;
	}
	
	public String getTexture(){
		return texture;
	}
	
	public PlayerEntity getPlayer(int id){
		return players.get(id);
	}
	
	public int getNumberOfPlayers(){
		return players.size();
	}
	
	
}


class PlayerEntity{
	
	private int column;
	private int row;
	private int color;
	private int dir;
	
	public int getDir(){
		return dir;
	}
	
	public int getRow(){
		return row;
	}
	
	public int getColumn(){
		return column;
	}
	
	public int getColor(){
		return color;
	}
	
}

