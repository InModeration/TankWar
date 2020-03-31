package tank;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.List;

import javax.swing.ImageIcon;

public class Walls extends Obstacles  {
	private final int ww = 50, hh = 50;
	int x2, y2;
	TankGame tc;
	
	public Walls(int x, int y, int w, int h, TankGame tc) {
		super(x, y, w, h, tc);
		x2 = x;
		y2 = y;
	}

	public void draw(Graphics g) {
		Image img = new ImageIcon("Icon/Ç½±Ú.png").getImage();
		for(int i = 1; i * ww <= w; i++) {
			for(int j = 1; j * hh <= h; j++) {
				g.drawImage(img, x2, y2, ww, hh, null);
				y2 += hh;
			}
			x2 += ww;
			y2 = y;
		}	
	}
	
	public boolean collidesWithWalls(Tank t) {
		if (t.isLive() && this.getRect().intersects(t.getRect())){       
			t.stay();
			return true;
		}                            
		return false;
	}
}
