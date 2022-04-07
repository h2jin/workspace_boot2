package tenco.com.test_04;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class BubbleFrame extends JFrame{
	
	private JLabel backgroundMqp;
	private Player player;
	
	public BubbleFrame() {
		initObject();
		initSetting();
		setVisible(true); // 마지막에 보여주도록 뒤로 뺌.
	}
	
	private void initObject() {
		backgroundMqp = new JLabel(new ImageIcon("images/backgroundMap.png")); 
		// 예외처리 안해줘도 되지만 오류가 찍히지 않아 디버깅하기가 힘들다..
		setContentPane(backgroundMqp);
		player = new Player();
		add(player);
		
	}
	
	private void initSetting() {
		setSize(1000, 640);
		setLayout(null); // absolute (좌표값으로 자유롭게 그림을 그릴 수 있다.)
		
		setLocationRelativeTo(null); // JFrame 가운데 배치하기
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public static void main(String[] args) {
		new BubbleFrame();
	}
	

}
