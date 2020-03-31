package tank;

import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class GameHelpFrame extends JFrame{
	JPanel panel =new JPanel(new GridLayout(10,1));
	public GameHelpFrame(final Frame father) 
	{
		this.setBounds(225 + TankGame.GAME_WIDTH / 4, 80 + TankGame.GAME_HEIGHT / 4, TankGame.GAME_WIDTH / 2, TankGame.GAME_HEIGHT / 2);
		this.add(panel);
		panel.setBackground(Color.white);
		JLabel label1 = new JLabel();
		JLabel label2 = new JLabel();
		JLabel label3 = new JLabel();
		JLabel label4 = new JLabel();
		JLabel label5 = new JLabel();
		JLabel label6 = new JLabel();
		JLabel label7 = new JLabel();
		JLabel label8 = new JLabel();
		JLabel label9 = new JLabel();
		JLabel label10 = new JLabel();
		label1.setText("你是ВладимирАндреевичНико нов贵族的王子。你将驾驶T14阿玛塔坦克，和敌方坦克进行战斗。");
		label2.setText("为什么呢。因为他们杀死了你亲爱的公主。噢，真是可恶呢！");
		label3.setText("你失忆了，所以我来教你怎么击杀更多的敌方坦克。如果你是铁头娃，你也可以不学。");
		label4.setText("游戏开始，你看不见你自己对吧，因为你在右下角的Shelter内。勇敢迈出你的第一步吧王子。");
		label5.setText("在Shelter里，你免疫敌方坦克伤害，而且你可以对他们做出攻击动作。下面讲第一个要点，请做笔记");
		label6.setText("Shelter可以作为你躲避强硬进攻的处所，也可以让你在角落当lyb，赖死敌方坦克。有丶帅。");
		label7.setText("可越地形分为三种：草地，为您加速；河流，为您减速；黑水泥地，没用。哦，还有你撞不动的墙");
		label8.setText("普通导弹杀一辆坦克获100分，用地雷击杀获200分。当你受到一定伤害，地图上会刷新地雷包、血包。");
		label9.setText("当然，你也可以通过技能维修自身坦克，但是最多三次，老师奉劝你们最好实在撑不住在用那个技能。");
		label10.setText("游戏难度会一直加大，很大那种。最后你不可能赢，因为游戏的目的就是输，得高分显示你的王子魅力");
		panel.add(label1);
		panel.add(label2);
		panel.add(label3);
		panel.add(label4);
		panel.add(label5);
		panel.add(label6);
		panel.add(label7);
		panel.add(label8);
		panel.add(label9);
		panel.add(label10);
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e)
			{
				father.setEnabled(true);
			}
		});
		this.setVisible(true);
		this.setResizable(false);
	}
}
