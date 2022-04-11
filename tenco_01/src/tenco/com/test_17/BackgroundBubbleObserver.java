package tenco.com.test_17;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

//스레드로 만들지 않을 것. -> 스레드로 설계하는 경우 느려짐. 계속 버블이 만들어지기 때문에

public class BackgroundBubbleObserver {
	
	private static int LEFT_XPOINT = 0;
	private static int RIGHT_XPOINT = 60;
	private static int CENTER_TOP = 25;
	private BufferedImage image;
	private Bubble bubble;
	
	// Color 변수를 멤버변수로 만드는게 좋은가?
	// 지역변수를 사용하는 것을 추천.
	// 리펙토링 과정 연습
	
	public BackgroundBubbleObserver(Bubble bubble) {
		this.bubble = bubble;
		
		try {
			image = ImageIO.read(new File("images/backgroundMapService.png"));
		} catch (IOException e) {
			System.out.println("파일이 없습니다.");
		}
	}
	
	
	public boolean checkLeftWall() {
		return isCrashColor(LEFT_XPOINT);
	}
	public boolean checkRightWall() {
		return isCrashColor(RIGHT_XPOINT);
	}
	
	public boolean checkTopWall() {
		return isCrashColor(CENTER_TOP); //어차피 리턴값을 주기 때문에 
	}
	// 점점 코드를 줄일 수 있다.
	
	// boolean 타입의 경우 이름을 is~~로 많이 설정함
	private boolean isCrashColor(int correctionPoint) {
		Color topColor = new Color(image.getRGB(bubble.getX() + correctionPoint, bubble.getY()));
		if(topColor.getRed() == 255 && topColor.getGreen() == 0 && topColor.getBlue() ==0) {
			return true;
		}
		return false;
	}

}
