package project1_1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class UserSocket extends Thread { // 클래스명..
	
	Server mContext;
	
	Socket socket;
	private InputStream inputStream;
	private DataInputStream dataInputStream;
	private OutputStream outputStream;
	private DataOutputStream dataOutputStream;
	private String nickname;
	
	
	public UserSocket(Server mContext, Socket socket) {
		this.mContext = mContext;
		this.socket = socket;
		try {
			inputStream = socket.getInputStream();
			dataInputStream = new DataInputStream(inputStream);
			outputStream = socket.getOutputStream();
			dataOutputStream = new DataOutputStream(outputStream);
			
			nickname = dataInputStream.readUTF();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// 메세지를 읽는 기능
	@Override
		public void run() {
			try {
				String message = dataInputStream.readUTF();
				System.out.println("서버 -> UserSocket : message ->" + message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
	// 메세지를 보내는 기능
	public void sendMessage(String msg) {
		try {
			dataOutputStream.writeUTF(msg);
			dataOutputStream.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	

}
