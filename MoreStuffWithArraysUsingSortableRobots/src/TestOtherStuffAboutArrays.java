
public class TestOtherStuffAboutArrays {

	public static void main(String[] args) {
		new TestOtherStuffAboutArrays().start();
	}

	private void start() {
		SortableRobot[] bots = makeBots();
		String[] strings = makeStrings();
		//int nullBots = countEmptySpots(bots);
		int nullStrings = countEmptySpots(strings);
		SortableRobot sr = findMax(bots);
		int i = findBot(sr);
		boolean newBot = replaceBot(i);
	}

	/**
	 * Makes an array of random Strings with random length between 7 and 15 inclusive.  
	 * The Strings contain random characters (0 - 255 ASCII vals) up to length 10.
	 * There must be at least one null String.
	 * Some of the array "buckets" may randomly contain null Strings.
	 * @return the array of random Strings
	 */
	private String[] makeStrings() {
		// TODO Auto-generated method stub
		String[] words = new String[(int)(7+Math.random()*9)];
		//System.out.println(words.length);
		for (int a= 0; a<words.length;a++){
			words[a]=randomWord();
			//System.out.println(words[a]);
		}
		int nullWords = 1+(int)(Math.random()*words.length-1);
		for (int c= 0; c<nullWords;c++){
			int a=(int)(Math.random()*words.length);
			if (words[a]!= null){
				words[a]= null;
			}
			else{
				c--;
			}
		}
		
		for (int b= 0; b<words.length;b++){
			System.out.println(words[b]);
		}
		return words;
	}

	private String randomWord() {
		// TODO Auto-generated method stub
		String s = "";
		for (int a = 0; a<(int)(Math.random()*11)+1;a++){
			s+= (char)(Math.random()*256);
		}
		return s;
		//System.out.println(s);
	}

	/**
	 * Counts the number of empty (null) locations in the specified arr.
	 * @param arr the array to be processed
	 * @return the number of empty (contain null) spots in the array. 
	 */
	private int countEmptySpots(Object[] arr) {
		// TODO Auto-generated method stub
		int nullSpots=0;
		for (int a= 0; a<arr.length;a++){
			//System.out.println(nullSpots);
			if (arr[a]==null){
				nullSpots++;
			}
		}
		System.out.println("no. of nulls is: "+nullSpots);
		return nullSpots;
	}

	/**
	 * attempts to place a new robot at the location of the array with a new Robot.
	 * @param i index of the array where the robot will be placed
	 * @return true if a new robot with a different number of beepers has been placed there
	 */
	private boolean replaceBot(int i) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * findBot returns the index of the specified robot.  
	 * @param sr The SortableRobot we are seeking
	 * @return the index where that SortableRobot can be found in the array
	 */
	private int findBot(SortableRobot sr) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * finds the SortableRobot with the maximum number of beeps and returns it.
	 * @param bots array of SortableRobots to be processed
	 * @return the SortableRobot with max num of beeps.  If tie, any robot.  If no 
	 * SortableRobots are in the array, null is returned.
	 */
	private SortableRobot findMax(SortableRobot[] bots) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Makes an array of SortableRobots of random length between 6 and 12.  
	 * Some positions in the array must contain null SRs, randomly.
	 * @return  the array of Robots that was created.
	 */
	
	private SortableRobot[] makeBots() {
		SortableRobot[] robots = new SortableRobot[(int) ((Math.random()*7)+6)];
		for (int a = 0; a<robots.length; a++){
			robots[a]= new SortableRobot(1, a+1, (int)(Math.random()*21));
		}
		for (int a = 0; a<Math.random()*robots.length;a++){
			int b = (int)(Math.random()*robots.length);
			if (robots[b]!= null){
				robots[b]=null;
			}
			else{
				a--;
			}
		}
		
		return robots;
	}

}
