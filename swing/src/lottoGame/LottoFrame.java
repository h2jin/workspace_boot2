package lottoGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;

public class LottoFrame extends JFrame implements ActionListener {

	private JButton startButton;
	private int[] lottoNumbers = new int[6];

	public LottoFrame() {
		setSize(600, 400);
		setBackground(Color.gray);
		setVisible(true);
		startButton = new JButton("로또");
		add(startButton, BorderLayout.NORTH);
		startButton.addActionListener(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		lottoNumbers = getLotto();
		repaint();
		
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.white);
		Font font = new Font("궁서체", Font.BOLD, 20);
		g.setFont(font);

		if (lottoNumbers[0] == 0) {
			// 게임을 실행하지 않은 상태라면
			g.drawString("로또 버튼을 클릭하세요", 50, 100);
			return; // 
		}

		// lottoNumbers[0] 값이 있다면 (6개 번호를 생성한 후)
		// 여기서 번호를 그려본다.
		g.drawString(lottoNumbers[0] + "", 50, 100);
		g.drawString(lottoNumbers[1] + "", 100, 100);
		g.drawString(lottoNumbers[2] + "", 150, 100);
		g.drawString(lottoNumbers[3] + "", 200, 100);
		g.drawString(lottoNumbers[4] + "", 250, 100);
		g.drawString(lottoNumbers[5] + "", 300, 100);

	}

	public static void main(String[] args) {
		new LottoFrame();
	}

	public int[] getLotto() {
		int[] lotto = new int[6];
		Random random = new Random();
		for (int i = 0; i < 6; i++) {
			int j = random.nextInt(45) + 1;
			lotto[i] = j;
			for (int e = 0; e < i; e++) {
				//중복된 번호가 나왔을 경우
				if (lotto[i] == lotto[e]) {
					i = i - 1;
					break;
				}
			}
		}
		// 정렬 문제 해결
		Arrays.sort(lotto); //오름차순 정렬
		for (int i : lotto) {
			System.out.println(i);
		}
		
		return lotto;
	} 


}
