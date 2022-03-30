package ch07;

public class MainTest {
	public static void main(String[] args) {
		
		Car car1 = new Car("BMW");
		Car car2 = new Car("Audi");
		
		
		if(car1.equals(car1)) {
			System.out.println("같은 차입니다.");
		} else {
			System.out.println("다른 차 입니다.");
		}
		
		
		
		
	}

}
