import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.world.World;

public class GridBasedGameDriver {
	private JFrame frame = new JFrame("2048 Game");
	private JPanel panel;
	private static final int NUM_COLS = 4;
	private static final int NUM_ROWS = 4;
	private Tile[] types = {new Tile2(), new Tile4(), new Tile8(), new Tile16(),
			new Tile32(), new Tile64(), new Tile128(), new Tile256(),
			new Tile512(), new Tile1024(), new Tile2048(), new Tile4096(),
			new Tile8192()};
	private ArrayList<Image> tileImages = new ArrayList<Image>();
	private int score=0;
	private ArrayList<Integer> highscores = new ArrayList<Integer>();
	private int high=0;

	World<GameObject> world = new World<GameObject>() {

		@Override
		public boolean locationClicked(Location loc) {
			System.out.println("You just clicked "+loc);
			return false;
		}

		@Override
		public boolean keyPressed(String key, Location loc) {
			//System.out.println("You just pressed the "+key+" key.");
			if(key.equals("LEFT")) {
				slideLeft();
				combineLeft();
				slideLeft();
				add2or4();
			}
			else if(key.equals("RIGHT")) {
				slideRight();
				combineRight();
				slideRight();
				add2or4();
			}
			else if(key.equals("DOWN")) {
				slideDown();
				combineDown();
				slideDown();
				add2or4();
			}
			else if(key.equals("UP")) {
				slideUp();
				combineUp();
				slideUp();
				add2or4();
			}
			//world.show();
			frame.repaint();
			highscores.add(score);
			updateMax(highscores);
			highscores.remove(highscores.size()-1);
			return false;
			
		}



	};

	Grid gr ;

	public static void main(String[] args) {
		new GridBasedGameDriver().start();

	}
	protected void combineUp() {
		for(int col = 0; col < this.NUM_COLS; col++) {
			for(int row = 1; row < NUM_ROWS; row++) {
				Location here = new Location(row,col),
						above = new Location(row-1, col);
				if(gr.get(here) != null && gr.get(above)!= null && gr.get(here).equals(gr.get(above))) {
					Tile t = (Tile) gr.remove(here);
					gr.put(above, makeTile(t.getValue()*2));
					score+=t.getValue()*2;
				}
			}
		}
	}
	private Tile makeTile(int i) {
		int index = (int) Math.round(Math.log(i)/Math.log(2));
		return types[index-1];
	}
	protected void combineDown() {
		for(int col = 0; col < this.NUM_COLS; col++) {
			for(int row = NUM_ROWS-1; row >0 ; row--) {
				Location here = new Location(row,col),
						above = new Location(row-1, col);
				if(gr.get(here) != null && gr.get(above)!= null && gr.get(here).equals(gr.get(above))) {
					Tile t = (Tile) gr.remove(above);
					gr.put(here, makeTile(t.getValue()*2));
					score+=t.getValue()*2;
				}
			}
		}
	}
	protected void combineRight() {
		for(int col = this.NUM_COLS-2; col >=0 ; col--) {
			for(int row = NUM_ROWS-1; row >=0 ; row--) {
				Location here = new Location(row,col),
						right = new Location(row, col+1);
				if(gr.get(here) != null && gr.get(right)!= null && gr.get(here).equals(gr.get(right))) {
					Tile t = (Tile) gr.remove(here);
					gr.put(right, makeTile(t.getValue()*2));
					score+=t.getValue()*2;
				}
			}
		}
	}
	protected void combineLeft() {
		for(int row = NUM_ROWS-1; row >=0 ; row--) {
			for(int col = 0; col < this.NUM_COLS-1; col++) {

				Location here = new Location(row,col),
						right = new Location(row, col+1);
				if(gr.get(here) != null && gr.get(right)!= null && gr.get(here).equals(gr.get(right))) {
					Tile t = (Tile) gr.remove(right);
					gr.put(here, makeTile(t.getValue()*2));
					score+=t.getValue()*2;
				}
			}
		}
	}
	//	public void slide(int startRow, int endRow, int dRow, int startCol,int endCol, int dCol) {
	//		int changes = 0;
	//		for(int col = 0; col < this.NUM_COLS; col++) {
	//			for(int row = 1; row < NUM_ROWS; row++) {
	//				Location here = new Location(row,col),
	//						 above = new Location(row-1, col);
	//				if(gr.get(here) != null && gr.get(above)== null) {
	//					changes++;
	//					gr.put(above, gr.get(here));
	//				}
	//			}
	//		}
	//		if(changes > 0)
	//			slideUp();
	//	}
	public void slideUp() {
		int changes = 0;
		for(int col = 0; col < this.NUM_COLS; col++) {
			for(int row = 1; row < NUM_ROWS; row++) {
				Location here = new Location(row,col),
						above = new Location(row-1, col);
				if(gr.get(here) != null && gr.get(above)== null) {
					changes++;
					gr.put(above, gr.remove(here));
					
				}
			}
		}
		if(changes > 0)
			slideUp();
	}
	protected void slideDown() {
		int changes = 0;
		for(int col = 0; col < this.NUM_COLS; col++) {
			for(int row = NUM_ROWS-2; row >=0; row--) {
				Location here = new Location(row,col),
						below = new Location(row+1, col);
				if(gr.get(here) != null && gr.get(below)== null) {
					changes++;
					gr.put(below, gr.remove(here));
				}
			}
		}
		if(changes > 0)
			slideDown();
	}

	protected void slideRight() {
		int changes = 0;
		for(int row = NUM_ROWS-1; row >=0; row--) {
			for(int col =  this.NUM_COLS-2; col >= 0; col--) {
				Location here = new Location(row,col),
						below = new Location(row, col+1);
				if(gr.get(here) != null && gr.get(below)== null) {
					changes++;
					gr.put(below, gr.remove(here));
				}
			}
		}
		if(changes > 0)
			slideRight();
	}

