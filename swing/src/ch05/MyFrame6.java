package ch05;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

// 마우스 어댑터 클래스를 만들어보자
public class MyFrame6 extends JFrame{
	
	public MyFrame6() {
		initData();
		setInitLayout();
		addEventListener();
	}
	private void initData() {
		setTitle("어댑터 클래스 사용");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 500);
	}
	private void setInitLayout() {
		setVisible(true);
		
	}
	private void addEventListener() {
		//1. 클래스 구현 방법
		//2. 익명 구현 객체로 만들어내는 방법
		//3. 내부 클래스를 정의해서(상속을 받고) 오버라이드 활용하는 방법 ---> 선택해서 구현하면 됨.
		this.addMouseListener(new MyCustomMouseListener()); // 다형성
	}
	
	//내부 클래스 사용
	// 추상메서드를 일반 메서드로 바꿔준 클래스
	private class MyCustomMouseListener extends MouseAdapter {
		
		@Override
		public void mouseClicked(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			System.out.println("x : " + x);
			System.out.println("y : " + y);
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			System.out.println("마우스 in");
			super.mouseEntered(e);
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			System.out.println("마우스 out");
			super.mouseExited(e);
		}
		
	}
	public static void main(String[] args) {
		new MyFrame6();
		
	}

}
