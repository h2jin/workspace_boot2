package ch03;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

class MyHousePanel extends JPanel {
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawRect(250, 450, 300, 300);
		g.drawLine(400, 200, 250, 450);
		g.drawLine(400, 200, 550, 450);
		g.drawRect(425, 525, 80, 80);
		g.drawLine(465, 525, 465, 605);
		g.drawLine(425, 565, 505, 565);
		g.drawString("★   ★    ★", 50, 50);
		g.drawString("★   ★    ★", 200, 50);
		g.drawString("★   ★    ★", 350, 50);
		g.drawString("★   ★    ★", 500, 50);
		g.drawString("★   ★    ★", 700, 50);
		g.drawString("★   ★    ★", 160, 100);
		g.drawString("★   ★    ★", 540, 100);
		g.drawLine(100, 500, 100, 800);
		g.drawLine(150, 500, 150, 800);
		g.drawLine(90, 500, 160, 500);
		g.drawLine(125, 400, 90, 500);
		g.drawLine(125, 400, 160, 500);
		
	}
}

public class MyHouseFrame extends JFrame{
	MyHousePanel myHousePanel;
	
	public MyHouseFrame() {
		initData();
		setInitLayout();
		
	}
	
	public void initData() {
		setTitle("임희진의 집");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 800);
		myHousePanel = new MyHousePanel();
	}
	public void setInitLayout() {
		setVisible(true);
		add(myHousePanel);
	}
	
	public static void main(String[] args) {
		new MyHouseFrame();
	}
	
	

}