	protected void slideLeft() {
		int changes = 0;

		for(int row = NUM_ROWS-1; row >=0; row--) {
			for(int col = 1; col < this.NUM_COLS; col++) {
				Location here = new Location(row,col),
						below = new Location(row, col-1);
				if(gr.get(here) != null && gr.get(below)== null) {
					changes++;
					gr.put(below, gr.remove(here));
				}
			}
		}
		if(changes > 0)
			slideLeft();
	}

	private void start() {
		setUpGameBoard();
		gr= world.getGrid();
		//world.show();// now the world is visible
		openImages();
		this.frame.setBackground(new Color(127, 127, 127));
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				//				Graphics2D g2d = (Graphics2D) g;
				//				g.setColor(new Color(75, 130, 200));
				//				g.fillRect(35, 100, 22, 200);
				//				////////  MORE HERE!!!!!!!
				//				g2d.setColor(Color.CYAN);
				//				g2d.fillOval(200, 400, 100, 50);
				//				g2d.setColor(Color.BLACK);
				//				g.drawLine(this.getWidth()/2, 0, this.getWidth()/2, 200);
				drawGame(g);


			}

		};
		panel.setPreferredSize(new Dimension(500,500));
		frame.add(panel);
		frame.pack();
		setUpKeyMappings();
		//panel.addKeyListener(l);

	}
	private void updateMax(ArrayList<Integer> arr){
		int bsf = 0;
		for(int i=0; i<arr.size();i++){
			if(arr.get(i)>bsf){
				bsf=arr.get(i);
			}
		}high = bsf;
	}
	private void setUpKeyMappings() {
		// maps keys with actions...
		//  The code below maps a KeyStroke to an action to be performed
		// In this case I mapped the space bar key to the action named "shoot"
		// Whenever someone hits the Space Bar the action shoot is sent out

		panel.getInputMap().put(KeyStroke.getKeyStroke("LEFT"),"slideLeft");
		panel.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"),"slideRight");
		panel.getInputMap().put(KeyStroke.getKeyStroke("UP"),"slideUp");
		panel.getInputMap().put(KeyStroke.getKeyStroke("DOWN"),"slideDown");
		panel.getInputMap().put(KeyStroke.getKeyStroke("R"), "reset");

		//  This associates the command shoot with some action.  In this 
		// case, the action triggers a shoot command invoked on my GameMap.  In general, whatever 
		// goes in the actionPerformed method will be executed when a shoot command
		// is sent...
			
		panel.getActionMap().put("reset", new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				for(int r =0; r<gr.getNumRows();r++){
					for(int c =0; c<gr.getNumCols();c++){
						gr.remove(new Location(r,c));
					}
				}
				highscores.add(score);
				//updateMax(highscores);
				score=0;
				frame.repaint();
			}
		});
		panel.getActionMap().put("slideLeft",new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				world.keyPressed("LEFT", null);
			}

		});
		panel.getActionMap().put("slideRight",new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				world.keyPressed("RIGHT", null);
			}

		});
		panel.getActionMap().put("slideUp",new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				world.keyPressed("UP", null);
			}

		});
		panel.getActionMap().put("slideDown",new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				world.keyPressed("DOWN", null);
			}

		});
		
		panel.requestFocusInWindow();		
	}
	private void openImages(){
		for(int i =0; i< 13; i++){
			String s = "src/"+(types[i].getClass().getName());
			s+=".gif";
			try {
				//System.out.println(s);
				Image img = ImageIO.read(new File(s));
				tileImages.add(img);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("bad file thing");
			}
			
			
		}
	}
	private void drawGame(Graphics g){
		for(int r=0; r<gr.getNumRows();r++){
			for(int c=0; c<gr.getNumCols();c++){
				if(gr.get(new Location(r,c)) instanceof Tile){
					String s = (gr.get(new Location(r,c)).getClass().getName());
					s=s.substring(4);
					int x = Integer.parseInt(s);
					x = (int) (Math.log(x)/(Math.log(2)))-1;
					
					int size = 80;
					g.drawImage(tileImages.get(x), c*size, r*size, size, size, null);
					g.drawString("Score: "+score, 400, 100);
					g.drawString("High Score: "+high, 400, 200);
				}
			}
		}
	}
	
	private void setUpGameBoard() {
		world.setGrid(new BoundedGrid(this.NUM_ROWS,this.NUM_COLS));

		add2();
		add2or4();


	}

	private void add2() {
		int rCol = (int) (Math.random()*NUM_COLS),
				rRow = (int) (Math.random()*NUM_ROWS);
		Grid g = world.getGrid();
		g.put(new Location(rRow, rCol), this.makeTile(2));
	}

	private void add2or4() {
		Grid g = world.getGrid();
		ArrayList<Location > emptyLocs = new ArrayList();
		for(int r = 0; r<this.NUM_ROWS; r++) {
			for(int c = 0; c<this.NUM_COLS; c++) {
				Location loc = new Location(r,c);
				if(g.get(loc) == null) {
					emptyLocs.add(loc);
				}
			}
		}
		if(emptyLocs.size()<=0) {
			gameOver();
			return;
		}

		// list wasn't empty
		int index = (int) (Math.random()*emptyLocs.size());
		Location loc = emptyLocs.get(index);
		if(Math.random()>=.5)
			g.put(loc, makeTile(2));
		else
			g.put(loc, makeTile(4));
	}

	private void gameOver() {
		// TODO Auto-generated method stub

	}

}
