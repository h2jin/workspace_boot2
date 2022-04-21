package file_io.project;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

// 날짜 기록
public class CheckDate {
	
	public String checkDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
		return dateFormat.format(Calendar.getInstance().getTime());
	}

}
