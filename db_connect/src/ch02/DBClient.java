package ch02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DBClient {

	private static final String DB_HOST = "localhost";
	private static final int DB_PORT = 3306;
	private static final String DB_DATABASE_NAME = "shopdb";
	private static final String DB_CHARSET = "UTF-8";
	private static final String DB_USER_NAME = "root";
	private static final String DB_PASSWORD = "asd123";

	private Connection conn;

	// 싱글톤 패턴 처리
	private static DBClient dbClient; // dbClient 안에 conn이 있음.
	// 생성자
	private DBClient() {
	}

	public static DBClient getInstance() {
		if (dbClient == null) {
			dbClient = new DBClient();
		}
		return dbClient;
	}

	// 커넥션을 리턴
	public Connection getConnection() {
		if (conn == null) {
			// jdbc:mysql://localhost:3306/shopdb?serverTimezone=Asia/Seoul&characterEncoding=UTF-8
			String urlFormat = "jdbc:mysql://%s:%d/%s?serverTimezone=Asia/Seoul&characterEncoding=%s";
			String url = String.format(urlFormat, DB_HOST, DB_PORT, DB_DATABASE_NAME, DB_CHARSET);

			// MYSQL JDBC 드라이버 클래스를 로딩해서 DriverManager 클래스에 등록한다.
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				// DriverManager 객체를 사용하여 DB서버에 접근할 수 있다.
				conn = DriverManager.getConnection(url, DB_USER_NAME, DB_PASSWORD);
				System.out.println(">>> Connection Success <<<");

			} catch (Exception e) {
				try {
					conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}

		}
		return conn;
	}

	public void connectionClose() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			conn = null;
		}
	}

}
