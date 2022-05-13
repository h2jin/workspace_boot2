package Manager;

import java.awt.Color;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import lombok.Getter;
import lombok.Setter;

public class ManagerFrame extends JFrame implements ActionListener {

	private JButton insertBtn;
	private JButton updateStarBtn;
	private JButton updateScoreBtn;
	private JButton deleteBtn;

	private JTabbedPane menuTab;

	private JPanel backgroundPanel;
	private JPanel updateTab;
	private JPanel insertTab;
	private JPanel listTab;
	private JPanel updatePanel;
	private JPanel deletePanel;

	private JTextField txtTitle;
	private JTextField txtReleaseDate;
	private JTextField txtStarScore;
	private JTextField txtGenre;
	private JTextField txtSales;

	private JLabel titleLabel;
	private JLabel releaseDateLabel;
	private JLabel starScoreLabel;
	private JLabel genreLabel;

	private ScrollPane scrollPane;
	
	

	private JList<String> movieList;
	private Vector<String> titleList = new Vector<String>();

	private UpdateStarScorePanel updateStarScorePanel;
	private UpdateScorePanel updateScorePanel;
	
	private ManagerDao managerDao;
	
	
	
		

	public ManagerFrame() {
		
		managerDao = new ManagerDao();
		
		initData();
		setInitLayout();
		addListener();
	}

	private void initData() {
		setTitle("영화 정보 관리 프로그램");
		setSize(500, 700);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		movieList = new JList<String>();
		titleList = managerDao.loadListMoive();
		movieList.setListData(titleList);
		

		updateStarScorePanel = new UpdateStarScorePanel();
		updateScorePanel = new UpdateScorePanel();

		insertBtn = new JButton("등록");
		updateStarBtn = new JButton("수정");
		updateScoreBtn = new JButton("수정");
		deleteBtn = new JButton("삭제");

		insertBtn.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		updateStarBtn.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		updateScoreBtn.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		deleteBtn.setFont(new Font("맑은 고딕", Font.BOLD, 14));

		txtTitle = new JTextField();
		txtReleaseDate = new JTextField();
		txtStarScore = new JTextField();
		txtGenre = new JTextField();

		menuTab = new JTabbedPane(JTabbedPane.TOP);

		backgroundPanel = new JPanel();
		updateTab = new JPanel();
		insertTab = new JPanel();
		listTab = new JPanel();

		updatePanel = new JPanel();
		deletePanel = new JPanel();

		titleLabel = new JLabel("영화 제목 : ");
		releaseDateLabel = new JLabel("개봉 일자 : ");
		genreLabel = new JLabel("장르 : ");
		starScoreLabel = new JLabel("평점 : ");

		titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		releaseDateLabel.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		starScoreLabel.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		genreLabel.setFont(new Font("맑은 고딕", Font.BOLD, 13));

		scrollPane = new ScrollPane();

		menuTab.addTab("목록", null, listTab, null);
		menuTab.addTab("등록", null, insertTab, null);
		menuTab.addTab("수정", null, updateTab, null);

		setVisible(true);
	}

	private void setInitLayout() {
		backgroundPanel.setLayout(null);
		updateTab.setLayout(null);
		insertTab.setLayout(null);
		listTab.setLayout(null);

		menuTab.setBounds(0, 10, getWidth(), getHeight());
		backgroundPanel.add(menuTab);

		txtTitle.setBounds(180, 100, 150, 25);
		txtReleaseDate.setBounds(180, 140, 150, 25);
		txtStarScore.setBounds(180, 180, 150, 25);
		txtGenre.setBounds(180, 220, 150, 25);

		insertBtn.setBounds(190, 350, 85, 30);
		updateStarBtn.setBounds(200, 200, 85, 30);
		deleteBtn.setBounds(200, 550, 85, 30);

		scrollPane.setBounds(60, 75, 370, 440);

		updateStarScorePanel.setBounds(30, 30, 420, 270);

		updateScorePanel.setBounds(30, 330, 420, 270);

		titleLabel.setBounds(110, 100, 100, 25);
		releaseDateLabel.setBounds(110, 140, 100, 25);
		starScoreLabel.setBounds(140, 180, 100, 25);
		genreLabel.setBounds(140, 220, 100, 25);

		backgroundPanel.setBackground(Color.darkGray);
		updateStarScorePanel.setBorder(new LineBorder(Color.lightGray));
		updateScorePanel.setBorder(new LineBorder(Color.lightGray));

		movieList.setBounds(65, 70, 350, 450);
		scrollPane.add(movieList);

		listTab.add(scrollPane);
		listTab.add(deleteBtn);

		updateTab.add(updateStarScorePanel);
		updateTab.add(updateScorePanel);

		insertTab.add(txtTitle);
		insertTab.add(txtReleaseDate);
		insertTab.add(txtStarScore);
		insertTab.add(txtGenre);
		insertTab.add(insertBtn);
		insertTab.add(titleLabel);
		insertTab.add(releaseDateLabel);
		insertTab.add(genreLabel);
		insertTab.add(starScoreLabel);
		
		setContentPane(backgroundPanel);
	}

