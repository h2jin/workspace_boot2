package tenco.com.test_20;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Bubble extends JLabel implements Moveable {

	private BubbleFrame mContext;
	private Bubble bubbleContext = this;

	private Player player;
	private BackgroundBubbleObserver backgroundBubbleObserver;
	// 많을 경우 Arraylist 사용
	private Enemy enemy;
	// 굳이 생성자에 하지 않고 new 로 생성.

	private int x;
	private int y;

	private boolean left;
	private boolean right;
	private boolean up;

	private int state; // 0이면 기본, 1이면 적군 가둔 상태

	private ImageIcon bubble; 
	private ImageIcon bubbled; 
	private ImageIcon bomb; 

	public Bubble(BubbleFrame mContext) {
		this.mContext = mContext; // 의존 주입
		this.player = mContext.getPlayer();
		initObject();
		initSetting();
		initThread();
		this.enemy = mContext.getEnemy();
	}

	private void initObject() {
		bubble = new ImageIcon("images/bubble.png");
		bubbled = new ImageIcon("images/bubbled.png");
		bomb = new ImageIcon("images/bomb.png");
		backgroundBubbleObserver = new BackgroundBubbleObserver(this);
	}

	private void initSetting() {
		left = false;
		right = false;
		up = false;

		x = player.getX();
		y = player.getY();

		setIcon(bubble);
		setSize(50, 50);

		state = 0;

	}

	private void initThread() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				if (player.getPlayerWay() == PlayerWay.LEFT) {
					left();
				} else {
					right();
				}

			}
		}).start();
	}

	@Override
	public void left() {
		left = true;
		for (int i = 0; i < 250; i++) {
			x--;
			setLocation(x, y);
			if (backgroundBubbleObserver.checkLeftWall()) {
				left = false;
				break;
			}
//
			if ((Math.abs(x - enemy.getX()) < 10)
					&& (Math.abs(y - enemy.getY()) > 0) && (Math.abs(y - enemy.getY()) < 50)) {
				System.out.println("물방울이 적군과 충돌");
				if(enemy.isLive()) {
					attack();					
					break;
				} 
			}
			ThreadSleep(1);
		}
		left = false;
		up();

	}

	@Override
	public void right() {
		right = true;
		for (int i = 0; i < 250; i++) {
			x++;
			setLocation(x, y);
			if (backgroundBubbleObserver.checkRightWall()) {
				right = false;
				break;	
			}
			//
			if ((Math.abs(x - enemy.getX()) < 10)
					&& (Math.abs(y - enemy.getY()) > 0) && (Math.abs(y - enemy.getY()) < 50)) {
				System.out.println("물방울이 적군과 충돌");
				if(enemy.isLive()) {
					attack();					
					break;
					
				} 
			}
			
			ThreadSleep(1);
		}
		right = false;
		up();

	}

	@Override
	public void up() {
		up = true;
		while (up) {
			y--;
			setLocation(x, y);
			if (backgroundBubbleObserver.checkTopWall()) {
				up = false;
				break;
			}
			
			if(state ==0) {
				try {
					Thread.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				try {
					Thread.sleep(10); // 적을 가둔 상태면 속도가 더 늦도록하기.
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		up = false;
		if(state == 0) {			
			removeBubble();
		} // 적군에 맞지 않았을 때만 removeBubble 메서드가 실행된다.
	}

	public void removeBubble() {
		try {
				Thread.sleep(2000);
				setIcon(bomb);
				Thread.sleep(500);
				bubbleContext = null; // 자바의 garbage Collection 필요없는 것은 메모리에서 삭제해줌.
				setIcon(null); // 전체그림 다시그리지 않고, 자기자신만 다시 그림.
				// 메모리에서 지우기, 다시 그림을 그려라 JFrame을 지워야한다..
//			mContext.remove(this); // 메모리에서 제거
//			mContext.repaint(); // 도화지에서 그림을 다시 그림. 
				// remove하지 않고 repaint만 하면 아직 메모리에 살아있음
				
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void attack() {
		state = 1;
		setIcon(bubbled);
		enemy.setLive(false); // 변수 선언하여 바꿔주면 가비지 컬렉터 바로 작용
		// 적군이 살아있다, 죽었다 -> 상태값 부여
		mContext.remove(enemy); // 메모리에 지우기 --> 사라지지만 버그 존재. 없어졌지만 적군 여전히 존재하는 것처럼 작용..
		mContext.repaint();
		// 자바의 가비지 컬렉터가 발생되지 않음.
		
		
	}
	
	private void ThreadSleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
