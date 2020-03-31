package tank;
import java.awt.*;
import java.util.List;

import javax.swing.ImageIcon;

public class Bullet {
	int x, y;
	Tank.Direction dir;
	public static final int XSPEED = 15, YSPEED = 15;             //子弹的速度
	public static final int WIDTH = 20, HEIGHT = 20;              //自身子弹的大小
	public static final int aiWIDTH = 10, aiHEIGHT = 10;
	private boolean good;
	private boolean live = true;
	private boolean visible = true;
	
	public boolean isVisible() {
		return visible;
	}
	
	public void setVisible(boolean visible) {              //自身坦克的visible为true时才可被子弹击中
		this.visible = visible;
	}
	
	public boolean hitTanks(List<Tank> tanks) {
		for (int i = 0; i < tanks.size(); i++) {
			if (hitTank(tanks.get(i))) {
				Tank.realScore += 100;
				Tank.score += 100;                               //如果敌方坦克死于基本的炮弹，则增加100分，死于地雷增加分数有所提升
				return true;
			}
		}
		return false;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

	private TankGame tc;

	public boolean isLive() {
		return live;
	}

	public Bullet(int x, int y, Tank.Direction dir) { 
		this.x = x;
		this.y = y;
		this.dir = dir;
	}                  

	public Bullet(int x, int y, boolean good, Tank.Direction dir, TankGame tc) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.tc = tc;
		this.good = good;
	}
                  //子弹从坦克当前位置发射，方向沿着当前方向
	public Rectangle getRect() {
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}

	public boolean hitTank(Tank t) {
		if(t.isVisible()) {
			if (this.getRect().intersects(t.getRect()) && t.isLive()
					&& this.good != t.isGood()) {
				if (t.isGood()) {
					t.setLife(t.getLife() - 20);                              //每次被敌方炮弹击中，血量减少20
					if (t.getLife() <= 0) {
						t.setLive(false);
					}
				} else {
					t.setLive(false);
				}
				this.setLive(false);
				return true;
			}
			return false;
		}
		return false;
	}
	
	public void hitWalls(Walls o) {
			if(o.getRect().intersects(this.getRect()))
				this.setLive(false);
	}
	
	public void draw(Graphics g) {      //画子弹
		if (!this.isLive()) {
			Image img1 = new ImageIcon("Icon/爆炸1.png").getImage();        //绘出爆炸效果，连续drawImage以得到动态爆炸效果
			g.drawImage(img1, x, y, WIDTH, HEIGHT, null);
			Image img2 = new ImageIcon("Icon/爆炸2.png").getImage();
			g.drawImage(img2, x, y, WIDTH, HEIGHT, null);
			Image img3 = new ImageIcon("Icon/爆炸3.png").getImage();
			g.drawImage(img3, x, y, WIDTH, HEIGHT, null);
			tc.bullets.remove(this);
			return;
		}
		if(this.isLive()) {
			
		Color c = g.getColor();
		if(good) {                                                              //自身坦克的子弹
			switch(dir) {
			case L:
				Image img1 = new ImageIcon("Icon/子弹左.png").getImage();
				g.drawImage(img1, x, y, WIDTH, HEIGHT, null);
				break;
				
			case R:
				Image img2 = new ImageIcon("Icon/子弹右.png").getImage();
				g.drawImage(img2, x, y, WIDTH, HEIGHT, null);
				break;
				
			case U:
				Image img3 = new ImageIcon("Icon/子弹上.png").getImage();
				g.drawImage(img3, x, y, WIDTH, HEIGHT, null);
				break;
				
			case D:
				Image img4 = new ImageIcon("Icon/子弹下.png").getImage();
				g.drawImage(img4, x, y, WIDTH, HEIGHT, null);
				break;
			}
		}
		else {                                                                    //敌方坦克的子弹
			g.setColor(Color.blue);	
			g.fillOval(x, y, aiWIDTH, aiHEIGHT);
			g.setColor(c);
		}
		}
		move();
	}

	private void move() {
		switch (dir) {
		case L:
			x = x - XSPEED;
			break;

		case U:
			y = y - YSPEED;
			break;

		case R:
			x = x + XSPEED;
			break;
			
		case D:
			y = y + YSPEED;
			break;                //实现子弹的移动
		}
		if (x < 15 || y < 30 || (x > (TankGame.GAME_WIDTH - 30))
				|| y > TankGame.GAME_HEIGHT -30) {
			this.setLive(false);                 //子弹到达边界即消失
		}

	}
}
