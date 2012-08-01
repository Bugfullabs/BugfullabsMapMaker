package com.bugfullabs.mapeditor;


public class Level{
	
	private int width;
	private int height;
	private int id;
	private int levelpack;
	private String texture;
	
	public final static int LEVEL = 0;
	public final static int ATTS = 1;
	
	
	public PlayerEntity player;
	public int pattern[][][];

	
	Level(int id, int pack, String tex, int w, int h, int px, int py){

		
		this.player = new PlayerEntity(px, py, 0);
		
		this.width = w;
		this.height = h;
		
		this.id = id;
		this.levelpack = pack;
		this.texture = tex;
		
		this.pattern = new int[w][h][2];
		
		for(int i = 0; i < w; i++){
			for(int j = 0; j < h; j++){
			
			this.pattern[i][j][LEVEL] = Values.SOLID_ID;
			this.pattern[i][j][ATTS] = 0;
				
			}
		}
		
	}
	
	
	public void setItem(int item, int column, int row, int attribute){
		if(isCorrect(column, row)){
			this.pattern[column][row][LEVEL] = item;
			this.pattern[column][row][ATTS] = attribute;
		}
		else
			System.out.println("Niepoprawne koordynaty");
	}
	
	public void setItem(int item, int column, int row){
		if(isCorrect(column, row)){
			this.pattern[column][row][LEVEL] = item;
		}
		else
			System.out.println("Niepoprawne koordynaty");
	}
	
	
	public void setPlayer(PlayerEntity p){
		this.player = p;
	}
	

	public int getItem(int column, int row){
		if(isCorrect(column, row)){
		return pattern[column][row][LEVEL];
		}
		else
			System.out.println("Niepoprawne koordynaty");
		return 0;
	}

	public int getItemAtts(int column, int row){
		if(isCorrect(column, row)){
		return pattern[column][row][ATTS];
		}
		else
			System.out.println("Niepoprawne koordynaty");
		return 0;
	}
	
	public void setItemAtts(int column, int row, int atts) {
		if(isCorrect(column, row)){
			pattern[column][row][ATTS] = atts;
		}
		else
			System.out.println("Niepoprawne koordynaty");
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
	
	public PlayerEntity getPlayer(){
		return player;
	}

	public int getNumberOfPlayers(){
		return 1;
	}
	
	
	
	
	private boolean isCorrect(int column, int row) {
		
		return (column >= 0 && column < this.width && row >= 0 && row < this.height);
	}
	
	
	
	
}


class PlayerEntity{

	private int column;
	private int row;
	private int color;

	public PlayerEntity(int c, int r, int color){
		this.color = color;
		this.column = c;
		this.row = r;
	}


	
	

	public void setColor(int c){
		this.color = c;
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
