import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.*;




public class GridBasedGameDriver {

	private static final int WIDTH = 1000;
	private static final int HEIGHT = 800;
	private JFrame frame = new JFrame("Tank Game.  Destroy your enemy");
	private JPanel panel;
	private List<Drawable> drawables= new ArrayList();
	private Terrain terrain;
	private Tank tank1;
	private Projectile p;
	private Timer ticker = new Timer(5, null);
	public static void main(String[] args) {
		new GridBasedGameDriver().start();

	}

	private void start() {
		setUpGame();

		this.frame.setBackground(new Color(127, 127, 127));
		frame.setVisible(true);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				drawGame(g);
			}

		};
		panel.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		frame.add(panel);
		frame.pack();

		setUpKeyMappings();


		panel.requestFocusInWindow();
		//panel.addKeyListener(l);

		setUpObjects();

		frame.repaint();
	}
	private void startTimer() {
		ticker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(p!=null)
				p.move(0.1);//what do you want to do every time the Timer goes off?
				//System.out.println("tick");
				frame.repaint();
			}

		});
		ticker.start();
	}

	private void setUpObjects() {
		//System.out.println(panel.getWidth());
		terrain = new Terrain(WIDTH, HEIGHT,150,4,2 );
		drawables.add(terrain);
		tank1 = new Tank(200,terrain.getColHeights().get(200)-20,Color.BLUE, 40,15, terrain);
		drawables.add(tank1);
		startTimer();

	}
	private void setUpKeyMappings(){
		panel.getInputMap().put(KeyStroke.getKeyStroke("LEFT"),"slideLeft");
		panel.getActionMap().put("slideLeft",new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(tank1.getX()-4>0){
					tank1.setXandY(tank1.getX()-5);
					frame.repaint();
				}


			}
		});
		panel.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"),"slideRight");
		panel.getActionMap().put("slideRight",new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(tank1.getXPoints()[2]+8<WIDTH){
					//System.out.println(tank1.getX()+5);
					tank1.setXandY(tank1.getX()+5);
					frame.repaint();
				}
			}
		});
		panel.getInputMap().put(KeyStroke.getKeyStroke("UP"),"raiseCannon");
		panel.getActionMap().put("raiseCannon",new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent arg0) {

				tank1.setAngle(tank1.getAngle()+(2*Math.PI/30));
				System.out.println(tank1.getAngle());
				frame.repaint();

			}
		});
		panel.getInputMap().put(KeyStroke.getKeyStroke("DOWN"),"lowerCannon");
		panel.getActionMap().put("lowerCannon",new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				tank1.setAngle(tank1.getAngle()-(2*Math.PI/30));
				System.out.println(tank1.getAngle());
				frame.repaint();

			}
		});
		panel.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),"shoot");
		panel.getActionMap().put("shoot",new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				p = new Projectile(tank1.getX(),tank1.getY(), tank1.getAngle(), 70);
				drawables.add(p);
				frame.repaint();
			}



		});



	}
	public void drawGame(Graphics g) {
		for(Drawable dr:drawables) {
			dr.draw(g);
		}
	}
	private void setUpGame() {


	}

	private void gameOver() {
		// TODO Auto-generated method stub

	}

}
