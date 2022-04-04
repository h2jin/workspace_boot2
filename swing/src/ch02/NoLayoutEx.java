package ch02;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

public class NoLayoutEx extends JFrame{
	
	ArrayList<JButton> buttons = new ArrayList<>();
	
	
	//좌표값으로 배치
	
	
	public NoLayoutEx() {
		initData();
		setInitData();
	}
	
	private void initData() {
		setTitle("No Layout ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 500);
//		for (int i = 0; i < buttons.size(); i++) {
//			buttons.add(new JButton("buttons" + i));
//		}
		buttons.add(new JButton("1"));
		buttons.add(new JButton("2"));
		buttons.add(new JButton("3"));
		
	}
	private void setInitData() {
		setVisible(true);
		setLayout(null);
		for (int i = 0; i < buttons.size(); i++) {
			buttons.get(i).setSize(50, 50);
			buttons.get(i).setLocation(50 + (10 * i), (10*i));
			add(buttons.get(i));
		}
		
//		button1.setSize(50, 50);
//		button1.setLocation(10, 10);
//		
//		button2.setSize(50, 50);
//		button2.setLocation(100, 100);
//		
//		button3.setSize(50, 50);
//		button3.setLocation(200, 200);
//		
//		add(button1);
//		add(button2);
//		add(button3);
		
	}
	public static void main(String[] args) {
		new NoLayoutEx();
		
	}

}
