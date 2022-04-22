package socket_ex.ch04;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class SeverFile extends JFrame {

	ServerSocket serverSockek;
	Socket socket;
	BufferedReader bufferedReader;

	// ==============================
	BufferedWriter bufferedWriter; // 클라이언트 쪽으로 데이터를 보내는 녀석
	BufferedReader keyboardBufferedReader; // 스레드 내부클래스로 상속받아 만들 것
	BufferedWriter bw;
	BorderLayout borderLayout;

	JLabel label;
	JTextArea textArea;
	

	public SeverFile() {
		System.out.println("1. >>> 서버 소켓 시작 <<<");
		try {
			serverSockek = new ServerSocket(10000);
			System.out.println("2. 서버 소켓 생성 완료");

			socket = serverSockek.accept(); // 클라이언트 연결 대기중 상태가 됨.
			System.out.println("3. 클라이언트 연결 완료");

			bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			// 초기화 처리
			// 기능의 확장이기 때문에 바로 입력 받을 수 없다.
			keyboardBufferedReader = new BufferedReader(new InputStreamReader(System.in));
			// 클라이언트에게 보낼 스트림 연결 (outputStream)
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			bw = new BufferedWriter(new FileWriter("소켓 통신.txt", true));
			
			initData();
			setInitLayout();

			// 스레드 처리 - 여기서 생성해줄 것임.
			WriteThread writeThread = new WriteThread();
			Thread thread = new Thread(writeThread);
			thread.start();

			while (true) {
				String msg = bufferedReader.readLine();
				System.out.println("4. 클라이언트로 받은 메세지: " + msg);
				bw.write(msg + '\n');
				bw.flush();
				textArea.setText(textArea.getText() +'\n' + msg);
			}

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("예외 발생");
		} finally {
			try {
				bufferedReader.close();
				keyboardBufferedReader.close();
				bufferedWriter.close();
				socket.close();
				serverSockek.close();
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void initData() {
		label = new JLabel("클라이언트로부터 받은 메세지");
		textArea = new JTextArea();
		borderLayout = new BorderLayout();
	}

	public void setInitLayout() {
		setSize(600, 600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(borderLayout);
		textArea.setBounds(50, 80, 300, 300);
		add(label, BorderLayout.NORTH);
		add(textArea, BorderLayout.CENTER);
	}

	// 내부 클래스 생성
	// 실행 시점도 중요 --> while문 전에
	private class WriteThread implements Runnable {

		@Override
		public void run() {

			while (true) {
				try {
					// 키보드에서 데이터를 읽어줌
					String msg = keyboardBufferedReader.readLine();
					// 클라이언트로 데이터 보내기 --> 소켓에 연결
					bufferedWriter.write(msg + "\n");
					bufferedWriter.flush();

				} catch (IOException e) {
					e.printStackTrace();
				}

			}

		}

	}

	public static void main(String[] args) {
		new SeverFile();
	}

}
