package ch02;

public class MainTest2 {
	
	public static void main(String[] args) {
		
		ICalc iMaxRambda = (x, y) -> {
			int a = 0;
			if(x > y) {
				a =  x;
			} else if (y > x) {
				a = y;
			} else {
				System.out.println("두 수가 같습니다.");
			}
			return a;
		};
		
		System.out.println(iMaxRambda.calc(7, 10));
		
		IMaxNumber iMaxNumber = (x, y) -> x > y ? x : y;
		System.out.println(iMaxNumber.getMax(100, 1000));
		
	}

}
