package ch01;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

// 지금관계에서 콜백을 받는 객체(응답자) : IcallbackBtnAction 인터페이스를 구현해서 정의하면 된다.
public class MainActivity extends JFrame implements ICallbackBtnAction{
	
	int count;
	JLabel label;
	

	public MainActivity() {
		count = 0;
		label = new JLabel("count : " + count);
		setSize(300, 300);
		setLayout(new FlowLayout());
		setVisible(true);
		add(label);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		new SubActivity(this); // 다형성 성립! -> 인터페이스 받고 있기 때문에 
	}

	// 추상메서드 오버라이드
	@Override
	public void ClickedAddBtn() {
		System.out.println(" + 버튼에 콜백 받았습니다.");
		count++;
		label.setText("count : " + count);
	}

	@Override
	public void ClickedMinusButton() {
		System.out.println("-버튼에 콜백");
		count--;
		label.setText("count : " + count);
	}

	@Override
	public void passValue(int result) {
		System.out.println("버튼3 콜백");
		label.setText("result : " + result);
		
	}
	
	// - 버튼의 동작을 줄 콜백 메서드 정의
	// 값을 전달하는 버튼

}
