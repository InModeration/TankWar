package tank;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Shelter extends Obstacles{
	TankGame tc;
	
	public Shelter(int x, int y, int w, int h, TankGame tc) {
		super(x, y, w, h, tc);
	}
	
	public void draw(Graphics g) {
		Image img = new ImageIcon("Icon/’ ≈Ò.png").getImage();
		g.drawImage(img, x, y, w, h, null);
	}
	
	public void getInTheShelter(Tank t) {
		if (t.isLive()) {
			if(this.getRect().intersects(t.getRect()))
				t.setVisible(false);
			else
				t.setVisible(true);
		}
	}
}
