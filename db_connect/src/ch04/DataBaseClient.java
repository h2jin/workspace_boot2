package ch04;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseClient {
	
	private static final String DB_HOST = "localhost";
	private static final int DB_PORT = 3306;
	private static final String DB_DATABASE_NAME = "employees";
	private static final String DB_CHARSET = "UTF-8";
	private static final String DB_USER_NAME = "root";
	private static final String DB_PASSWORD = "asd123";
	
	private Connection connection;
	
	//싱글톤 패턴
	private static DataBaseClient dbClient;
	
	public static DataBaseClient getInstance() {
		if(dbClient == null) {
			dbClient = new DataBaseClient();
		}
		return dbClient;
	}
	
	public Connection getConnection() {
		if (connection == null) {
			String urlFormat = "jdbc:mysql://%s:%d/%s?serverTimezone=Asia/Seoul&characterEncoding=%s";
			String url = String.format(urlFormat, DB_HOST, DB_PORT, DB_DATABASE_NAME, DB_CHARSET);
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection(url, DB_USER_NAME, DB_PASSWORD);
				System.out.println("연결 성공");
			} catch (Exception e) {
				try {
					connection.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
			
			
		}
		return connection;
	}
	
	public void connectionClose() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			connection = null;
		}
	}
 
}
