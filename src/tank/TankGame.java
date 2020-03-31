package tank;
/**
 * ����˵÷�ϵͳ
 */
import java.awt.*;
import javax.swing.JOptionPane;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class TankGame extends Frame {

	public static final int GAME_WIDTH = 1200;
	public static final int GAME_HEIGHT = 700;              //��Ϸ���ڴ�С(���ɸı�)       
	
	private int tanksX, tanksY;
	Random r = new Random();
	boolean i = true;
	
	Tank myTank = new Tank(1100, 600, true, Tank.Direction.STOP, this);
	Blood b = new Blood();
	Mines m = new Mines();
	ArrayList<Mines> mines = new ArrayList<Mines>();
	ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	ArrayList<Tank> tanks = new ArrayList<Tank>();          //����̹�˺��ӵ���������
	Walls []walls = new Walls[8]; 
	Rivers []rivers = new Rivers[3];
	Image offScreenImage = null;// ���屳�������ͼƬ
	Panel panel = new Panel();
	
	public void paint (Graphics g) {

		g.setColor(Color.orange);
		g.drawString("  (��ǰЯ����������" + myTank.minesNum + "/3)", 10, 80); //ʹ�õ��׻�ɱ�ɻ�ö���ķ���
		g.drawString("��ǰ�÷��� ��" + Tank.score, 1000, 80);
		g.setFont(new Font("����",Font.PLAIN,25));
		g.setColor(Color.blue);
		walls[0] = new Walls(100, 100, 100, 400, this);
		walls[1] = new Walls(100, 550, 100, 50, this);
		walls[2] = new Walls(200, 100, 200, 100, this);
		walls[3] = new Walls(500, 100, 100, 100, this);
		walls[4] = new Walls(600, 100, 100, 300, this);
		walls[5] = new Walls(600, 500, 300, 100, this);
		walls[6] = new Walls(1000, 500, 100, 100, this);
		walls[7] = new Walls(1000, 100, 100, 300, this);
		rivers[0] = new Rivers(100, 500, 100, 50, this);
		rivers[1] = new Rivers(600, 400, 100, 100, this);
		rivers[2] = new Rivers(1000, 400, 100, 100, this);
		Grass grass = new Grass(200, 100, 800, 500, this);
		Shelter shelter1 = new Shelter(1100, 600, 100, 100, this);
		
		shelter1.draw(g);
		shelter1.getInTheShelter(myTank);
		
		grass.draw(g);
		grass.getInTheGrass(myTank);
		
		for (Rivers r : rivers) {
			r.draw(g);
			r.getInTheRivers(myTank);
		}
		for (Walls o : walls)
			o.draw(g);
		if (myTank.isLive()==false) {
                                                          //̹����������Ϸ����
			g.drawString("Wasted��", 550, 90);
		}
		
		for (int i = 0; i < bullets.size(); i++) {
			Bullet m = bullets.get(i);
			m.hitTanks(tanks);
			m.hitTank(myTank);
			m.hitWalls(walls[0]);
			m.hitWalls(walls[1]);
			m.hitWalls(walls[2]);
			m.hitWalls(walls[3]);
			m.hitWalls(walls[4]);
			m.hitWalls(walls[5]);
			m.hitWalls(walls[6]);
			m.hitWalls(walls[7]);
			m.draw(g);
		}

		for (int i = 0; i < tanks.size(); i++) {
			Tank t = tanks.get(i);
			t.collidesWithTanks(tanks);
			t.draw(g);
		}
		for (Walls o : walls){
			for(Tank t : tanks)
				o.collidesWithWalls(t);
			o.collidesWithWalls(myTank);
		}

		myTank.collidesWithTanks(tanks);
		myTank.draw(g);
		if (myTank.getLife() < 61) {
			b.setLive(true);                              //����̹��Ѫ���ٵ���60��ʱ�򣬽��ڵ�ͼ������Ѫ��
		}
		if (b.isLive()) {
			myTank.eat(b);
			b.draw(g);
		}
		
		if(myTank.getLife() < 81) {
			m.setLive(true);
		}
		if(m.isLive()) {
			myTank.pickMines(m);
			m.drawPickMines(g);
		}
		
		for(Mines m : mines) {
			if(m.isLive()) {
				m.drawUseMines(g);
			}
			for(Tank t: tanks)
				 m.hitTank(t);
		}
		
		if(tanks.size() <= 5) {
			if(tanks.size() == 0) {
				if(r.nextInt(255) <= 20) {
					do {
						tanksX = r.nextInt(1100) + 50;
						tanksY = r.nextInt(400) + 100;
					}while(walls[0].getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 20, Tank.HEIGHT + 20)) || walls[1].getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 20, Tank.HEIGHT + 20)) || walls[2].getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 20, Tank.HEIGHT + 20)) || walls[3].getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 20, Tank.HEIGHT + 20)) || walls[4].getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 20, Tank.HEIGHT + 20)) || walls[5].getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 20, Tank.HEIGHT + 20)) || walls[6].getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 20, Tank.HEIGHT + 20)) || walls[7].getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 20, Tank.HEIGHT + 20))
							|| myTank.getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 15, Tank.HEIGHT + 15)));
					/*if((!walls[0].getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 20, Tank.HEIGHT + 20))) && (!walls[1].getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 20, Tank.HEIGHT + 20))) && !walls[2].getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 20, Tank.HEIGHT + 20)) && !walls[3].getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 20, Tank.HEIGHT + 20)) && !walls[4].getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 20, Tank.HEIGHT + 20)) && !walls[5].getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 20, Tank.HEIGHT + 20)) && !walls[6].getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 20, Tank.HEIGHT + 20)) && !walls[7].getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 20, Tank.HEIGHT + 20)))
						System.out.println(tanksX + "         " + tanksY);*/
					tanks.add(new Tank(tanksX, tanksY, false, Tank.Direction.D, this));
				}
			}
			if(tanks.size() == 1) {
				if(r.nextInt(255) <= 10) {
					do {
						tanksX = r.nextInt(1100) + 50;
						tanksY = r.nextInt(400) + 100;
					}while(walls[0].getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 20, Tank.HEIGHT + 20)) || walls[1].getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 20, Tank.HEIGHT + 20)) || walls[2].getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 20, Tank.HEIGHT + 20)) || walls[3].getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 20, Tank.HEIGHT + 20)) || walls[4].getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 20, Tank.HEIGHT + 20)) || walls[5].getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 20, Tank.HEIGHT + 20)) || walls[6].getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 20, Tank.HEIGHT + 20)) || walls[7].getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 20, Tank.HEIGHT + 20)) || tanks.get(0).getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 20, Tank.HEIGHT + 20))
							|| myTank.getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 15, Tank.HEIGHT + 15)));
					tanks.add(new Tank(tanksX, tanksY, false, Tank.Direction.D, this));
				}
			}
			if(tanks.size() == 2) {
				if(r.nextInt(255) <= 5) {
					do {
						tanksX = r.nextInt(1100) + 50;
						tanksY = r.nextInt(400) + 100;
					}while(walls[0].getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 20, Tank.HEIGHT + 20)) || walls[1].getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 20, Tank.HEIGHT + 20)) || walls[2].getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 20, Tank.HEIGHT + 20)) || walls[3].getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 20, Tank.HEIGHT + 20)) || walls[4].getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 20, Tank.HEIGHT + 20)) || walls[5].getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 20, Tank.HEIGHT + 20)) || walls[6].getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 20, Tank.HEIGHT + 20)) || walls[7].getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 20, Tank.HEIGHT + 20)) || tanks.get(0).getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 20, Tank.HEIGHT + 20)) || tanks.get(1).getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 20, Tank.HEIGHT + 20))
							|| myTank.getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 15, Tank.HEIGHT + 15)));
					tanks.add(new Tank(tanksX, tanksY, false, Tank.Direction.D, this));
					}
				}
			}
			if(tanks.size() == 3) {
				if(r.nextInt(255) <= 5) {
					do {
						tanksX = r.nextInt(1100) + 50;
						tanksY = r.nextInt(400) + 100;
					}while(walls[0].getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 20, Tank.HEIGHT + 20)) || walls[1].getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 20, Tank.HEIGHT + 20)) || walls[2].getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 20, Tank.HEIGHT + 20)) || walls[3].getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 20, Tank.HEIGHT + 20)) || walls[4].getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 20, Tank.HEIGHT + 20)) || walls[5].getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 20, Tank.HEIGHT + 20)) || walls[6].getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 20, Tank.HEIGHT + 20)) || walls[7].getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 20, Tank.HEIGHT + 20)) || tanks.get(0).getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 20, Tank.HEIGHT + 20)) || tanks.get(1).getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 20, Tank.HEIGHT + 20)) || tanks.get(2).getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 20, Tank.HEIGHT + 20))
							|| myTank.getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 15, Tank.HEIGHT + 15)));
					tanks.add(new Tank(tanksX, tanksY, false, Tank.Direction.D, this));
				}
			}
			if(tanks.size() == 4) {
				if(r.nextInt(255) <= 5) {
					do {
						tanksX = r.nextInt(1100) + 50;
						tanksY = r.nextInt(400) + 100;
					}while(walls[0].getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 20, Tank.HEIGHT + 20)) || walls[1].getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 20, Tank.HEIGHT + 20)) || walls[2].getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 20, Tank.HEIGHT + 20)) || walls[3].getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 20, Tank.HEIGHT + 20)) || walls[4].getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 20, Tank.HEIGHT + 20)) || walls[5].getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 20, Tank.HEIGHT + 20)) || walls[6].getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 20, Tank.HEIGHT + 20)) || walls[7].getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 20, Tank.HEIGHT + 20)) || tanks.get(0).getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 20, Tank.HEIGHT + 20)) || tanks.get(1).getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 20, Tank.HEIGHT + 20)) || tanks.get(2).getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 20, Tank.HEIGHT + 20)) || tanks.get(3).getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 20, Tank.HEIGHT + 20))
							|| myTank.getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 15, Tank.HEIGHT + 15)));
					tanks.add(new Tank(tanksX, tanksY, false, Tank.Direction.D, this));
				}
			}
			if(tanks.size() == 5) {
				if(r.nextInt(255) <= 5) {
					do {
						tanksX = r.nextInt(1100) + 50;
						tanksY = r.nextInt(400) + 100;
					}while(walls[0].getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 20, Tank.HEIGHT + 20)) || walls[1].getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 20, Tank.HEIGHT + 20)) || walls[2].getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 20, Tank.HEIGHT + 20)) || walls[3].getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 20, Tank.HEIGHT + 20)) || walls[4].getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 20, Tank.HEIGHT + 20)) || walls[5].getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 20, Tank.HEIGHT + 20)) || walls[6].getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 20, Tank.HEIGHT + 20)) || walls[7].getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 20, Tank.HEIGHT + 20)) || tanks.get(0).getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 20, Tank.HEIGHT + 20)) || tanks.get(1).getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 20, Tank.HEIGHT + 20)) || tanks.get(2).getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 20, Tank.HEIGHT + 20)) || tanks.get(3).getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 20, Tank.HEIGHT + 20)) || tanks.get(4).getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 20, Tank.HEIGHT + 20))
	 						|| myTank.getRect().intersects(new Rectangle(tanksX, tanksY, Tank.WIDTH + 15, Tank.HEIGHT + 15)));
					tanks.add(new Tank(tanksX, tanksY, false, Tank.Direction.D, this));
				}
			}
		}
		
		
	// ��update��סpaint
	public void update(Graphics g) {
		if (offScreenImage == null) {
			offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
		}
		Graphics gOffScreen = offScreenImage.getGraphics();
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.black);
		gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		gOffScreen.setColor(c);
		paint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
	}

	public void tankFrame() {
		MenuBar mb = new MenuBar();
		Menu m3 = new Menu("���ܤϤ����ˤ����");
		Menu m = new Menu("��Ϸ����");
		Menu m2 = new Menu("������Ϸ");
		MenuItem mi3 = new MenuItem("΢�Ŵ���");
		MenuItem mi5 = new MenuItem("֧��������");
		MenuItem mi1 = new MenuItem("����˵��");
		MenuItem mi2 = new MenuItem("��Ϸ˵��");
		this.setMenuBar(mb);
		mb.add(m3);
		mb.add(m);
		mb.add(m2);
		m2.add(mi3);
		m.add(mi1);
		m.add(mi2);
		m2.add(mi5);
		mi1.setEnabled(true);
		mi2.setEnabled(true);
		mi3.setEnabled(true);
		mi5.setEnabled(true);
		final TankGame temp=this;
		mi1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				temp.setEnabled(false);
				SkillsHelpFrame frame = new SkillsHelpFrame(temp);
			}
			
		});
		mi2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				temp.setEnabled(false);
				GameHelpFrame frame = new GameHelpFrame(temp);
			}
			
		});
		mi3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				temp.setEnabled(false);
				WechatFrame frame = new WechatFrame(temp);
			}
			
		});
		mi5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				temp.setEnabled(false);
				PayFrame frame = new PayFrame(temp);
			}
			
		});
		this.setLocation(225, 80);
		this.setSize(GAME_WIDTH, GAME_HEIGHT);
		this.setTitle("TankWar");
		// �رմ��ڵļ�����,ʹ�������ࡣ
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		this.setBackground(Color.black);
		this.setResizable(false);// �����Ե������ڴ�С
		this.addKeyListener(new KeyMonitor());
		this.setVisible(true);

		new Thread(new PaintThread()).start();
		
}	
	
	public static void main(String[] args) {
		TankGame tc = new TankGame();
		tc.tankFrame();
	}

	private class PaintThread implements Runnable {
		public void run() {
			while (true) {
				repaint();
				try {
					Thread.sleep(40);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private class KeyMonitor extends KeyAdapter {
		public void keyReleased(KeyEvent e) {
			myTank.keyReleased(e);
		}

		public void keyPressed(KeyEvent e) {
			myTank.keyPressed(e);
		}
	}
	
}