	private void addListener() {
		insertBtn.addActionListener(this);
		deleteBtn.addActionListener(this);

	}

	@Getter
	@Setter
	class UpdateStarScorePanel extends JPanel {

		private JLabel titleLabel;
		private JLabel scoreLabel;
		private JTextField updateTF;
		private JTextField updateStarTF;
		private JButton button;

		private JLabel label;

		public UpdateStarScorePanel() {
			setLayout(null);
			setSize(420, 270);
			setVisible(true);

			label = new JLabel("평점 수정");
			label.setBounds(10, 5, 80, 30);
			label.setFont(new Font("맑은 고딕", Font.BOLD, 16));
			add(label);

			titleLabel = new JLabel("수정할 영화 제목 : ");
			titleLabel.setBounds(20, 50, 150, 30);
			titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 13));
			add(titleLabel);

			updateTF = new JTextField();
			updateTF.setBounds(150, 57, 200, 20);
			add(updateTF);

			scoreLabel = new JLabel("평점 : ");
			scoreLabel.setBounds(95, 100, 50, 30);
			scoreLabel.setFont(new Font("맑은 고딕", Font.BOLD, 13));
			add(scoreLabel);

			updateStarTF = new JTextField();
			updateStarTF.setBounds(150, 107, 200, 20);
			add(updateStarTF);

			button = new JButton("수정");
			button.setFont(new Font("맑은 고딕", Font.BOLD, 13));
			button.setBounds(290, 157, 80, 30);
			add(button);

		}

	}

	@Getter
	@Setter
	class UpdateScorePanel extends JPanel {

		private JLabel scoreUpdate;

		private JLabel updateScoreLabel;
		private JLabel updateAudience;
		private JLabel updateSales;
		private JTextField updateScoreTF;
		private JTextField updateAudienceTF;
		private JTextField updateSalesTF;

		private JButton button;

		public UpdateScorePanel() {
			setLayout(null);
			setSize(420, 270);
			setVisible(true);

			scoreUpdate = new JLabel("관객수 / 매출 수정");
			scoreUpdate.setBounds(10, 5, 200, 30);
			scoreUpdate.setFont(new Font("맑은 고딕", Font.BOLD, 16));
			add(scoreUpdate);

			updateScoreLabel = new JLabel("수정할 영화 제목 : ");
			updateScoreLabel.setBounds(20, 50, 150, 30);
			updateScoreLabel.setFont(new Font("맑은 고딕", Font.BOLD, 13));
			add(updateScoreLabel);

			updateScoreTF = new JTextField();
			updateScoreTF.setBounds(150, 57, 200, 20);
			add(updateScoreTF);

			updateAudience = new JLabel("관객 수 : ");
			updateAudience.setBounds(80, 100, 70, 30);
			updateAudience.setFont(new Font("맑은 고딕", Font.BOLD, 13));
			add(updateAudience);

			updateAudienceTF = new JTextField();
			updateAudienceTF.setBounds(150, 107, 200, 20);
			add(updateAudienceTF);

			updateSales = new JLabel("매출액 : ");
			updateSales.setBounds(80, 150, 70, 30);
			updateSales.setFont(new Font("맑은 고딕", Font.BOLD, 13));
			add(updateSales);

			updateSalesTF = new JTextField();
			updateSalesTF.setBounds(150, 157, 200, 20);
			add(updateSalesTF);

			button = new JButton("수정");
			button.setFont(new Font("맑은 고딕", Font.BOLD, 13));
			button.setBounds(290, 207, 80, 30);
			add(button);

		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton selectedBtn = (JButton) e.getSource();

		if (selectedBtn == insertBtn) {
			managerDao.insertMovieInfo(txtTitle.getText(), txtReleaseDate.getText(), Double.parseDouble(txtStarScore.getText()), txtGenre.getText(), txtTitle.getText() + ".jpg");
		
		} else if (selectedBtn == deleteBtn) {
			managerDao.deleteMovieInfo(movieList.getSelectedValue());
			
		} else if (selectedBtn == updateStarBtn) {
			managerDao.updateStarScore(Double.parseDouble(updateStarScorePanel.getUpdateStarTF().getText()), updateStarScorePanel.getUpdateTF().getText());
		
		} else if (selectedBtn == updateScoreBtn) {
			managerDao.updateScore(Integer.parseInt(updateScorePanel.getUpdateAudienceTF().getText()), BigDecimal.valueOf(Long.parseLong(updateScorePanel.getUpdateSalesTF().getText())), updateScorePanel.getUpdateScoreTF().getText());
		
		}

	}

	public static void main(String[] args) {
		new ManagerFrame();
	}

}
