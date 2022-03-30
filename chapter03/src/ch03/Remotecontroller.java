package ch03;

public interface Remotecontroller {
	// 구현된 것이 아무것도 없는 밑그림만 있는 기본 설계도
	// 멤버 변수. 일반 메서드를 가질 수 없고 오직 추상메서드와 상수만을
	// 멤버로 가질 수 있다.

	// 추상클래스보다 추상화가 더 높다.
	// 인터페이스: 강제성이 있는 약속이다., 표준, 규칙, 규약

	// 사용 방법
	// class 키워드 대신에 interface 라는 키워드 사용한다.
	// class와 마찬가지로 접근제어 지시자로 public default 사용할 수 있다.

	// 원형의 모습
	public static final int SERIAL_NUMBER = 1000; // 상수

	public abstract void turnOn();

	// 축약형
	int SERIAL_NUMBER2 = 100; // 멤버변수 가질 수 없으므로 축약형으로도 상수인 것을 알 수 있다. public static final 생략됨

	void turnOff(); // public abstract 생략되어 있음

}
