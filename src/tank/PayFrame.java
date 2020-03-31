package tank;

import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class PayFrame extends JFrame{
	PayPanel panel =new PayPanel();
	public PayFrame(final Frame father) 
	{
		this.setBounds(250 + TankGame.GAME_WIDTH / 4, 80 + TankGame.GAME_HEIGHT / 4, 400, 500);
		this.add(panel);
		
		
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
