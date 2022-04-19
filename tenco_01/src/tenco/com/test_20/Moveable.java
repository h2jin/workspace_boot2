package tenco.com.test_20;

/* 
 * 인터페이스에서 default를 사용하면 인터페이스도 몸체가 있는 메서드를 만들 수 있다.
 * 다중 상속이 안되기 때문에 추가되었다.
 * 어뎁터 패턴보다는 좀 더 유연한 코드를 작성할 수 있다.
 * 
 */

public interface Moveable {
	// public abstract 생략되어 있음.
	void left();
	void right();
	void up();
	default public void down() {}; 
	

}
