package project2;

public interface CallbackCheckMessage {
	
	// 그냥 채팅 기능
	void sendBtnClicked();
	
	// 채팅창에서 엔터
	void enterKeyboard();
	
	// 닉네임 변경 기능
	void changeNicknameBtnClicked();
	
	// 귓속말 기능
	void whisperingBtnClicked();
	
	// 방 만들기 기능
	void makeRoomBtnClicked();
	
	// 방 나가기 기능
	void outRoomBtnClicked();
	
	// 방 합류 기능
	void joinRoomBtnClicked();
	
	// 글 저장 기능
//	void saveBtnClicked();

}
