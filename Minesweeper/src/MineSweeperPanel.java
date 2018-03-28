import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
//import javafx.scene.layout.GridPane;

public class MineSweeperPanel extends JPanel {

	private final int SIZE_PANEL = 603;
	private final int SIDE = 9;
	private GridLayout gl = new GridLayout(SIDE, SIDE);
	private Board board = new Board(SIDE, SIDE,SIZE_PANEL);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MineSweeperPanel().start();
		
		
	}
	public void start(){
		setUpFrame();
		setUpGrid();
		
	}
	public int getPanelSize(){
		return SIZE_PANEL;
	}
	private void setUpGrid() {
		// TODO Auto-generated method stub
		
		
		
		
	}
	private void setUpFrame(){
		JFrame frame = new JFrame("Minesweeper!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new MineSweeperPanel(frame));
		frame.pack();
		frame.setVisible(true);
	}
	public MineSweeperPanel(JFrame frame) {
		this.setPreferredSize(new Dimension(this.SIZE_PANEL,SIZE_PANEL));
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("You just clicked: "+arg0);
				frame.repaint();
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				//System.out.println("You just entered!! "+arg0);
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				//System.out.println("You just exited!! "+arg0);
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// write your clicking code here!!
				//System.out.println("You just pressed: "+arg0);
				
				
				
				
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				//System.out.println("You just released: "+arg0);
			}
			
		});
	}
	
	public MineSweeperPanel() {
		// TODO Auto-generated constructor stub
	}
	public void paintComponent(Graphics g) {
		//g.drawString("yeet", 40, 200);
		
	}
	
	
	
	

}
