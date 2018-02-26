import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * This code has errors right now.  We need to actually add in the methods that 
 * all lists have.  We need to do this because we implemented the List interface
 * and classes that implement an interface promise to add that functionality (at least)
 * 
	When you have the IDE implement the interface, LOTS of methods will be added to 
	the class.  You will have to appropriately implement most of them.
 *
 */


public class WordWithCountList implements List<WordWithCount> {

	// stores all the words added to the list
	private WordWithCount[] words = new WordWithCount[10];
	// keeps track of the next open slot at the end of the occupied "buckets" of the array
	private int nextAvailable = 0;

	public static void main (String[] args){
		WordWithCountList list = new WordWithCountList();
		WordWithCount wwc0 = new WordWithCount("apple");
		WordWithCount wwc1 = new WordWithCount("zebra");
		WordWithCount wwc2 = new WordWithCount("horse");
		WordWithCount wwc3 = new WordWithCount("tyu");
		WordWithCount wwc4 = new WordWithCount("bee");
		list.add(wwc4);
		list.add(wwc0);
		list.add(wwc1);
		list.add(wwc3);
		list.add(wwc1);
		list.add(wwc2);
		list.add(wwc3);
		for (int i = 0; i<list.size(); i++){
			System.out.println(list.get(i));
		}
		System.out.println(list.indexOf(wwc3));

	}

	@Override
	public boolean add(WordWithCount e) {
		if (this.size()==0){
			words[0]=e;
			nextAvailable++;
			return true;
		}
		if (this.size()==1){
			if (e.compareTo(words[0])>0){
				words[1]=e;
				nextAvailable++;
				return true;
			}
			words[1]=words[0];
			words[0]=e;
			nextAvailable++;
			return true;
		}
		int i = this.indexOf(e);

		if(i >= 0) {
			get(i).increment();
			return false;
		}

		// new word
		if(size() == words.length) {
			doubleArray();
		}



		int spot = findSpot(e, 0, this.size()-1);

		//System.out.println(spot);
		//		for (int a = this.size(); a>spot; a-- ){
		//			words[a] = words[a-1];
		//		}
		//		if (e.compareTo(words[spot])>0){
		//			words[spot+1]=e;
		//			nextAvailable++;
		//			return true;
		//		}else if (e.compareTo(words[spot-1])<0){
		//			words[spot]= words[spot-1];
		//			words[spot-1]=e;
		//			nextAvailable++;
		//			return true;
		//		}else{
		for (int a = this.size(); a>spot; a-- ){
			words[a] = words[a-1];
		}
		words[spot]=e;
		nextAvailable++;
		return true;

		//}
	}
	//binary search
	private int findSpot (WordWithCount e,int low, int high){
		if (high - low ==1){
			//System.out.println(high+" ****"+e);
			
			if (e.compareTo(words[high])>0){
				return high+1;
			}
			else if (e.compareTo(words[low])<0){
				return low;
			}else if(e.equals(words[low])){
				return low;
			}

			return high;

		}
		int mid = (int)((low + high)/2);

		if (e.equals(words[mid])){
			return mid;
		}
		if (e.compareTo(words[mid])>0){
			low = mid;
			return(findSpot(e, low, high));
		}

		high = mid;
		return(findSpot(e, low, high));



	}
	// user must call change in sortType to see change
	public void sort(int low, int high){
		int mid = (high+low)/2;
		WordWithCount pivot = get(mid);
		for (int i = low; i<high; i++){
			if (get(i).compareTo(pivot)<0){
				swap(get(i), pivot);
			}
		}
		if (!(high==low)){
			high = mid;
			sort(low, high);
		}
		
		
		
		
	}
	private void swap(WordWithCount w1, WordWithCount w2){
		WordWithCount temp = w1;
		words[indexOf(w1)] = w2;
		words[indexOf(w2)] = temp;
	}
	private void swap(int a, int b){
		WordWithCount temp = get(a);
		words[a] = get(b);
		words[b] = temp;
	}
	public int getTotalWords(){
		int sum = 0;
		for (int i=0; i< this.size();i++){
			sum+=this.get(i).getCount();
		}
		return sum;
	}
	private void doubleArray() {
		WordWithCount[] bigger = new WordWithCount[size()*2];

		for(int i = 0; i < size(); i++) {
			bigger[i]=words[i];
		}
		words = bigger;
	}

	@Override
	public void add(int index, WordWithCount element) {
		add(element);
		//throw new UnsupportedOperationException ("Sry, but this is for your own good!");
	}

	@Override
	public boolean addAll(Collection<? extends WordWithCount> c) {
		// TODO Auto-generated method stub
		int i = size();
		for (Object o: c){
			if (o instanceof WordWithCount){
				add((WordWithCount) o);
			}
		}
		if (i == size())
			return false;
		return true;
	}

	@Override
	public boolean addAll(int index, Collection<? extends WordWithCount> c) {
		throw new UnsupportedOperationException ("Sry, but this is for your own good!");
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		words = new WordWithCount[10];

	}

	@Override
	public boolean contains(Object o) {

		return indexOf(o) >= 0;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		for(Object o:c){
			if (!contains(o)){
				return false;
			}
		}
		return true;
	}

	@Override
	public WordWithCount get(int index) {
		if(index < this.size() && index >= 0)
			return words[index];
		throw new IndexOutOfBoundsException("Hey dude! Bad index: "+index+" Size: "+this.size());
	}

	@Override
	public int indexOf(Object o) {

//				for(int x = 0; x<size(); x++) {
//					if(get(x).equals(o)) {
//						return x;
//					}
//				}
		if (o instanceof WordWithCount){
			WordWithCount w = (WordWithCount)o;
			int a = findSpot(w, 0, size()-1);
			if (a>size()-1){
				return -1;
			}
			if (get(a).compareTo(w)==0){
				return a;
			}
		}
		return -1;
	}

	@Override
	public boolean isEmpty() {

		return size() == 0;
	}

	@Override
	public Iterator<WordWithCount> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		return indexOf(o);
	}

	@Override
	public ListIterator<WordWithCount> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<WordWithCount> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		int num = size();
		for (int i = indexOf(o);i<size()-1;i++){
			words[i]=words[i+1];
		}
		words[size()+1]=null;
		if (num == size())
			return false;
		return true;
	}

	@Override
	public WordWithCount remove(int index) {
		// TODO Auto-generated method stub
		WordWithCount temp = get(index);
		if (index<0 || index>size()-1){
			throw new ArrayIndexOutOfBoundsException("bad index. index = "+index+"size = "+size());
		}
		remove(get(index));
		return temp;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		int i = size();
		for (Object o: c){
			if (o instanceof WordWithCount){
				remove(o);
			}
		}
		if (i==size())
			return false;
		return true;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		int i = size();
		for (WordWithCount w: this){
			if (c.contains(w)){
				remove(w);
			}
		}
		if (i==size())
			return false;
		return true;
	}

	@Override
	public WordWithCount set(int index, WordWithCount element) {
		throw new UnsupportedOperationException ("Sry, but this is for your own good!");
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.nextAvailable;
	}

	@Override
	public List<WordWithCount> subList(int fromIndex, int toIndex) {
		if(fromIndex < 0 || toIndex > size() || fromIndex > toIndex) {
			throw new IndexOutOfBoundsException ("bad");
		}


		List<WordWithCount> littleList = new WordWithCountList();

		while(fromIndex < toIndex) {
			littleList.add(this.get(fromIndex));
			fromIndex++;
		}

		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		Object[] arr = words;
		return arr;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}



}
