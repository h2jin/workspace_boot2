package ch09;

import java.util.ArrayList;

public class BookDaoMysql implements BookDao{
	
	//인터페이스를 활용해서 기능을 구현
	//Arraylist 사용
	
	ArrayList<Book> books;

	@Override
	public void insertBookInfo(Book book) {
		books.add(book);
	}

	@Override
	public void updateBookInfo(Book book) {
		books.set(0, book);
	}

	@Override
	public void deleteBookInfo(Book book) {
		books.remove(0);
		
	}

	@Override
	public void showBookInfo(Book book) {
		for (Book book1 : books) {
			System.out.println(book1);
		}
	}
	
	
	
	
	
	

}
