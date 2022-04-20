package ch02;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalenderMainTest {
	
	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance(); // 시간 개념을 사용하는데, 힙영역에 여러개의 메모리 올릴 필요가 없음 
		//--> 싱글톤 패턴으로 만들어짐.
		System.out.println(calendar.getTimeInMillis()); // 현재시간 (1000분에 1초) --> 나라마다 현재시간 다르기 때문에 
		//보여주는 방식은 각자 알아서 구현하도록 설계해줌.
		
		// 보기 불편한 형식을 편하게 변환해서 사용하는 방법을 알아보자.
		DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss"); // 다형성
		String date = dateFormat.format(calendar.getTimeInMillis());
		System.out.println(date);
		
	}

}
