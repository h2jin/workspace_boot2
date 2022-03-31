package ch13;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class MyException {
	
	String fileName;
	
	public MyException(String fileName) {
		this.fileName = fileName;
	}
	
	
	public String readFile () throws IOException {
		FileInputStream fis = new FileInputStream(this.fileName);
		Properties propertise = new Properties();
		propertise.load(fis);
		String dbType = propertise.getProperty("DBTYPE");
		return dbType;
	}

}
