package ch05;

import java.util.Scanner;

public class UserInfoClient {

	public static final String MYSQL = "mysql"; // static 주소 다름
	public static final String ORACLE = "oracle";

	public static void main(String[] args) {

		// 사용자한테 userInfo 정보 받는다.
		// UserInfo 스캐너로 받아서 흐름 만들기

		// 인터페이스 활용

		// 1. A회사
//		UserInfoMySqlDao mySqlDao = new UserInfoMySqlDao();
//		mySqlDao.insertUserInfo(info);

		// 1. B회사
//		UserInfoOracleDao oracleDao = new UserInfoOracleDao();
//		oracleDao.insertUserInfo(info);
//		String str = new String("mysql"); // heap 주소 다름

		// 문자열을 비교할 때는 무조건 equals라는 것을 사용한다.
//		if (str == (MYSQL)) {
//		if("mysql".equals(MYSQL)) {
//			System.out.println("문자열이 같습니다.");
//		} else {
//			System.out.println("문자열이 다릅니다.");
//		}

		// equals 와 == 의 차이점
		// equals 는 문자열의 값을 비교한다.
		// == 는 객체의 주소값을 비교한다.

		// 문제
		// 1. mysql 문자열이면 --> UserInfoDao 동작
		// 2. oracle 문자열이면 --> UserInfoOracle 동작
		// 단, 다형성을 사용헤서 처리

//		if ("mysql".equals(MYSQL)) {
//			UserInfoMySqlDao mySqlDao = new UserInfoMySqlDao();
//			mySqlDao.insertUserInfo(info);
//			mySqlDao.updateUserInfo(info);
//			mySqlDao.deleteUserInfo(info.getUserId());
//
//		} else if ("Oracle".equals(ORACLE)) {
//			UserInfoOracleDao oracleDao = new UserInfoOracleDao();
//			oracleDao.insertUserInfo(info);
//			oracleDao.updateUserInfo(info);
//			oracleDao.deleteUserInfo(info.getUserId());
//		}

		Scanner sc = new Scanner(System.in);
		System.out.println("ID를 입력해주세요");
		String userInputId = sc.nextLine();
		System.out.println("PW를 입력해주세요");
		String userInputPw = sc.nextLine();
		System.out.println("이름을 입력해주세요");
		String userName = sc.nextLine();

		UserInfo info = new UserInfo();
		info.setUserId(userInputId);
		info.setPassward(userInputPw);
		info.setUserName(userName);
		
		UserInfoDao dao = null;

		String str = "oracle";

		if (MYSQL.equals(str)) {
			dao = new UserInfoMySqlDao();
		} else if (ORACLE.equals(str)) {
			dao = new UserInfoOracleDao();
		} else {
			System.out.println("잘못된 입력입니다.");
		}
		if (dao != null) {
			dao.insertUserInfo(info);

		}

	}

}
