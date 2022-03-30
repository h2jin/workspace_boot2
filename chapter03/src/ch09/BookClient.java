package ch09;

import java.util.Scanner;

public class BookClient {
	
	// 스캐너를 이용하여 메서드로 만들어라
	//생성
	public void insert(Book book) {
		Scanner sc = new Scanner(System.in);
		System.out.println("id: ");
		int inputId = sc.nextInt();
		System.out.println("제목: ");
		String inputTitle = sc.nextLine();
		System.out.println("작가: ");
		String inputAuthor = sc.nextLine();
		inputId = book.getId();
		inputTitle = book.getTitle();
		inputAuthor = book.getAuthor();
	}
	
	
	//수정
	
	// 삭제
	
	// 출력
	
	//

}
