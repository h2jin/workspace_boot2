package adressBook;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class AdressFrame extends JFrame {


	private JTextField jTextField;
	private JButton[] buttons = new JButton[6];
	private String[] titles = { "주소록 전체", "친구", "회사", "학교", "가족", "추가" };
	private String[] names = { "1. 홍길동", "임꺽정", "3.홍보이", "4. 고두심", "5. 이선정", "6. 강동원", "7.하지원", "성룡" };
	
	private JPanel panel1, panel2, panel3;;
	
	
	public AdressFrame() {
		initData();
		setInitLayout();
		addEventListener();
	}

	private void initData() {
		setTitle("주소록 메인");
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new JButton(titles[i]);
		}
		JScrollPane jsp = new JScrollPane(jTextField);
		add(jsp);
		
		panel1 = new JPanel();
		
		

	}

	private void setInitLayout() {
		setVisible(true);
		setLayout(new GridLayout(4,1));
		

//		for(int i = 1; i < 5; i++) {
//			add(buttons[i]);
//			buttons[i].setLayout(new FlowLayout(FlowLayout.LEFT));
//		}
		


	}

	private void addEventListener() {

	}
	
	public static void main(String[] args) {
		new AdressFrame();
	}

}
