package ch04;

public class MainTest {
	// 주소연결 생성자를 통해 하지 않고 메서드 방식으로 하기
	// 실무에서는 이 방법을 많이 사용함.
	// 옵저버 패턴 중에 하나 - 관찰하고 있다가 신호가 오면 알려줌..
	public static void main(String[] args) {
		Activity1 activity1 = new Activity1("화면1");
		Activity2 activity2 = new Activity2("화면 2");
		
		//메서드를 통해서 콜백 연결하기
		activity2.setCallbackCheckPosition(activity1.callback);
	}

}
