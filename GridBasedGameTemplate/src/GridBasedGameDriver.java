import info.gridworld.grid.Location;
import info.gridworld.world.World;

public class GridBasedGameDriver {

	World<GameObject> world = new World<GameObject>() {
		
		@Override
		public boolean locationClicked(Location loc) {
			System.out.println("You just clicked "+loc);
			
			
			return false;
		}
		
		@Override
		public boolean keyPressed(String key, Location loc) {
			System.out.println("You just pressed the "+key+" key.");
			
			
			return false;
		}
		
	};
	
	public static void main(String[] args) {
		new GridBasedGameDriver().start();
	}

	private void start() {
		setUpGameBoard();
		world.show();// now the world is visible
	}

	private void setUpGameBoard() {
		
	}

}
