package ch03;

public class StringConcatInplTest {

	public static void main(String[] args) {
		String s1 = "hello";
		String s2 = "java";
		
		// 우리가 알고 있던 OOP 방식 --> StringConcalInpl 생성하여 사용
		StringConcatInpl impl = new StringConcatInpl();
//		impl.makeString(s1, s2);
		
		// 익명 구현 클래스 활용
		// 클래스 설계 없이 바로 사용 가능하다.
		IStringConcat iStringConcat = new IStringConcat() {
			
			@Override
			public void makeString(String s1, String s2) {
				System.out.println("[[[" + s1 + s2 + "]]]");
			}
		};
		
		// 람다 표현식으로 설계하여 사용
		IStringConcat stringRambda = (s3, s4) -> System.out.println("!!!" + s3 + s4 +"!!!");

		stringRambda.makeString(s1, s2);
		
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				
//			}
//		});
		
		// 러너블 익명 구현 객체를 
        // 람다식으로 표현한 것. --> runnable에 run()메서드 하나밖에 없기 때문에 람다식으로 표현 가능하다!
        new Thread(() -> {});
		
	}

}
