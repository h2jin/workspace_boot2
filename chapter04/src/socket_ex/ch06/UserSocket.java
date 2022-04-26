package socket_ex.ch06;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class UserSocket extends Thread {
	
	SeverFile mContext;
	Socket socket;
	// 버퍼 리더 달기
	BufferedReader bufferedReader;
	BufferedWriter bufferedWriter;
	
	
	
	//의존성 컴포지션 관계
	public UserSocket(SeverFile mContext, Socket socket) {
		// 생성자에서 객체가 메모리에 올라갈 때 필요한 데이터 초기화
		this.mContext = mContext; 
		this.socket = socket;
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {// 다른 기능을 사용해야 한다면
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					try {
						String msg = bufferedReader.readLine();
						System.out.println("서버 --> UserSocket : msg : " + msg);
						mContext.broadcast("서버로 부터 받은 메세지: " + msg);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		}).start();
	}
	
	public void sendMessage(String msg) {
		try {
			bufferedWriter.write(msg + "\n");
			bufferedWriter.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	// mContext 아닌 콜백 메서드로 만들 수도 있다.

}
