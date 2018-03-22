import java.util.ArrayList;

import kareltherobot.Robot;
import kareltherobot.World;


public class SortableRobot extends Robot implements SortableBotInterface {
int b=1;

	public SortableRobot(int street, int avenue, int numBeeps) {
		super(street, avenue, North, numBeeps);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void turnRight() {
		// TODO Auto-generated method stub
		this.turnLeft();
		this.turnLeft();
		this.turnLeft();

	}

	@Override
	public void turnAround() {
		// TODO Auto-generated method stub
		for (int a=0;a<2;a++){
			this.turnLeft();
		}

	}

	@Override
	public void slideLeft(int steps) {
		// TODO Auto-generated method stub
		this.turnLeft();
		for (int a=0;a<steps-1;a++){
			this.move();
		}
		turnRight();

	}

	@Override
	public void slideRight(int steps) {
		// TODO Auto-generated method stub
		turnRight();
		for (int a=0;a<steps-1;a++){
			this.move();
		}
		this.turnLeft();

	}

	@Override
	public void showAllBeeps() {
		// TODO Auto-generated method stub
		while(anyBeepersInBeeperBag()==true){
			this.putBeeper();
			this.move();
			
		}
		
	}
	
	public void pickUpAllBeeps(){
		this.turnAround();
		while(this.frontIsClear()==true){
			this.move();
			this.pickBeeper();
		}
		this.turnAround();
	}

	@Override
	public int getNumBeeps() {
		// TODO Auto-generated method stub
		
		return this.beepers();
	}

	@Override
	public int[] getLocation() {
		// TODO Auto-generated method stub
		int[] loc={this.street(),this.avenue()};
		return loc;
	}

	@Override
	public void moveToLocation(int[] loc) {
		// TODO Auto-generated method stub
		moveToLocationHelper(loc);
		this.turnLeft();
		b--;
		moveToLocationHelper(loc);
		this.turnRight();
		b=1;
		
		
		

	}
	public void moveToLocationHelper(int[] loc){
		if (getLocation()[b]<loc[b]){
			slideRight(loc[b]-getLocation()[b]+1);
		}
		if (getLocation()[b]>loc[b]){
			slideLeft(getLocation()[b]-loc[b]+1);
		}
	}
	public void start(){
		int[] loc={5,7};
		
		System.out.println(getLocation()[0]);
		System.out.println(getLocation()[1]);
		moveToLocation(loc);
		//slideRight(3);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		World.setSize(12, 12);
		World.setDelay(1/100);
		World.setVisible(true);
		new SortableRobot(2,3,5).start();
	}

}
