package miniGame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Frame extends JFrame {

	private JLabel background;
	private Player player;
	private Monster monster;

	public Frame() {
		initData();
		setinitLayout();
		addEventListener();
		setVisible(true);

	}

	public void initData() {
		background = new JLabel(new ImageIcon("images/minigame.jpg"));// 오류나면 확인해보기!
		setContentPane(background);
		player = new Player();
		add(player);
		monster = new Monster();
		add(monster);

	}

	public void setinitLayout() {
		
		setTitle("미니게임");
		setSize(1500, 1000);
		setLayout(null);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	private void addEventListener() {
		
		
		
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					if (!player.isLeft()) {
						player.left();
					}
					break;
				case KeyEvent.VK_RIGHT:
					if (!player.isRight()) {
						player.right();
					}
					break;
				case KeyEvent.VK_UP:
					if (!player.isUp() && !player.isDown()) {
						player.up();
					}
					break;
				default:
					break;
				}
			}// end of keypressed

			@Override
			public void keyReleased(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					player.setLeft(false);
					break;
				case KeyEvent.VK_RIGHT:
					player.setRight(false);
					break;
				default:
					break;
				}
			}
		});// end of addKeyListener
	}// end of addEventListener
	
	public static void main(String[] args) {
		new Frame();
	}// end of main

}
