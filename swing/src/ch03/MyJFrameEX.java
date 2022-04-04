package ch03;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

// JTable 연습 

public class MyJFrameEX extends JFrame {

	String[][] d = { { "Sam", "29", "Twinkle House" }, { "Anna Sam", "27", "Happy Villa" },
			{ "Iza Norah", "4", "HAppy House" } };
	//이중 배열
	//배열안에 배열이 들어가 있는 구조
	
	String[] cn = { "name", "Age", "House Address" };
	
	public MyJFrameEX() {
		initData();
		setInitLayout();
	}

	private void initData() {
		JTable jTable = new JTable(d, cn); //생성자 확인하기
		jTable.setBounds(30, 40, 200, 300);
		JScrollPane jsp = new JScrollPane(jTable); //공간이 부족하면 스크롤을 넣어주는 부분
		add(jsp);
		setTitle("JTable 연습");
		setSize(500, 200);
	}

	private void setInitLayout() {
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		new MyJFrameEX();
	}


}
