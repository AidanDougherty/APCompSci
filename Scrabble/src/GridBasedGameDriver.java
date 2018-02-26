import java.util.ArrayList;
import java.util.Collection;

import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;
import info.gridworld.world.World;

public class GridBasedGameDriver {

	private boolean firstTurn =true;
	private BoundedGrid<Object> gr = new BoundedGrid<Object>(18,15);
	private ArrayList<Tile> hand = new ArrayList<Tile>();
	private ArrayList<Location> handSpace = new ArrayList<Location>();
	private ArrayList<Location> play = new ArrayList<Location>();
	private WordWithCountList dic = new WordWithCountList();
	private ArrayList<Location> specialSpots = new ArrayList<Location>();
	private ArrayList<Location> triples = new ArrayList<Location>();
	private ArrayList<Location> doubles = new ArrayList<Location>();
	private ArrayList<Location> protectedTiles = new ArrayList<Location>();
	private ArrayList<Tile> wordPlayed = new ArrayList<Tile>();
	private ArrayList<Tile>bag = new ArrayList<Tile>();

	World<Object> world = new World<Object>() {

		@Override
		public boolean locationClicked(Location loc) {
			System.out.println("You just clicked "+loc);


			return true;
		}

		@Override
		public boolean keyPressed(String key, Location loc) {

			if(isValidKey(key)){
				//String s = (String) gr.get(loc);
				if(gr.get(loc) instanceof String && ((String)(gr.get(loc))).length()>0){

				}else{
					gr.put(loc, key);
					play.add(loc);
					//maybe some bugs here, depends on how break works idk
					for(int a =0; a<handSpace.size();a++){
						if(gr.get(handSpace.get(a)).equals(key)){
							gr.put(handSpace.get(a),"");
							for(int b =0; b< hand.size();b++){
								if(hand.get(b).getLet().equals(key)){
									hand.set(b,new Tile(""));
									break;
								}
							}
							break;
						}
					}
				}
			}
			if(key.equals("BACK_SPACE")){
				if(!protectedTiles.contains(loc)){
					if (checkSpecialSpace(loc) instanceof SpecialSpace && gr.get(loc) instanceof String){
						returnToHand(loc);
						gr.put(loc, checkSpecialSpace(loc));
						play.remove(loc);
					}else if(gr.get(loc) instanceof String){
						//gr.put(loc, "");
						returnToHand(loc);
						play.remove(loc);
					}
				}
			}


			return false;
		}

		private boolean isValidKey(String key) {
			// TODO Auto-generated method stub
			for(int a = 0; a<hand.size(); a++){
				if(hand.get(a).getLet().equals(key))
					return true;
			}
			return false;
		}
		public void step(){
			if(play.size()==0){
				System.out.println("ERR: Play a word plz");
			}else if(gr.get(new Location(7,7))instanceof Star){

				System.out.println("ERR: must go thru star 1st turn");
			}else if (validLine(play)>0){
				//System.out.println("BUENO!");
				sortPlay(play);
				if(play!=null){
					if ((dic.contains(new WordWithCount(toWord(play))))){

						sumPoints(play);
						protectedTiles.addAll(play);
						play.clear();
						refillHand();
						firstTurn=false;
					}else{
						System.out.println("ERR: NOT A WORD");
					}
				}else{
					System.out.println("ERR: place word connecting to other word plz");
				}

			}
			//			for(int i=0; i< play.size(); i++)
			//			System.out.println(play.get(i).getRow() + " "+play.get(i).getCol());
		}



	};

	public static void main(String[] args) {
		new GridBasedGameDriver().start();
	}
	private void fillBag(){
		int[] dist = new int[]{9,2,2,4,12,2,3,2,9,1,1,4,2,6,8,2,1,6,4,6,4,2,2,1,2,1};
		for(int letNum = 65; letNum <91; letNum++){
			char let = (char) letNum;

			for(int x = 0; x<dist[letNum-65];x++){
				bag.add(new Tile(""+ let));
				//System.out.println(bag.size());
			}

		}
	}
	private void returnToHand(Location loc){
		for(int i=0; i< handSpace.size();i++){
			if(gr.get(handSpace.get(i)).equals("")){
				String s = (String) gr.get(loc);
				hand.set(i, new Tile(s));
				play.remove(loc);
				gr.put(handSpace.get(i), s);
				gr.remove(loc);
				break;
			}
		}
	}
	private SpecialSpace checkSpecialSpace(Location loc){
		if(loc.getRow()==7 && loc.getCol()==7){
			return new Star();
		}
		if(doubles.contains(loc)){
			return new DoubleWord();
		}
		if(triples.contains(loc)){
			return new TripleWord();
		}
		return null;
	}
	private void refillHand(){
		for(int i=0; i< hand.size();i++){
			System.out.println("***"+hand.get(i).getLet());
			if(hand.get(i).getLet().equals("")){
				Tile t = randomLetter();
				hand.set(i,t);
				gr.put(handSpace.get(i),t.getLet());
			}
		}
	}
	private String toWord(ArrayList<Location> locs){
		String word ="";
		for(int i=0; i< locs.size();i++){
			word+=gr.get(locs.get(i));
		}
		System.out.println(word.toLowerCase());
		return word.toLowerCase();
	}
	private void sortPlay(ArrayList<Location> locs){
		int v = validLine(locs);
		if(v==1){
			for(int i= 0; i<locs.size(); i++){
				for(int a =0; a<locs.size();a++){
					if(locs.get(i).getCol()<locs.get(a).getCol()){
						Location temp = locs.get(i);
						locs.set(i,locs.get(a));
						locs.set(a, temp);
					}
				}
			}
		}
		if(v==2){
			for(int i= 0; i<locs.size(); i++){
				for(int a =0; a<locs.size();a++){
					if(locs.get(i).getRow()<locs.get(a).getRow()){
						Location temp = locs.get(i);
						locs.set(i,locs.get(a));
						locs.set(a, temp);
					}
				}
			}
		}
		//PROBLEMO:: HOW TO ADD TILES TO PLAY FROM LAST TURN??
		if(firstTurn){

		}else{
			ArrayList<Location> arr = findPlayedTiles(play);
			if(play.size()==arr.size()){
				
				play=null;
			}else{
				play=arr;
			}
		}
	}

