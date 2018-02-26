import java.util.*;

//Aidan Dougherty, Parth Saxena, Chloe Engel


public class RollableWithStringTester {

	public static void main(String[] args) {
		RollableWithStringTester rwst = new RollableWithStringTester();
		Rollable rand = new Die(30);
		String s = rwst.getRolls(rand,10 );
		//System.out.println(s);
		
		//	rwst.testFindComma();
		//rwst.testNextInt();
		//rwst.testInARow();
		rwst.testLongestRuns();
	}

	private String getRolls(Rollable rand, int numTosses) {
		// your code here!
		String rolls = "";
		rolls+=rand.toss();
		for(int i =1; i<numTosses;i++){
			rolls+=","+rand.toss();
		}
		return rolls;
	}

	private int nextInt(String s, int index) {
		String s2 = s.substring(index);
		//System.out.println(s2);
		if(findComma(s,index)>0){
		//	System.out.println("**");
			s2=s2.substring(0,findComma(s,index)-index);
			//System.out.println(s2);
		}
		if(findComma(s,index)==index){
			return -1;
		}
		//System.out.println(s2);
		return Integer.parseInt(s2);
	}

	private int inARow(String s, int index) {
		int i = index;
		if(s.substring(index,index+1).equals(",")){
			//System.out.println("***");
			return 0;
		}
		int num = nextInt(s,index);
		int count = 1;
		boolean a = true;
		while(a){
			if(num==nextInt(s,findComma(s,i)+1)){
				count++;
				i=findComma(s,i)+1;
			}else{
				a=false;
			}
		}
		return count;
	}

	private List<Integer> longestRuns(String s) {
		// TODO Auto-generated method stub
		ArrayList<Integer> times = new ArrayList<Integer>();
		boolean a = true;
		int i = 0;
		while(a){
			
			times.add(inARow(s,i));
			int h = findComma(s,i)+1;
			for(int z = i; z<h-1; z++){
				times.add(0);
			}
			i=findComma(s,i)+1;
			//System.out.println(i);
			if(i==0){
				a=false;
			}
		}
		int bsf = 0;
		for(int j = 0; j<times.size();j++){
			if(times.get(j)>bsf){
				bsf=times.get(j);
			}
		}
		if(bsf==1){
			return new ArrayList<Integer>();
		}
		ArrayList<Integer> indexes = new ArrayList<Integer>();
		for(int j = 0; j<times.size();j++){
			if(times.get(j)==bsf){
				indexes.add(j);
			}
		}
		
		return indexes;
	}

	private int findComma(String s, int i) {
		if(i<=s.length()) {
			int x = s.substring(i).indexOf(",");
			if(x>-1)
				return x+i;
		}
		return -1;
	}

	private void testLongestRuns() {
		String s = "3,12,136,6,6,29,3,47,47,47,12";
		List<Integer> list  = longestRuns(s);
		System.out.println("longestRuns(s) "+list);
		list  = longestRuns(s.substring(7));
		System.out.println("longestRuns(s.substring(7)) "+list);
		list  = longestRuns(s.substring(13,20));
		System.out.println("longestRuns(s.substring(13,20)) "+list);

	}


	private void testInARow() {
		String s = "3,12,136,6,6,29,3,47,47,47,12";
		int n = inARow(s,0);
		System.out.println("inARow(s,0) "+n);
		n =inARow(s,1);
		System.out.println("inARow(s,1); "+n);
		n =inARow(s,5);
		System.out.println("inARow(s,5); "+n);
		n =inARow(s,7);
		//System.out.println(n);
		System.out.println("inARow(s,7); "+n);
		n =inARow(s,9);
		System.out.println("inARow(s,9); "+n);
	}


	private void testNextInt() {
		String s = "3,12,136,6,6,29,3,47,47,47,12";
		int n = nextInt(s,0);
		System.out.println("nextInt(s,0) "+n);
		n =nextInt(s,1);
		System.out.println("nextInt(s,1); "+n);
		n =nextInt(s,5);
		System.out.println("nextInt(s,5); "+n);
		n =nextInt(s,6);
		System.out.println("nextInt(s,6); "+n);
		n =nextInt(s,21);
		System.out.println("nextInt(s,21); "+n);
	}
	private void testFindComma() {
		String s = "3,12,136,6,6,29,3,47,47,47,12";
		int n = findComma(s,0);
		System.out.println("findComma(s,0) "+n);
		n =findComma(s,3);
		System.out.println("findComma(s,3); "+n);
		n =findComma(s,4);
		System.out.println("findComma(s,4); "+n);
		n =findComma(s,27);
		System.out.println("findComma(s,27); "+n);
	}
}
interface Rollable{
	int toss();
}
class Die implements Rollable{
	int sides;
	public Die(int i) {
		sides = i;
	}
	@Override
	public int toss() {
		return (int) (Math.random()*sides);
	}
}
