package ch03;

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
	
	final Object h[] = {"name", "Agr", "Occupation"};
	public MyJFrameEx2() {
		final JTable jTable = new JTable(r,h);
		JScrollPane jsp = new JScrollPane(jTable); //???
		
	}
	
	private void initData() {
		
	}
	private void serInitLayout() {
		
	}
	
	
	
}
