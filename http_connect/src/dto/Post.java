package dto;

import lombok.ToString;

@ToString
public class Post {
	//구문을 분석해서 클래스화 하는 것을 모델링 함. 
	// 이런 역할을 하는 클래스를 dto라고 한다.
	int userId;
	int id;
	String title;
	String body;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	
}
