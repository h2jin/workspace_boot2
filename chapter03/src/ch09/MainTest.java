package ch09;

public class MainTest {

	public static void main(String[] args) {
		Book book1 = new Book(1, "흐르는강물처럼", "파울로코엘료");
		Book book2 = new Book(2, "플러터UI실전", "김근호");
		Book book3 = new Book(3, "무궁화꽃이피었습니다", "김진명");
		Book book4 = new Book(4, "사피엔스", "유발하라리");
		
		BookDaoMysql mysql = new BookDaoMysql();
		mysql.insertBookInfo(book1);
		mysql.insertBookInfo(book2);
		mysql.insertBookInfo(book3);
		mysql.insertBookInfo(book4);
		
		
		
		
		
		
			
		
		

	}

}
