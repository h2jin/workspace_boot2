package ch12;

import java.util.logging.Logger;

class Student {
	String name;
	int grade;
	
}

public class MyLog {
	public static void main(String[] args) {
		//로그 남겨보기
		Logger logger = Logger.getLogger("MyLog"); //java.util.logging 원시코드
		
		Student student = null;
		try {
			student.name = "홍길동";			
		} catch (NullPointerException e) {
			e.printStackTrace();
			System.out.println("===========================");
			logger.warning("오류 확인: " + e.toString()); //시간까지 확인 가능 
		}
		System.out.println("여기코드 실행 확인!!");
		
	}

}
