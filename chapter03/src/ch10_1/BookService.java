package ch10_1;

public interface BookService {
	// 객체를 저장, 수정, 삭제, 책 한권의 정보 출력, 모든 책의 정보 출력
	
	void addBook(Book book);
	
	void updateBook(String title, Book book); // 책 제목으로 수정할 책을 찾고, 책의 정보를 수정
	
	void deleteBook(String title); //책 제목으로 삭제할 책을 찾음
	
	void selectedBookPrint(String title); //책의 제목으로 출력할 책을 찾음
	
	void showAllBook();
	

}
