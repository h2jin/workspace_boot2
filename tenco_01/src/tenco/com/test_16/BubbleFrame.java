package tenco.com.test_16;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

// 단일 상속만 되기 때문에 MoveableAdapter 상속받을 수 없음.
// 어뎁터 --> 알맞게 전압 수정--> 필터와 같다. 30v 를 10v로 변환. 이런식으로 설계를 수정할 수 있다. 
public class BubbleFrame extends JFrame {

	// 스페이스바를 눌렀을 때 버블이 나오는 기능
	// 버블 하나의 객체

	// 메모리에서 버블을 지워주기 1단계
	private BubbleFrame mContext = this;
	// 멤버변수에 자기자신을 선언하고 this 넣어줌.

	private JLabel backgroundMqp;
	public Player player;

	public BubbleFrame() {
		initObject();
		initSetting();
		initListener();
		setVisible(true); // 마지막에 보여주도록 뒤로 뺌.
		// 실행 순서 스페이스바 누르면 생성자 메서드 호출되고 initThread 에 가서 플레이어 상태 확인 후
		// 레프트 라이트 메서드 구현
	}

	private void initObject() {
		backgroundMqp = new JLabel(new ImageIcon("images/backgroundMap.png"));
		// 예외처리 안해줘도 되지만 오류가 찍히지 않아 디버깅하기가 힘들다.
		setContentPane(backgroundMqp);
		player = new Player();
		add(player);

	}

	private void initSetting() {
		setSize(1000, 640);
		setLayout(null); // absolute (좌표값으로 자유롭게 그림을 그릴 수 있다.)

		setLocationRelativeTo(null); // JFrame 가운데 배치하기
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	private void initListener() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
//				System.out.println(e.getKeyCode());

				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					if (!player.isLeft() && !player.isLeftWallCrash()) {
						player.left(); // isleft가 아닐 경우에만 player.left가 됨.
					}
					break;
				case KeyEvent.VK_RIGHT:
					if (!player.isRight() && !player.isRightWallCrash()) {
						player.right();

					}
					break;
				case KeyEvent.VK_UP:
					if (!player.isUp() && !player.isDown()) {
						player.up();
					}
					break;
				case KeyEvent.VK_SPACE:
//					Bubble bubble = new Bubble(player);
					Bubble bubble = new Bubble(mContext);
					add(bubble); // 생성만 한 상태

				default:
					break;
				}
			}// end of keyPressed

			// 키보드 해제 이벤트 처리
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
			}// key released

		});
	}

	public static void main(String[] args) {
		new BubbleFrame();
	}

}
