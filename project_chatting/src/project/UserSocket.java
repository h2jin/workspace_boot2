package project;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.StringTokenizer;

import jdk.net.Sockets;

public class UserSocket extends Thread {
	
	Server mContext;
	
	String nickname;
	String myRoomName;
	// 소켓
	Socket socket;
	
	// 입출력 스트림
	InputStream inputStream;
	DataInputStream dataInputStream;
	OutputStream outputStream;
	DataOutputStream dataOutputStream;
	
	
	//의존성 컴포지션 관계
	public UserSocket( Server mContext , Socket socket) {
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
			
			//기존 사용자 알림
			for(int i = 0; i < mContext.sockets.size(); i++) {
				UserSocket userSocket = mContext.sockets.elementAt(i);
				sendMessage("OriginUser/" + nickname);
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
				while(true) {
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
		
		if(protocol.equals("whisper")) {
			System.out.println("귓속말 기능");
			
		} else if (protocol.equals("SendMsg")) {
			String msg = tokenizer.nextToken();
			mContext.broadCast("SendMsg/" + message +" : "+ "/" + msg);
		}
	}
	
	

}
