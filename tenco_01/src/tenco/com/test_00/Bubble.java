package tenco.com.test_00;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

//getter setter 만들어주는 것
public class Bubble extends JLabel implements Moveable {
	// down메서드 나오지 않음.

//	//2단계
//	private BubbleFrame mContext; //버블이 모든 주소값을 가지고있어야하나? - 아님.
	private Bubble bubbleContext = this;
	
	// 의존성 컴포지션 관계
	private Player player;
	private BackgroundBubbleObserver backgroundBubbleObserver;
	// 굳이 생성자에 안함. new 로 생성.

	// 위치 상태를 나타내는 변수
	private int x;
	private int y;

	// 움직임 상태
	private boolean left;
	private boolean right;
	private boolean up;

	// 적군을 맞춘 상태
	private int state; // 0이면 기본, 1이면 적군 가둔 상태

	private ImageIcon bubble; // 기본 물방울
	private ImageIcon bubbled; // 적을 가둔 물방울
	private ImageIcon bomb; // 물방울이 터진 상태

	// getter / setter

	// 생성자
	// 의존 주입 --> 보통 생성자에서 주입을 받는다
	// 타이트 커플링, 루즈 커플링

	public Bubble(Player player) {
		this.player = player;
		initObject();
		initSetting();
		initThread();
	}

//	public Bubble(BubbleFrame mContext) {
//		this.mContext = mContext;
//		this.player = mContext.player; //context정보 전달받음.
//		initObject();
//		initSetting();
//		initThread();
//	}

	private void initObject() {
		bubble = new ImageIcon("C:\\workspace_boot2\\tenco_01\\images/bubble.png");
		bubbled = new ImageIcon("C:\\workspace_boot2\\tenco_01\\images/bubbled.png");
		bomb = new ImageIcon("C:\\workspace_boot2\\tenco_01\\images/bomb.png");
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

		// 플레이어의 x,y 가지고 와야함.
		// 객체와 객체의 상호작용 이용 --> 생성자에서

	}

	private void initThread() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// 왼쪽방향으로 나가야하는지 오른쪽 방향으로 나가야 하는지
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
		// 상태값 변경 --> 다 끝난 후 초기화 해주는 것이 좋음. 나중에
		for (int i = 0; i < 400; i++) {
			x--;
			setLocation(x, y);
			// 현재 색상 계속 체크 --> 메서드 호출. 굳이 변수를 공용으로 쓰지 않기 때문에 지역변수가 더 낫다..
			if (backgroundBubbleObserver.checkLeftWall()) {
				left = false;
				break;
			}
			ThreadSleep(1);
		}
		left = false;
		up();

	}

	@Override
	public void right() {
		right = true;
		for (int i = 0; i < 400; i++) {
			x++;
			setLocation(x, y);
			if (backgroundBubbleObserver.checkRightWall()) {
				right = false;
				break;
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
			ThreadSleep(1);
		}
		up = false;
		// removeBubble(); 해줘야함.
		removeBubble();
	}

	public void removeBubble() {
		try {
			Thread.sleep(2000);
			setIcon(bomb);
			Thread.sleep(500);
			bubbleContext = null; //자바의 garbage Collection 필요없는 것은 메모리에서 삭제해줌.
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

	private void ThreadSleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
