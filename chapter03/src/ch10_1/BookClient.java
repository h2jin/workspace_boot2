package ch10_1;

public class BookClient {
	
	private static int serialBookNum = 0;
	// 북 객체 생성
	public Book creatBook(String title, String author) {
		serialBookNum++;
		return new Book(serialBookNum, title , author);
	}

}
