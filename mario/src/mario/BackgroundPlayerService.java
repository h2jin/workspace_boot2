package mario;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import lombok.Data;

@Data
public class BackgroundPlayerService implements Runnable {

	private BufferedImage bgImage;
	private Player player;

	private int playerLeftRightY;
	private int playerRightX;
	private int playerBottomX;
	private int playerBottomY;
	private int playerTopX;
	
	private int crashX;
	private int crashY;
	
	private boolean isWin;

	public BackgroundPlayerService(Player player) {
		this.player = player;
		try {
			bgImage = ImageIO.read(new File("images/MapService.png"));
		} catch (IOException e) {
			System.out.println("파일이 없습니다.");
		}
		initSetting();
	}

	private void initSetting() {
		playerLeftRightY = 50;
		playerRightX = 50;
		playerBottomX = 25;
		playerBottomY = 60;
		playerTopX = 25;
		
		crashX = 0;
		crashY = 0;
		
		isWin = false;
	}

	@Override
	public void run() {
		while(true) {
			try {
				Color leftColor = new Color(bgImage.getRGB(player.getX(), player.getY() + playerLeftRightY));
				Color rightColor = new Color(bgImage.getRGB(player.getX() + playerRightX, player.getY() + playerLeftRightY));
				Color bottomColor = new Color(bgImage.getRGB(player.getX() + playerBottomX, player.getY() + playerBottomY));
				Color topColor = new Color(bgImage.getRGB(player.getX() + playerTopX, player.getY()));
				
				// 바닥 색상 확인
				if(!(bottomColor.getRed() == 255 && bottomColor.getGreen() == 255 && bottomColor.getBlue() == 255)) {
					player.setDown(false);
				} else {
					if (!player.isUp() && !player.isDown()) {
						player.down();
					}
				}

				// 벽 넘지 못하게 확인
				if((leftColor.getRed() == 255 && leftColor.getGreen() == 0 && leftColor.getBlue() == 0)) {
					player.setLeftWallCrash(true);
					player.setLeft(false);
				} else if ((rightColor.getRed() == 255 && rightColor.getGreen() == 0 && rightColor.getBlue() == 0)) {
					player.setRightWallCrash(true);
					player.setRight(false);
				} else {
					player.setLeftWallCrash(false);
					player.setRightWallCrash(false);
				}
				
				if((leftColor.getRed() == 172 && leftColor.getGreen() == 172 && leftColor.getBlue() == 255)) {
					isWin = true;
				}
				
				if (topColor.getRed() == 0 && topColor.getGreen() == 0 && topColor.getBlue() == 0) {
					// 246 246 246
					System.out.println(topColor);
					player.setCrashOk(true);
					crashX = player.getX();
					crashY = player.getY();
				}
				if (!(topColor.getRed() == 255 && topColor.getGreen() == 255 && topColor.getBlue() == 255)) {
					player.setUp(false);
				}
				
			} catch (Exception e) {

			}
		}
	}
}