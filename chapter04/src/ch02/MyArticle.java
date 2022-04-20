package ch02;

// 뉴스를 작성하여 보냄 --> 호출자 인터페이스를 멤버변수로 선언 --> 포함관계를 만들어주는 것.
public class MyArticle {
	
	String article;
	WriteArticle onWriteArticle;
	
	//주소값 연결 : 생성자에서 연결. 다른방법도 존재
	public MyArticle(String article, WriteArticle onWriteArticle) {
		this.article = article;
		this.onWriteArticle = onWriteArticle;
	}
	
	public void complete() {
		onWriteArticle.printArticle(article); // 콜백 메서드의 단점. 인터페이스로 가서 어떤 객체에서 쓰고 있는지 찾기가 힘들다.
	}

}
