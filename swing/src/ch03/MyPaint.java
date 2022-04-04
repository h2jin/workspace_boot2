package ch03;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

// 외부 클래스(outter class)
public class MyPaint extends JFrame {

	MyPanel1 myPanel1;

	public MyPaint() {
		initData();
		setInitLayout();
	}

	private void initData() {
		setTitle("직접 그려보기 연습");
		setSize(800, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myPanel1 = new MyPanel1();

	}

	private void setInitLayout() {
		setVisible(true);
		add(myPanel1);
	}

	public static void main(String[] args) {
		new MyPaint();
	}

	class MyPanel1 extends JPanel {

		@Override
		public void paint(Graphics g) {

			super.paint(g);// 무조건 호출되어야 작동. 상속관계이기 때문에
			g.drawString("안녕 Java 2D ~", 200, 200);
			g.drawLine(20, 30, 100, 100); // (x 좌표, y 좌표)
			g.drawRect(100, 100, 150, 150);
		}

	}// end of inner class (내부 클래스)
	//왜 내부, 외부 나눌까? MyPanel 클래스는 이 클래스 내부에서만 
	//사용할 것이기 때문에 굳이 외부로 뺼 필요가 없다. 그리고 외부에서
	//접근할 필요가 없으면 private 

}// end of class
