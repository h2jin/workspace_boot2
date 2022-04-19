package tenco.com.test_18;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Enemy extends JLabel implements Moveable {
	
	private BubbleFrame mContext;

	// 위치 상태
	private int x;
	private int y;
	
	// 플레이어의 방향 
	private EnemyWay enemyWay;
	

	// 움직임 상태
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;


	// 적군 속도 상태
	private final int SPEED = 2;
	private final int JUPMPSPEED = 1;

	// 벽에 충돌한 상태
	private boolean leftWallCrash;
	private boolean rightWallCrash;

	private ImageIcon enemyR;
	private ImageIcon enemyL;

	public Enemy(BubbleFrame mContext) {
		this.mContext = mContext;
		initObject();
		initSetting();
		//initBackgroundPlayerService();
	}

	private void initBackgroundPlayerService() {
		//new Thread(new BackgroundPlayerService(this)).start();// runnable 타입으로 만들었기 때문에 넣을 수 있다.
		// 플레이어가 자기자신이기 때문에 this 넣어줌.

	}

	private void initObject() {
		enemyR = new ImageIcon("images/enemyR.png");
		enemyL = new ImageIcon("images/enemyL.png");

	}

	private void initSetting() {
		x = 500;
		y = 180;

		left = false;
		right = false;
		up = false;
		down = false;

		leftWallCrash = false;
		rightWallCrash = false;
		enemyWay = EnemyWay.LEFT;

		setIcon(enemyL);
		setSize(50, 50);
		setLocation(x, y); // 위치만

	}

	// 이벤트 핸들러
	@Override
	public void left() {
		enemyWay = EnemyWay.LEFT;
		left = true;
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (left) {
					setIcon(enemyL);
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
		enemyWay = EnemyWay.RIGHT;
		right = true;

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (right) {
					setIcon(enemyR);
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


}// end of class
