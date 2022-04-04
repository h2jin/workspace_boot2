package ch02;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MyFrame2 extends JFrame {

	private BorderLayout borderLayout = new BorderLayout();
	private ArrayList<JButton> buttons = new ArrayList<JButton>();
	private ArrayList<String> titles = new ArrayList<>();
	private ArrayList<String> directions = new ArrayList<>();

	public MyFrame2() {
		initData();
		setInitData();
	}

	private void initData() {
		setTitle("BorderLayout Arraylist");
		setSize(400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	private void setInitData() {
		setVisible(true);
		setLayout(borderLayout);

	}
}
