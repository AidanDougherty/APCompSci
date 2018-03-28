import java.awt.Graphics;

public class Tile {
	private boolean isAMine, beenClicked, flagged;
	private int numMinesAround,r,c,sizeFactor;
	
	
	public Tile(int r, int c, int boardSize,int sideLength){
		this.r=r;
		this.c=c;
		this.sizeFactor = boardSize/sideLength;
	}
	
	public void draw(Graphics g) {
		g.drawOval(r*sizeFactor, c*sizeFactor, sizeFactor, sizeFactor);
	}

	public void setMine() {
		// TODO Auto-generated method stub
		isAMine=!isAMine;
	}
	
	
}
