import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;


public class Tank extends GameObject{
private int x;
private int y;				//BOT LEFT CORNER
private Color c;
private int[] xPoints;		//COUNTER-CLOCKWISE POLYGONS
private int[] yPoints;
private int w;
private int h;
private Terrain t;
private double angle =0; //DEGREES
	public Tank(int x, int y, Color c, int w, int h, Terrain t){
		this.w=w;
		this.h=h; 
		this.x=x;
		this.y=y;
		this.c=c;
		this.t=t;
//		xPoints= new int[]{x,x,x+w,x+w};
//		yPoints = new int[]{y,y+h,y+h,y};
		moveRect(this.x, this.y);
		
	}
	
	private void moveRect(int x, int y) {
		// TODO Auto-generated method stub
		
//		int tHeight = t.getColHeights().get(x+getSide(w,h));
//		xPoints= new int[]{x,x,x+getSide(w,h),x+getSide(w,h)};
//		yPoints=new int[]{y,y+h,tHeight, tHeight-h};
		int brx = findBotRightX(x);
		int bry = t.getColHeights().get(brx);
		double theta1 = Math.asin((double)(bry-y)/(findDist(x,brx)));
		//System.out.println(bry-y);
		//System.out.println(findDist(x,brx));
	//	System.out.println(theta1);
		//System.out.println((double)(brx-x)/w);
		double theta2 = Math.PI/2-Math.abs(theta1);
		//System.out.println(x+" "+brx);
//		int tlx = (int) (x-(Math.cos(theta2)*h));
//		tlx = x-tlx;
//		int tly = (int) (x-(Math.sin(theta2)*h));
//		tly = y-tly;
		//System.out.println(tlx);
		
		double slope = findSlope(x,brx);
		double slope2= -1/slope;
		
		int tlx = (int) (((double)(h))/slope2);
		tlx = (int)(0.65*tlx);
		tlx = x-tlx;
		int tly = y-h;
		int trx= (brx-x)+tlx;
		
		int trY = bry-(y-tly);
		xPoints = new int[]{tlx,x,brx,trx};
		//System.out.println(trx);
		yPoints = new int[]{tly,y,bry,trY};
		
	}
	private double findSlope(int x2, int brx) {
		// TODO Auto-generated method stub
		return (t.getColHeights().get(brx)-t.getColHeights().get(x2))/((double)(brx-x));
	}

	private int findBotRightX(int x1) {
		// TODO Auto-generated method stub
		double dOff=Integer.MAX_VALUE;
		int botR=0;
		for(int i =0; i<w+3; i++){
			double a = Math.abs(w-findDist(x1, x1+i));
			//System.out.println(a);
			if(a<dOff){
				dOff = a;
				botR=x1+i;
			}
		}//System.out.println(dOff);
		return botR;
	}
	private double findDist(int x1, int x2){
		int y1 = t.getColHeights().get(x1);
		int y2 = t.getColHeights().get(x2);
		return Math.sqrt(Math.abs(((x2-x1)*(x2-x1))-((y2-y1)*(y2-y1))));
	}
	public int getSide(int hyp, int height){
		return (int)(Math.sqrt(hyp*hyp-height*height));
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public int  getW(){
		return w;
	}
	public int getH(){
		return h;
	}
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(c);
		
		moveRect(x,y);
		//System.out.println(x+" "+y);
		
		g.fillPolygon(xPoints, yPoints, 4);
		int[] mid = findMidpoint(xPoints[0],yPoints[0],xPoints[3],yPoints[3]);
		int[] canPt = findCannonPoint(mid);
		g.setColor(Color.black);
		g.drawLine(mid[0], mid[1],canPt[0], canPt[1]);
		//g.drawLine(xPoints[1], yPoints[1], xPoints[2], yPoints[2]);
		//g.drawImage(img, x, y, null);
	}

	public int getX() {
		// TODO Auto-generated method stub
		return x;
	}
	private int[] findMidpoint(int x1, int y1, int x2, int y2){
		return new int[]{(x1+x2)/2, (y1+y2)/2};
	}
	private int[] findCannonPoint(int[] arr){
		//r^2 = (x-x1)^2 +(y-y1)^2
		//sin(angle) = changeInH/r
		//cos(angle) = changeInB/r
		double r = w/2;
		int changeInY = (int) (r*Math.sin(-angle));
		//System.out.println(changeInY);
		int changeInX = (int) (r*Math.cos(-angle));
		return new int[]{arr[0]+changeInX, arr[1]+changeInY};
	}
	public double getAngle(){
		return angle;
	}
	public void setAngle(double angle){
		this.angle = angle;
	}
	public int[] getXPoints(){
		return xPoints;
	}
	public int getY(){
		return y;
	}
	public void setXandY(int i) {
		// TODO Auto-generated method stub
		x=i;
		y = t.getColHeights().get(x);
	}

	

}
