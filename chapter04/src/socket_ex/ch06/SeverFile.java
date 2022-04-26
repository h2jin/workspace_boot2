package socket_ex.ch06;

import java.awt.BorderLayout;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class SeverFile extends JFrame {
	
	SeverFile mContext = this;

	ServerSocket serverSockek; // 다른 클라이언트 연결 대기 (포트번호 설정)
	
	Vector<UserSocket> sockets = new Vector<UserSocket>();

	// ==============================

	BorderLayout borderLayout;
	JLabel label;
	JTextArea textArea;

	public SeverFile() {
		System.out.println("1. >>> 서버 소켓 시작 <<<");
		try {
			serverSockek = new ServerSocket(10000); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					try {
						Socket socket = serverSockek.accept(); // 여기서 멈춰있음 계속 돌지 않음.
						UserSocket userSocket = new UserSocket(mContext, socket); //하나의 소켓이 하나의 유저 소켓과 연결
						userSocket.start();
						sockets.add(userSocket);
						
						System.out.println("계속 도나요??");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				// TODO Auto-generated method stub
				
			}
		}).start();
		
	}
	
	// 생성된 UserSocket 에 접근해서 하나씩 메세지 보내기 
	// 방송하다. broadcast
	public void broadcast(String msg) {
		for(int i = 0; i < sockets.size(); i++ ) {
			sockets.get(i).sendMessage(msg);
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

	public static void main(String[] args) {
		new SeverFile();
	}

}
