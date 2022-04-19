package tenco.com.slider;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class SliderFrame extends JFrame{
	
	private JLabel bgMap;
	int pointX = 0;
	int pointY = 0;
	
	public SliderFrame() {
		initObject();
		initSetting();
		initListener();
	}
	
	private void initListener() {
		bgMap = new JLabel(new ImageIcon("images/backgroundMap.png"));
		
		setSize(400, 400);
		setVisible(true);
		setResizable(true);
		setLocationRelativeTo(this); //모니터의 정중앙에 프레임이 온다.
		
	}
	
	private void initSetting() {
		setContentPane(bgMap);
		
	}
	
	private void initObject() {
		addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				switch (keyCode) {
				case KeyEvent.VK_RIGHT:
					 new Thread(new Runnable() {
						
						@Override
						public void run() {
							
							for (int i = 0; i < 100; i++) {
								
								bgMap.setLocation(pointX, pointY);
								pointX++;
								try {
									Thread.sleep(1);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
							
						}
					}).start();;
					break;
				}
//				super.keyPressed(e);
				
			}
		});
		
	}
	
	public static void main(String[] args) {
		new SliderFrame();
	}
	
	

}
