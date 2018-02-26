import javax.swing.JOptionPane;

import kareltherobot.World;

import java.util.ArrayList;


public class SortRobotDriver {
int arraySize;
int numBots;
int maxBeeps;
int minBeepsInSet;
int maxBeepsInSet;
int pivotIndex;
SortableRobot minBeepsRobot;
SortableRobot maxBeepsRobot;
//ArrayList<SortableRobot> minBots =new ArrayList<SortableRobot>();
ArrayList<SortableRobot> bots = new ArrayList<SortableRobot>();


	public static void main(String[] args) {
		new SortRobotDriver().start();
	}

	private void start() {
		//1. input number of robots, and max number of beepers
		//2. create that number of robots (from part 1) with max number
		// of beeps, and place into an array and return that array to here.
		//3. Find robot with min # of beeps. Have that bot show all beeps
		//4. Repeat looking for max # of beeps.
		
		
		//Now sort 
		//A.  BubbleSort
		//B.  SelectionSort
		//C.  InsertionSort
		//D.  QuickSort
		//E.  MergeSort
		String numberBots = JOptionPane.showInputDialog("input number of robots:");
		numBots = Integer.parseInt(numberBots);
		String numberBeeps = JOptionPane.showInputDialog("input max beepers:");
		maxBeeps = Integer.parseInt(numberBeeps);
		World.setSize( maxBeeps+4,numBots+4 );
		World.setDelay(2);
		World.setVisible(true);
		makeBots(numBots, maxBeeps);
		showMeTheBeeps(numBots);
		dontShowMeTheBeeps(numBots);
//		getMin(0).showAllBeeps();
//		JOptionPane.showConfirmDialog(null, "min beeps is: "+minBeepsInSet);
//		minBeepsRobot.pickUpAllBeeps();
//		getMax().showAllBeeps();
//		JOptionPane.showConfirmDialog(null, "max beeps is: "+maxBeepsInSet);
//		maxBeepsRobot.pickUpAllBeeps();
		//JOptionPane.showConfirmDialog(null, "mid beeps is: "+bots.get(getPivot(0,numBots)).getNumBeeps());
		JOptionPane.showConfirmDialog(null, "pivot beeps is: "+bots.get(numBots-1).getNumBeeps());
		Object[] sortChoices = {"My Sort","Bubble Sort", "Selection Sort", "Insertion Sort","Quick Sort"};
		Object sortChoice = JOptionPane.showInputDialog(null,"choose sort","input",JOptionPane.INFORMATION_MESSAGE,null,sortChoices,sortChoices[0]);
		
		if (sortChoice.equals(sortChoices[0])){
			mySort();
		}else if (sortChoice.equals(sortChoices[1])){
			bubbleSort();
		}else if (sortChoice.equals(sortChoices[2])){
			selectionSort();
		}else if (sortChoice.equals(sortChoices[3])){
			insertionSort();
		}else if(sortChoice.equals(sortChoices[4])){
			quickSort();
		}
		//mySort();
		//bubbleSort();
		//selectionSort();
		//insertionSort();
		showMeTheBeeps(numBots);
		//dontShowMeTheBeeps(numBots);
		//getMin(5).showAllBeeps();
		//swapBots(bots.get(0),bots.get(3));
		//System.out.println(bots.get(0).getNumBeeps());
		//int[]loc={7,5};
		//bots[numBots-1].moveToLocation(loc);
		
		
		
		
		
		
		
		
	}
	private void showMeTheBeeps(int numBots){
		for (int c=0;c<numBots;c++){
			bots.get(c).showAllBeeps();
		}
	}
	private void dontShowMeTheBeeps(int numBots){
		for(int c=0;c<numBots;c++){
			bots.get(c).pickUpAllBeeps();
			
		}
	}
	private SortableRobot getMin(int startIndex){
		minBeepsRobot=bots.get(startIndex);
		for (int c=startIndex;c<numBots;c++){
			if (bots.get(c).getNumBeeps()<minBeepsRobot.getNumBeeps()){
				minBeepsRobot=bots.get(c);
				
			}
			//System.out.println(c);
		}
		minBeepsInSet = minBeepsRobot.getNumBeeps();
		
		
		return minBeepsRobot;
	}
	private SortableRobot getMax(){
		maxBeepsRobot=bots.get(0);
		for (int c=0;c<numBots;c++){
			if (bots.get(c).getNumBeeps()>maxBeepsRobot.getNumBeeps()){
				maxBeepsRobot=bots.get(c);
			}
			//System.out.println(c);
		}
		maxBeepsInSet = maxBeepsRobot.getNumBeeps();
		
		return maxBeepsRobot;
	}
	private void mySort(){
		for(int c=0; c<numBots; c++){
			for (int d=0;d<numBots; d++){
				if(bots.get(c).getNumBeeps()<bots.get(d).getNumBeeps()){
					swapBots(bots.get(c),bots.get(d));
					SortableRobot temp=bots.get(c);
					bots.set(c, bots.get(d));
					bots.set(d, temp);
				}
			}
		}
	}
	private void selectionSort(){
		//int d = 1;
		for (int d=0; d<numBots;d++){
			swapBots(bots.get(d), getMin(d));
			SortableRobot temp = bots.get(d);
			int temp2 = bots.indexOf(getMin(d));
			bots.set(d, getMin(d));
			bots.set(temp2, temp);
		}
		
	}
	private void insertionSort(){
		//int counter = 0;
		for (int e=1; e<numBots;e++){
			//int e=2;
			
			for (int d=e-1;d>-1;d--){
				if(bots.get(d+1).getNumBeeps()<bots.get(d).getNumBeeps()){
					swapBots(bots.get(d+1),bots.get(d));
					SortableRobot temp=bots.get(d+1);
					bots.set(d+1, bots.get(d));
					bots.set(d, temp);
					//counter++;
					
					//slide em over
					showMeTheBeeps(e);
				dontShowMeTheBeeps(e);
				}
				
				else{
					//insert
					
				}
				
			}
		}
	}
	private void quickSort(){
		
		movePivotToMiddle(0,numBots-1);
//		boolean a=true;
//		int f=numBots-1;
//		while (a){
//			
//			f= (int)(movePivotToMiddle(0,f)/2);
//			if (f <= 1){
//				a=false;
//			}
//		}
		
	}
	private void bubbleSort(){
		for (int times = 0; times<numBots-1;times++){
			for (int c=0; c+1<numBots-times;c++){
				if(bots.get(c).getNumBeeps()>bots.get(c+1).getNumBeeps()){
					swapBots(bots.get(c),bots.get(c+1));
					SortableRobot temp = bots.get(c);
					bots.set(c, bots.get(c+1));
					bots.set(c+1, temp);
					
				}
			}
		}
	}
	private Integer getPivot(int startIndex, int endIndex){
		getMax();
		double pivotValue = (maxBeepsInSet/2);
		double smallestSoFar=Math.abs(bots.get(startIndex).getNumBeeps()-pivotValue);
		for(int c=startIndex+1;c<endIndex;c++){
			if(Math.abs(bots.get(c).getNumBeeps()-pivotValue)<smallestSoFar){
				smallestSoFar = Math.abs(bots.get(c).getNumBeeps()-pivotValue);
				pivotIndex=bots.indexOf(bots.get(c));
			}
		}
		//returns index of robot closest to ideal pivot
		return pivotIndex;
	}
	private Integer movePivotToMiddle(int firstValue, int lastValue){
		int a = lastValue;//getPivot(0,numBots);
//		swapBots(bots.get(a),bots.get(numBots-1));
//		SortableRobot temp = bots.get(a);
//		bots.set(a, bots.get(numBots-1));
//		bots.set(numBots-1, temp);
		System.out.println("method");
		for(int c =firstValue; c<a;c++){
			System.out.println("***"+c);
			if(bots.get(c).getNumBeeps()>bots.get(a).getNumBeeps()){
				//swap next to pivot w/ pivot
				System.out.println("^^^if statement greater");
				swapBots(bots.get(a),bots.get(a-1));
				SortableRobot temp2 = bots.get(a);
				bots.set(a, bots.get(a-1));
				bots.set(a-1, temp2);
				a--;
				//swap end + next to pivot
				swapBots(bots.get(a+1),bots.get(c));
				SortableRobot temp3 = bots.get(a+1);
				bots.set(a+1, bots.get(c));
				bots.set(c, temp3);
				c--;
			}
			else if(bots.get(c).getNumBeeps()<bots.get(a).getNumBeeps()){
				System.out.println("&&&& if statement lesser");
			}
		}
		return a;
	}
	private void swapBots(SortableRobot r, SortableRobot r1){
		int[] rloc=r.getLocation();
		
		r.moveToLocation(r1.getLocation());
		r1.moveToLocation(rloc);
		
	}
	private void makeBots(int numBots, int maxBeeps){
		for (int c=0;c<numBots;c++){
			
			bots.add(c,new SortableRobot(1,c+1,(int)(Math.random()*maxBeeps)));
			
			
		}
	}

}
