import java.util.Scanner;

import kareltherobot.Directions;
import kareltherobot.Robot;
import kareltherobot.World;


public class MakeADiamond  implements Directions{
	Robot bobbie; 
	int x=0;
	static int beepersOnSide;
	public static void main(String[] args) {
		MakeADiamond temp = new MakeADiamond();
		
		temp.prompt();
		
		temp.octagon(beepersOnSide);
		//temp.diamond(beepersOnSide);
		
	}
	private void prompt() {
		// Ask the user for the diamond size
		System.out.println("Hey dude, I see you want to make a diamond.  Give me the deets: ");
		Scanner keyboard  = new Scanner(System.in);
		beepersOnSide = keyboard.nextInt();
		//System.out.println(beepersOnSide);
		
	}
	 private void octagon(int beepersOnSide) {
		// TODO Auto-generated method stub
		 bobbie = new Robot(2*beepersOnSide+beepersOnSide-2,2*beepersOnSide-1,North,infinity);
		 setupOctagon(beepersOnSide);
		 DrawOctagon(beepersOnSide);
		 FillOctagon(beepersOnSide);
		 
	}

	private void FillOctagon(int beepersOnSide) {
		// TODO Auto-generated method stub
		//inner octagons
		for (int b=0;b<beepersOnSide-1;b++){
			bobbie.turnLeft();
			bobbie.turnLeft();
			bobbie.move();
			bobbie.turnLeft();
			bobbie.turnLeft();
			for(int c=0;c<4;c++){
				DrawOctSide(beepersOnSide);
				DrawOctDiagonal(beepersOnSide-(b+1));
			}
		}
		//inner square
		bobbie.turnLeft();
		bobbie.move();
		bobbie.turnLeft();
		bobbie.move();
		for (int c=0;c<beepersOnSide-2;c++){
			//down
			for (int d=0;d<beepersOnSide-2;d++){
				bobbie.putBeeper();
				bobbie.move();
			}
			//up
			bobbie.turnLeft();
			bobbie.turnLeft();
			for (int e=0;e<beepersOnSide-2;e++){
				bobbie.move();
			}
			bobbie.turnLeft();
			bobbie.move();
			bobbie.turnLeft();
		}
		
	}
	private void DrawOctagon(int beepersOnSide) {
		// TODO Auto-generated method stub
		for (int a=0;a<4;a++){
			DrawOctSide(beepersOnSide);
			DrawOctDiagonal(beepersOnSide);
		}
		
	}
	private void DrawOctSide(int beepersOnSide) {
		// TODO Auto-generated method stub
		bobbie.turnLeft();
		for (int y=0;y<beepersOnSide-1;y++){
			bobbie.putBeeper();
			bobbie.move();
		}
	}
	private void DrawOctDiagonal(int beepersOnSide) {
		// TODO Auto-generated method stub
		for (int z=0;z<beepersOnSide-1;z++){
			bobbie.putBeeper();
			bobbie.turnLeft();
			bobbie.move();
			turnRight();
			bobbie.move();
		}
	}
	private void setupOctagon(int beepersOnSide) {
		// TODO Auto-generated method stub
		World.setVisible(true);
		World.setDelay(1/(beepersOnSide));
		World.setSize(2*beepersOnSide+beepersOnSide-2, 2*beepersOnSide+beepersOnSide-2);
	}
	private void diamond(int beepersOnSide) {
		// TODO Auto-generated method stub
		bobbie = new Robot(2*beepersOnSide-1,beepersOnSide,North,infinity);
		setupDiamond(beepersOnSide);
		DrawDiamond(beepersOnSide);
	}

	
	 private void setupDiamond(int beepersOnSide){
		 World.setVisible(true);
			World.setDelay(1/(beepersOnSide));
			World.setSize(2*beepersOnSide, 2*beepersOnSide);
			
	 }
	 private void DrawDiamond(int beepersOnSide) {
		// make the robot street 1, middle of diamond (corner)
		for(int y = 0;y<4;y++){
		 for(x=0;x<beepersOnSide-1;x++){
			bobbie.putBeeper();
			bobbie.turnLeft();
			bobbie.move();
			bobbie.turnLeft();
			bobbie.move();
			bobbie.turnLeft();
			bobbie.turnLeft();
		}
		 bobbie.turnLeft();
		 x=0;
	   }
	 }
	 
	private void turnRight(){
		bobbie.turnLeft();
		bobbie.turnLeft();
		bobbie.turnLeft();
	}
	private void drawSide(Robot r, int beepersOnSide) {
		
	}

}
