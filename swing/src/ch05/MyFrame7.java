package ch05;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JTextArea;

//키보드 이벤트
public class MyFrame7 extends JFrame implements KeyListener {
	
	private JTextArea textArea;
	
	public MyFrame7() {
		initData();
		setInitLayout();
		addEventListener();
	}
	
	private void initData() {
		setTitle("키 이벤트 연습");
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		textArea = new JTextArea(); //한줄이 아니라 많이 적을 수 있는
	}
	private void setInitLayout() {
		setVisible(true);
		add(textArea);
		
	}
	private void addEventListener() {
		textArea.addKeyListener(this);
	}
	
	

	// 문자 키에만 가능하다
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	//키보드를 눌렀을 때 반응
	@Override
	public void keyPressed(KeyEvent e) {
//		char c = e.getKeyChar();
//		int keycode = e.getKeyCode();
//		System.out.println("c :" + c);
//		System.out.println("keycode : " + keycode);
//		// 도전과제 
//		// textArea의 키코드 그리고 char c 값을 보이게 코딩해 주세요.
//		textArea.append("/n" + "c : " + c + " : " + "keycode" + keycode );
		
		// 콘솔창에 왼쪽 화살표, 오른쪽 , 아래, 위 구분해서 출력해주기
		
		int keycode = e.getKeyCode();
		if (keycode == KeyEvent.VK_UP) {
			System.out.println("위쪽 화살표를 클릭했습니다.");
		} else if (keycode == KeyEvent.VK_LEFT) {
			System.out.println("왼쪽 화살표를 클릭했습니다.");
		} else if (keycode == KeyEvent.VK_DOWN) {
			System.out.println("아래쪽 화살표를 클릭했습니다.");
		} else if (keycode == KeyEvent.VK_RIGHT) {
			System.out.println("오른쪽 화살표를 클릭했습니다.");
		}
		
		
	}

	// 키보드를 눌렀다가 떼었을 때 반응
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		new MyFrame7();
	}
	
	

}
