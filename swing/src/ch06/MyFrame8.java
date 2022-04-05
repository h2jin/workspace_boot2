package ch06;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyFrame8 extends JFrame{

	// 변수의 효용!!!

	private BufferedImage bgImage;
	private BufferedImage imageIcon;
	private MyImagePanel myImagePanel;
	
	// 내부클래스 선언 --> paint 오버라이드

	// 이벤트 리스너 등록

	public MyFrame8() {
		initData();
		setInitLayout();
		addEventListener();
	}

	private void initData() {
		setTitle("이미지 백그라운드");
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myImagePanel = new MyImagePanel();
		try {
			bgImage = ImageIO.read(new File("image2.jpg"));
			imageIcon = ImageIO.read(new File("icon_2.png"));

		} catch (IOException e) {
			System.out.println("파일이 없습니다.");
		}
		
		

	}

	private void setInitLayout() {
		setVisible(true);
		setResizable(false);
		add(myImagePanel);

	}

	private void addEventListener() {
		myImagePanel.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				int keycode = e.getKeyCode();
				if (keycode == KeyEvent.VK_UP) {
					
				} else if (keycode == KeyEvent.VK_DOWN) {
					
				} else if (keycode == KeyEvent.VK_LEFT) {
					
				} else if (keycode == KeyEvent.VK_RIGHT) {
					
				}
				
			}
		});
		
	}

	private class MyImagePanel extends JPanel {
		@Override
		public void paint(Graphics g) {
			super.paintComponent(g);
			g.drawImage(bgImage, 0, 0, 500, 500, null);
			g.drawImage(imageIcon, 0, 0, 100, 100, this);
		}
		
	}// end of inner class
	

	public static void main(String[] args) {
		new MyFrame8();

	}// end of main

}// end of class
