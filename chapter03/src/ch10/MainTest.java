package ch10;

public class MainTest {
	public static void main(String[] args) {
		// 기능이 완선 되었다면
		BookClient bookClient = new BookClient();
		bookClient.creatBookObj();
		bookClient.deleteBook("홍길동전");
		
		BookDaoMySql bookDaoMySql = new BookDaoMySql();
		bookDaoMySql.addBook(book);
		
		//실행의 흐름 만들기
		
		
		//1. 조회 2. 생성 3. 수정 4. 삭제 
	}

}
