package ch10;

import java.awt.print.Book;
import java.util.Scanner;

public class BookClient {
	
	Scanner sc = new Scanner(System.in);
	
	// 사용자한테 book ㅐㄱ체를 생성하는 메서드 기능을 만든다.
	public void creatBookObj() {
		
		int bookId = sc.nextInt();
		String bookTitle = sc.nextLine();
		String author = sc.nextLine();
		
		ch10.Book book = new ch10.Book(bookId, bookTitle, author);
//		Book book = new Book(bookId, bookTitle, author);
//		return book;
	}
	
	// 어떤 것을 할지 
	//String title 도 괜찮음
	//책의 정보를 확인하는 기능
	public void showBookInfo(int index) {
		
	}
	
	//저장되어 있는 책을 삭제하는 기능
	public void deleteBook(String title) {
		
	}
	
	//수정하는 기능
	
	public void updateBook(Book book) {
		
	}
}
//