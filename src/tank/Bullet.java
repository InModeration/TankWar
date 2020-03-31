package tank;
import java.awt.*;
import java.util.List;

import javax.swing.ImageIcon;

public class Bullet {
	int x, y;
	Tank.Direction dir;
	public static final int XSPEED = 15, YSPEED = 15;             //�ӵ����ٶ�
	public static final int WIDTH = 20, HEIGHT = 20;              //�����ӵ��Ĵ�С
	public static final int aiWIDTH = 10, aiHEIGHT = 10;
	private boolean good;
	private boolean live = true;
	private boolean visible = true;
	
	public boolean isVisible() {
		return visible;
	}
	
	public void setVisible(boolean visible) {              //����̹�˵�visibleΪtrueʱ�ſɱ��ӵ�����
		this.visible = visible;
	}
	
	public boolean hitTanks(List<Tank> tanks) {
		for (int i = 0; i < tanks.size(); i++) {
			if (hitTank(tanks.get(i))) {
				Tank.realScore += 100;
				Tank.score += 100;                               //����з�̹�����ڻ������ڵ���������100�֣����ڵ������ӷ�����������
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
                  //�ӵ���̹�˵�ǰλ�÷��䣬�������ŵ�ǰ����
	public Rectangle getRect() {
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}

	public boolean hitTank(Tank t) {
		if(t.isVisible()) {
			if (this.getRect().intersects(t.getRect()) && t.isLive()
					&& this.good != t.isGood()) {
				if (t.isGood()) {
					t.setLife(t.getLife() - 20);                              //ÿ�α��з��ڵ����У�Ѫ������20
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
	
	public void draw(Graphics g) {      //���ӵ�
		if (!this.isLive()) {
			Image img1 = new ImageIcon("Icon/��ը1.png").getImage();        //�����ըЧ��������drawImage�Եõ���̬��ըЧ��
			g.drawImage(img1, x, y, WIDTH, HEIGHT, null);
			Image img2 = new ImageIcon("Icon/��ը2.png").getImage();
			g.drawImage(img2, x, y, WIDTH, HEIGHT, null);
			Image img3 = new ImageIcon("Icon/��ը3.png").getImage();
			g.drawImage(img3, x, y, WIDTH, HEIGHT, null);
			tc.bullets.remove(this);
			return;
		}
		if(this.isLive()) {
			
		Color c = g.getColor();
		if(good) {                                                              //����̹�˵��ӵ�
			switch(dir) {
			case L:
				Image img1 = new ImageIcon("Icon/�ӵ���.png").getImage();
				g.drawImage(img1, x, y, WIDTH, HEIGHT, null);
				break;
				
			case R:
				Image img2 = new ImageIcon("Icon/�ӵ���.png").getImage();
				g.drawImage(img2, x, y, WIDTH, HEIGHT, null);
				break;
				
			case U:
				Image img3 = new ImageIcon("Icon/�ӵ���.png").getImage();
				g.drawImage(img3, x, y, WIDTH, HEIGHT, null);
				break;
				
			case D:
				Image img4 = new ImageIcon("Icon/�ӵ���.png").getImage();
				g.drawImage(img4, x, y, WIDTH, HEIGHT, null);
				break;
			}
		}
		else {                                                                    //�з�̹�˵��ӵ�
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
			break;                //ʵ���ӵ����ƶ�
		}
		if (x < 15 || y < 30 || (x > (TankGame.GAME_WIDTH - 30))
				|| y > TankGame.GAME_HEIGHT -30) {
			this.setLive(false);                 //�ӵ�����߽缴��ʧ
		}

	}
}
