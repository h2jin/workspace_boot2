package project1;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Client extends JFrame implements ActionListener {

	// 액션 리스너를 상속받아서 처리함.

	private JPanel panel; // ?
	private JPanel panel2; // ?
	private JTextField ipTextField;
	private JTextField portTextField;
	private JTextField nicknameTextField;
	private JTextField chattingTextField;
	private JTextArea showChattingTextArea;
	private JButton connectBtn;
	private JButton sendBtn; // ?
//	private JButton sendWhisperBtn; // 귓속말 버튼 말고 기능으로 구현하기
	private JButton joinRoomBtn;
	private JList totalUserList;
	private JList roomList;
	private JButton makeRoomBtn;
	private JButton outRoomBtn;
	private JButton endBtn; // ?

	// 변수 선언
	private Socket socket;
	private String ip;
	private int port;
	private String nickname;
	private InputStream inputStream;
	private OutputStream outputStream;
	private DataInputStream dataInputStream;
	private DataOutputStream dataOutputStream;

	// 벡터
//	private Vector<String> userListVector = new Vector<String>();
//	private Vector<String> roomListVector = new Vector<String>();
	private StringTokenizer stringTokenizer;
	private String setRoomName; // 사용자가 설정한 방의 이름
	
	// 콜백메서드 ==========================================================================
//	CallbackCheckMessage callbackCheckMessage;
	//=====================================================================================

	// 생성자
	public Client() {
		initGUIData();
		addListener();
	}

	// 화면 프레임 만들기
	private void initGUIData() {
		setTitle("해피챗");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 500);
		setLocationRelativeTo(null);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5,5,5,5));
		setContentPane(panel);
		panel.setLayout(null);
		
		JTabbedPane Jtab = new JTabbedPane(JTabbedPane.TOP);
		Jtab.setBounds(12, 27, 328, 407);
		panel.add(Jtab);
		
		panel2 = new JPanel();
		Jtab.addTab("서버 연결", null, panel2, null);
		panel2.setLayout(null);
		
		JLabel hostIP_lbl = new JLabel("Host_IP ");
		hostIP_lbl.setFont(new Font("휴먼모음T", Font.BOLD, 13));
		hostIP_lbl.setBounds(12, 25, 91, 15);
		panel2.add(hostIP_lbl);
		
		ipTextField = new JTextField();
		ipTextField.setFont(new Font("휴먼모음T", Font.BOLD, 13));
		ipTextField.setBounds(112, 21, 199, 21);
		panel2.add(ipTextField);
		ipTextField.setColumns(10);
		
		JLabel port_lbl = new JLabel("Server_Port");
		port_lbl.setFont(new Font("휴먼모음T", Font.BOLD, 13));
		port_lbl.setBounds(12, 72, 91, 15);
		panel2.add(port_lbl);

		portTextField = new JTextField();
		portTextField.setFont(new Font("휴먼모음T", Font.BOLD, 13));
		portTextField.setBounds(112, 69, 199, 21);
		panel2.add(portTextField);
		portTextField.setColumns(10);

		JLabel userID_lbl = new JLabel("User_ID");
		userID_lbl.setFont(new Font("휴먼모음T", Font.BOLD, 13));
		userID_lbl.setBounds(12, 122, 91, 15);
		panel2.add(userID_lbl);

		nicknameTextField = new JTextField();
		nicknameTextField.setBounds(112, 119, 199, 21);
		panel2.add(nicknameTextField);
		nicknameTextField.setColumns(10);
		
		JLabel img_lbl = new JLabel("input the image");
		img_lbl.setIcon(new ImageIcon());
		img_lbl.setBounds(12, 213, 299, 155);
		panel2.add(img_lbl);

		connectBtn = new JButton("connect");
		connectBtn.setFont(new Font("휴먼모음T", Font.BOLD, 12));
		connectBtn.setBounds(214, 162, 97, 23);
		panel2.add(connectBtn);
		
		JPanel panel_2 = new JPanel();
		Jtab.addTab("채팅", null, panel_2, null);
		panel_2.setLayout(null);
		
		showChattingTextArea = new JTextArea();
		showChattingTextArea.setEnabled(false);
		showChattingTextArea.setEditable(false);
		showChattingTextArea.setFont(new Font("휴먼모음T", Font.BOLD, 12));
		showChattingTextArea.setBounds(0, 0, 323, 337);
		panel_2.add(showChattingTextArea);

		chattingTextField = new JTextField();
		chattingTextField.setFont(new Font("휴먼모음T", Font.BOLD, 11));
		chattingTextField.setBounds(0, 347, 214, 21);
		panel_2.add(chattingTextField);
		chattingTextField.setColumns(10);

		sendBtn = new JButton("전 송");
		sendBtn.setFont(new Font("휴먼모음T", Font.BOLD, 12));
		sendBtn.setBounds(226, 346, 97, 23);
		panel_2.add(sendBtn);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setBounds(0, 0, 323, 337);
		panel_2.add(scrollPane);
		
		setVisible(true);
	}

	// 기능 만들기

	// 서버에 접속
	private void AccessServer() { // access?

		try {
			socket = new Socket(ip, port);
			netWork();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 입출력 스트림 설정, 서버로부터 메세지 받는 기능

	private void netWork() { // 메서드 이름 생각해보기
		try {
			inputStream = socket.getInputStream();
			dataInputStream = new DataInputStream(inputStream);
			outputStream = socket.getOutputStream();
			dataOutputStream = new DataOutputStream(outputStream);

			nickname = nicknameTextField.getText().trim(); // 문자열 앞 뒤 공백 제거
//			userListVector.add(nickname);
//			totalUserList.setListData(userListVector); // ?? 리스트에 유저백터(배열)추가
			// 유저 닉네임 서버에 보내주기

			// 서버로부터 정보를 받는 스레드 생성
			Thread readServer = new Thread(new Runnable() {

				@Override
				public void run() {
					while (true) {
						try {
							String getMessage = dataInputStream.readUTF();
							// TODO 서버로부터 받은 메세지를 프로토콜로 구분하여 적절한 처리

						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

				}
			});
			readServer.start();// 한번에 안됨...

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, " 연결 오류", "알림!", JOptionPane.ERROR_MESSAGE);// 오류 알림창
		}
		connectBtn.setEnabled(false); // 연결 됐으므로 비활성화
	}

	// 서버로부터 받은 메세지를 구분하여 기능 처리
	private void divideGetMessage(String str) {

		stringTokenizer = new StringTokenizer(str, "/");

		String protocol = stringTokenizer.nextToken();
		String getMessage = stringTokenizer.nextToken(); // TODO 서버에서 보내는 메세지 항상 바뀜. 변수이름 나중에 바꿔주기!!

	}

	// 서버에게 메세지를 보내기
	private void sendMessage(String msg) {
		try {
			dataOutputStream.writeUTF(msg);
			System.out.println("클라이언트 프로토콜/메세지 보냄");
			dataOutputStream.flush();
		} catch (IOException e) {
			System.out.println("클라이언트 sendMessage 오류");
			e.printStackTrace();
		}

	}
	
	private void addListener() {
		connectBtn.addActionListener(this);
		ipTextField.addActionListener(this);
		portTextField.addActionListener(this);
		nicknameTextField.addActionListener(this);
		chattingTextField.addActionListener(this);
		sendBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == connectBtn) {
			System.out.println("서버와 연결");
			if (ipTextField.getText().length() == 0) { // null로도 해보기!
				JOptionPane.showMessageDialog(null, "IP번호를 입력해주세요", "알림", JOptionPane.ERROR_MESSAGE);
				ipTextField.requestFocus();
			} else if (portTextField.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "포트번호를 입력해주세요", "알림", JOptionPane.ERROR_MESSAGE);
				portTextField.requestFocus();
			} else if (nicknameTextField.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "닉네임을 입력해주세요", "알림", JOptionPane.ERROR_MESSAGE);
				nicknameTextField.requestFocus();
			} else {
				ip = ipTextField.getText();
				port = Integer.parseInt(portTextField.getText().trim());
//				nickname = nicknameTextField.getText().trim();
				AccessServer();
				ipTextField.setEnabled(false);
				portTextField.setEnabled(false);
				System.out.println("서버와 연결 성공!");
			}
		} else if (e.getSource() == sendBtn) {
//			callbackCheckMessage.sendBtnClicked();
			System.out.println("전송버튼");
			if (chattingTextField.getText().replaceAll(" ", "") == null) {
				JOptionPane.showMessageDialog(null, "보낼 메세지를 입력해 주세요", "알림", JOptionPane.ERROR_MESSAGE);
				chattingTextField.requestFocus();
			} else {
				sendMessage("Send/" + setRoomName + "/" + chattingTextField.getText().trim());

			}
		} else if (e.getSource() == makeRoomBtn) {
			System.out.println("방 만들기");
			String roomName = JOptionPane.showInputDialog("방 이름을 입력해주세요");
			if (roomName != null) {
				sendMessage("CreateRoom/" + roomName);
			}
		} else if (e.getSource() == joinRoomBtn) {
			System.out.println("방 들어가기");
			String selectedRoom = (String) roomList.getSelectedValue();
			if (selectedRoom == null) {
				JOptionPane.showMessageDialog(null, "방을 선택해주세요", "알림", JOptionPane.ERROR_MESSAGE);
			} else {
				sendMessage("JoinRoom/" + nickname + "/" + selectedRoom);
			}
		} else if (e.getSource() == chattingTextField) {
			if (chattingTextField.getText().replaceAll(" ", "") == null) {
				JOptionPane.showMessageDialog(null, "보낼 메세지를 입력해 주세요", "알림", JOptionPane.ERROR_MESSAGE);
				chattingTextField.requestFocus();
			} else {
				sendMessage("Send/" + setRoomName + "/" + chattingTextField.getText());
			}
		} else if (e.getSource() == outRoomBtn) {
			System.out.println("방 나가기 버튼");
			sendMessage("OutRoom/" + setRoomName);
			
		} else if (e.getSource() == endBtn) {
			System.out.println("종료 버튼");
			System.exit(0);
		}

	}
	
	public static void main(String[] args) {
		new Client();
	}



}