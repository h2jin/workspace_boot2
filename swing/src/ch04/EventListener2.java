package ch04;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class EventListener2 extends JFrame implements ActionListener{
	
	private JButton button1;
	private JButton button2;
	
	public EventListener2() {
		initData();
		setInitLayout();
		addEventListener();
		
	}
	
	private void initData () {
		setTitle("이벤트 리스너 연습2");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 500);
		button1 = new JButton("button1");
		button2 = new JButton("button2");
		
	}
	
	private void setInitLayout() {
		setVisible(true);
		setLayout(new FlowLayout(FlowLayout.CENTER, 20,20));
		add(button1);
		add(button2);
	}
	
	private void addEventListener() {
		button1.addActionListener(this);
		button2.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
//		System.out.println(e.toString()); //주소값을 출력
//		1. getActionCommand 는 String을 반환한다.
//		System.out.println(e.getActionCommand() + "버튼이 클릭되었습니다.");
//		2. 
//		if(e.getActionCommand().equals(button1.getText())) {
//			System.out.println("버튼1이 클릭되었습니다.");
//		} else {
//			System.out.println("버튼2가 클릭되었습니다.");
//		}
		// 3. getSource 오브젝트를 반환 
//		Object obj = e.getSource();
		//하지만 우리는 여기서 오는 Object ---> JButton 파악 가능!
		JButton selectedbutton = (JButton)e.getSource(); //다운캐스팅
		if(selectedbutton.getText().equals(this.button1.getText())) {
			System.out.println(button1.getText() + "가 클릭되었습니다.");
		} else {
			System.out.println(button2.getText() + "가 클릭되었습니다.");
		}
		
		
		
//		if(button1.equals(e.getSource())) {
//			System.out.println("버튼1이 클릭되었습니다.");
//		} else {
//			System.out.println("버튼 2가 클릭되었습니다.");
//		}
		//문제1 
		// 버튼1이 눌러졌는지 버튼 2가 눌러졌는지 구분해서 화면에 출력하세요.
		
		
		
	}

}
