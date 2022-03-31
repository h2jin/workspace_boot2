package ch11;

import java.util.Scanner;

public class MainTest2 {
	public static void main(String[] args) {

		BookArray bookArray = new BookArray();
		BookClient bookClient = new BookClient();
		Scanner sc = new Scanner(System.in);
		String selectedNum = "";
		do {
			System.out.println("=========================\n" + "1.책 생성\n" + "2. 책 조회\n" + "3. 책 삭제\n" + "4. 책 전체조회\n" + "5. 책 수정\n"
					+ "0. 프로그램 종료\n" + "=========================");
			selectedNum = sc.nextLine();
			if (selectedNum.equals("0")) {
				System.out.println("프로그램을 종료합니다.");
				sc.close();
			} else if (selectedNum.equals("1")) {
				System.out.println("책 제목: ");
				String title = sc.nextLine();
				System.out.println("작가 이름: ");
				String author = sc.nextLine();
				Book book = bookClient.creatBook(title, author);
				bookArray.addBook(book); // !!!!!!!!!!
			} else if (selectedNum.equals("2")) {
				System.out.println("책 제목: ");
				String title = sc.nextLine();
				bookArray.selectedByTitleBook(title);
			} else if (selectedNum.equals("3")) {
				System.out.println("책 제목: ");
				String title = sc.nextLine();
				bookArray.deleteBook(title);
				bookArray.showAllBook();
			} else if (selectedNum.equals("4")) {
				bookArray.showAllBook();
			} else if (selectedNum.equals("5")) {
				System.out.println("책 제목: ");
				String savedtitle = sc.nextLine();
				System.out.println("수정할 제목: ");
				String title = sc.nextLine();
				System.out.println("수정할 작가: ");
				String author = sc.nextLine();
				Book book = bookClient.creatBook(title, author);
				bookArray.updateBook(savedtitle, book);
				bookArray.showAllBook();
			} else {
				System.out.println("잘못입력하셨습니다.");
			}

		} while (!selectedNum.equals("0"));
	}

}
