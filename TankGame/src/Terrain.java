import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Terrain implements Drawable {
	private int rectWidth;
	private ArrayList<Integer> colHeights = new ArrayList<Integer>();
	private double s1,s2,s3,s4,s5;

	public Terrain(int width, int maxHeight, int minHeight, int variability, int rectWidth) {
		this.rectWidth=rectWidth;
		
		s1=0.86;
		s2=1.2;
		s3=0.86;
		s4=0.67;
		s5=0.9;
		genHeights(width, maxHeight, minHeight, variability);
	}

	private void genHeights(int width, int maxHeight, int minHeight, int varb) {
		// TODO Auto-generated method stub
		int avg = (maxHeight + minHeight)/3;
		int avg2 = 2*(maxHeight+minHeight)/3;
		int h1 = (int) (Math.random()*(avg2-avg)+avg);
		//System.out.println(h1);
		colHeights.add(h1);
		for(int i =1; i<width; i++){
			int h;
			if(i<width/5){
				h = (int) ((Math.random()*2*varb)+colHeights.get(i-1)-s1*varb);
			}else if(i>width/5 && i<2*width/5){
				h = (int) ((Math.random()*2*varb)+colHeights.get(i-1)-s2*varb);
			}else if(i>(2*width/5)&&i<3*width/5){
				h = (int) ((Math.random()*2*varb)+colHeights.get(i-1)-s3*varb);
			}else if(i>3*width/5&&i<4*width/5){
				h = (int) ((Math.random()*2*varb)+colHeights.get(i-1)-s4*varb);
			}else{
				h = (int) ((Math.random()*2*varb)+colHeights.get(i-1)-s5*varb);
			}
			
			//System.out.println(i);
			//System.out.println(h);
			
			//h+=i/10;
			
			if(h<maxHeight&&h>minHeight){
				colHeights.add(h);
				//System.out.println(h);
			}else{
				i--;
			}
		}
	}
	public ArrayList<Integer> getColHeights(){
		return colHeights;
	}
	@Override
	public void draw(Graphics g) {
		g.setColor(new Color(20, 190, 58));
		//System.out.println("halp");
		//g.fillRect(0, 400, 100, 400);
		for(int i=0; i< colHeights.size();i++){
			g.fillRect(i,colHeights.get(i),rectWidth,800-colHeights.get(i));
		}
		g.setColor(Color.black);
//		g.drawLine(160, 0, 160, 800);
//		g.drawLine(320, 0, 320, 800);
//		g.drawLine(480, 0, 480, 800);
//		g.drawLine(640, 0, 640, 800);
		
	}

}
