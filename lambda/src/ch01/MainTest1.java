package ch01;

public class MainTest1 {

	public static void main(String[] args) {
		// 익명구현 클래스 방법
		IAdd iAdd = new IAdd() {

			@Override
			public int add(int x, int y) {
				return x + y;
			}
		};

		// 사용방법
		System.out.println(iAdd.add(10, 20));
		// 원래 사용했던 익명구현 클래스를 사용하는 방법

		// 람다식으로 변경한다면? --> 표현하는 식일 뿐. 간소화하는 것임.
		// 가능한 이유는? 타입추론이 가능하기 때문
		IAdd iAddLambda = (x, y) -> {
			return x + y;
		};

		// 사용하는 방법
		System.out.println(iAddLambda.add(10, 20));

		// 결론
		// 기본 OOP문법 : 인터페이스를 선언 --> 익명 구현객체로 만들어서 활용 --> 사용
		// 람다 표현식 : 인터페이스 선언 --> 람다 표현식으로 변경 --> 사용

	}

}
