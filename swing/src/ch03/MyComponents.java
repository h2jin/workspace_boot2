package ch03;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class MyComponents extends JFrame {

	private JPanel jPanel;
	private JButton button;
	private JLabel label;
	JTextField textField;
	private JPasswordField passWordField;
	private JCheckBox checkBox;

	public MyComponents() {
		initDate();
		setInitLayout();
	}

	private void initDate() {

		setTitle("컴포넌트 연습");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 800);

		jPanel = new JPanel();
		Dimension dimention = new Dimension(300, 300);

		jPanel.setPreferredSize(dimention);

		button = new JButton("button");
		label = new JLabel("label");
		textField = new JTextField("hint", 20);
		passWordField = new JPasswordField(20);
		checkBox = new JCheckBox("chexkBox", true);

	}

	private void setInitLayout() {
		setVisible(true);
		setLayout(new FlowLayout(FlowLayout.LEFT, 100, 200)); // 바로 생성해줌 메모리에 접근해서 추가하지 않음
		// static 변수 --> 클래스 이름으로 접근 가능, hgap, vgap

		add(jPanel);
		jPanel.setBackground(Color.BLUE); // static 이기 때문에 클래스로 접근함. 사용자에게 편의성 제공
//		add(button); //this의 add
		jPanel.add(button);
		jPanel.add(label);
		jPanel.add(textField);
		jPanel.add(passWordField);
		jPanel.add(checkBox);
	}
	// textField에 글자를 넣고싶으면? 메인함수에서 코딩

}
