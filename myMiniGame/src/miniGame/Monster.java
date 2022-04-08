package miniGame;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Monster extends JLabel {

	private int x;
	private int y;
	private boolean left; // 사용
	private boolean right;

	private ImageIcon monster;

	// 생성자
	public Monster() {
		initObject();
		initSetting();
		addEventListener();
	}

	// 이미지
	private void initObject() {
		monster = new ImageIcon("images/monster1.png");
	}

	// 위치, 크기 설정
	private void initSetting() {
		x = 30;
		y = 650;
		setIcon(monster);
		setLocation(x, y);
		setSize(200, 200);

	}

	private void addEventListener() {
		setIcon(monster);
		new Thread(new Runnable() {
			boolean direction = true;

			@Override
			public void run() {
				setIcon(monster);
				while (true) {
					if (x == 0) {
						direction = true;
					}
					if (x == 1300) {
						direction = false;
					}
					if (direction) {
						x = x + 10;
						setLocation(x, y); //!!!
					} else {
						x = x - 10;
						setLocation(x, y);
					}
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {

						e.printStackTrace();
					}
				}

			}
		}).start();
	}

}
