package tank;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;

public class Mines {
	private final int pickWIDTH = 25, pickHEIGHT = 20;
	public final static int useWIDTH = 35;
	public final static int useHEIGHT = 35;
	private TankGame tc;
	int x, y;
	private boolean live = false;
	Random r = new Random();
	
	public Mines() {
		this.x = r.nextInt(200)+700;
		this.y = r.nextInt(400);
	}
	
	public Mines(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public boolean isLive() {
		return live;
	}
	
	public void setLive(boolean live) {
		this.live = live;
	}
	
	public void drawPickMines(Graphics g) {
		Image img = new ImageIcon("Icon/地雷.png").getImage();
		g.drawImage(img, x, y, pickWIDTH, pickHEIGHT, null);
	}
	
	public void drawUseMines(Graphics g) {
		Image img = new ImageIcon("Icon/地雷2.png").getImage();
		g.drawImage(img, x, y, useWIDTH, useHEIGHT, null);
	}
	
	public Rectangle getPickRect() {
		return new 	Rectangle(x, y, pickWIDTH, pickHEIGHT);
	}
	
	public Rectangle getUseRect() {
		return new 	Rectangle(x, y, useWIDTH, useHEIGHT);
	}
	
	public boolean hitTank(Tank t) {
		if (t.isLive() && (!t.isGood()) && this.getUseRect().intersects(t.getRect())) {
			t.setLive(false);
			this.setLive(false);
			Tank.realScore += 200;
			Tank.score += 200;       //如果敌方坦克死于地雷，则每炸毁一辆敌方坦克增加双倍分数
			return true;
		}
		return false;
	}
	
	public void changeXY() {
		this.x = r.nextInt(200) + 400;
		this.y = r.nextInt(400) + 200; 
	}
}
