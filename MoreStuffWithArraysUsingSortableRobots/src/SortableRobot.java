

import kareltherobot.Robot;
import kareltherobot.World;

public class SortableRobot extends Robot implements SortableBotInterface {

	public SortableRobot(int st, int arg1, int arg3) {
		super(st, arg1, North, arg3);
	}

	@Override
	public void turnRight() {
		this.turnLeft();
		this.turnAround();
	}

//	@Override
//	public String toString() {
//		return "Super"+super.toString();
//	}
	
	
	@Override
	public void turnAround() {
		this.turnLeft();
		this.turnLeft();
	}

	@Override
	public void slideLeft(int steps) {
		this.turnLeft();
		while(steps > 0) {
			steps--;
			this.move();
		}
		this.turnRight();
	}

	@Override
	public void slideRight(int steps) {
		this.turnAround();
		this.slideLeft(steps);
		this.turnAround();
	}

	@Override
	public void showAllBeeps() {
		while(this.getNumBeeps()>0) {
			this.putBeeper();
			this.move();
		}
	}

	@Override
	public int getNumBeeps() {
		
		return this.beepers();
	}

	@Override
	public int[] getLocation() {
		
		return new int[] {this.street(), this.avenue()};
	}

	@Override
	public int[] moveToLocation(int[] loc) {
		if(loc == null || loc.length < 2 || loc[0]<1||loc[1]<1)
			throw new IllegalArgumentException("loc: "+loc);
		int[] oldLoc = getLocation();
		int streets = this.street()-loc[0];
		if(streets !=0) {
			if(streets > 0) {
				this.faceDir(West);
			}
			else  {
				this.faceDir(East);
			}
			this.slideLeft(Math.abs(streets));
		}
		int aves = this.avenue()-loc[1];
		if(aves !=0) {
			if(aves > 0) {
				this.faceDir(North);
			}
			else  {
				this.faceDir(South);
			}
			this.slideLeft(Math.abs(streets));
		}
		faceDir(North);
		

		
		return oldLoc;
	}

	public static void main(String[] args) {
		SortableRobot sr = new SortableRobot(3,7,9),
				      sr2  = new SortableRobot(5,5,2),
				      sr3 = new SortableRobot(4,8,19);
		World.setVisible(true);
		//sr.moveToLocation(sr2.moveToLocation(sr.getLocation()));
		Robot r = new Robot(3,45,North,6);
		
		
		System.out.println(sr.getLocation()[1]);
		
	}

	@Override
	public Direction faceDir(Direction dir) {
		Direction oldDir = this.direction();
		while(!this.direction().equals(dir))
			this.turnLeft();
		return oldDir;
	}

	public void scoopBeeps() {
		while(frontIsClear()) {
			while(nextToABeeper())
				this.pickBeeper();
			move();
		}
		while(nextToABeeper())
			this.pickBeeper();
	}

}
