package ch01;

public class MainTest2 {

	public static void main(String[] args) {
		
		ICalc iCalAdd = new ICalc() {
			
			@Override
			public double calc(int a, int b, int c) {
				return a + b + c;
			}
		};
		
		ICalc iCalMinus = new ICalc() {
			
			@Override
			public double calc(int a, int b, int c) {
				return a - b - c;
			}
		};
		
		// 인터페이스의 유연함 --> 사용자가 기능 직접 구현할 수 있도록 해줌
		
		ICalc iCalMulti = new ICalc() {
			
			@Override
			public double calc(int a, int b, int c) {
				return a * b * c;
			}
		};
		
		System.out.println(iCalAdd.calc(1, 1, 1));
		System.out.println(iCalMinus.calc(1, 1, 1));
		System.out.println(iCalMulti.calc(1, 1, 1));
		
		//문제 1 -> 람다표현식으로 만들어서 사용해보기
		ICalc iAddLambda = (a, b, c) -> {
			return a + b + c;
		};
		
		// 실행문이 한 문장인 경우 중괄호와 return 키원드를 생략할 수 있다. 
		// 가독성 더 떨어질 수 있지만 코드를 많이 생략할 수 있음.
		ICalc iMinusLambda = (a, b, c) ->  a - b - c;
		
		ICalc iMultiLambda = (a, b, c) -> {
			return a * b * c;
		};
		
		System.out.println(iAddLambda.calc(3, 6, 9));
		System.out.println(iMinusLambda.calc(3, 6, 9));
		System.out.println(iMultiLambda.calc(3, 6, 9));
		
		
	}

}
