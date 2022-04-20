package ch04;

import java.awt.Color;

public class Activity1 extends BaseActivity {
	
	// 변수선언, 초기화
	
	// 익명구현 객체 --> 변수의 선언과 초기화 동시에 해줌.
	CallbackCheckPosition callback = new CallbackCheckPosition() {
		
		@Override
		public void checkPosition(int x, int y) {
			System.out.println(name + "가 콜백을 받았습니다." + x);
			System.out.println(name + "가 콜백을 받았습니다." + y);
			
		}
	};
	
	public Activity1(String name) {
		super(name);
		
	}
	
	@Override // <-- 주석이지만 어노테이션. 컴파일러 무시하지 않음
	// 컴파일러가 베이스액티비티에 가서 initData 메서드가 있는지 확인해봄.
	protected void initData() {
		super.initData();
//		setTitle(name);
//		setSize(500, 500);
	}
	
	@Override
	// 부모 클래스의 메서드 실행하고 자신이 추가한 기능 실행해줌
	protected void setInitLayout() {
		super.setInitLayout();
		panel.setBackground(Color.blue);
	}
	

}
