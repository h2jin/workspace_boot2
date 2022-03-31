package ch11;

public class BookArray implements BookService {

	Book[] books = new Book[10];

	@Override
	public void addBook(Book book) {
		System.out.println("저장합니다.");
		for (int i = 0; i < books.length; i++) {
			if (books[i] == null) {
				books[i] = book;
				showAllBook();
				return;
			}
		}
		System.out.println("저장할 공간이 부족합니다.");

	}

	@Override
	public void updateBook(String title, Book book) {
		for (int i = 0; i < books.length; i++) {
			if (books[i] == null) {
				continue;

			} else if (books[i].getTitle().equals(title)) {
				books[i] = book;
				showAllBook();
			}
		}
		System.out.println("책의 이름이 존재하지 않습니다.");

	}

	@Override
	public void deleteBook(String title) {
		for (int i = 0; i < books.length; i++) {
			if (books[i] == null) {
				continue;
			} else if (books[i].getTitle().equals(title)) {
				books[i] = null;
				System.out.println("삭제하였습니다.");
				showAllBook();
				return;
			}
		}

	}

	@Override
	public void selectedByTitleBook(String title) {
		for (int i = 0; i < books.length; i++) {
			if (books[i] == null) {
				continue;
			} else if (books[i].getTitle().equals(title)) {
				System.out.println("<조회 결과>");
				System.out.println(books[i]);
				return;
			}
		}

	}

	@Override
	public void showAllBook() {
		for (int i = 0; i < books.length; i++) {
			if (books[i] == null) {
				continue;
			} else {
				System.out.println(">>>>>>>현재 저장된 데이터<<<<<<<" + books[i]);
			}
		}

	}

}
