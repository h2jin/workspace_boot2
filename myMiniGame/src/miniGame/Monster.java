package miniGame;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Monster extends JLabel{
	// TODO 초기 위치 설정???
	
	private int x;
	private int y;
	private boolean left; // 사용
	private boolean right;
	
	
	private ImageIcon monster;
	
	public Monster() {
		initObject();
		initSetting();
		addEventListener();
	}
	private void initObject() {
		monster = new ImageIcon("images/enemyR.png");
	}
	private void initSetting() {
		setIcon(monster);
		setLocation(x, 800);
		setSize(150, 150);
		
	}
	private void addEventListener() {
		new Thread(new Runnable() {
			
			boolean direction = true;
			@Override
			public void run() {
//				setIcon(monster);
				while (true) {
					if(direction) {
						x = x + 10;
					} else {
						x = x - 10;
					}
					if(x == 400) {
						direction = false;
					}
					if (x == 10) {
						direction = true;
					}
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
				}
				
			}
		}).start();
	} 
	
	
	
	

}
