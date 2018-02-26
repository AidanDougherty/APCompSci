import java.util.*;

import info.gridworld.grid.*;
import info.gridworld.world.*;

public final class LifeRunner {

	private int currentGen;
	private List<int[][]> gridList = new ArrayList(); // this List will hold all the generations
	// thus allowing us to look at previous gens
	private World<String> world = new World<String>() {
		@Override 
		public void step() {
			// This method is called every time the step method is called
			//System.out.println("You clicked the step button!");
			numClicks++;
			nextGen();
			showGrid();
		}
	};
	private int[][] grid;// to be constructed in the makeGrid method
	private final boolean SHOW_GUI = true;

	public static void main(String[] args) {
		new LifeRunner().start();
	}

	private void start() {
		makeGrid(40,40, 800);// add 50 "lives" to a 25 row X 20 col grid

		if(this.SHOW_GUI) {
			world.show();
			showGrid();
		}
		//System.out.println(getNeighbors(grid, 3, 3));
		//		printGrid(grid);
		//		if(this.SHOW_GUI)
		//			showGrid();
		//		nextGen();
		//		if(this.SHOW_GUI)
		//			showGrid();
		//		printGrid(grid);

	}



	/**
	 * create a grid with dimension of rows X cols with lives objects
	 * randomly placed into the grid.  One tricky part is to make sure you are 
	 * placing the correct number of objects in the grid.  If you randomly 
	 * come up with the same location, your code needs to account for that!
	 * 
	 * After making the grid, it is added to the List of grids
	 * 
	 * @param rows number of rows in the grid
	 * @param cols number of cols in the grid
	 * @param lives number of lives to be added to the grid
	 */
	private void makeGrid(int rows, int cols, int lives) {
		grid = new int[rows][cols];
		BoundedGrid<String> gr = new BoundedGrid<String>(rows,cols);
		//UnboundedGrid<String>gr = new UnboundedGrid<String>();
		world.setGrid(gr);

		while(lives>0){
			int r = (int)(Math.random()*rows),
					c = (int)(Math.random()*cols);
			if(grid[r][c]==0){
				grid[r][c]=1;
				lives--;
			}

		}
		gridList.add(grid);
	}
	/**
	 * Prints the specified 2D array of int to the console.
	 * @param grid2 
	 */
	private void printGrid(int[][] grid2) {


	}
	/**
	 * This method advances grid from the previous generation to the next generation
	 * based on these two simple rules:
	 * 1.  If there are 3 neighboring objects around a cell, that cell will contain a 
	 *     life in the subsequent generation.
	 * 2.  If there are 3 neighboring objects around a cell that currently contains an 
	 *     object, then that object lives on into the next generation.
	 *     
	 * Be sure to make all the changes to a different grid.
	 * Finally, the newest grid is added to the list of generations
	 */
	private void nextGen() {
//		if(gridList.size()>3 && gridList.get(gridList.size()-1).equals(gridList.get(gridList.size()-3))){
//			System.out.println("max gens: "+gridList.size());

//		}else{
			int[][] newgrid = new int[grid.length][grid[0].length];
			for(int r =0; r< grid.length; r++){
				for(int c = 0; c<grid[r].length; c++){
					if (getNeighbors(grid,r,c)==2 && grid[r][c]==1){
						newgrid[r][c]=1;
					}else if(getNeighbors(grid,r,c)==3){
						newgrid[r][c]=1;
					}else{
						newgrid[r][c]=0;
					}

				}
			}
			gridList.add(newgrid);
			grid = newgrid;
		}

	//}

	private int getNeighbors(int[][] grid2, int r, int c) {
		// TODO Auto-generated method stub
		int neighbors = 0;
		int col = c-1;
		int row = r-1;
		for(row = r-1; row<=r+1; row++){
			for(col = c-1; col<=c+1;col++){
				if(inbounds(grid,row,col) && grid[row][col]==1){
					neighbors++;
				}
			}col=c-1;
		}
		//System.out.println(r+" "+c);
		if(grid[r][c]==1)
			return neighbors-1;
		return neighbors;
	}

	private boolean inbounds(int[][] grid2, int row, int col) {
		// TODO Auto-generated method stub
		//System.out.println(row+" "+col);
		return row>-1 && col >-1 && row<grid2.length && col<grid2[row].length;
	}

	private int numClicks;
	/**
	 * This method shows the current grid in the World.  The World cells contain Strings, 
	 * so you can add Strings (either empty String:  "" or "*" if there is a life there).
	 */
	private void showGrid() {
		// example of adding to the world.
		//int[][] nextGrid = gridList.get(gridList.size()-1);
		for(int r = 0; r<grid.length; r++){
			for(int c = 0; c<grid[r].length;c++){
				if (grid[r][c]==1)
					this.world.add(new Location(r,c), "*");
				else{
					this.world.add(new Location(r,c),"");
				}
			}
		}
	}
}
