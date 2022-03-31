package ch11;

import java.util.Scanner;

public class MainTest {
	public static void main(String[] args) {
		
		String str = "             문자열 공백 제거";
		System.out.println(str);
		String removeBlank = str.trim(); // 앞의 공백이나 뒤의 공백 제거해줌. 중간의 공백은 제거X
		System.out.println(removeBlank);
		// replace 사용
		String str2 = removeBlank.replace(" ", "");
		System.out.println(str2);
		
		System.out.println(removeBlankString("         공백     제거    확인      1111"));
		
		BookClient bookClient = new BookClient();
		BookService bookArraylist = new BookArraylist();
		// 스캐너
		Scanner sc = new Scanner(System.in);

		// do while 반복문
		String selectedMenu = ""; // null 은 new로 생성하지 않은 것
		// 1234 --> 반복 0 --> 종료

		do {
			System.out.println("----------------------------------------------------------------------------");
			System.out.println("1. 책 생성 2. 책 조회 3. 책 삭제 4. 책 전체조회 5. 책 수정 0. 프로그램 종료");
			System.out.println("----------------------------------------------------------------------------");
			selectedMenu = sc.nextLine();
			
			if (selectedMenu.equals("0")) {
				System.out.println("프로그램을 종료합니다.");
				sc.close();
			} else if (selectedMenu.equals("1")) {
//				System.out.println("책 제목을 입력하세요.");
				bookClient.printTitle();
				// 객체 생성 코드 작성
				String title = sc.nextLine();
				System.out.println("작가의 이름을 입력하세요");
				String author = sc.nextLine();
				Book book = bookClient.creatBook(title, author); //리턴타입 지역변수로 받음
				bookArraylist.addBook(book);
				
			} else if (selectedMenu.equals("2")) {
				System.out.println("책 제목을 입력해주세요.");
				String title = sc.nextLine();
				bookArraylist.selectedByTitleBook(title);
				
			} else if (selectedMenu.equals("3")) {
				System.out.println("삭제할 책 제목을 입력해주세요");
				String title = sc.nextLine();
				bookArraylist.deleteBook(title);
				
			} else if (selectedMenu.equals("4")) {
				System.out.println("저장되어 있는 책 목록 조회");
				bookArraylist.showAllBook();
				
			} else if (selectedMenu.equals("5")) {
				System.out.println("수정하려는 책 제목을 입력해주세요");
				String savedTitle = sc.nextLine();
				System.out.println("새로운 책 제목을 입력하세요");
				String title = sc.nextLine();
				System.out.println("새로운 작가 이름을 입력하세요");
				String author = sc.nextLine();
				Book book = bookClient.creatBook(title, author);
				
				bookArraylist.updateBook(savedTitle, book);
			} else {
				System.out.println("잘못된 입력입니다.");
			}

		} while (!selectedMenu.equals("0"));
		

	}// end of main

	public static String removeBlankString(String str) {
		String result1 = str.trim();
		String result2 = result1.replace(" ", "");
		return result2;
	}

}// end of class
