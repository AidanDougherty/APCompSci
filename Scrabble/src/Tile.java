import info.gridworld.grid.Location;


public class Tile {
	private String let;
	
	private Location tileLoc;
	public Tile(String letter){
		setLet(letter);
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	public String getLet() {
		return let;
	}
	public void setLet(String let) {
		this.let = let;
	}
	public Location getTileLoc() {
		return tileLoc;
	}
	public void setTileLoc(Location tileLoc) {
		this.tileLoc = tileLoc;
	}
	

}
