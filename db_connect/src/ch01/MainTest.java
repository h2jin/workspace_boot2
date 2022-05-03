package ch01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainTest {

	// DB서버와 연결하기 위한 준비물
	private Connection conn; // DB 커넥션 연결 객체
	private static final String USER_NAME = "root";
	private static final String PASSWORD = "asd123";
	private static final String URL = "jdbc:mysql://localhost:3306/shopdb_1?serverTimezone=Asia/Seoul&characterEncoding=UTF-8";
	// 프로토콜//로컬호스트(자신의 컴퓨터):포트번호/데이터베이스 지정 ? 서버타임존 부가적으로 넣어줘야 함= 아시아/서울&엔코딩UTF-8

	private Statement stmt; // String으로 쿼리문 작성 --> mysql이 이해할 수 있는 쿼리구문으로 변경해주는 녀석
	private ResultSet rs; // 결과값을 받아주는 녀석

	// 생성자
	public MainTest() {

		// reflection 기법 : 컴파일 시점에 문자열에서 --> 런타임 시점에 실제 클래스가 존재하는지 확인
		// 메모리(heap) 영역에 올라간다.
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD); // 연결처리 --> conn - 연결되어있는 객체
			stmt = conn.createStatement(); //

			String sql1 = "select * from membertbl";
			rs = stmt.executeQuery(sql1); // 쿼리문 던짐. 쿼리구문이 실행되면 결과는 rs에 담기게 됨.
			
			while(rs.next()) {
				String memberId = rs.getString("memberId");
				String memberName = rs.getString("memberName");
				System.out.println("ID : " + memberId  + " ," + "name : " + memberName);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}

	// 코드의 시작점
	public static void main(String[] args) {
		new MainTest();
	} // end of main

}
