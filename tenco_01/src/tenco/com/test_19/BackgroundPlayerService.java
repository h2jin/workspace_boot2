package tenco.com.test_19;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

// 메인스레드는 바쁨 --> 키보드 이벤트 처리해야하기 떄문에
// 백그라운드에서 계속 관찰
public class BackgroundPlayerService implements Runnable {

	private BufferedImage image;
	private Player player;
	private List<Bubble> bubbles = new ArrayList<Bubble>();

	public BackgroundPlayerService(Player player) { // 메서등에 참조타입으로 넣어주기. 컴포지션
		this.player = player;
		this.bubbles = player.getBubbles();

		try {
			image = ImageIO.read(new File("images/backgroundMapService.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() throws ArrayIndexOutOfBoundsException {
		// 도전과제 - 던지거나 처리 (프레임 벗어나면 예외처리 문제 해결하기)
		// 색상 확인
		while (true) {

			// 버블 출동 체크
			for (int i = 0; i < bubbles.size(); i++) {
				Bubble targetBubble = bubbles.get(i);
				if (targetBubble.getState() == 1) {

					if ((Math.abs(player.getX() - targetBubble.getX()) < 10)
							&& (Math.abs(player.getY() - targetBubble.getY()) > 0)
							&& (Math.abs(player.getY() - targetBubble.getY()) < 50)) {
						
						System.out.println("플레이어와 물방울 충돌");
						
						new Thread(new Runnable() {
							
							@Override
							public void run() {
								targetBubble.removeBubble();								
							}
						}).start();
						break;
					}

				}
			}

			// 한번 실행하고 죽기 때문에 while문에 넣어줘야함.
			Color leftColor = new Color(image.getRGB(player.getX() + 10, player.getY() + 25));
			Color rightColor = new Color(image.getRGB(player.getX() + 60, player.getY() + 25));

//				Color bottonColor = new Color(image.getRGB(player.getX() + 25, player.getY()+ 60));
			int bottomColor = image.getRGB(player.getX() + 10, player.getY() + 50 + 5)
					+ image.getRGB(player.getX() + 50, player.getY() + 50 + 5); // 흰색이면 -1
//			System.out.println(bottomColor);

//				System.out.println("바닥 색상: " + bottomColor);
			// -1이 아니라는 것은 빨간색이거나 파란색이다.
			if (bottomColor != -2) {
//				System.out.println("바닥컬러 : " + bottomColor);
				player.setDown(false);

			} else {
				// 점프하는 순간 down 메서드가 호출
				if (!player.isUp() && !player.isDown()) { // 점프한 후 내려가다가 바닥이 닿아도 계속 내려가게됨.
					// 플레이어가 점프하거나 내려가는 중이 아닐 때 한번 호출해라
					player.down();
				}
			}

			if (leftColor.getRed() == 255 && leftColor.getGreen() == 0 && leftColor.getBlue() == 0) {
//				System.out.println("왼쪽 벽에 충돌했다.");
				player.setLeftWallCrash(true);
				player.setLeft(false);
			} else if (rightColor.getRed() == 255 && rightColor.getGreen() == 0 && rightColor.getBlue() == 0) {
//				System.out.println("오른쪽 벽에 충돌했다.");
				player.setRightWallCrash(true);
				player.setRight(false);
			} else {
				player.setLeftWallCrash(false);
				player.setRightWallCrash(false);
			}
			// else 문을 하지 않으면 오른쪽 벽에 출동하는 순간 작동하지 않음.
			// 다시 실행을 시켜줘야함!

			try {
				Thread.sleep(3);
				// 캐릭터가 이동될 때 값을 못찾는 경우가 있다.
				// 이동속도보다 더 빠르게 움직여야 해결 가능
			} catch (InterruptedException e) {
				e.printStackTrace();
			} // 스레드 try catch
		}

	}

}
