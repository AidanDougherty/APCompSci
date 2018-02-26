import java.awt.Graphics;


public class Projectile extends GameObject{
	private int power;
	private double angle;
	private int x;
	private int y;
	private int initialH;
	private double t;
	private double xvi;
	private double yvi;
	private int initialX;
	
	public Projectile(int x, int y, double angle, int power){
		this.x = x-15;
		initialX = this.x;
		this.y = y-20;
		initialH = this.y;
		this.angle = angle;
		this.power = power;
		yvi = power*Math.sin(angle);
		xvi = power*Math.cos(angle);
		
	}
		
	private int calcY() {
		// TODO Auto-generated method stub
		int a;
		//GRAV*a^2 + power*a + initialH
		//GRAV*a + power = tan(angle)
		//a = (int) ((Math.tan(angle) - power)/GRAV);
		return (int) (800-1*(GRAV*t*t + yvi*t + initialH));
	}
	private double calcAngle(){
		return Math.atan(GRAV*t+yvi);
	}
	private int calcX(){
		return (int) (initialX+xvi*t) /*x+1*/;
	}
	public void move(double dist){
		t+=dist;
		x = calcX();
		//x+=dist;
		//System.out.println(x);
		y = calcY();
		//angle = calcAngle();
		//angle = Math.PI/2-Math.abs(angle);
		//System.out.println(angle);
		
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.drawOval(x, y, 10, 10);
		//System.out.println(x+" "+y);
	}

}
