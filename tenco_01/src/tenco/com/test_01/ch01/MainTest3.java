package tenco.com.test_01.ch01;

public class MainTest3 {

	public static void main(String[] args) {
		
		// 재료 메모리에 올림
		Powder powder = new Powder();
		Plastic plastic = new Plastic();
		
		// 사용하는 시점에 지정된 자료형으로 컴파일 된다.
		GenericPrinter<Powder> genericPrinter1 = new GenericPrinter<>(); // 어떤 자료형을 쓸건지 먼저 지정 
		
		genericPrinter1.setMaterial(powder); 
		System.out.println(genericPrinter1.getMaterial());
		
		GenericPrinter<Plastic> genericPrinter2 = new GenericPrinter<Plastic>();
		genericPrinter2.setMaterial(plastic);
		System.out.println(genericPrinter2.getMaterial());
		
		GenericPrinter<Water> genericPrinter3 = new GenericPrinter<>();
		genericPrinter3.setMaterial(genericPrinter3.getMaterial()); // 아무 문자를 넣어도 코드문법상으로는 문제가 없다. 오류 날 수 있음.
		
		GenericPrinter2<Powder> g2 = new GenericPrinter2<>();
		g2.setMaterial(powder);
		System.out.println(g2); //이것만 쓰면, null point exception 일어남. 윗줄 입력해줘야함.
		
		// <T extends> 사용하기
		// 상위 클래스의 필요성
		// T자료형의 범위를 제한할 수 있다. 제한하지 않으면 아무 클래스나 올 수 있음
		
		
		
		

	}

}
