package ch01;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MyFrame extends JFrame{
	
	public MyFrame() {
		super.setTitle("MyFrame");
		super.setSize(500, 500);
		super.setVisible(true);
//		super.setDefaultCloseOperation(ABORT);
	}
	
	
	
	
	
	
	public static void main(String[] args) {
//		new MyFrame(); //주소값 X 바로 메모리에 올림
		String title = JOptionPane.showInputDialog("입력값 받기");
		System.out.println(title);
				
		
	}//end of main

}// end of class
