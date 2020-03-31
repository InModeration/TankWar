package tank;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.*;

public class WechatPanel extends JPanel{
	Image img;
    public WechatPanel() {  
    	img = new ImageIcon("Icon/н╒пе.jpg").getImage();
    }  
    public void paintComponent(Graphics g) {  
        super.paintComponent(g);  

        g.drawImage(img, 0, 0,this.getWidth(), this.getHeight(), this);  
    }  
}
