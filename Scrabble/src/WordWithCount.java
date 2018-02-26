/**
 * Each WordWithCount will represent a token (String) found in a text file
 * not necessarily English or a word... and the frequency that the token has
 * appeared in the text (or texts, really). 
 * 
 * A WordWithCount needs a way to give us its token and frequency.  We should 
 * be able to increase its frequency (in the event that we find a subsequent occurrence
 * of the token).  We need an appropriate toString method.  We need an appropriate 
 * compareTo method.
 * 
 * @author RHanson
 *
 */

public class WordWithCount implements Comparable<WordWithCount>{

	private static int compareType = 0;// 0 for alpha, 1 for freq, 2 for length
	
	/*
	 * Add private data here.  What does our WordWithCount need to know about
	 * itself and keep track of throughout its existence?
	 */
	
	//private int length;
	
	private int count; 
	
	private String word;
	
	
	
	/**
	 * Constructor for WordWithCount probably only needs one argument.
	 * It could make sense to write a second constructor for this class.
	 * @param args
	 */
	
	

	/**
	 * Like all other compareTo methods, this returns positive int if this
	 * WordWithCount is larger than the specified (input) WordWithCount.
	 * 
	 * This really can get more complicated, though.  Do I always want to 
	 * compare myself alphabetically?  There may be some times when I want 
	 * to compare myself by frequency, or by length of token...  How could 
	 * we accomplish this different behavior?
	 *  0 for alpha, 1 for freq, 2 for length
	 */
	@Override
	public int compareTo(WordWithCount other) {

		if(compareType == 2)
			return this.word.length()-other.word.length();	
		if(compareType == 0)
			return this.word.compareTo(other.word);
		
		return this.count-other.count;
	}
	
	public static void setSortToAlpha() {
		compareType = 0;
	}
	public static void setSortToLength() {
		compareType = 2;
	}
	public static void setSortToFreq() {
		compareType = 1;
	}
	
	private WordWithCount() {
		
	}
	public WordWithCount(String word) {
		count = 1;
		this.word = word;
	}

	public static void main(String[] args) {
		// test the constructors and accessors and modifiers
		// make a couple of WordWithCount Objects
		WordWithCount wwc1 = new WordWithCount("hello");
		
		WordWithCount[] words = new WordWithCount[10];
		WordWithCount wwc2 = wwc1;
		int i = 0;

		WordWithCount.setSortToFreq();
		
		words[0]=wwc1;
		wwc1.increment();
		words[1]=new WordWithCount("my");
		words[1]=new WordWithCount("name");
		
		wwc1.setSortToLength();
		
		System.out.println(wwc1);
		wwc1.increment();
		
		wwc1.increment(6);
		
		// test the accessors
		
		
		// test the compareTo (possibly using different criteria to base the 
		// comparison on)
	}
	public int getCount(){
		return count;
	}
	public void increment(int i) {
		if(i >0) {
			increment();
			increment(i);
			System.out.println(i);
		}
	}

	public WordWithCount clone() {
		WordWithCount temp = new WordWithCount(this.word);
		while(temp.count < this.count)
			temp.increment();
		return temp;
	}
	public void increment() {
		count++;
	}

	@Override
	public String toString() {
		return "" +  word+" "+ count  ;
	}

	
	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		WordWithCount w = null;
		try {
			w = (WordWithCount) obj;
		}
		catch(Exception e) {
			return false;
		}
		
		return (this.word.equals(w.word));
	}


}
