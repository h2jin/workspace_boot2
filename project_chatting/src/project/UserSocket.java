package project;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Iterator;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

import jdk.net.Sockets;

public class UserSocket extends Thread {

	Server mContext;
	UserSocket userSocket = this;

	String nickname;
	String myRoomName;
	// 소켓
	Socket socket;

	// 입출력 스트림
	InputStream inputStream;
	DataInputStream dataInputStream;
	OutputStream outputStream;
	DataOutputStream dataOutputStream;

	// 의존성 컴포지션 관계
	public UserSocket(Server mContext, Socket socket) {
		this.mContext = mContext;
		this.socket = socket;

		try {
			inputStream = socket.getInputStream();
			dataInputStream = new DataInputStream(inputStream);
			outputStream = socket.getOutputStream();
			dataOutputStream = new DataOutputStream(outputStream);
			nickname = dataInputStream.readUTF();
			mContext.getChattingTextArea().append("<" + nickname + "> 님이 접속했습니다.\n");
			mContext.broadCast("NewUser/" + nickname);

			// 기존 사용자 알림
			for (int i = 0; i < mContext.sockets.size(); i++) {
				UserSocket userSocket = mContext.sockets.elementAt(i);
				sendMessage("OriginUser/" + userSocket.nickname);
			}

			for (int i = 0; i < mContext.roomList.size(); i++) {
				ChattingRoom chattingRoom = mContext.roomList.elementAt(i);
				sendMessage("OriginRoom/" + chattingRoom.roomName);
			}

			// 방 리스트에 추가
			// 벡터리스트에 자신 추가

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void run() {

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						// 클라이언트의 메세지를 받아줄 스레드
						String msg = dataInputStream.readUTF();
						System.out.println("서버 메세지 : " + msg);
						mContext.getChattingTextArea().append("<" + nickname + ">" + msg + "\n");
						getMessage(msg);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}
		}).start();
	}

	public void sendMessage(String str) {
		try {
			dataOutputStream.writeUTF(str);
			dataOutputStream.flush();
			System.out.println("서버에서의 sendMessage");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void getMessage(String str) {
		StringTokenizer tokenizer = new StringTokenizer(str, "/");

		String protocol = tokenizer.nextToken();
		String message = tokenizer.nextToken();

		System.out.println("protocol" + protocol);
		System.out.println("message : " + message);

		if (protocol.equals("whisper")) {
			System.out.println("귓속말 기능");
			tokenizer = new StringTokenizer(message, ".");
			String sendUser = tokenizer.nextToken();
			String getUser = tokenizer.nextToken();
			String msg = tokenizer.nextToken();
			for (int i = 0; i < mContext.sockets.size(); i++) {
				UserSocket userSocket = mContext.sockets.elementAt(i);
				if (userSocket.nickname.equals(getUser)) {
					userSocket.sendMessage("Whisper/" + sendUser + "." + msg);
				}
			}
		} else if (protocol.equals("SendMsg")) {
			String sendUser = tokenizer.nextToken();
			String msg = tokenizer.nextToken();
			if (message.equals("null")) {
				mContext.broadCast("SendMsg/" + sendUser + "/" + msg);
			} else {
				for (int i = 0; i < mContext.roomList.size(); i++) {
					ChattingRoom chattingRoom = mContext.roomList.elementAt(i);
					if (chattingRoom.roomName.equals(message)) {
						mContext.roomList.elementAt(i).roomBroadcast("SendMsg/" + sendUser + "/" + msg);
					}
				}
			}

		} else if (protocol.equals("ChangeNick")) {
			for (int i = 0; i < mContext.sockets.size(); i++) {
				UserSocket userSocket = mContext.sockets.elementAt(i);
				if (userSocket.nickname != message) {
					sendMessage("ChangeNick/" + message);
				}
			}
		} else if (protocol.equals("MakeRoom")) {
			System.out.println("방 생성 메세지 받음");
			// 같은 방 이름이 있는지 확인
			boolean isMakeRoom;
			for (int i = 0; i < mContext.roomList.size(); i++) {
				ChattingRoom room = mContext.roomList.elementAt(i);
				if (room.roomName.equals(message)) {
					sendMessage("MakeRoomFail/");
					isMakeRoom = false;
				} else {
					isMakeRoom = true;
				}
			}
			if (isMakeRoom = true) {
				ChattingRoom chattingRoom = new ChattingRoom(mContext, message, this);
				mContext.roomList.add(chattingRoom);
				// 자신에게 방 생성 알림
				sendMessage("MakeRoom/" + message);
				mContext.broadCast("NewRoom/" + message);
			}
		} else if (protocol.equals("JoinRoom")) {
			System.out.println("유저소켓의 조인룸 들어옴");
			for (int i = 0; i < mContext.roomList.size(); i++) {
				ChattingRoom chattingRoom = mContext.roomList.elementAt(i);
				if (chattingRoom.roomName.equals(message)) {
					System.out.println("유저소켓의 이프문 안에 들어옴 ");
					chattingRoom.rooms.add(this);
					mContext.roomList.elementAt(i).roomBroadcast("JoinRoom/" + nickname);
				}
			}
		} else if (protocol.equals("OutRoom")) {
			for (int i = 0; i < mContext.roomList.size(); i++) {
				ChattingRoom chattingRoom = mContext.roomList.elementAt(i);
				if (chattingRoom.roomName.equals(message)) {
					chattingRoom.outRoom(this);
					sendMessage("OutRoom/" + message);
					break;
				}
			}
		}
	}

}
