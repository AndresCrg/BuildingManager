package views;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PanelLeftLogin extends JPanel{

	private static final String IMG_FOOD_EXPRESS = "/imgs/banner.png";
	private static final long serialVersionUID = 1L;
	private Image img;
	
	public PanelLeftLogin() {
		img = new ImageIcon(getClass().getResource(IMG_FOOD_EXPRESS)).getImage();
	}
	
	 public void paintComponent(Graphics g){
		  super.paintComponents(g);
		  g.drawImage(img, 0, 0, 500, 500, this);
		 }
	
	protected ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
}
