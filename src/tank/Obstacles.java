package tank;

import java.awt.Rectangle;

public class Obstacles implements GetRect{
	int x, y, w, h, x2, y2;
	private final int ww = 50, hh = 50;
	TankGame tc;
	
	public Obstacles(int x, int y, int w, int h, TankGame tc) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.tc = tc;
	}
	
	public Rectangle getRect() {
		return new Rectangle(x, y, w, h);
	}
}
