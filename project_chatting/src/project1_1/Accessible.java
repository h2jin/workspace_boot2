package project1_1;

public interface Accessible {
	
	// 서버와 연결
	void connectServer();
	
	// 메세지 보내는 기능
	void sendMessage(String string);
	
	// 메세지를 받음
	void getMessage();
	
	// 전송버튼 클릭
	void sendBtnClicked();
	
	// 닉네임 변경 기능
	void changeNicknameBtnClicked();
	
	// 귓속말 기능
	void whisperingBtnClicked();
	
	// 방 만들기 기능
	void makeRoomBtnClicked();
	
	// 방 삭제 기능
	void removeRoomBtnClicked();
	
	// 글 저장 기능
	void saveBtnClicked();

}
