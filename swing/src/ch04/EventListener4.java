package ch04;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class EventListener4 extends JFrame implements MouseListener{

	private JLabel label1;
	
	public EventListener4() {
		initData();
		setInitLayout();
		addEventListener();
	}
	
	private void initData() {
		setTitle("마우스 이벤트 확인");
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		label1 = new JLabel("hello java ~~~~~~~~~~~~"); //'....' --> white space
	}
	
	private void setInitLayout() {
		setVisible(true);
		setLayout(null);
		add(label1);
		label1.setSize(100, 100);
		label1.setLocation(100, 100);
		
	}
	private void addEventListener() {
		this.addMouseListener(this);
	}
	
	
	
	//마우스가 클릭되었을 때 호출
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("x 좌표값: " + e.getX());
		System.out.println("y 좌표값: " + e.getY());
		
		System.out.println(label1.getBounds()); //이동
		System.out.println("label width: " + label1.getBounds().width);
		System.out.println("label height: " + label1.getBounds().height);
		// 문제: 현재 간격을 최소화로 만들어서 정확성을 높여보자
		// 수정해서 만들어 보자
		System.out.println("get Height() :" + getHeight());
		label1.setLocation(e.getX()-label1.getSize().width, e.getY()-label1.getSize().height); // ??
	}
	
	// 마우스가 눌러졌을 때 호출 
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	//마우스가 떨어졌을 때 호출 (눌렀다가 떼었을 때)
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	//마우스가 어떤 영역 안으로 들어왔을 때 호출
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	// 마우스가 떤 영역 밖으로 나갔을 때 호출
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	//메인 함수
	public static void main(String[] args) {
		new EventListener4();
	}//end of main
	
	
}// end of class
