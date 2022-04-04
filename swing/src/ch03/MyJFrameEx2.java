package ch03;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

// JTable 연습2
public class MyJFrameEx2 extends JFrame {
	final Object r[][] = {
			{"Adam", "13", "Doctor"},
			{"Anna", "21", "Engineer"},
			{"Annamu", "31", "Technician"},
			{"Iza", "41", "Physician"},
			{"Norah", "11", "Author"},
			{"Naashy", "12", "Artist"},
			{"Poosa", "33", "Actor"},
			{"Pichi", "14", "Author"},
			{"Kunjol", "31", "Police"},
			{"Thukidi", "12", "Doctor"},
			{"Sam", "13", "Engineer"},
			{"Kukku", "24", "Technician"},
			{"Remya", "31", "Engineer"},
			{"Radha", "42", "Lawer"},
			{"Harini", "43", "Artist"},
			{"Vaibhav", "44", "Engineer"}
	}; //오브젝트 개념 이해하기
	
	final Object h[] = {"name", "Age", "Occupation"};
	private JButton button;
	
	public MyJFrameEx2() {
		initData();
		setInitLayout();
	}
	
	private void initData() {
		final JTable jTable = new JTable(r,h);
		JScrollPane jsp = new JScrollPane(jTable); //???
		setTitle("Printing Table");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(jsp, BorderLayout.CENTER); //???
		button = new JButton("출력하려면 이 버튼을 누르세요");
	}
		
	private void addEventListener() {
		button.addActionListener(null); //?
		
	}
		
		
	private void setInitLayout() {
		
	}
	
	
	
}
