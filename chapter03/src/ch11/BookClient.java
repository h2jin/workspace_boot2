package ch11;

public class BookClient {

	private static int serialBookNumber = 0;

	// 북 객체 생성
	public Book creatBook(String title, String author) {
		serialBookNumber++;
		return new Book(serialBookNumber, title, author);
	}
	
	public void printTitle() {
		System.out.println("책 제목을 입력합니다");
		System.out.println("공백없이 입력해 주세요");
	}
	

}
