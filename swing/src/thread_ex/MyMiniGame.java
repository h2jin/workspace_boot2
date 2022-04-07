package thread_ex;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyMiniGame extends JFrame implements ActionListener {

	private BufferedImage image1;
	private BufferedImage image2;
	private BufferedImage image3;
	private CustomJpanel customJpanel;
	private JButton button1, button2;
	private JPanel panel;
	private BorderLayout borderLayout;

	private int xPoint;
	private int yPoint;
	private int xPoint1;

	public MyMiniGame() {
		initData();
		setInitLayout();
		addEventListener();
		// 생성자에서 Thread start 처리해주기
		customJpanel.run();

	}

	private void initData() {
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 파일 가져오기 TODO
		try {
			image1 = ImageIO.read(new File("minigame.jpg"));
			image2 = ImageIO.read(new File("monster1.png"));
			image3 = ImageIO.read(new File("warrior1.png"));
		} catch (Exception e) {
			System.out.println("파일이 없습니다.");

		}

		customJpanel = new CustomJpanel();
		button1 = new JButton("Stop");
		button2 = new JButton("Continue");
		panel = new JPanel();
		borderLayout = new BorderLayout();
	}

	private void setInitLayout() {
		setVisible(true);
		setResizable(false); //프레임 크기변경을 불가능하게 만드는 것.
		add(customJpanel);
		this.add(panel,BorderLayout.SOUTH);
		panel.add(button1);
		panel.add(button2);
	}

	private void addEventListener() {
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// 여기서는 이미지2를 키 이벤트를 받아서 동작시켜 주기.
				int keycode = e.getKeyCode();
				if (keycode == KeyEvent.VK_UP) {
					yPoint = (yPoint < 0) ? 0 : yPoint - 10;
				} else if (keycode == KeyEvent.VK_DOWN) {
					yPoint = (yPoint > 600 - image3.getHeight() / 2) ? 600 - image3.getHeight() / 2 : yPoint + 10;
				} else if (keycode == KeyEvent.VK_LEFT) {
					xPoint = (xPoint < 0) ? 0 : xPoint - 10;
				} else if (keycode == KeyEvent.VK_RIGHT) {
					xPoint = (xPoint > 600 - 100) ? 600 - 100 : xPoint + 10;
				}
				repaint();
			}
		});
		

	}

	private class CustomJpanel extends JPanel implements Runnable {

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			// 이미지 그리기 3개 TODO
			g.drawImage(image1, 0, 0, getWidth(), getHeight(), null);
			g.drawImage(image3, xPoint, yPoint, 150, 150, null);
			g.drawImage(image2, xPoint1, 380, 200, 200, null);

		}

		boolean direction = true;
		@Override
		public void run() {
			while (true) {
				if (direction) {
					xPoint1 += 10;
				} else {
					xPoint1 -= 10;
				}
				if (xPoint1 == 400) {
					direction = false;
				}
				if (xPoint1 == 0) {
					direction = true;
				}
				try {
					Thread.sleep(100);
				} catch (Exception e) {
					e.printStackTrace();
				}
				repaint();
			}

			// 이미지 3번 좌 우로
			// while(true) {} 이미지 하나 >>>>>>> <<<<<<<< 여기서 만들어주기
			// x좌표값을 + max좌표값을 확인하고 x좌표값 - 해주면 됨.
			// 그림을 다시 그려주기repaint;

			// x좌표값 600넘기 전까지 계속 +

			// Thread.sleep 써주기

		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}

}
