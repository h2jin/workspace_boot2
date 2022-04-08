package tenco.com.test_09;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

// 메인스레드는 바쁨 --> 키보드 이벤트 처리해야하기 떄문에
// 백그라운드에서 계속 관찰
public class BackgroundPlayerService implements Runnable {

	private BufferedImage image;
	private Player player;

	public BackgroundPlayerService(Player player) { // 메서등에 참조타입으로 넣어주기. 컴포지션
		this.player = player;

		try {
			image = ImageIO.read(new File("images/backgroundMapService.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// 도전과제 - 던지거나 처리 (프레임 벗어나면 예외처리 문제 해결하기)
		// 색상 확인
		while (true) { // 한번 실행하고 죽기 때문에 while문에 넣어줘야함.
			try {
				Color leftColor = new Color(image.getRGB(player.getX() + 10, player.getY()+ 25));
				Color rightColor = new Color(image.getRGB(player.getX() + 60, player.getY()+ 25));
				
				if(leftColor.getRed() == 255 && leftColor.getGreen() == 0 && leftColor.getBlue() == 0) {
					System.out.println("왼쪽 벽에 충돌했다.");
					player.setLeftWallCrash(true);
					player.setLeft(false);
				} else if (rightColor.getRed() == 255 && rightColor.getGreen() == 0 && rightColor.getBlue() == 0) {
					System.out.println("오른쪽 벽에 충돌했다.");
					player.setRightWallCrash(true);
					player.setRight(false);
				} else {
					player.setLeftWallCrash(false);
					player.setRightWallCrash(false);
				} 
				// else 문을 하지 않으면 오른쪽 벽에 출동하는 순간 작동하지 않음.
				//다시 실행을 시켜줘야함!
				
				
				System.out.println("=========================");
				System.out.println("왼쪽 색상 : " + leftColor);
				System.out.println("오른쪽 색상 : " + rightColor);
				System.out.println("=========================");
			} catch (Exception e) {
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
	
	

}
