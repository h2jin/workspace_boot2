package ch03;

import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyTwoPanel extends JFrame {
	
	private JPanel panel1;
	private JPanel panel2;
	
	private Button button1;
	private Button button2;
	private Button button3;
	private Button button4;
	private Button button5;
	
	public MyTwoPanel() {
		initDate();
		setInitDate();
	}
	
	private void initDate() {
		setTitle("패널 연습");
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel1 = new JPanel();
		panel1.setBackground(Color.YELLOW);
		
		
		panel2 = new JPanel();
		panel2.setBackground(Color.blue);
		
		
		button1 = new Button("button1");
		button2 = new Button("button2");
		button3 = new Button("button3");
		button4 = new Button("button4");
		button5 = new Button("button5");
	}
	private void setInitDate() {
		setVisible(true);
		setLayout(new GridLayout(2,1)); // 줄, 칸 전체레이아웃 설정
		panel1.setLayout(new FlowLayout(FlowLayout.LEFT)); //각 그룹별로도 레이아웃 설정 가능
		panel2.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		add(panel1);
		add(panel2);
		
		panel1.add(button1);
		panel1.add(button2);
		panel1.add(button3);
		
		panel2.add(button4);
		panel2.add(button5);
		
	}
	
	public static void main(String[] args) {
		new MyTwoPanel();
	}

}
