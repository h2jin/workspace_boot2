package ch10_1;

import java.util.Scanner;

public class MainTest {
	public static void main(String[] args) {

		BookClient bookClient = new BookClient();
		BookArraylist bookArraylist = new BookArraylist();
		Scanner sc = new Scanner(System.in);
		String selectedMenu = "";
		do {
			System.out.println("==================\n" + "1. 책 생성\n" + "2. 책 조회\n" + "3. 책 삭제\n" + "4. 책 수정\n"
					+ "5. 책 전체조회\n" + "0. 프로그램 종료\n" + "==================");
			selectedMenu = sc.nextLine();
			if (selectedMenu.equals("0")) {
				System.out.println("프로그램을 종료합니다.");
				sc.close();
			} else if (selectedMenu.equals("1")) {
				System.out.println("책 제목을 입력해주세요");
				String title = sc.nextLine();
				System.out.println("작가의 이름을 입력해주세요");
				String author = sc.nextLine();
				Book book = bookClient.creatBook(title, author);// ????
				bookArraylist.addBook(book);

			} else if (selectedMenu.equals("2")) {
				// 책 조회
				System.out.println("책 제목을 입력해주세요");
				String title = sc.nextLine();
				bookArraylist.selectedBookPrint(title);
			} else if (selectedMenu.equals("3")) {
				// 책 삭제
				System.out.println("책 제목을 입력해주세요");
				String title = sc.nextLine();
				bookArraylist.deleteBook(title);

			} else if (selectedMenu.equals("4")) {
				// 책 수정
				System.out.println("수정하려는 책의 제목을 입력해주세요");
				String savedtitle = sc.nextLine();
				System.out.println("새로운 제목을 입력해주세요");
				String title = sc.nextLine();
				System.out.println("새로운 작가 이름을 입력해주세요");
				String author = sc.nextLine();
				Book book = bookClient.creatBook(title, author);
				bookArraylist.updateBook(savedtitle, book);

			} else if (selectedMenu.equals("5")) {
				// 책 전체 조회
				bookArraylist.showAllBook();
			} else {
				System.out.println("잘못된 입력입니다.");
			}

		} while (!selectedMenu.equals("0"));
	}// end of main

	public static String removeBlankString(String str) {
		String result1 = str.trim();
		String result2 = result1.replaceAll(" ", "");
		return result2;
		//공백을 없애주는 메서드
	}

}// end of class
