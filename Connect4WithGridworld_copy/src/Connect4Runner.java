import java.util.*;

import javax.swing.JOptionPane;

import info.gridworld.grid.*;
import info.gridworld.world.*;

public class Connect4Runner {
	private List<Location> FourInARowlocs = new ArrayList<Location>();
	private boolean gameOver = false;
	private  final int NUM_ROWS ;
	private  final int NUM_COLS ;
	private boolean redsTurn;
	private int highlightedColumn=0;
	Location dropLoc=null;
	private int[][] grid;
	private Location lastMove;

	private World world = new World() {
		@Override
		public boolean keyPressed(String key, Location loc) {
			//System.out.println(key);
			if(key.equals("RIGHT")){
				world.add(new Location(0,highlightedColumn), "");
				if(highlightedColumn==6){

				}else{
					highlightedColumn++;
				}
				if(redsTurn)
					world.add(new Location(0,highlightedColumn), new RedCheckerFaint());
				else{
					world.add(new Location(0,highlightedColumn), new BlackCheckerFaint());
				}
				//System.out.println(highlightedColumn);
				return false;
			}
			if(key.equals("LEFT")){
				world.add(new Location(0,highlightedColumn), "");
				if(highlightedColumn==0){

				}else{
					highlightedColumn--;
				}
				//System.out.println(highlightedColumn);
				if(redsTurn)
					world.add(new Location(0,highlightedColumn), new RedCheckerFaint());
				else{
					world.add(new Location(0,highlightedColumn), new BlackCheckerFaint());
				}
				return false;
			}
			if(key.equals("SPACE")){
				if(lowest(highlightedColumn)!=null){
					if(redsTurn)
						world.add(new Location(0,highlightedColumn), new BlackCheckerFaint());
					else{
						world.add(new Location(0,highlightedColumn), new RedCheckerFaint());
					}
				}
				return false;
			}
			if(key.equals("R")){
				clearGrid();
				return false;
			}
			return true;

		}
		@Override
		public void step(){

			if(lowest(highlightedColumn) != null){
				lastMove= new Location(lowest(highlightedColumn).getRow(),highlightedColumn);
				if(redsTurn){
					world.add(lowest(highlightedColumn),new RedChecker());
					grid[lowest(highlightedColumn).getRow()][highlightedColumn]=1;
				}else{
					world.add(lowest(highlightedColumn),new BlackChecker());
					grid[lowest(highlightedColumn).getRow()][highlightedColumn]=2;
				}

				tryMove();

			}
			if(gameOver()){
				highlightCells(findWin());
				showInfo();
				//System.out.println("*****win");
			}
		}

	};

	public Connect4Runner(int rows, int cols) {
		this.NUM_ROWS=rows;
		this.NUM_COLS=cols;
	}

	/**
	 * Try to drop a checker in the highlighted col.  If a checker can be dropped in that
	 * col (that column is not full), then a checker of the current player's color is 
	 * "dropped" as low as possible in that col.  The turn is advanced.  If that col
	 * is full, then the attempt is ignored
	 */
	protected void tryMove() {


		redsTurn = !redsTurn;
	}

	private void showInfo() {
		JOptionPane.showMessageDialog(null, "GAME OVER!! Hit 'R' key to restart");

	}

	public static void main(String[] args) {
		new Connect4Runner(6, 7).start();
	}

	/**
	 * Shows the current players checker above the board and a faint image of the
	 * checker where it would be placed if dropped.
	 */
	protected void showHighLightedSpot() {

	}
	private void highlightCells(ArrayList<Location> locs) {
		// TODO Auto-generated method stub
		if(redsTurn){
			for(Location l: locs){
				world.add(l, new WinningBlackChecker());
			}
		}
		else{
			for(Location l: locs){
				world.add(l, new WinningRedChecker());
			}
		}
	}
	private Location lowest(int col) {
		for(int r = grid.length-1; r>0;r--){
			if(grid[r][col]==0){
				return new Location(r,col);
			}
		}
		return null;
	}

	private void clearFaintCheckers() {
		//TODO auto-generated method stub
	}

	private void clearTopRow() {
		for(int c=0;c<this.NUM_COLS;c++) {
			world.remove(new Location(0,c));
		}
	}

	private void start() {
		gameOver = false;
		grid = new int[NUM_ROWS+1][NUM_COLS];
		world.setGrid(new BoundedGrid(NUM_ROWS+1, NUM_COLS));
		clearGrid();
		world.show();// shows the world
		pickRandomColorToStart();
		this.showHighLightedSpot();
	}
	/**
	 * Check to see if there are any 4-in-a-rows on the board.  This could
	 * be done more efficiently if the location of the latest checker is remembered.
	 * @return true if game is over.
	 */
	private boolean gameOver() {

		return findWin()!=null;
	}
	private ArrayList<Location> findWin(){
		if(checkRDiagonal(lastMove)!=null){
			return checkRDiagonal(lastMove);
		}
		if(checkLDiagonal(lastMove)!=null){
			return checkLDiagonal(lastMove);
		}
		if(checkVertical(lastMove)!=null){
			return checkVertical(lastMove);
		}
		if(checkHorizontal(lastMove)!=null){
			return checkHorizontal(lastMove);
		}
		return null;
	}

