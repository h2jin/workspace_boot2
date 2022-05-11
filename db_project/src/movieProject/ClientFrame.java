package movieProject;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class ClientFrame extends JFrame {
	
	// GUI 변수
	private JPanel panel;
	private JPanel moviePanel;
	private JPanel actorPanel;
	private JPanel recentMoviePanel;
	private JTabbedPane tap;
	private JList movieList;
	private JList actorList;
	private JList recentMovieList;
	private JButton searchBtn1;
	private JButton searchBtn2;
	private JButton searchBtn3;
	
	public ClientFrame() {
		initData();
		setVisible(true);
	}
	
	private void initData() {
		setTitle("Search Movie");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 800);
		setLocationRelativeTo(null);
		
		panel = new JPanel();
		setContentPane(panel);
		panel.setLayout(null);
		
		// 영화 리스트를 가진 탭
		tap = new JTabbedPane(JTabbedPane.TOP);
		tap.setBounds(40, 70, 700, 600);
		panel.add(tap);
		tap.addTab("영화 리스트", null, moviePanel, null);
		moviePanel = new JPanel();
		moviePanel.setLayout(null);
		
		movieList = new JList();
		movieList.setBounds(10, 10, 100, 250);
		moviePanel.add(movieList);
		
		
		// 배우 리스트를 가진 탭
		actorPanel = new JPanel();
		tap.addTab("배우 리스트", null, actorPanel, null);
		actorPanel.setLayout(null);
		
		actorList = new JList();
		actorList.setBounds(10, 10, 100, 250);
		actorPanel.add(actorList);
		
		
		// 최신 영화 리스트를 가진 탭
		recentMoviePanel = new JPanel();
		tap.addTab("최신 영화 리스트", null, recentMoviePanel, null);
		recentMoviePanel.setLayout(null);
		
		recentMovieList = new JList();
		recentMovieList.setBounds(10, 10, 100, 250);
		recentMoviePanel.add(recentMovieList);
		
		searchBtn1 = new JButton("검색");
		searchBtn1.setBounds(100, 700, 100, 20);
		panel.add(searchBtn1);
		
		
	}
	
	public static void main(String[] args) {
		new ClientFrame();
	}
 	
	

}
