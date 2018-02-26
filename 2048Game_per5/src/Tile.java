
public class Tile extends GameObject {

	int value;
	public int getValue() {
		// TODO Auto-generated method stub
		return value;
	}
	public Tile(int n) {
		value = n;
	}

	@Override
	public boolean equals(Object obj) {
		try {
			Tile other = (Tile)obj;
			return other.getValue()==this.getValue();
		}
		catch(Exception e) {
			return false;
		}
	}
}
