package tank;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;

public class Blood {                        
	int x, y, w, h;
	TankGame tc;
	
	private boolean live = false;
	private Image image;
	Random r = new Random();

	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

	public Blood() {           
		x = r.nextInt(200) + 400;
		y = r.nextInt(400) + 200;  							
		w = h = 20;                     //血包的大小
	}

	public void draw(Graphics g) {
		if (!live) {
			return;
		}
		image = new ImageIcon("Icon/血包.png").getImage();
		g.drawImage(image, x, y, w, h, null);
	}
	
	public void changeXY()
	{
		this.x = r.nextInt(300)+700;
		this.y = r.nextInt(400);
	}
	
	public Rectangle getRect() {
		return new Rectangle(x, y, w, h);
	}

}
