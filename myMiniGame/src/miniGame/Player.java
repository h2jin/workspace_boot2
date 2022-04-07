package miniGame;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Player extends JLabel implements Moveable {
	// 위치 상태
	private int x;
	private int y;

	// 이미지 변수 선언
	private ImageIcon player;

	// 움직임 상태
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;

	// 플레이어 속도 상태
	private final int SPEED = 4;
	private final int JUMPSPEED = 2;

	// get, set
	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	// 생성자
	public Player() {
		initObject();
		initSetting();
	}

	// 이미지
	private void initObject() {
		player = new ImageIcon("images/warrior1.png");
	}

	// 위치, 크기 설정
	private void initSetting() {

		x = 40;
		y = 150;

		left = false;
		right = false;
		up = false;
		down = false;
		
		setLocation(x, y);
		setSize(500, 500);
		setIcon(player);

	}

	// 이벤트 핸들러
	@Override
	public void left() {
		System.out.println("left");
		left = true;
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (left) {
					setIcon(player);
					x = x - SPEED;
					setLocation(x, y);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		}).start();
	}

	@Override
	public void right() {
		System.out.println("right");
		right = true;
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (right) {
					setIcon(player);
					x = x + SPEED;
					setLocation(x, y);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		}).start();

	}

	@Override
	public void up() {
		System.out.println("up");
		up = true;
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i = 0; i < 100; i ++) {
					y = y - JUMPSPEED;
					setLocation(x, y);
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				up = false;
				down();
				
			}
		}).start();

	}

	@Override
	public void down() {
		System.out.println("down");
		down = true;
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i = 0; i < 100; i++) {
					y = y + JUMPSPEED;
					setLocation(x, y);
					try {
						Thread.sleep(3);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} 
				down = false;
				
			}
		}).start();

	}

}
