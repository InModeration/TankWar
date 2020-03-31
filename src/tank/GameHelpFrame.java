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
		label1.setText("���ǧ��ݧѧէڧާڧ⧡�ߧէ�֧֧ӧڧ駯�ڧܧ� �ߧ�ӹ�������ӡ��㽫��ʻT14������̹�ˣ��͵з�̹�˽���ս����");
		label2.setText("Ϊʲô�ء���Ϊ����ɱ�������װ��Ĺ������ޣ����ǿɶ��أ�");
		label3.setText("��ʧ���ˣ���������������ô��ɱ����ĵз�̹�ˡ����������ͷ�ޣ���Ҳ���Բ�ѧ��");
		label4.setText("��Ϸ��ʼ���㿴�������Լ��԰ɣ���Ϊ�������½ǵ�Shelter�ڡ��¸�������ĵ�һ�������ӡ�");
		label5.setText("��Shelter������ߵз�̹���˺�����������Զ����������������������潲��һ��Ҫ�㣬�����ʼ�");
		label6.setText("Shelter������Ϊ����ǿӲ�����Ĵ�����Ҳ���������ڽ��䵱lyb�������з�̹�ˡ���ؼ˧��");
		label7.setText("��Խ���η�Ϊ���֣��ݵأ�Ϊ�����٣�������Ϊ�����٣���ˮ��أ�û�á�Ŷ��������ײ������ǽ");
		label8.setText("��ͨ����ɱһ��̹�˻�100�֣��õ��׻�ɱ��200�֡������ܵ�һ���˺�����ͼ�ϻ�ˢ�µ��װ���Ѫ����");
		label9.setText("��Ȼ����Ҳ����ͨ������ά������̹�ˣ�����������Σ���ʦ��Ȱ�������ʵ�ڳŲ�ס�����Ǹ����ܡ�");
		label10.setText("��Ϸ�ѶȻ�һֱ�Ӵ󣬺ܴ����֡�����㲻����Ӯ����Ϊ��Ϸ��Ŀ�ľ����䣬�ø߷���ʾ�����������");
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
