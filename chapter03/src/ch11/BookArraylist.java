package ch11;

import java.util.ArrayList;

public class BookArraylist implements BookService {

	private ArrayList<Book> books = new ArrayList<Book>();

	public BookArraylist() {
		Book book1 = new Book(1, "흐르는 강물처럼", "미상");
		BookClient.serialBookNumber = 5;
		books.add(book1);
		
	}

	/**
	 * Book 객체를 arraylist 자료구조에 저장하기
	 */
	@Override // 추상클래스의 메서드를 일반 메서드로 불러오기
	public void addBook(Book book) {
		System.out.println("저장합니다.");
		books.add(book);
		showAllBook();
	}

	/*
	 * 책의 타이틀로 책 존재여부 확인 있다면 매개 변수로 넘어오는 Book 객체를 arraylist에 수정한다.
	 */

	@Override
	public void updateBook(String title, Book book) {
		System.out.println("수정합니다.");
		// 인덱스 값 반환받아야 함.
		// 책 타이틀로 인덱스 번호를 찾아야 한다.
		int bookIndex = -1; // 연관없는 값 넣어줌
		for (int i = 0; i < books.size(); i++) {
			if (books.get(i).getTitle().equals(title)) {
				// 책이 존재
				bookIndex = i;

			}
		}

		if (bookIndex == -1) {
			System.out.println(title + "책이 존재하지 않습니다.");
		} else {
			books.set(bookIndex, book);
		}
		showAllBook();
//		books.set(0, book)

	}
	/*
	 * 책 타이틀로 arraylist에 객체를 삭제한다.
	 */

	@Override
	public void deleteBook(String title) {
		System.out.println("삭제합니다.");
		boolean deleteOk = false;
		for (int i = 0; i < books.size(); i++) {
			if (books.get(i).getTitle().equals(title)) {
				books.remove(i);
				deleteOk = true;
			}

		}
		if (deleteOk) {
			System.out.println(title + "책을 삭제하였습니다.");
		} else {
			System.out.println(title + "책이 존재하지 않습니다.");
		}
		showAllBook();

	}

	/*
	 * 책 제목에 해당하는 객체에 정보를 출력합니다.
	 */
	@Override
	public void selectedByTitleBook(String title) {
		System.out.println("조회합니다.");
		for (int i = 0; i < books.size(); i++) {
			if (books.get(i).getTitle().equals(title)) {
				System.out.println(books.get(i));
				return; // 실행되는 경우, 여기서 실행이 멈춤
			}
		}
		System.out.println(title + "제목으로 책을 찾을 수 없습니다.");
	}

	/*
	 * 모든 데이터를 출력
	 */
	@Override
	public void showAllBook() {
		System.out.println(">>>>> 현재 저장된 데이터 확인 <<<<<");
		for (Book book : books) {
			System.out.println(book);
		}

	}

}
