import java.util.*;

import info.gridworld.grid.*;
import info.gridworld.world.*;
public class KnightsTour {

	private final boolean SHOW_GUI = true;
	private int[][] grid;
	private int numClicks=1;
	private int[] loc = new int[2];
	private ArrayList<int[]> moves = new ArrayList<int[]>();

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
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new KnightsTour().start();
	}

	private void start() {
		// TODO Auto-generated method stub

		makeGrid(8,8,7,7);

		if(this.SHOW_GUI) {
			world.show();
			showGrid();
		}
	}
	private void showGrid() {
		// TODO Auto-generated method stub
		for(int r = 0; r<grid.length; r++){
			for(int c = 0; c<grid[r].length;c++){
				if (grid[r][c]==-1*numClicks){
					//grid[r][c]-=numClicks;
					this.world.add(new Location(r,c), "N"/*+grid[r][c]*/);
				}
				else if(grid[r][c]<0){
					this.world.add(new Location(r,c),""+-1*grid[r][c]);
				}
				else{
					this.world.add(new Location(r,c),""/*+grid[r][c]*/);
				}
			}
		}
	}
	private void updateBoard(){
		for(int r = 0; r<grid[0].length;r++){
			for(int c =0; c<grid[r].length;c++){
				if(grid[r][c]>=0){
					grid[r][c]=getPosMoves(new int[]{r,c}).size();
				}
			}
		}
	}
	private void nextGen() {
		// TODO Auto-generated method stub
		//loc = getRandMove(getPosMoves());
		if(getPosMoves(loc).size()==0){
			System.out.println("no");
			numClicks--;
			return;
		}

		updateBoard();
		int[]move = getSmartMove();
		loc[0]=move[0];
		loc[1]=move[1];
		
		
		grid[loc[0]][loc[1]]=-1*numClicks;
	}
	private int[] getSmartMove(){

		int minSoFar =Integer.MAX_VALUE;
		int newR = 0;
		int newC = 0;
		int[] move = new int[2];
		
		if(getPosMoves(loc).size()==1){
			move[0]=getPosMoves(loc).get(0)[0];
			move[1]=getPosMoves(loc).get(0)[1];
			return move;
		}
		
		for(int i =0; i<getPosMoves(loc).size(); i++){
			int r = getPosMoves(loc).get(i)[0];
			int c = getPosMoves(loc).get(i)[1];
			if(grid[r][c]<minSoFar && grid[r][c]>0){
				minSoFar = grid[r][c];
				newR=r;
				newC=c;
			}
		}
		ArrayList<int[]> choices = new ArrayList<int[]>();
		for(int i=0; i<getPosMoves(loc).size();i++){
			int r = getPosMoves(loc).get(i)[0];
			int c = getPosMoves(loc).get(i)[1];
			if(grid[r][c]==grid[newR][newC]){
				choices.add(new int[]{r,c});
			}
		}
		move = getRandMove(choices);
		//int[]move = new int[]{newR,newC};
		return move;
	}
	

	private ArrayList<int[]> getPosMoves(int[]loc){
		ArrayList<int[]> posMoves = new ArrayList<int[]>();
		int r = loc[0];
		int c = loc[1];
		int[] rowChange = new int[]{-2,-2,-1,+1,+2,+2,+1,-1,};
		int[] colChange = new int[]{-1,+1,+2,+2,+1,-1,-2,-2};
		for(int i = 0; i< 8; i++){
			int newR = r+rowChange[i];
			int newC = c+colChange[i];
			if(inbounds(grid,newR, newC) && grid[newR][newC]>=0){
				posMoves.add(new int[]{newR,newC});
			}
		}
		return posMoves;
	}

	private int[] getRandMove(ArrayList<int[]> posMoves) {
		// TODO Auto-generated method stub
//		if(posMoves.size()==0){
//			return null;
//		}
		//System.out.println(posMoves.size()+" "+getPosMoves(loc).size());
		return posMoves.get((int)(Math.random()*posMoves.size()));
		
		
		
	}
	private boolean inbounds(int[][] grid2, int row, int col) {
		// TODO Auto-generated method stub
		//System.out.println(row+" "+col);
		return row>-1 && col >-1 && row<grid2.length && col<grid2[row].length;
	}

	private void makeGrid(int rows, int cols, int x, int y) {
		// TODO Auto-generated method stub
		
		loc[0] = x;
		loc[1]=y;
		grid = new int[rows][cols];
		BoundedGrid<String> gr = new BoundedGrid<String>(rows,cols);
		//UnboundedGrid<String>gr = new UnboundedGrid<String>();
		world.setGrid(gr);
		grid[x][y]=-1;
	}

}
