package ch02_1;

import java.util.Scanner;

public class UserInfoSaveSystem {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		Client client = new Client();
		UserInfoArraylist userInfoarraylist = new UserInfoArraylist();

		String selectedNum = "";
		do {
			System.out.println("==========================\n" + "1. 회원등록\n" + "2. 회원 정보 보기\n" + "3. 회원 정보 수정\n"
					+ "4. 회원 삭제\n" + "5. 회원 전체정보 보기\n" + "0. 프로그램 종료\n" + "==========================");
			selectedNum = sc.nextLine();
			if (selectedNum.equals("0")) {
				System.out.println("프로그램을 종료합니다.");
				sc.close();
			} else if (selectedNum.equals("1")) {
				System.out.println("ID: ");
				String id = sc.nextLine();
				System.out.println("PW: ");
				String pw = sc.nextLine();
				System.out.println("이름: ");
				String name = sc.nextLine();
				System.out.println("전화번호: ");
				String phoneNum = sc.nextLine();
				UserInfo userInfo = client.creatId(id, pw, name, phoneNum);
				userInfoarraylist.join(userInfo);

			} else if (selectedNum.equals("2")) {
				System.out.println("아이디를 입력해 주세요");
				String pw = sc.nextLine();
				userInfoarraylist.checkMyInfo(client.removeBlank(pw));

			} else if (selectedNum.equals("3")) {
				System.out.println("아이디를 입력해 주세요");
				String savedId = sc.nextLine();
				boolean checkIdOkay;
				checkIdOkay = userInfoarraylist.checkId(savedId);
				if (checkIdOkay) {
					System.out.println("새로운 ID: ");
					String id = sc.nextLine();
					System.out.println("새로운 PW: ");
					String pw = sc.nextLine();
					System.out.println("변경할 이름: ");
					String name = sc.nextLine();
					System.out.println("변경할 전화번호:");
					String phoneNum = sc.nextLine();
					UserInfo userInfo = client.creatId(id, pw, name, phoneNum);
					userInfoarraylist.updateMyInfo(savedId, userInfo);
				} else {
					System.out.println("정보를 찾을 수 없습니다.");
				}

			} else if (selectedNum.equals("4")) {
				System.out.println("아이디를 입력해주세요.");
				String pw = sc.nextLine();
				userInfoarraylist.delete(client.removeBlank(pw));
			} else if (selectedNum.equals("5")) {
				userInfoarraylist.showAllInfo();
			} else {
				System.out.println("잘못된 입력입니다.");
			}

		} while (!selectedNum.equals("0"));
	}// end of main

}// end of class
