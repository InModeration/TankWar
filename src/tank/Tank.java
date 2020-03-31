package tank;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;

import javax.swing.ImageIcon;

public class Tank {
	private boolean bL = false, bU = false, bD = false, bR = false;
	int x, y;
	int oldX, oldY;
	static boolean end = false;

	public static int score = 0;
	public static int realScore = 0;
	public static int XSPEED = 5, YSPEED = 5;     //敌方坦克的速度
	public static int SPEED = 5;                  //我方坦克的速度
	private int life = 100;                       //自身坦克的血量为100
	private int repaireTimes = 3;                   //自身维修的次数
	protected int minesNum = 0;                     //坦克携带的地雷数
	
	private Bloodbar bb = new Bloodbar();         //bb为血条

	/**
	 * 
	 * @return 生命值
	 */
	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	private static Random r = new Random();

	private boolean live = true;                            

	public boolean isLive() {                     
		return live;
	}
	
	/**
	 * 
	 * @param live 444
	 */

	public void setLive(boolean live) {
		this.live = live;
	}

	public static final int WIDTH = 35;
	public static final int HEIGHT = 35;

	TankGame tc;      

	private boolean good;                   //区分自身坦克与敌方坦克

	public boolean isGood() {
		return good;
	}

	private boolean visible = true;                
	
	public boolean isVisible() {
		return visible;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public enum Direction {          //设置上，下，左，右，静止。五个状态
		L, D, U, R, STOP
	}

	private Direction dir = Direction.STOP;          
	private Direction ptDir = Direction.U;          

	private int step = r.nextInt(10) + 4;
	static int win = 0;

	public void move() {
		this.oldX = x;
		this.oldY = y;
		if(!good) {
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
				break;

			case STOP:
				break;
			}
		}
		else {
			switch (dir) {
			case L:
				x = x - SPEED;
				break;

			case U:
				y = y - SPEED;
				break;

			case R:
				x = x + SPEED;
				break;

			case D:
				y = y + SPEED;
				break;

			case STOP:
				break;
			}
		}
		if (this.dir != Direction.STOP)        
			this.ptDir = this.dir;
		if (x < 11) {
			x = 11;
		}
		if (y < HEIGHT + 15) {
			y = HEIGHT + 15;
		}
		if (x + Tank.WIDTH > TankGame.GAME_WIDTH - 11) {
			x = TankGame.GAME_WIDTH - Tank.WIDTH - 11;
		}
		if (y + Tank.HEIGHT > TankGame.GAME_HEIGHT - 11) {
			y = TankGame.GAME_HEIGHT - Tank.HEIGHT - 11;
		}
                                                           //保证坦克在游戏边界之内
		if (!good) {                                       //设置AI坦克
			Direction[] dirs = Direction.values();

			if (step == 0) {
				step = r.nextInt(10) + 4;
				int rn = r.nextInt(dirs.length);
				dir = dirs[rn];
                                                            //随机移动
			}
			step--;   
			if(Tank.realScore < 1000) {
				if (r.nextInt(255) < 10) {                 //随机发射炮弹（可以通过修改数值更改炮弹发射频率，以此更改游戏难度）
					this.fire();
				}
			}
			if(Tank.realScore >= 1000 && Tank.realScore < 2000)
				if(r.nextInt(255) < 20)
					this.fire();
			if(Tank.realScore >= 2000 && Tank.realScore < 4000)
				if(r.nextInt(255) < 30)
					this.fire();
			if(Tank.realScore >= 4000 && Tank.realScore < 6000)
				if(r.nextInt(255) < 40)
					this.fire();
			if(Tank.realScore >= 6000 && Tank.realScore < 8000)
				if(r.nextInt(255) < 60)
					this.fire();
			if(Tank.realScore >= 8000)
				if(r.nextInt(255) < 100)
					this.fire();
		}                    

	}

	public Tank(int x, int y, boolean good) {                               
		this.x = x;
		this.y = y;
		this.good = good;
	}

	public Tank(int x, int y, boolean good, Direction dir, TankGame tc) {  
		this(x, y, good);
		this.dir = dir;
		this.tc = tc;
		this.oldX = x;
		this.oldY = y;
	}

	public void stay() {
		this.x = oldX;
		this.y = oldY;
	}

	public boolean eat(Blood b) {              //当坦克模型到血包位置，则拾取血包，并将血量补充满
		if (this.live && b.isLive() && this.getRect().intersects(b.getRect())) {
			this.life = 100;
			b.setLive(false);
			b.changeXY();
			return true;
		}
		return false;
	}
	
	public boolean pickMines(Mines m) {
		if(this.live && m.isLive() && this.getRect().intersects(m.getPickRect()) && this.minesNum < 3) {
			this.minesNum += 1;
			m.setLive(false);
			m.changeXY();
			return true;
		}
		return false;
	}
	
	public Mines useMines() {
		if (!live || this.getMinesNum() == 0) {
			return null;
		}                  

		int x = this.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2 - Mines.useWIDTH / 2;
		int y = this.y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2 - Mines.useHEIGHT / 2;   
		Mines m = new Mines(x, y);
		m.setLive(live);
		tc.mines.add(m);
		this.minesNum -= 1;

		return m;
	}
	
	public int getMinesNum() {
		return this.minesNum;
	}
	
	private void drawTrackUp(Graphics g){
		if(good) {
			Image img = new ImageIcon("Icon/坦克上.png").getImage();
			g.drawImage(img, x, y, WIDTH, HEIGHT, null);
		}
		else {
			Image img = new ImageIcon("Icon/ai坦克上.png").getImage();
			g.drawImage(img, x, y, WIDTH, HEIGHT, null);
		}
	}
	
