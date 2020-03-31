package tank;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Rivers extends Obstacles{
	private final int ww = 50, hh = 50;
	int x2, y2;
	TankGame tc;
	
	public Rivers(int x, int y, int w, int h, TankGame tc) {
		super(x, y, w, h, tc);
		x2 = x;
		y2 = y;
	}
	
	public void draw(Graphics g) {
		Image img = new ImageIcon("Icon/ºÓÁ÷.png").getImage();
		for(int i = 1; i * ww <= w; i++) {
			for(int j = 1; j * hh <= h; j++) {
				g.drawImage(img, x2, y2, ww, hh, null);
				y2 += hh;
			}
			x2 += ww;
			y2 = y;
		}	
	}
	
	public void getInTheRivers(Tank t) {                              
		if (t.isLive() && this.getRect().intersects(t.getRect())){       
			if(t.isGood()) {
				Tank.SPEED = 2;
				}
			}
			
		
	}
}
