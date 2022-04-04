package ch03;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyImageFrame2 extends JFrame{
	
	// 코드를 조금 수정해 주세요 
	// 상수, 스테이틱 활용해서 처리해주세요
	
	private BufferedImage backGroundImage;
	private BufferedImage imageIcon;
	private MyImagePanel myImagePanel;
	private JButton button;
	
	public MyImageFrame2() {
		initData();
		setInitLayout();
	}
	
	private void initData() {
		setTitle("이미지 백그라운드 연습");
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try {
			backGroundImage = ImageIO.read(new File("image1.jpg")); //예외 처리. 파일이름 다를수 있고 없을 수 있어서
			imageIcon = ImageIO.read(new File("icon2.png"));
			
//			imageIcon = ImageIO.read(new File(("icon1.jpg"));
			
		} catch (IOException e) {
			System.out.println("파일이 없습니다.");
		}
		
		myImagePanel = new MyImagePanel();
		
	}
	private void setInitLayout( ) {
		setVisible(true);
		setResizable(false);
		add(myImagePanel);
	}
	
	
	
	
	
	private class MyImagePanel extends JPanel {
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			// 이미지 두개를 그려주는 기능을 완료
			g.drawImage(backGroundImage, 0, 0, 500, 500, null); //재활용되는 값? 고정되는 상수 값?
			g.drawImage(imageIcon, 0, 0, 100, 100, null);
		}
		
	}// end of inner class
	
	//메인함수
	public static void main(String[] args) {
		new MyImageFrame2();
		
		
		
	}//end of main
	
	public void addEventListener() {
		System.out.println("브랜치를 만들었습니다.");
	}

}// end of outer class
