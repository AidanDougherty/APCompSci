import java.util.Scanner;

import kareltherobot.Directions;
import kareltherobot.Robot;
import kareltherobot.World;

import java.lang.Math;

import javax.swing.JOptionPane;
public class CircleDrawer implements Directions{
	Robot r;
	double radius;
	int helperRadius;
	double chordLength;
	double a=0;
	boolean tester=true;
	int beeperCount;
	boolean placeIt = false;
	double deltaTheta=.05;
	double theta=0;
	double deltaX;
	double deltaY;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new CircleDrawer().start();
	}
	private void start() {
		// TODO Auto-generated method stub
		//System.out.println("hello");
		//System.out.println("Hey dude, I see you want to make a circle.  Give me the deets: ");
		//Scanner keyboard  = new Scanner(System.in);
		//radius = keyboard.nextDouble();
		String input = JOptionPane.showInputDialog("Hey dude, I see you want to make a circle.  Give me the deets (radius): ");
		radius=Double.parseDouble(input);
		helperRadius = (int) (radius);					  
		r = new Robot(helperRadius+1,helperRadius+1,West,infinity);
		setUpWorld();
		//drawFullCircle();
		drawCircle2();


	}
	private void drawCircle2() {
		// TODO Auto-generated method stub
		r.turnLeft();
		r.turnLeft();
		moveRadius();
		r.turnLeft();
		drawQuad1();
	}
	private void drawQuad1() {
		// TODO Auto-generated method stub
		//loop (theta increases by deltaTheta) 
		while(theta<(2*3.14159)){
			r.putBeeper();
			//move up deltaY
			deltaY=Math.round(100*(Math.sin(theta)-Math.sin(theta-deltaTheta)));
			//System.out.println(100*Math.round(Math.sin(theta)-Math.sin(theta-deltaTheta)));
			if (deltaY==0){

			}else if(deltaY==-1){
				r.turnLeft();
				r.turnLeft();
				r.move();
			}
			//deltaY=1
			else{
				r.move();
			}


			//move left deltaX
			deltaX=Math.round(100*(Math.cos(theta-deltaTheta)-Math.cos(theta)));
			//System.out.println(Math.round(100*(Math.cos(theta-deltaTheta)-Math.cos(theta))));
			if (deltaX==0){

			}else if(deltaX==-1){
				r.turnLeft();
				r.move();
			}
			//deltaX=1
			else{
				turnRight();
				r.move();
			}
			faceNorth();
			theta+=deltaTheta;
			System.out.println(theta);
		}
	}
	private void drawFullCircle(){
		drawCircle();
		turnRight();
		moveRadius();
		r.turnLeft();
		r.turnLeft();
		placeIt=true;
		a=0;
		chordLength=0;
		drawCircle();
		System.out.println(beeperCount);
	}
	private void moveRadius(){
		for (int f=0;f<radius;f++){
			r.move();
		}
	}
	private void drawCircle() {
		// TODO Auto-generated method stub
		//South-half
		drawSemicircle();
		//re-orient to 1 above center
		turnRight();
		for(int c=0;c<radius;c++){
			r.move();
		}
		r.move();
		turnRight();
		//North-half
		a=1;
		drawSemicircle();
		//fill in empty spots
		//re-orient to center(face South)
		turnRight();
		for (int d=0;d<radius;d++){
			r.move();
		}
		//East-Half
		a=0;
		drawSemicircle();
		//re-orient to 1 West of Center(face North)
		turnRight();
		for (int e=0;e<radius+1;e++){
			r.move();
		}
		turnRight();
		//West-half
		a=1;
		drawSemicircle();
	}
	private void drawSemicircle() {
		// TODO Auto-generated method stub


		while (a<radius){
			chordLength=Math.round(Math.sqrt(radius*radius-a*a));
			drawChord(chordLength);
			r.turnLeft();
			r.move();
			turnRight();
			a++;
		}
		myPutBeeper();
	}
	private void drawChord(double chordLength){
		for (int b=0;b<chordLength;b++){
			r.move();
		}
		myPutBeeper();
		r.turnLeft();
		r.turnLeft();
		for (int b=0;b<2*chordLength;b++){
			r.move();
		}
		myPutBeeper();
		r.turnLeft();
		r.turnLeft();
		for (int b=0;b<chordLength;b++){
		r.move();
		}

	}
	private void turnRight(){
		r.turnLeft();
		r.turnLeft();
		r.turnLeft();
	}
	private void myPutBeeper() {
		// TODO Auto-generated method stub
		if (tester=r.nextToABeeper()){

		}else if (placeIt==true){
			r.putBeeper();
		}
		else{
			beeperCount+=1;
		}

	}

	private void setUpWorld() {
		// TODO Auto-generated method stub
		World.setVisible(true);
		World.setDelay(1/100);
		World.setSize(2*helperRadius+1,2*helperRadius+1);
	}
	private void faceNorth(){
		if (r.facingNorth()==true){

		}else if (r.facingEast()){
			r.turnLeft();
		}else if (r.facingSouth()){
			r.turnLeft();
			r.turnLeft();
		}else if (r.facingWest()){
			turnRight();
		}
	}
}
