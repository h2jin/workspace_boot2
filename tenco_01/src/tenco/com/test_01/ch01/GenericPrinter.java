package tenco.com.test_01.ch01;

public class GenericPrinter <T>{
	
	// 보통 T라는 대체문자를 사용한다, E - element, K - Key, V - value (사실 아무 문자나 상관 없음)
	//T 를 자료형으로 선언 할 수 있다.
	private T material; //T 자료형으로 선언한 변수

	public T getMaterial() {
		return material;
	}

	public void setMaterial(T material) {
		this.material = material;
	}
	

}
