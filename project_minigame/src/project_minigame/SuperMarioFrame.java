package project_minigame;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class SuperMarioFrame {

	private JFrame frame;
	JLabel label;

	Image backgroundImage = new ImageIcon("images/backgroundMap.gif").getImage();

	int x = 0;

	public SuperMarioFrame() {
		initData();
		setInitLayout();
	}

	private void setInitLayout() {
		frame = new JFrame();
		frame.setSize(500 , 224);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 라벨에 배경 올려주기
		// 
		
	}

	private void initData() {
		// TODO Auto-generated method stub
		
	}
	
	
}