	private ArrayList<Location> checkLDiagonal(Location lastMove) {
		// TODO Auto-generated method stub
		ArrayList<Location> locs =new ArrayList<Location>();
		ArrayList<Location> locs2 =new ArrayList<Location>();
		for(int i = 3; i>=0;i--){
			int r = lastMove.getRow()+i;
			for(int c = lastMove.getCol()+i; c>lastMove.getCol()+i-4; c--){

				//System.out.println(a);
				//System.out.println(r + " c: "+c);
				if (inbounds(grid,r,c)){

					if(grid[r][c]==1){
						//System.out.println("added 1 red");
						locs.add(new Location(r,c));
					}

					if((grid[r][c]==2)){
						locs2.add(new Location(r,c));
					}

				}
				r--;
			}
			if(locs.size()==4){
				//System.out.println("***win");
				return locs;
			}
			locs.clear();
			if(locs2.size()==4){
				//System.out.println("***win");
				return locs2;
			}
			locs2.clear();
		}

		return null;
	}
	private ArrayList<Location> checkRDiagonal(Location lastMove){
		ArrayList<Location> locs =new ArrayList<Location>();
		ArrayList<Location> locs2 =new ArrayList<Location>();
		for(int i = 3; i>=0;i--){
			int r = lastMove.getRow()+i;
			for(int c = lastMove.getCol()-i; c<lastMove.getCol()-i+4; c++){

				//System.out.println(a);
				//System.out.println(r + " c: "+c);
				if (inbounds(grid,r,c)){

					if(grid[r][c]==1){
						//System.out.println("added 1 red");
						locs.add(new Location(r,c));
					}

					if((grid[r][c]==2)){
						locs2.add(new Location(r,c));
					}

				}
				r--;
			}
			if(locs.size()==4){
				//System.out.println("***win");
				return locs;
			}
			locs.clear();
			if(locs2.size()==4){
				//System.out.println("***win");
				return locs2;
			}
			locs2.clear();
		}

		return null;
	}
	private ArrayList<Location> checkVertical(Location lastMove) {
		// TODO Auto-generated method stub
		ArrayList<Location> locs =new ArrayList<Location>();
		ArrayList<Location> locs2 =new ArrayList<Location>();

		for(int a = lastMove.getRow(); a<lastMove.getRow()+4; a++){
			//System.out.println(a);
			if (inbounds(grid,a,lastMove.getCol())){
				if(grid[a][lastMove.getCol()]==1){
					//System.out.println("added 1 red");
					locs.add(new Location(a,lastMove.getCol()));
				}
				if(grid[a][lastMove.getCol()]==2){
					//System.out.println("added 1 black");
					locs2.add(new Location(a,lastMove.getCol()));
				}
			}
		}
		if(locs.size()==4){
			//System.out.println("***win");
			return locs;
		}

		if(locs2.size()==4){
			//System.out.println("***win");
			return locs2;
		}
		return null;
	}

	private ArrayList<Location> checkHorizontal(Location lastMove) {
		// TODO Auto-generated method stub
		ArrayList<Location> locs =new ArrayList<Location>();
		ArrayList<Location> locs2 =new ArrayList<Location>();
		for(int i = 3; i>=0;i--){
			for(int c = lastMove.getCol()+i; c>lastMove.getCol()+i-4; c--){
				//System.out.println(a);
				if (inbounds(grid,lastMove.getRow(),c)){

					if(grid[lastMove.getRow()][c]==1){
						//System.out.println("added 1 red");
						locs.add(new Location(lastMove.getRow(),c));
					}

					if((grid[lastMove.getRow()][c]==2)){
						locs2.add(new Location(lastMove.getRow(),c));
					}
				}
			}
			if(locs.size()==4){
				//System.out.println("***win");
				return locs;
			}
			locs.clear();
			if(locs2.size()==4){
				//System.out.println("***win");
				return locs2;
			}
			locs2.clear();
		}
		return null;

	}

	private boolean inbounds(int[][] grid2, Location l) {
		// TODO Auto-generated method stub
		return l.getRow()>-1 && l.getCol() >-1 && l.getRow()<grid2.length && l.getCol()<grid2[l.getRow()].length;
	}
	private boolean inbounds(int[][] grid2, int row, int col) {
		// TODO Auto-generated method stub
		//System.out.println(row+" "+col);
		return row>-1 && col >-1 && row<grid2.length && col<grid2[row].length;
	}


	// Randomly choose between the red and black player.
	private void pickRandomColorToStart() {
		this.redsTurn=Math.random()>=.5;
	}

	// clear out the grid so a new game can be started.  Basically go to each 
	// location and remove the object there.
	private void clearGrid() {
		for(int r=1; r<this.NUM_ROWS+1;r++) {
			for(int c=0; c<this.NUM_COLS;c++) {
				world.add(new Location(r,c), new EmptySquare());
				grid[r][c]=0;
			}
		}

	}



}
