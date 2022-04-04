package ch03;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyImageFrame1 extends JFrame{
	
	private ImagePanel imagePanel;
	
	public MyImageFrame1() {
		initData();
		setInitLayout();
	}
	
	private void initData() {
		setTitle("JPanel에 이미지 넣기");
		setSize(500, 500);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		imagePanel = new ImagePanel();
	}
	
	private void setInitLayout () {
		setVisible(true);
		add(imagePanel);
	}
	
	public static void main(String[] args) {
		new MyImageFrame1();
	}
	
	
	private class ImagePanel extends JPanel {
		
		private Image image; //ctrl shift o
		
		
		public ImagePanel() {
			image = new ImageIcon("image1.jpg").getImage();
		}
		
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			g.drawImage(image, 0, 0, getWidth(), getHeight(), null); //getWidth, getHeight ->
		}
		
	}//end of inner class
	
	
}// end of outer class
