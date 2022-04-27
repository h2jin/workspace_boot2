package project;

import java.util.Vector;

public class ChattingRoom {
	
	Server mContext;

	String roomName;
	Vector<UserSocket> rooms = new Vector<UserSocket>();

	public ChattingRoom(Server mContext ,String roomName, UserSocket userSocket) {
		this.mContext = mContext;
		this.roomName = roomName;
		this.rooms.add(userSocket);
		userSocket.myRoomName = roomName;
	}
	
	// 방에 있는 사용자들에게만 메세지 전송
	private void roomBroadcast(String str) {
		for (int i = 0; i < rooms.size(); i++) {
			UserSocket userSocket = rooms.elementAt(i);
			userSocket.sendMessage(str);
		}
	}
	
	// 방 참가
	private void addUser(UserSocket userSocket) {
		rooms.add(userSocket);
	}
	
	// 방 나가기
	private void outRoom(UserSocket userSocket) {
		rooms.remove(userSocket);
		if(rooms.isEmpty()) {
			for(int i =0; i < mContext.roomList.size(); i++) {
				ChattingRoom chattingRoom = mContext.roomList.elementAt(i);
				if(roomName.equals(chattingRoom.roomName)) {
					mContext.roomList.remove(this);
					mContext.broadCast("RemoveRoom/"+ roomName);
					break;
				}
			}
		}
	}

}
