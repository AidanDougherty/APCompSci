import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

import javax.swing.filechooser.*;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import kareltherobot.*;


public class Driver implements Directions{

	Robot roomba ;
	int beeperCountTotal;
	int height;
	int totalArea;
	int width;
	int numberOfPiles;
	double avgPileSize;
	double percentDirty;
	ArrayList<Integer> pileSizes = new ArrayList<Integer>();
	ArrayList<String> pileLocations = new ArrayList<String>();
	String largestPileLocation;
	int n=0;
	int beepersInPile;
	int largestPileSize;
	int currentX;
	int currentY;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// LEAVE THIS ALONE!!!!!!
		Driver d = new Driver();
		d.getInfo();
		d.cleanRoom();
		d.printResults();
		System.exit(0);
		
	}

	private void printResults() {
		// A bunch of System.out.prints go here
		//System.out.println("The biggest pile was ");
		height+=1;
		width=totalArea/height;
		avgPileSize=(double)beeperCountTotal/(double)numberOfPiles;
		percentDirty=100*(double)numberOfPiles/(double)totalArea;
		JOptionPane.showConfirmDialog(null, "the area is "+height+"x"+width);
		JOptionPane.showConfirmDialog(null,"total area is "+totalArea);
		JOptionPane.showConfirmDialog(null,"the number of piles is "+numberOfPiles);
		JOptionPane.showConfirmDialog(null,"the total number of beepers is "+beeperCountTotal);
		JOptionPane.showConfirmDialog(null,"the average pile size is "+avgPileSize);
		JOptionPane.showConfirmDialog(null,"the percent dirty is "+percentDirty+"%");
		orderArrayList();
		JOptionPane.showConfirmDialog(null,"the biggerest pile is "+largestPileSize);
		JOptionPane.showConfirmDialog(null,"largest pile at "+largestPileLocation);
		//System.out.println(height);
		
	}

	private void cleanRoom() {
		// all the code that cleans and counts goes here
		// obviously, lots more here
		moveToCorner();
		//System.out.println("1");
		cleanRoad();
		cleanAllRoads();
		//if (bottomRightCornerTest()==false){
			//turnRight();
			//roomba.move();
			//turnRight();
			//cleanRoad();
		//}
		
		
	}
	
	private void cleanAllRoads() {
		// TODO Auto-generated method stub
		if (bottomRightCornerTest()==false){
			turnRight();
			roomba.move();
			updateCoordinates();
			height++;
			turnRight();
			cleanRoad();
		}
		if (bottomLeftCornerTest()==false){
			roomba.turnLeft();
			roomba.move();
			updateCoordinates();
			height++;
			roomba.turnLeft();
			cleanRoad();
			cleanAllRoads();
			}
		
	}
	private void orderArrayList(){
		int biggestSoFar=pileSizes.get(0);
		largestPileLocation=pileLocations.get(0);
		for (int a=1;a<pileSizes.size();a++){
			if (pileSizes.get(a)>biggestSoFar){
				biggestSoFar=pileSizes.get(a);
				largestPileLocation=pileLocations.get(a);
			}
		}
		largestPileSize=biggestSoFar;
	}
	private boolean bottomLeftCornerTest() {
		// TODO Auto-generated method stubx
		roomba.turnLeft();
		if (roomba.frontIsClear()==true){
			turnRight();
			return false;
		}
		else{
			return true;
		}
	}

	private boolean bottomRightCornerTest() {
		// TODO Auto-generated method stub
		turnRight();
		if (roomba.frontIsClear()==true){
			roomba.turnLeft();
			return false;
		}
		else{
			return true;
		}
		
	}

	private void cleanRoad() {
		// TODO Auto-generated method stub
		while (roomba.frontIsClear()==true){
			if (roomba.nextToABeeper()==true){
			while(roomba.nextToABeeper()==true){
				roomba.pickBeeper();
				beeperCountTotal++;
				beepersInPile++;
			}
			numberOfPiles++;
			pileSizes.add(n,beepersInPile);
			pileLocations.add(n,"("+currentX+","+currentY+")");
			n++;
			beepersInPile=0;
			
			}
			roomba.move();
			updateCoordinates();
			totalArea++;
		}
		if (roomba.frontIsClear()==false){
			if (roomba.nextToABeeper()==true){
			while(roomba.nextToABeeper()==true){
				roomba.pickBeeper();
				beeperCountTotal++;
				beepersInPile++;
			}
			numberOfPiles++;
			pileSizes.add(n, beepersInPile);
			pileLocations.add(n,"("+currentX+","+currentY+")");
			n++;
			beepersInPile=0;
			}
		}
		totalArea+=1;
	}

	private void moveToCorner() {
		// TODO Auto-generated method stub
		
		if (roomba.frontIsClear()==true){
			roomba.move();
			updateCoordinates();
			moveToCorner();
		}else{
			roomba.turnLeft();
			while (roomba.frontIsClear()==true){
				roomba.move();
				updateCoordinates();
			}
			roomba.turnLeft();
			roomba.turnLeft();
		}
	}
	private void updateCoordinates(){
		if (roomba.facingNorth()==true){
		currentY+=1;
		}else if(roomba.facingEast()==true){
			currentX+=1;
		}else if(roomba.facingSouth()==true){
			currentY-=1;
		}else if(roomba.facingWest()==true){
			currentX-=1;
		}
		}
	private boolean tester(){
		return true;
	}
	//still broken
	private void faceNorth(){
		if (roomba.facingNorth()==true){
			
		}else if (roomba.facingEast()==true){
			roomba.turnLeft();
		}else if (roomba.facingSouth()==true){
			roomba.turnLeft();
			roomba.turnLeft();
		}else if (roomba.facingWest()==true){
			turnRight();
		}
	}
	private void turnRight(){
		roomba.turnLeft();
		roomba.turnLeft();
		roomba.turnLeft();
	}

	private void getInfo() {
		// this method acquires the starting street, avenue and direction
		// of the robot from the user.  Also it inputs the name of the world
		// file.  It then opens the world file and creates the robot
		JFileChooser jfc = new JFileChooser("directory");
		jfc.setCurrentDirectory(new File(System.getProperty("user.home")));
		int result = jfc.showOpenDialog(jfc);
		if (result == JFileChooser.APPROVE_OPTION) {
		    File selectedFile = jfc.getSelectedFile();
		    String wrldName = jfc.getSelectedFile().toString();
		    World.readWorld(wrldName);
		}
		//String wrldName = "basicRoom.wld";
		//String wrldName = "Room2.kwld";
		
		
		
		World.setDelay(1/100);
		World.setVisible(true);
		String streetStart = JOptionPane.showInputDialog("input starting street (y-var):");
		int streetStartNumber = Integer.parseInt(streetStart);
		String avenueStart = JOptionPane.showInputDialog("input starting avenue (x-var)");
		int avenueStartNumber = Integer.parseInt(avenueStart);
		Object[] directionsChoices = {"North","South","East","West"};
		Object directionChoice = JOptionPane.showInputDialog(null,"choose direction","input",JOptionPane.INFORMATION_MESSAGE,null,directionsChoices,directionsChoices[0]);
		//String directionStart = JOptionPane.showInputDialog("input starting direction:");
		if (directionChoice=="North"){
			roomba = new Robot(avenueStartNumber,streetStartNumber,North,0);
			//System.out.println("one");
		}else if(directionChoice=="West"){
			
			roomba = new Robot(avenueStartNumber,streetStartNumber,West,0);
			//turnRight();
			//System.out.println("two");
		}else if(directionChoice=="South"){
			
			roomba = new Robot(avenueStartNumber,streetStartNumber,South,0);
			//roomba.turnLeft();
			//roomba.turnLeft();
		}else if(directionChoice=="East"){
			
			roomba = new Robot(avenueStartNumber,streetStartNumber,East,0);
			//roomba.turnLeft();
		}
		currentX=avenueStartNumber;
		currentY=streetStartNumber;
		
		
		
	}

}
