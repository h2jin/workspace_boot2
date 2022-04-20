package ch02;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

//작성된 뉴스를 받고, 꾸며주는 기능 추가
// 콜백 받는 객체
public class HackerNews implements WriteArticle{
	
	private String printDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
		return dateFormat.format(Calendar.getInstance().getTime()); // 코드 줄임.
	}
	

	@Override
	public void printArticle(String article) {
		System.out.println("**** HackerNews ****");
		System.out.println();
		System.out.println(article);
		System.out.println();
		System.out.println("기사 작성일 : " + printDate());
		
	}
	

}
