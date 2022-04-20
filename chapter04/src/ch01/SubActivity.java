package ch01;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

// 호출자 or 콜리 (신호를 주는 객체) : 징검다리 역할을 하는 인터페이스를 멤버 변수로 먼저 선언한다.
public class SubActivity extends JFrame{
	
	JButton button1;
	//- 버튼 하나 
	JButton button2;
	JButton button3;
	int result = 999;
	

	// 값을 전달하는 버튼 하나
	ICallbackBtnAction callbackBtnAction; //일단 선언만
	
	// 콜리는 콜백받는 객체의 주소값을 알고 있어야 메서드가 서브액티비티에서 호출되었다고 알릴 수 있다.
	public SubActivity(ICallbackBtnAction iCallbackBtnAction) {
		
		// 하지만 (new 하지 않음 --> nullpointException)
		this.callbackBtnAction = iCallbackBtnAction; //매개 변수로 들어오는 주소값을 넣어준다.
		
		setSize(300, 300);
		setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		button1 = new JButton("더하기 버튼 + ");
		button2 = new JButton("빼기 버튼 ");
		button3 = new JButton("값을 전달하는 버튼");
		add(button1);
		add(button2);
		add(button3);
		button1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("더하기 버튼 클릭.");
				callbackBtnAction.ClickedAddBtn();

				
			}
		});
		button2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				callbackBtnAction.ClickedMinusButton();
				
			}
		});
		button3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				callbackBtnAction.passValue(result);
				
			}
		});
		
		
		
	}

}
