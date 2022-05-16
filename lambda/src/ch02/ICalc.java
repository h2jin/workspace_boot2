package ch02;

@FunctionalInterface
// 이 인터페이스는 람다 표현식으로 사용하기 위한 인터페이스라고 알려줌.
// 그래서 하나 이상의 추상메서드를 만들면 오류나게 됨
public interface ICalc {
	
	int calc(int x, int y);
//	int sub(int x, int y);

}
