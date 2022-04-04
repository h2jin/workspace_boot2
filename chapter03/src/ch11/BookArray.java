package ch11;

public class BookArray implements BookService {

	Book[] books = new Book[10];

	@Override
	public void addBook(Book book) {
		for (int i = 0; i < books.length; i++) {
			if (books[i] == null) {
				books[i] = book;
				System.out.println("책을 등록했습니다.");
				showAllBook();
				return;
			}
		}
		System.out.println("책을 등록할 수 없습니다.");

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
		System.out.println("정보를 찾을 수 없습니다.");

	}

	@Override
	public void deleteBook(String title) {
		for (int i = 0; i < books.length; i++) {
			if (books[i] == null) {
				continue;
			} else if (books[i].getTitle().equals(title)) {
				books[i] = null;
				System.out.println("데이터를 삭제하였습니다.");
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
				System.out.println("=======현재 저장된 데이터=======\n" 
			+ books[i] + "\n" 
						+ "===============================");
			}
		}

	}

}
