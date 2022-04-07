package tenco.com.test_02;

public class WrapperClass1 {

	public static void main(String[] args) {
		
		Integer num = new Integer(100); //박싱
		Number n1 = 10;
		
		int n = num.intValue(); // 언박싱
		int n2 = num; //오토박싱
		System.out.println(n);
		System.out.println("================");
		
		Integer num1 = 200; // 기본데이터타입 넣어도 되는 이유 컴파일러가 new Interger(200); 을 자동으로 해주기 때문.
		int num10 = num1; //자동 언박싱 ( num1.intValue(); 자동으로 )

	}

}
