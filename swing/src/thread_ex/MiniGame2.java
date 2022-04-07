package thread_ex;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
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

public class MiniGame2 extends JFrame implements ActionListener {

	private BufferedImage image1;
	private BufferedImage image2;
	private BufferedImage image3;
	private JPanel panel2;
	private JButton button1, button2;
	private CustomJpanel customJpanel;
//	backGroundPanel = 

	private int xPoint;
	private int yPoint;
	private int xPoint1;
	private boolean isThread = true;

	public MiniGame2() {
		initData();
		setInitLayout();
		addEventListener();
		// Thread 실행 처리
		customJpanel.run();
	}

	private void initData() {
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			image1 = ImageIO.read(new File("minigame.jpg"));
			image2 = ImageIO.read(new File("warrior1.png"));
			image3 = ImageIO.read(new File("monster1.png"));
		} catch (Exception e) {
			System.out.println("파일이 없습니다.");
		}

		button1 = new JButton("Stop");
		button2 = new JButton("Continue");
//		panel1 = new JPanel();
		panel2 = new JPanel(new GridLayout(1,2));
		customJpanel = new CustomJpanel();

	}

	private void setInitLayout() {
		setVisible(true);
		// TODO
		panel2.add(button1);
		panel2.add(button2);
		add(customJpanel, BorderLayout.CENTER);
		add(panel2, BorderLayout.SOUTH);
		this.requestFocusInWindow();

	}

	private void addEventListener() {
		button1.addActionListener(this);
		button2.addActionListener(this);
		
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int keycode = e.getKeyCode();
				if (keycode == KeyEvent.VK_UP) {
					yPoint = (yPoint < 0) ? 0 : yPoint - 10;
				} else if (keycode == KeyEvent.VK_DOWN) {
					yPoint = (yPoint > 450) ? 450 : yPoint + 10;
				} else if (keycode == KeyEvent.VK_LEFT) {
					xPoint = (xPoint < 0) ? 0 : xPoint - 10;
				} else if (keycode == KeyEvent.VK_RIGHT) {
					xPoint = (xPoint > 450) ? 450 : xPoint + 10;
				}
			}
		});
		this.requestFocusInWindow();

	}

	private class CustomJpanel extends JPanel implements Runnable {

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(image1, 0, 0, getWidth(), getHeight(), null);
			g.drawImage(image2, xPoint, yPoint, 150, 150, null);
			g.drawImage(image3, xPoint1, 380, 200, 200, null);
		}

		boolean direction = true;

		@Override
		public void run() {
			while (true) {
				if (isThread) {
					if (xPoint1 == 0) {
						direction = true;
					}
					if (xPoint1 == 380) {
						direction = false;
					}
					if (direction) {
						xPoint1 += 10;
					} else {
						xPoint1 -= 10;
					}
					try {
						Thread.sleep(100);
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
				repaint();
			}

		}

	} // end of class

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton targetButton = (JButton) e.getSource();
		if (button2 == targetButton) {
			System.out.println("d 1");
			isThread = true;
		} else {
			System.out.println("d 2");
			isThread = false;
		}
		this.requestFocusInWindow();

	}

	public static void main(String[] args) {
		new MiniGame2();
	}

}
