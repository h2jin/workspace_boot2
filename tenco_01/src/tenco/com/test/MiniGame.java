package tenco.com.test;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MiniGame extends JFrame implements ActionListener {

	private BufferedImage bgImage;
	private BufferedImage playerImage;
	private BufferedImage playerImageL;
	private BufferedImage playerImageR;

	private BufferedImage enemyImage;
	private BufferedImage enemyImageL;
	private BufferedImage enemyImageR;

	private JButton startBtn;
	private JButton endBtn;
	private JPanel bottomPanel;

	private boolean isThread = true;

	private int playerX;
	private int playerY;
	private int enemyX;
	private int enemyY;

	private CustomJPanel customJPanel;

	public MiniGame() {
		initData(); // 순서도 이유가 있다.
		setInitLayout();
		addEventListener();

		new Thread(customJPanel).start();

	}

	private void initData() {
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		startBtn = new JButton("start");
		endBtn = new JButton("end");
		

		try {
			bgImage = ImageIO.read(new File("images/backgroundMap.png"));
			playerImageL = ImageIO.read(new File("images/playerL.png"));
			playerImageR = ImageIO.read(new File("images/playerR.png"));
			playerImage = playerImageR;

			enemyImageL = ImageIO.read(new File("images/enemyL.png"));
			enemyImageR = ImageIO.read(new File("images/enemyR.png"));
			enemyImage = enemyImageR;

		} catch (IOException e) {
			System.out.println("파일이 없습니다.");
		}
		customJPanel = new CustomJPanel();
		
		enemyX = 100;
		enemyY = 400;
		
		bottomPanel = new JPanel(new FlowLayout());

		// 내부클래스를 초기화 하면

	}

	private void setInitLayout() {
		bottomPanel.add(startBtn);
		bottomPanel.add(endBtn);
		add(customJPanel, BorderLayout.CENTER);
		
		add(bottomPanel, BorderLayout.SOUTH);
		setVisible(true);
		this.requestFocusInWindow();
	}

	private void addEventListener() {
		
		startBtn.addActionListener(this);
		endBtn.addActionListener(this);
		
		
		this.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				int keycode = e.getKeyCode();
				switch (keycode) {
				case KeyEvent.VK_UP:
					playerY -= 10;
					break;
				case KeyEvent.VK_DOWN:
					playerY += 10;
					break;
				case KeyEvent.VK_LEFT:
					playerX -= 10;
					playerImage = playerImageL;
					break;
				case KeyEvent.VK_RIGHT:
					playerX += 10;
					playerImage = playerImageR;
					break;
				}
				repaint();

			}

		});
		
		this.requestFocusInWindow();

	}// end of addEventListener

	private class CustomJPanel extends JPanel implements Runnable {

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			g.drawImage(bgImage, 0, 0, 600, 600, null);
			g.drawImage(playerImage, playerX, playerY, 55, 55, null);
			g.drawImage(enemyImage, enemyX, enemyY, 50, 50, null);
		}

		@Override
		public void run() {

			boolean direction = true;
			while (true) {
				if (isThread) {
					if (direction) {
						enemyX += 10;
					} else {
						enemyX -= 10;
					}
					if (enemyX == 400) {
						direction = false;
						enemyImage = enemyImageL;
					}
					if (enemyX == 10) {
						direction = true;
						enemyImage = enemyImageR;
					}
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} // end of isThread
				repaint(); //if문 밖으로 나와야한다.
			}

		}

	}// end of inner class

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton targetBtn = (JButton) e.getSource();
		if(startBtn == targetBtn) {
			System.out.println("d 1");
			isThread = true;
		} else {
			System.out.println("d 2");
			isThread = false;
		}
		
		this.requestFocusInWindow();

	}

	public static void main(String[] args) {
		new MiniGame();
	}

}
