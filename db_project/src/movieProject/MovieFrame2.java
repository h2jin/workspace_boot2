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

public class MovieFrame2 extends JFrame{
	
	private JPanel panel;
	private JLabel imageLabel;
	private JLabel titleLabel;
	private JLabel genreLabel;
	private JLabel yearLabel;
	private JLabel starLabel;
	private JLabel audienceLabel;
	private JLabel salesLabel;
	private JLabel roleLabel;
	private JList roleList;
	private JScrollPane scrollPane;
	private JLabel title;
	private JButton button;
	private Font font = new Font("맑은 고딕", Font.BOLD, 13);
	
	
	public MovieFrame2() {
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
		panel.setBorder(new LineBorder(Color.white));
		panel.setBounds(20, 70, 730, 670);
		panel.setLayout(null);
		add(panel);
		
		imageLabel = new JLabel(new ImageIcon("images/poster.jpg"));
		imageLabel.setBounds(100, 50, 200, 286);
		panel.add(imageLabel);
		
		String title = "영화 제목";
		titleLabel = new JLabel("제목 : " + title);
		titleLabel.setFont(font);
		titleLabel.setBounds(350, 50, 250, 30);
		panel.add(titleLabel);
		
		String genre = "영화 장르";
		genreLabel = new JLabel("장르 : " + genre);
		genreLabel.setFont(font);
		genreLabel.setBounds(350, 100, 250, 30);
		panel.add(genreLabel);
		
		String year = "개봉연도";
		yearLabel = new JLabel("개봉연도 : " + year);
		yearLabel.setFont(font);
		yearLabel.setBounds(350, 150, 250, 30);
		panel.add(yearLabel);
		
		String star = "평점";
		starLabel = new JLabel("평점 : " + star);
		starLabel.setFont(font);
		starLabel.setBounds(350, 200, 250, 30);
		panel.add(starLabel);
		
		String audience = "관객 수";
		audienceLabel = new JLabel("관객 수 : " + audience);
		audienceLabel.setFont(font);
		audienceLabel.setBounds(350, 250, 250, 30);
		panel.add(audienceLabel);
		
		String sales = "매출액";
		salesLabel = new JLabel("매출액 : " + sales);
		salesLabel.setFont(font);
		salesLabel.setBounds(350, 300, 250, 30);
		panel.add(salesLabel);
		
		roleLabel = new JLabel("등장인물");
		roleLabel.setBounds(100, 350, 80, 30);
		roleLabel.setFont(font);
		panel.add(roleLabel);
		
		roleList = new JList();
		roleList.setBounds(100, 380, 550, 200);
		panel.add(roleList);
		
//		actorLabel = new JLabel("배우");
//		actorLabel.setBounds(355, 480, 80, 30);
//		panel.add(actorLabel);
//		
//		actorList = new JList();
//		actorList.setBounds(355, 510, 255, 200);
//		panel.add(actorList);
		
		// 스크롤
		scrollPane = new JScrollPane();
		roleList.add(scrollPane);
		
		button = new JButton("검색");
		button.setBounds(570, 600, 80, 20);
		panel.add(button);
		
		
	}
	
	
	public static void main(String[] args) {
		new MovieFrame2();
	}


}
