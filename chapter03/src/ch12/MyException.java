package ch12;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class MyException {
	
	String fileName;
	
	public MyException(String fileName) {
		this.fileName = fileName;
	}
	
	
	// throws 란? 던지기 (사용하는 사람이 직접 예외처리를 해라고 지시함)
	public String readFile() throws IOException {
		FileInputStream fis = new FileInputStream(this.fileName);
		Properties properties = new Properties();
		properties.load(fis);
		String dbType = properties.getProperty("DBTYPE");
		return dbType;
	}
	
	
	public static void main(String[] args) {
		
		String dbType = null;
		MyException my = new MyException("b.txt");
		
		try {
			dbType = my.readFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); //어디서 난 예외인지 알려줌
		}
		
		System.out.println("결과값 : " + dbType);
		//파일에서 데이터를 읽어서 올림
		// 파일로 저장해 두면 영구적 
	}// end of main

}// end of class