	private ArrayList<Location> findPlayedTiles(ArrayList<Location> locs) {
		// TODO Auto-generated method stub
		ArrayList<Location> newLocs = new ArrayList<Location>();
		
		if(validLine(locs)==1){
			for(int i=0; i<locs.size();i++){
				//checkVertical(locs.get(i))
			}
		}else if(validLine(locs)==2){

		}
		return null;
	}
	private boolean validLoc(Location x) {
		// TODO Auto-generated method stub
		return x.getRow()>0&&x.getRow()<gr.getNumRows()&&x.getCol()>0&&x.getCol()<gr.getNumCols();
	}
	private int validLine(ArrayList<Location> locs){
		//is in 1 column
		boolean colCheck =true;
		// is in 1 row
		boolean rowCheck = true;
		int c = locs.get(0).getCol();
		int r = locs.get(0).getRow();
		for(int i=0; i< locs.size();i++){
			if(locs.get(i).getCol()!=c){
				colCheck=false;
			}
			if(locs.get(i).getRow()!=r){
				rowCheck=false;
			}
		}
		if(rowCheck){
			return 1;
		}else if(colCheck){
			return 2;
		}else{
			return 0;
		}
	}

	private void sumPoints(ArrayList<Location> play) {
		// TODO Auto-generated method stub
		//System.out.println("YASS BOI");
		String[] arr = new String[]{"EAIONRTLSU","DG","BCMP","FHVWY","K" };
		int sum=0;
		for(int i=0; i<play.size();i++){
			for(int a=0; a<arr.length;a++){
				if(arr[a].contains((String) gr.get(play.get(i)))){
					sum+=a+1;
				}
				if("JX".contains((String) gr.get(play.get(i)))){
					sum+=8;
				}
				if("QZ".contains((String) gr.get(play.get(i)))){
					sum+=10;
				}
			}
		}
		System.out.println(sum);
	}

	private void start() {
		FileProcessor.main(null);
		dic = FileProcessor.dictionary;
		fillBag();
		setUpGameBoard();
		world.show();// now the world is visible
	}
	private void showGrid(){

	}
	private void randLets(ArrayList<Location> places){
		for(int a =0; a< places.size(); a++){
			Tile rand = randomLetter();
			hand.add(rand);
			gr.put(places.get(a), rand.getLet());
		}
	}
	private Tile randomLetter(){
		int a = ((int)((Math.random()*bag.size())));
		Tile randTile = bag.get(a);
		bag.remove(a);
		//System.out.println(bag.size());
		return randTile;
	}

	private void setUpGameBoard() {


		for(int x = 0; x< 15; x++){
			gr.put(new Location(x,x), new DoubleWord());
			specialSpots.add(new Location(x,x));
			doubles.add(new Location(x,x));
		}
		int x = 0;
		for(int c = 14; c>0;c--){
			gr.put(new Location(x,c), new DoubleWord());
			specialSpots.add(new Location(x,c));
			doubles.add(new Location(x,c));
			x++;
		}

		for (int r = 0; r<16; r+=7){
			for(int y = 0; y<15; y+=7){
				gr.put(new Location(r,y), new TripleWord());
				specialSpots.add(new Location(r,y));
				triples.add(new Location(r,y));
				doubles.remove(new Location(r,y));
			}
		}

		gr.put(new Location(7,7), new Star());
		specialSpots.add(new Location(7,7));
		doubles.remove(new Location(7,7));
		triples.remove(new Location(7,7));

		for(int a =0; a<7; a++){
			handSpace.add(new Location(16,a));
		}
		randLets(handSpace);
		//UnboundedGrid<String>gr = new UnboundedGrid<String>();
		world.setGrid(gr);
	}

}
