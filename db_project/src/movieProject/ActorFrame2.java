package movieProject;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

public class ActorFrame2 extends JFrame{
	
	private JPanel panel;
	private JPanel imagePanel;
	private JLabel imageLabel;
	private JLabel nameLabel;
	private JLabel ageLabel;
	private JLabel weightLabel;
	private JLabel heightLabel;
	private JLabel partnerLabel;
	private JLabel movieLabel;
	private JList movieList;
	private JScrollPane scrollPane;
	private JButton button;
	private JLabel title;
	
	
	public ActorFrame2() {
		initData();
		setVisible(true);
	}

	private void initData() {
		setTitle("검색 결과");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 800);
		setLocationRelativeTo(null);
		setLayout(null);
		
		getContentPane().setBackground(Color.black);
		
		title = new JLabel("MOVIEWIKI");
		title.setBounds(20, 10, 400, 50);
		title.setFont(new Font("맑은 고딕", Font.BOLD, 40));
		title.setOpaque(true);
		title.setForeground(Color.pink);
		title.setBackground(Color.black);
		add(title);
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(Color.black));
		panel.setBounds(20, 70, 730, 670);
		panel.setLayout(null);
		add(panel);
		
		imageLabel = new JLabel(new ImageIcon("images/actor.jpg"));
		imageLabel.setBounds(100, 50, 200, 286);
		panel.add(imageLabel);
		
		String name = "이름";
		nameLabel = new JLabel("이름 : " + name);
		nameLabel.setBounds(400, 50, 250, 30);
		panel.add(nameLabel);
		
		String age = "나이";
		ageLabel = new JLabel("나이 : " + age);
		ageLabel.setBounds(400, 100, 250, 30);
		panel.add(ageLabel);
		
		String year = "몸무게";
		weightLabel = new JLabel("개봉연도 : " + year);
		weightLabel.setBounds(400, 150, 250, 30);
		panel.add(weightLabel);
		
		String height = "키";
		heightLabel = new JLabel("키 : " + height);
		heightLabel.setBounds(400, 200, 250, 30);
		panel.add(heightLabel);
		
		String partner = "배우자";
		partnerLabel = new JLabel("배우자 : " + partner);
		partnerLabel.setBounds(400, 250, 250, 30);
		panel.add(partnerLabel);
		
		
		movieLabel = new JLabel("출연작");
		movieLabel.setBounds(100, 350, 80, 30);
		panel.add(movieLabel);
		
		movieList = new JList();
		movieList.setBounds(100, 380, 550, 200);
		panel.add(movieList);
		
		
		// 스크롤
		scrollPane = new JScrollPane();
		movieList.add(scrollPane);
		
		button = new JButton("검색");
		button.setBounds(570, 600, 80, 20);
		panel.add(button);
		
		
	}
	
	
	public static void main(String[] args) {
		new ActorFrame2();
	}


}
