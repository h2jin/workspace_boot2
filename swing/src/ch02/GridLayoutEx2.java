package ch02;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class GridLayoutEx2 extends JFrame{
	
	//배열을 사용
	
	private GridLayout gridLayout;
	private JButton[] buttons = new JButton[6];
	private String[] titles = {"가", "나", "다", "라", "마", "바"};
	
	public GridLayoutEx2() {
		initData();
		setInitData();
	}
	
	private void initData() {
		setTitle("GridLayout 연습");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 500);
		gridLayout = new GridLayout(3, 2);
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new JButton(titles[i]);
		}
		
	}
	private void setInitData() {
		setVisible(true);
		setLayout(gridLayout);
		for (int i = 0; i < buttons.length; i++) {
			add(buttons[i]);
		}
		
	}
	
	public static void main(String[] args) {
		new GridLayoutEx2();
	}
	
}
