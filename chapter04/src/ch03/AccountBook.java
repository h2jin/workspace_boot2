package ch03;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AccountBook implements WriteSales{

	private String printDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
		return dateFormat.format(Calendar.getInstance().getTime());
	}
	
	@Override
	public void printTotalSaled(int totalMoney) {
		System.out.println("========오늘의 총 매출액========");
		System.out.println();
		System.out.println(totalMoney);
		System.out.println();
		System.out.println("날짜 : " + printDate());
		System.out.println("===================================");
		
	}
	

}