	private void drawTrackDown(Graphics g){
		if(good) {
			Image img = new ImageIcon("Icon/坦克下.png").getImage();
			g.drawImage(img, x, y, WIDTH, HEIGHT, null);
		}
		else {
			Image img = new ImageIcon("Icon/ai坦克下.png").getImage();
			g.drawImage(img, x, y, WIDTH, HEIGHT, null);
		}
	}
	
	private void drawTrackLeft(Graphics g){
		if(good) {
			Image img = new ImageIcon("Icon/坦克左.png").getImage();
			g.drawImage(img, x, y, WIDTH, HEIGHT, null);
		}
		else {
			Image img = new ImageIcon("Icon/ai坦克左.png").getImage();
			g.drawImage(img, x, y, WIDTH, HEIGHT, null);
		}
	}
	
	private void drawTrackRight(Graphics g){
		if(good) {
			Image img = new ImageIcon("Icon/坦克右.png").getImage();
			g.drawImage(img, x, y, WIDTH, HEIGHT, null);
		}
		else {
			Image img = new ImageIcon("Icon/ai坦克右.png").getImage();
			g.drawImage(img, x, y, WIDTH, HEIGHT, null);
		}
	}
	public void draw(Graphics g) {
		if (!live) {
			if (!good) {
				tc.tanks.remove(this);
			}
			return;
		}
		if(this.isVisible()) {
			if (good) {                          //如果为自身坦克，画出血条
				bb.draw(g);
			}
			Color c = g.getColor();
			if (good) {
				g.setColor(Color.RED);
			} else {
				g.setColor(Color.blue);
			}                                    
			g.setColor(Color.white);             
			switch (ptDir) {
				case L:
				{
					drawTrackLeft(g);
				break;
				}

				case U:
				{
					drawTrackUp(g);
					break;
				}

				case R:
				{
					drawTrackRight(g);
					break;
				}

				case D:
				{	drawTrackDown(g);
				break;
				}
				default:
				break;
			}                                  //画出上下左右四个方向的坦克
			g.setColor(c);
		}
		move();
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_RIGHT:
			bR = true;
			break;
		case KeyEvent.VK_LEFT:
			bL = true;
			break;
		case KeyEvent.VK_UP:
			bU = true;
			break;
		case KeyEvent.VK_DOWN:
			bD = true;
			break;                           //读取方向键，坦克移动
		}	
		locateDirection();
	}

	public Rectangle getRect() {
		return new Rectangle(x, y, WIDTH+5, HEIGHT+5);

	}

	public void locateDirection() {
		if (bL && !bU && !bR && !bD)
			dir = Direction.L;

		else if (!bL && bU && !bR && !bD)
			dir = Direction.U;

		else if (!bL && !bU && !bR && bD)
			dir = Direction.D;

		else if (!bL && !bU && bR && !bD)
			dir = Direction.R;
		else
			dir = Direction.STOP;
                                          //坦克每次只能向一个方向移动
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_RIGHT:
			bR = false;
			break;
		case KeyEvent.VK_LEFT:
			bL = false;
			break;
		case KeyEvent.VK_UP:
			bU = false;
			break;
		case KeyEvent.VK_DOWN:
			bD = false;
			break;
		case KeyEvent.VK_SPACE:
			fire();
			break;                      //释放方向键时，坦克停止移动
		case KeyEvent.VK_CONTROL:
			superFire();
			break;
		case KeyEvent.VK_A:
			useMines();
			break;
		case KeyEvent.VK_S:
			getRepaired();
			break;
		}	
		locateDirection();

	}

	public boolean collidesWithTanks(java.util.ArrayList<Tank> tanks) {  
		for (int i = 0; i < tanks.size(); i++) {
			Tank t = tanks.get(i);
			if (this != t) {
				if (this.live && t.isLive()
						&& this.getRect().intersects(t.getRect())) {       //两个坦克所在矩形框是否交叉
					this.stay();
					t.stay();
					return true;
				}
			}
		}                                  //当坦克碰撞时，双方不再移动，等待下一步随机移动或读键移动
		return false;
	}

	public Bullet fire() {
		if (!live) {
			return null;
		}                        //如果坦克已经死亡，则不能发射炮弹
		

		int x = this.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
		int y = this.y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;   //从炮管位置发射子弹
		Bullet m = new Bullet(x, y, good, ptDir, tc);
		tc.bullets.add(m);

		return m;
}

	public Bullet fire(Direction dir) {                     //
		if (!live) {
			return null;
		}

		int x = this.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
		int y = this.y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
		Bullet m = new Bullet(x, y, good, dir, tc);
		tc.bullets.add(m);

		return m;
	}

	private class Bloodbar {                // 坦克的血条类
		public void draw(Graphics g) {
			Color c = g.getColor();
			g.setColor(Color.red);
			g.drawRect(x, y - 15, WIDTH, 5);
			int w = WIDTH * life / 100;
			g.fillRect(x, y - 15, w, 5);
			g.setColor(c);
		}
	}
	
	private void superFire() {  
		if(Tank.score >= 100) {
			Tank.score -= 100;                        
			Direction[] dirs = Direction.values();
			for (int i = 0; i < dirs.length - 1; i++) {
				fire(dirs[i]);
			}
		}
	}
	
	public void getRepaired() {
		if(Tank.score >= 400 && this.life < 100 && repaireTimes > 0) {
			this.life = 100;
			Tank.score -= 400; 
			repaireTimes --;
		}
	}
	
	public void faster() {
		if(Tank.SPEED < 7)
			SPEED += 2;
	}
	
	public void slower() {
		if(Tank.SPEED > 5)
			SPEED -= 2;
	}
}
