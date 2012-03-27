package com.bugfullabs.mapeditor;

import java.util.ArrayList;



public class Level{
	
	private int width;
	private int height;
	private int id;
	private int levelpack;
	private String texture;
	
	
	private ArrayList<PlayerEntity> players;
	public int pattern[][];
	
	Level(int id, int pack, String tex, int w, int h){
		
		this.width = w;
		this.height = h;
		
		this.id = id;
		this.levelpack = pack;
		this.texture = tex;
		
		this.players = new ArrayList<PlayerEntity>();
		
		this.pattern = new int[w][h];
		
		for(int i = 0; i < w; i++){
			for(int j = 0; j < h; j++){
			
			this.pattern[i][j] = 0;
				
			}
		}
		
	}
	
	
	public void setItem(int item, int column, int row){
		if(isCorrect(column, row)){
			this.pattern[column][row] = item;
		}
		else
			System.out.println("Niepoprawne koordynaty");
	}
	
	public void setPlayer(PlayerEntity p){
		this.players.add(p);
	}
	

	public int getItem(int column, int row){
		if(isCorrect(column, row)){
		return pattern[column][row];
		}
		else
			System.out.println("Niepoprawne koordynaty");
		return 0;
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
	
	public void setId(int i){
		id = i;
	}
	
	public int getLevelPack(){
		return levelpack;
	}
	
	public void setLevelPack(int lp){
		levelpack = lp;
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
	
	private boolean isCorrect(int column, int row) {
		
		return (column >= 0 && column < this.width && row >= 0 && row < this.height);
	}
	
}


class PlayerEntity{
	
	private int column;
	private int row;
	private int color;
	private int dir;
	
	public PlayerEntity(int c, int r, int color, int d){
		this.color = color;
		this.dir = d;
		this.column = c;
		this.row = r;
	}
	
	public int getDir(){
		return dir;
	}
	
	public void setColor(int c){
		this.color = c;
	}	
	
	public void setDir(int d){
		this.dir = d;
	}
	
	public int getRow(){
		return row;
	}

	public void setRow(int r){
		this.row = r;
	}
	
	public int getColumn(){
		return column;
	}
	
	public void setColumn(int c){
		this.column = c;
	}
	
	public int getColor(){
		return color;
	}
	
}

