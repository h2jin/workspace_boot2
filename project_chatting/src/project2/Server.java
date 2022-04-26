package project2;

import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Server extends JFrame implements ActionListener {

	// 변수
	private JPanel panel;
	private JLabel setPortLabel;
	private JTextField setPortTextField;
	private JButton creatServerBtn;
	private JButton endServerBtn;
	private JTextArea chattingTextArea;
	private JButton saveBtn;

	private ServerSocket serverSocket;
	private Socket socket;
	private int port;

	// 벡터리스트
	private Vector<Clients> clientsVector = new Vector<Clients>();
	private Vector<CreatedRoom> roomListVector = new Vector<CreatedRoom>();

	// 생성자
	public Server() {
//		initGUIData();
//		addActionListener();
//		setPortTextField.requestFocus();
	}

	// 화면 만들기
	private void initGUIData() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 410);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);

		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setBounds(10, 10, 309, 229);
		chattingTextArea = new JTextArea();
		chattingTextArea.setBounds(12, 11, 310, 230);
		scrollPane.add(chattingTextArea);
		chattingTextArea.add(scrollPane);
		chattingTextArea.setEditable(false);

		setPortLabel = new JLabel("포트번호 :");
		setPortLabel.setBounds(12, 273, 82, 15);
		panel.add(setPortLabel);

		setPortTextField = new JTextField();
		setPortTextField.setBounds(98, 270, 224, 21);
		panel.add(setPortTextField);
		setPortTextField.setColumns(10);

		creatServerBtn = new JButton("서버실행");
		creatServerBtn.setBounds(12, 315, 154, 23);
		panel.add(creatServerBtn);

		endServerBtn = new JButton("서버중지");
		endServerBtn.setBounds(168, 315, 154, 23);
		panel.add(endServerBtn);
		endServerBtn.setEnabled(false);

		setVisible(true);
	}
	
	private void addActionListener() {
		setPortTextField.addActionListener(this);
		creatServerBtn.addActionListener(this);
		endServerBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == creatServerBtn) {
			if (setPortTextField.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "포트번호를 입력해주세요.", "알림", JOptionPane.ERROR_MESSAGE);
			} else if (setPortTextField.getText().length() != 0) {
				port = Integer.parseInt(setPortTextField.getText());
				try {
					serverSocket = new ServerSocket(port);
					chattingTextArea.append("서버를 시작합니다.\n");
					connectClient(); // 포트번호 사용가능 확인하고, 연결 시작
					setPortTextField.setEnabled(false);
//					setPortTextField.setEditable(false);
					creatServerBtn.setEnabled(false);
					endServerBtn.setEnabled(true);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "잘못된 포트번호입니다", "알림", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
					creatServerBtn.setEnabled(true);
					endServerBtn.setEnabled(false);
				}

			}
		} else if (e.getSource() == endServerBtn) {
			try {
				serverSocket.close();
				clientsVector.removeAllElements();
				roomListVector.removeAllElements();
				setPortTextField.setEnabled(true); // ??
//			setPortTextField.setEditable(true);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

	}

	private void connectClient() {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						chattingTextArea.append("연결중...");
						socket = serverSocket.accept();
						Clients client = new Clients(socket);
						// 스레드 실행
					} catch (Exception e) {
						System.out.println("서버 소켓 연결 오류");
						e.printStackTrace();
					}

				}

			}
		});
		thread.start();
	}

	// 전체 사용자에게 전송
	private void broadCast(String string) {
		for (int i = 0; i < clientsVector.size(); i++) {
			Clients client = clientsVector.elementAt(i);
			client.sendMessage(string);
		}
	}

	// 내부클래스 클래스 이름 설정!
	class Clients {
		private InputStream inputStream;
		private OutputStream outputStream;
		private DataInputStream dataInputStream;
		private DataOutputStream dataOutputStream;
		private String nickname;
		private String roomName;
		private Socket clientSocket;
		private boolean isRoomExist = true;

		public Clients(Socket socket) {
			this.clientSocket = socket;
			network();
			thread.start();
		}

		private void network() {
			try {
				inputStream = clientSocket.getInputStream();
				dataInputStream = new DataInputStream(inputStream);
				outputStream = clientSocket.getOutputStream();
				dataOutputStream = new DataOutputStream(outputStream);

				nickname = dataInputStream.readUTF(); // client의 네트워크 메서드
				chattingTextArea.append("<" + nickname + ">님이 참가하였습니다.\n");
				// 기존 사용자들에게 새로운 유저 알림
				broadCast("NewUser/" + nickname);
				// 새로운 유저에게 기존 사용자들을 목록에 추가시켜줌
				for (int i = 0; i < clientsVector.size(); i++) {
					Clients client = clientsVector.elementAt(i);
					sendMessage("OriginUser/" + client.nickname);
				}
				// 기존에 있던 방 목록을 새로운 유저의 목록에 추가시켜줌
				for (int i = 0; i < roomListVector.size(); i++) {
					CreatedRoom existRoom = roomListVector.elementAt(i);
					sendMessage("OriginRoomList/" + existRoom.roomName);
				}
				// 자신을 사용자 목록에 추가시켜줌
				clientsVector.add(this);

			} catch (IOException e) {
				System.out.println("스트림 설정 오류"); // 오류 확인
				e.printStackTrace();
			}

		}

		// 클라이언트에 메세지 보내기
		private void sendMessage(String sendMessage) {
			try {
				dataOutputStream.writeUTF(sendMessage);
				dataOutputStream.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// 클라이언트의 메세지 받고 읽기
		private void divideGetMessage(String string) { // 메서드 이름 설정
			StringTokenizer stringTokenizer = new StringTokenizer(string, "/");

			String protocol = stringTokenizer.nextToken();
			String getMessage = stringTokenizer.nextToken();

			if (protocol.equals("Send")) {
				if (getMessage.equals("귓속말")) {

				}
				String message = stringTokenizer.nextToken(); // 변수 이름 설정하기
				chattingTextArea.append("<" + nickname + ">" + message);
			}
		}

		// 스레드 상속 or 포함 결정하기
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					String getMessage = dataInputStream.readUTF();
					chattingTextArea.append("<" + nickname + "> : " + getMessage + "\n"); // 없애줘도 될 것같음
					divideGetMessage(getMessage);
				} catch (IOException e) {
					System.out.println("서버 스레드 오류");
					// 사용자 접속이 끊어짐. 닫을 것은 닫아주고, 설정 변경해줘야함
					e.printStackTrace();
				}

			}
		});

	}
	
	// 내부 클래스
	class CreatedRoom {
		String roomName;
		Vector<Clients> roomUserVector = new Vector<Clients>(); 
		
		public CreatedRoom(String roomName, Clients client) {
			this.roomName = roomName;
			this.roomUserVector.add(client); //??
			client.roomName = roomName; // 변수 명 다 고치기
		}
		
		// 방에 있는 모든 사용자들에게 채팅보내기
		private void roomBroadcast(String string) {
			for (int i = 0; i < roomUserVector.size() ; i++) {
				Clients client = roomUserVector.elementAt(i);
				client.sendMessage(string);
			}
		}
		
		private void addclient(Clients client) {
			roomUserVector.add(client);
		}
		// to string 오버라이드 ??????
		
		private void removeRoom(Clients client) {
			roomUserVector.remove(client);
			if(roomUserVector.isEmpty()) {
				for(int i = 0; i < roomListVector.size(); i++) {
					CreatedRoom room = roomListVector.elementAt(i);
					if(room.roomName.equals(roomName)) {
						roomListVector.remove(this);
						broadCast("RemoveRoom/" + roomName);
//						broadCast("UserDataUpdate/Ok"); ?????
						break;
					}
				}
			}
		}
	}
	


}
