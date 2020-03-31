package tank;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PayPanel extends JPanel{
	Image img;
    public PayPanel() {  
    	img = new ImageIcon("Icon/Ö§¸¶±¦.jpg").getImage();
    }  
    public void paintComponent(Graphics g) {  
        super.paintComponent(g);  

        g.drawImage(img, 0, 0,this.getWidth(), this.getHeight(), this);  
    }  
}
