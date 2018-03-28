import java.awt.Graphics;


public class Board {
	
	Tile[][] tiles;
	boolean[][] mines;
	public Board(int rows, int cols, int boardSize){
		tiles = new Tile[rows][cols];
		randBoard(boardSize);
		mines = new boolean[rows][cols];
	}
	private void randBoard(int boardSize) {
		// TODO Auto-generated method stub
		for(int r = 0; r< tiles.length; r++){
			for(int c=0; c< tiles.length;c++){
				tiles[r][c] = new Tile(r,c,boardSize, tiles.length);
				if(mines[r][c]){
					tiles[r][c].setMine();
				}
			}
		}
	}
	public void draw(Graphics g) {
		for(int r = 0; r< tiles.length; r++){
			for(int c=0; c< tiles.length;c++){
				tiles[r][c].draw(g);
			}
		}
	}
}
