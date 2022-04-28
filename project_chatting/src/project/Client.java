package project;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.imageio.ImageIO;
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

public class Client extends JFrame implements ActionListener {
	// GUI 변수
	private JPanel panel;
	private JPanel panel2;
	private JTextField ipTextField;
	private JTextField portTextField;
	private JTextField nicknameTextField;
	private JTextField chattingTextField;
	private JTextArea showChattingTextArea;
	private JButton connectBtn;
	private JButton sendBtn;
	private JButton whisperBtn;
	private JButton joinRoomBtn;
	private JList totalUserList;
	private JList roomList;
	private JButton makeRoomBtn;
	private JButton outRoomBtn;
	private JButton endBtn;
	private BufferedImage image;

	// 소켓통신 변수
	private Socket socket;
	private String ip;
	private int port;
	private String nickname;
	private InputStream inputStream;
	private OutputStream outputStream;
	private DataInputStream dataInputStream;
	private DataOutputStream dataOutputStream;

	private JLabel imgLabel;
	private JButton changeNick;

	// JList에 추가해줄 접속자와 방들
	private Vector<String> users = new Vector<String>();
	private Vector<String> rooms = new Vector<String>();
	private String myRoomName;

	public Client() {
		initData();
		addListener();
	}

	// 프레임
	private void initData() {
		setTitle("해피챗");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setLocationRelativeTo(null);
		panel = new JPanel();
//			panel.setBorder(new EmptyBorder(5,5,5,5));

		add(panel);
		panel.setLayout(null);

		JTabbedPane Jtab = new JTabbedPane(JTabbedPane.LEFT);
		Jtab.setBounds(30, 20, 650, 500);
		panel.add(Jtab);

		imgLabel = new JLabel(new ImageIcon("images/background.png"));
		imgLabel.setBounds(0, 0, 800, 600);
		panel.add(imgLabel);

		panel2 = new JPanel();
		Jtab.addTab("서버 연결", null, panel2, null);
		panel2.setLayout(null);

		JLabel welcomeLabel = new JLabel("해피챗 접속");
		welcomeLabel.setFont(new Font("@CookieRun", Font.BOLD, 17));
		welcomeLabel.setBounds(215, 20, 300, 15);
		panel2.add(welcomeLabel);

		JLabel ipLabel = new JLabel(" IP : ");
		ipLabel.setFont(new Font("@CookieRun", Font.BOLD, 15));
		ipLabel.setBounds(150, 55, 91, 15);
		panel2.add(ipLabel);

		ipTextField = new JTextField("127.0.0.1");
		ipTextField.setFont(new Font("@CookieRun", Font.BOLD, 15));
		ipTextField.setBounds(250, 51, 199, 21);
		panel2.add(ipTextField);
		ipTextField.setColumns(10);

		JLabel port_lbl = new JLabel(" Port : ");
		port_lbl.setFont(new Font("@CookieRun", Font.BOLD, 15));
		port_lbl.setBounds(150, 102, 91, 15);
		panel2.add(port_lbl);

		portTextField = new JTextField();
		portTextField.setFont(new Font("@CookieRun", Font.BOLD, 15));
		portTextField.setBounds(250, 99, 199, 21);
		panel2.add(portTextField);
		portTextField.setColumns(10);

		JLabel userID_lbl = new JLabel(" 닉네임 : ");
		userID_lbl.setFont(new Font("@CookieRun", Font.BOLD, 15));
		userID_lbl.setBounds(150, 152, 91, 15);
		panel2.add(userID_lbl);

		nicknameTextField = new JTextField();
		nicknameTextField.setBounds(250, 149, 199, 21);
		panel2.add(nicknameTextField);
		nicknameTextField.setColumns(10);

		JLabel img_lbl = new JLabel("input the image");
		img_lbl.setIcon(new ImageIcon());
		img_lbl.setBounds(150, 243, 299, 155);
		panel2.add(img_lbl);

		connectBtn = new JButton("접속");
		connectBtn.setFont(new Font("@CookieRun", Font.BOLD, 13));
		connectBtn.setBounds(352, 192, 97, 23);
		panel2.add(connectBtn);

		JPanel panel = new JPanel();
		Jtab.addTab("대기실", null, panel, null);
		panel.setLayout(null);

		JLabel totalUser = new JLabel("현재 접속자");
		totalUser.setFont(new Font("@CookieRun", Font.BOLD, 16));
		totalUser.setBounds(40, 80, 102, 20);
		panel.add(totalUser);

		JLabel roomLabel = new JLabel("방 목록");
		roomLabel.setFont(new Font("@CookieRun", Font.BOLD, 16));
		roomLabel.setBounds(50, 280, 102, 20);
		panel.add(roomLabel);

		totalUserList = new JList();
		totalUserList.setBounds(150, 40, 320, 140);
		panel.add(totalUserList);

		roomList = new JList();
		roomList.setBounds(150, 220, 320, 140);
		panel.add(roomList);

		changeNick = new JButton("닉네임 변경");
		changeNick.setBounds(210, 190, 120, 23);
		panel.add(changeNick);

		whisperBtn = new JButton("귓속말 보내기");
		whisperBtn.setBounds(350, 190, 120, 23);
		panel.add(whisperBtn);

		makeRoomBtn = new JButton("방 만들기");
		makeRoomBtn.setBounds(150, 400, 100, 23);
		panel.add(makeRoomBtn);

		joinRoomBtn = new JButton("방 참여");
		joinRoomBtn.setBounds(260, 400, 100, 23);
		panel.add(joinRoomBtn);

		outRoomBtn = new JButton("방 나가기");
		outRoomBtn.setBounds(370, 400, 100, 23);
		panel.add(outRoomBtn);
		outRoomBtn.setEnabled(false);

		JPanel panel_2 = new JPanel();
		Jtab.addTab("채팅방", null, panel_2, null);
		panel_2.setLayout(null);

		try {
			image = ImageIO.read(new File("images/yellow.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		showChattingTextArea = new JTextArea() {
//			@Override
//			public void paint(Graphics g) {
//				g.drawImage(image, 0, 0, 590, 450 , null);
//			}
		};
		showChattingTextArea.setLineWrap(true);
		showChattingTextArea.setEnabled(false);
		showChattingTextArea.setEditable(false);
		showChattingTextArea.setFont(new Font("@CookieRun", Font.BOLD, 18));
		showChattingTextArea.setBounds(0, 0, 590, 450);
		panel_2.add(showChattingTextArea);

		chattingTextField = new JTextField();
		chattingTextField.setFont(new Font("@CookieRun", Font.BOLD, 13));
		chattingTextField.setBounds(0, 460, 454, 21);
		panel_2.add(chattingTextField);
		chattingTextField.setColumns(10);

		sendBtn = new JButton("전 송");
		sendBtn.setFont(new Font("@CookieRun", Font.BOLD, 13));
		sendBtn.setBounds(470, 460, 97, 23);
		panel_2.add(sendBtn);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setBounds(0, 0, 590, 450);
		panel_2.add(scrollPane);

		setVisible(true);

	}

	// 소켓통신
	private void connectServer() {
		try {
			Socket socket = new Socket(ip, port);
			inputStream = socket.getInputStream();
			dataInputStream = new DataInputStream(inputStream);
			outputStream = socket.getOutputStream();
			dataOutputStream = new DataOutputStream(outputStream);
			nickname = nicknameTextField.getText().trim();

			// 접속하면 서버에 전송
			sendMessage(nickname);

			// 리스트
			users.add(nickname);
			totalUserList.setListData(users);

			// 서버로부터 메세지를 계속 받을 수 있는 스레드 생성
			new Thread(new Runnable() {

				@Override
				public void run() {
					while (true) {
						try {
							String msg = dataInputStream.readUTF();
							// msg 메세지 받는 메서드에 넣어주기!
							getMessage(msg);
							// 로그
							System.out.println("서버에서 온 메세지: " + msg);
						} catch (IOException e) {
							JOptionPane.showMessageDialog(null, " 연결 오류", "알림!", JOptionPane.ERROR_MESSAGE);
							e.printStackTrace();
						}

					}

				}
			}).start();

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, " 연결 오류", "알림!", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		connectBtn.setEnabled(false);
	}

	// 서버에 접속
	private void accessServer() {

		if (ipTextField.getText().replaceAll(" ", "").length() == 0) { // null로도 해보기!
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
			connectServer();
			ipTextField.setEnabled(false);
			portTextField.setEnabled(false);
			System.out.println("서버와 연결 성공!");
		}

	}

	private void getMessage(String str) {
		StringTokenizer tokenizer = new StringTokenizer(str, "/");

		String protocol = tokenizer.nextToken();
		String message = tokenizer.nextToken();

		System.out.println("프로토콜 : " + protocol);
		System.out.println("메세지 : " + message);

		if (protocol.equals("SendMsg")) {
			System.out.println("sendMessage 실행 됨.");
			String msg = tokenizer.nextToken();
			showChattingTextArea.append(message + ":" + msg + "\n");
			showChattingTextArea.requestFocus();

		} else if (protocol.equals("NewUser")) {
			showChattingTextArea.append("<" + message + "> 가 접속했습니다\n");
			users.add(message);
			totalUserList.setListData(users);
		} else if (protocol.equals("OriginUser")) {
			users.add(message);
			totalUserList.setListData(users);
		} else if (protocol.equals("OriginRoom")) {
			rooms.add(message);
			roomList.setListData(rooms);
		} else if (protocol.equals("Whisper")) {
			String whisper = tokenizer.nextToken();
			showChattingTextArea.append("[" + message + "]가 보낸 귓속말 :" + whisper);
			showChattingTextArea.requestFocus();
		} else if (protocol.equals("MakeRoomFail")) {
			JOptionPane.showMessageDialog(null, "이미 존재하는 방 이름 입니다" , "알림", JOptionPane.ERROR_MESSAGE);
		} else if (protocol.equals("MakeRoom")) {
			System.out.println("방 생성 실행");
			myRoomName = message;
			makeRoomBtn.setEnabled(false);
			joinRoomBtn.setEnabled(false);
			outRoomBtn.setEnabled(true);
		} else if (protocol.equals("NewRoom")) {
			System.out.println("새로운 방 들어옴");
			rooms.add(message);
			roomList.setListData(rooms);
			System.out.println(rooms);
		} else if (protocol.equals("JoinRoom")) {
			System.out.println("JoinRoom 실행됨");
			
			showChattingTextArea.append("<" + message + "> 이(가) 채팅방에 입장했습니다.\n");
			myRoomName = message;
		} else if (protocol.equals("OutRoom")) {
			showChattingTextArea.append("[" + message + "] 에서 퇴장하였습니다.");
		} else if (protocol.equals("RemoveRoom")) {
			rooms.remove(message);
			roomList.setListData(rooms);
		}

	}

	// 서버에 메세지 전송
	private void sendMessage(String str) {
		try {
			dataOutputStream.writeUTF(str);
			dataOutputStream.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void addListener() {
		connectBtn.addActionListener(this);
		sendBtn.addActionListener(this);
		whisperBtn.addActionListener(this);
		makeRoomBtn.addActionListener(this);
		joinRoomBtn.addActionListener(this);
		outRoomBtn.addActionListener(this);
		ipTextField.addActionListener(this);
		portTextField.addActionListener(this);
		nicknameTextField.addActionListener(this);
		chattingTextField.addActionListener(this);
		changeNick.addActionListener(this);
		// 버튼들 액션리스너 추가해주기!
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == connectBtn) {
			accessServer();
		} else if (e.getSource() == sendBtn) {
			System.out.println("전송버튼");
			if (chattingTextField.getText().replaceAll(" ", "").length() == 0) {
				JOptionPane.showMessageDialog(null, "보낼 메세지를 입력해 주세요", "알림", JOptionPane.ERROR_MESSAGE);
				chattingTextField.requestFocus();
			} else {
				String msg = "SendMsg/" + myRoomName + "/" + nickname + "/" + chattingTextField.getText().trim();
				sendMessage(msg);// (SendMsg/닉네임/보낸메세지)
			}
		} else if (e.getSource() == whisperBtn) {
			String user = (String) totalUserList.getSelectedValue();
			if (user == null) {
				JOptionPane.showMessageDialog(null, "보낼 사람을 선택해 주세요", "알림", JOptionPane.ERROR_MESSAGE);
			} else {
				String whispering = JOptionPane.showInputDialog("귓속말");
				sendMessage("Whisper/" + nickname + "." + user + "." + whispering); // (Whispering/닉네임/보낼 사람.보낸 메세지)
			}
		} else if (e.getSource() == changeNick) {
			String nick = JOptionPane.showInputDialog("변경할 닉네임");
			sendMessage("ChangeNick/" + nick);

		} else if (e.getSource() == makeRoomBtn) {
			String roomName = JOptionPane.showInputDialog("채팅방 이름 : ");
			if (roomName == null) {
				JOptionPane.showMessageDialog(null, "방 이름을 입력하지 않았습니다.", "알림", JOptionPane.ERROR_MESSAGE);
			} else {
				sendMessage("MakeRoom/" + roomName);
			}
		} else if (e.getSource() == joinRoomBtn) {
			String joinRoom = (String) roomList.getSelectedValue();
			outRoomBtn.setEnabled(true);
			joinRoomBtn.setEnabled(false);
			sendMessage("JoinRoom/" + joinRoom);
		} else if (e.getSource() == outRoomBtn) {
			sendMessage("OutRoom/" + myRoomName);
		} else if (e.getSource() == chattingTextField) {
			if (chattingTextField.getText().replaceAll(" ", "").length() == 0) {
				JOptionPane.showMessageDialog(null, "보낼 메세지를 입력해 주세요", "알림", JOptionPane.ERROR_MESSAGE);
				chattingTextField.requestFocus();
			} else {
				String msg = "SendMsg/" + myRoomName + "/" + nickname + "/" + chattingTextField.getText().trim();
				sendMessage(msg);// (SendMsg/닉네임/보낸메세지)
			}
		} else if (e.getSource() == endBtn) {
			System.exit(0);
		}
		chattingTextField.setText("");

	}

	public static void main(String[] args) {
		new Client();
	}

}
