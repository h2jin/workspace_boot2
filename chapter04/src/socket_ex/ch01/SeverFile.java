package socket_ex.ch01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SeverFile {
	
	ServerSocket serverSockek;
	Socket socket;
	BufferedReader bufferedReader;
	public SeverFile() {
		System.out.println("1. >>> 서버 소켓 시작 <<<");
		try {
			serverSockek = new ServerSocket(10000);
			System.out.println("2. 서버 소켓 생성 완료");
			
			socket = serverSockek.accept(); // 클라이언트 연결 대기중 상태가 됨.
			System.out.println("3. 클라이언트 연결 완료");
			
			bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			String msg = bufferedReader.readLine();
			System.out.println("4. 클라이언트로 받은 메세지: " + msg);
			
			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("예외 발생");
		}
	}
	
	public static void main(String[] args) {
		new SeverFile();
	}

}
