package ch02;

public class MainTest {
	
	public static void main(String[] args) {
		
		// 해커뉴스 생성
		HackerNews hackerNews = new HackerNews();
		// 기사 작성
		MyArticle article = new MyArticle("오늘 날씨가 좋다.", hackerNews);
		
		article.complete();
		
		// 글만 작성해주면 꾸며주는 기능은 HackerNews에서 해주도록 된다.
		
	}

}
