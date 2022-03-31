package ch10_1;

import java.util.ArrayList;
import java.util.Scanner;

public class BookArraylist implements BookService {

	private ArrayList<Book> books = new ArrayList<Book>();

	@Override
	public void addBook(Book book) {
		System.out.println("저장합니다.");
		books.add(book);
		showAllBook();
	}

//	@Override
//	public void updateBook(String title, Book book) {
//		System.out.println("수정합니다.");
//		int bookIndex = -1; // ??????
//		for (int i = 0; i < books.size(); i++) {
//			if (books.get(i).getTitle().equals(title)) {
//				bookIndex = i;// bookIndex의 값이 i로 변경됨.
//			}
//		}
//		if (bookIndex == -1) {
//			System.out.println("책이 존재하지 않습니다."); // i의 값이 그대로 -1이면 존재하지 않는 책
//		} else {
//			books.set(bookIndex, book); // i의 값이 변경되면 존재하는 책이므로 책의 값을 수정
//		}
//		showAllBook();
//
//	}
	@Override
	public void updateBook(String title, Book book) {
		Scanner sc = new Scanner(System.in);
		System.out.println("수정하려는 책의 제목을 입력해주세요");
		String savedTitle = sc.nextLine();
		System.out.println("새로운 제목을 입력해주세요");
		String newTitle = sc.nextLine();
		System.out.println("새로운 작가 이름을 입력해주세요");
		String author = sc.nextLine();
		for (int i = 0; i < books.size(); i++) {
			
		}
		
		
	}
	

	@Override
	public void deleteBook(String title) {
		System.out.println("삭제합니다.");
		boolean deleteOk = false; // ??????
		for (int i = 0; i < books.size(); i++) {
			if (books.get(i).getTitle().equals(title)) {
				books.remove(i);
				deleteOk = true; // 책이 존재하면 deleteOk의 값이 true로 변경
			}
		}
		if (deleteOk) {
			System.out.println(title + "책을 삭제하였습니다."); // deleteOk의 값이 true이면 책 삭제
		} else {
			System.out.println("존재하지 않는 책입니다.");// deleteOk 의 값이 그대로 false면 존재하지 않는 책
		}
		showAllBook();
	}

	@Override
	public void selectedBookPrint(String title) {
		System.out.println("조회합니다.");
		for (int i = 0; i < books.size(); i++) {
			if (books.get(i).getTitle().equals(title)) {
				System.out.println(books.get(i));
				return; // 값을 반환하거나 실행의 제어권을 반납.
			}
		}
		System.out.println("존재하지 않는 책입니다.");
	}

	@Override
	public void showAllBook() {
		System.out.println("========현재 저장된 데이터========");
		for (Book book : books) {
			System.out.println(book);
		}

	}

}
