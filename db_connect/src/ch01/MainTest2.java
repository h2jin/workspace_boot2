package ch01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainTest2 {
	
	// buytbl 테이블 가져오기
	private Connection conn;
	private static final String USER_NAME = "root";
	private static final String PASSWORD = "asd123";
	private static final String URL = "jdbc:mysql://localhost:3306/shopdb?serverTimezone=Asia/Seoul&characterEncoding=UTF-8";

	private Statement stmt;
	private ResultSet rs;
	
	public MainTest2() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
			stmt = conn.createStatement();
			
			String sql = "select * from buytbl where userName = '이승기'";
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				String prodName = rs.getString("prodName");
				int price = rs.getInt("price");
				System.out.println("제품명 : " + prodName + ", " + "가격 : " + price);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	// 코드의 시작점
	public static void main(String[] args) {
		
		new MainTest2();

	}

}
