package tenco.com.test_17;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Player extends JLabel implements Moveable {

	// 위치 상태
	private int x;
	private int y;
	
	// 플레이어의 방향 
	private PlayerWay playerWay;
	

	// 움직임 상태
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;


	// 플레이어 속도 상태
	private final int SPEED = 4;
	private final int JUPMPSPEED = 2;

	// 벽에 충돌한 상태
	private boolean leftWallCrash;
	private boolean rightWallCrash;

	private ImageIcon playerR;
	private ImageIcon playerL;

	public Player() {
		initObject();
		initSetting();
		initBackgroundPlayerService();
	}

	private void initBackgroundPlayerService() {
		new Thread(new BackgroundPlayerService(this)).start();// runnable 타입으로 만들었기 때문에 넣을 수 있다.
		// 플레이어가 자기자신이기 때문에 this 넣어줌.

	}

	private void initObject() {
		playerR = new ImageIcon("images/playerR.png");
		playerL = new ImageIcon("images/playerL.png");

	}

	private void initSetting() {
		x = 80;
		y = 535;

		left = false;
		right = false;
		up = false;
		down = false;

		leftWallCrash = false;
		rightWallCrash = false;
		playerWay = PlayerWay.RIGHT;

		setIcon(playerR);
		setSize(50, 50);
		setLocation(x, y); // 위치만

	}

	// 이벤트 핸들러
	@Override
	public void left() {
		playerWay = PlayerWay.LEFT;
		System.out.println(playerWay);
		left = true;
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (left) {
					setIcon(playerL);
					x = x - SPEED;
					setLocation(x, y);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {

						e.printStackTrace();
					} // 0.01초
				}

			}
		}).start();
		;

	}

	@Override
	public void right() {
		System.out.println("right");
		playerWay = PlayerWay.RIGHT;
		right = true;

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (right) {
					setIcon(playerR);
					x = x + SPEED;
					setLocation(x, y);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {

						e.printStackTrace();
					}
				}

			}
		}).start();
		;

	}

	// left + up, right + up
	@Override
	public void up() {
		System.out.println("up");
		up = true;

		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < (130 / JUPMPSPEED); i++) {
					y = y - JUPMPSPEED;
					setLocation(x, y);
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
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
//		System.out.println("down");
		down = true;
		new Thread(new Runnable() {

			@Override
			public void run() {

				while (down) {
					y = y + JUPMPSPEED;
					setLocation(x, y);
					try {
						Thread.sleep(5);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				down = false;

//				for (int i = 0; i < (130 / JUPMPSPEED); i++) {
//					y = y + JUPMPSPEED;
//					setLocation(x, y);
//					try {
//						Thread.sleep(3);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}

			}
		}).start();

	}


	//메서드 이름 동사+명사 많이 활용. 메서드는 객체의 행위이기 때문에
	public Bubble attackBubble() {
		return new Bubble(this);
	}

}// end of class
