package tank;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SkillsHelpFrame extends JFrame{
	JPanel panel =new JPanel(new GridLayout(6,1));
	public SkillsHelpFrame(final Frame father) 
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
		label6.setText("                                                                   技能使用说明及键位控制");
		label1.setText("方向键      ： 控制坦克的移动方向。");
		label2.setText("空格键      ： 控制坦克发射普通炮弹。");
		label3.setText("Ctrl     ： 大杀四方-坦克朝着四个方向开火（消耗100得分）。");
		label4.setText("A        ： 使用地雷。");
		label5.setText("S        ： 坦克维修（消耗400得分，并且只有3次使用机会）。");
		panel.add(label6);
		panel.add(label1);
		panel.add(label2);
		panel.add(label3);
		panel.add(label4);
		panel.add(label5);

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

