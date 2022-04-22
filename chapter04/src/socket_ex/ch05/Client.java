package socket_ex.ch05;

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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;



public class Client extends JFrame implements ActionListener {

	// GUI자원
	private JPanel main_pnl; //메인패널
	private JTextField hostIP_tf; // 호스트 아이피를 적는 텍스트필드
	private JTextField port_tf; //포트 번호 적는 텍스트 필드
	private JTextField userID_tf; // 유저 아이디 ..
	private JTextField chatting_tf; // 채팅 텍필
	private JTextArea viewChat_ta; // 챗을 보는 텍에
	private JButton connect_btn; // 연결 버튼
	private JButton confirm_btn; // 확인 버튼
	private JButton sendNote_btn; // 쪽지 보내기 버튼이다.
	private JButton joinRomm_btn; // 방 참여 버튼
	private JList totalList_lst; // 전체접속자 리스트
	private JList roomList_lst; // 방 리스트
	private JButton btn_makeRoom; // 방 만들기 버튼
	private JButton btn_outRoom; // 방 나가기 버튼
	private JButton btn_end; // 종료 버튼
	private JPanel panel_1; // 부가적인 패널

	// network 자원
	private Socket socket; 
	private String ip;
	private int port;
	private String user_id;
	private InputStream is; 
	private OutputStream os; 
	private DataInputStream dis; 
	private DataOutputStream dos; 

	// 그외 변수들
	private Vector<String> user_Vclist = new Vector<String>();
	private Vector<String> roomList_vc = new Vector<String>(); // 백터를 사용한 이유
	private StringTokenizer st; 
	private String my_roomName; // 사용자가 입력하는 방의 이름

		// 생성자
	public Client() {
		init();
		addListener();
	}

	private void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 474, 483);
		main_pnl = new JPanel();
		main_pnl.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(main_pnl);
		main_pnl.setLayout(null);

		JTabbedPane Jtab = new JTabbedPane(JTabbedPane.TOP); // 패널을 나눠줌.
		Jtab.setBounds(12, 27, 328, 407);
		main_pnl.add(Jtab);

		panel_1 = new JPanel();
		Jtab.addTab("로그인", null, panel_1, null);
		panel_1.setLayout(null);

		JLabel hostIP_lbl = new JLabel("Host_IP ");
		hostIP_lbl.setFont(new Font("휴먼모음T", Font.BOLD, 13));
		hostIP_lbl.setBounds(12, 25, 91, 15);
		panel_1.add(hostIP_lbl);

		hostIP_tf = new JTextField();
		hostIP_tf.setFont(new Font("휴먼모음T", Font.BOLD, 13));
		hostIP_tf.setBounds(112, 21, 199, 21);
		panel_1.add(hostIP_tf);
		hostIP_tf.setColumns(10);

		JLabel port_lbl = new JLabel("Server_Port");
		port_lbl.setFont(new Font("휴먼모음T", Font.BOLD, 13));
		port_lbl.setBounds(12, 72, 91, 15);
		panel_1.add(port_lbl);

		port_tf = new JTextField();
		port_tf.setFont(new Font("휴먼모음T", Font.BOLD, 13));
		port_tf.setBounds(112, 69, 199, 21);
		panel_1.add(port_tf);
		port_tf.setColumns(10);

		JLabel userID_lbl = new JLabel("User_ID");
		userID_lbl.setFont(new Font("휴먼모음T", Font.BOLD, 13));
		userID_lbl.setBounds(12, 122, 91, 15);
		panel_1.add(userID_lbl);

		userID_tf = new JTextField();
		userID_tf.setBounds(112, 119, 199, 21);
		panel_1.add(userID_tf);
		userID_tf.setColumns(10);

		JLabel img_lbl = new JLabel("input the image"); // 공간 차지를 위해서 넣어준 것 같음
		img_lbl.setIcon(new ImageIcon());
		img_lbl.setBounds(12, 213, 299, 155);
		panel_1.add(img_lbl);

		connect_btn = new JButton("connect");
		connect_btn.setFont(new Font("휴먼모음T", Font.BOLD, 12));
		connect_btn.setBounds(214, 162, 97, 23);
		panel_1.add(connect_btn);

		JPanel panel = new JPanel();
		Jtab.addTab("대기실", null, panel, null);
		panel.setLayout(null);

		JLabel totalList_lbl = new JLabel("전체접속자");
		totalList_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		totalList_lbl.setFont(new Font("휴먼모음T", Font.BOLD, 13));
		totalList_lbl.setBounds(12, 28, 102, 15);
		panel.add(totalList_lbl);

		JLabel roomList_lbl = new JLabel("방 리스트");
		roomList_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		roomList_lbl.setFont(new Font("휴먼모음T", Font.BOLD, 13));
		roomList_lbl.setBounds(209, 27, 102, 15);
		panel.add(roomList_lbl);

		totalList_lst = new JList();
		totalList_lst.setBounds(12, 69, 102, 257);
		panel.add(totalList_lst);

		roomList_lst = new JList();
		roomList_lst.setBounds(209, 69, 102, 257);
		panel.add(roomList_lst);

		sendNote_btn = new JButton("쪽지보내기");
		sendNote_btn.setFont(new Font("휴먼모음T", Font.BOLD, 12));
		sendNote_btn.setBounds(12, 345, 102, 23);
		panel.add(sendNote_btn);

		joinRomm_btn = new JButton("채팅방참여");
		joinRomm_btn.setFont(new Font("휴먼모음T", Font.BOLD, 12));
		joinRomm_btn.setBounds(209, 345, 102, 23);
		panel.add(joinRomm_btn);
		hostIP_tf.setText("127.0.0.1");

		JPanel panel_2 = new JPanel();
		Jtab.addTab("채팅", null, panel_2, null);
		panel_2.setLayout(null);

		viewChat_ta = new JTextArea();
		viewChat_ta.setEnabled(false);
		viewChat_ta.setEditable(false);
		viewChat_ta.setFont(new Font("휴먼모음T", Font.BOLD, 12));
		viewChat_ta.setBounds(0, 0, 323, 337);
		panel_2.add(viewChat_ta);

		chatting_tf = new JTextField();
		chatting_tf.setFont(new Font("휴먼모음T", Font.BOLD, 11));
		chatting_tf.setBounds(0, 347, 214, 21);
		panel_2.add(chatting_tf);
		chatting_tf.setColumns(10);

		confirm_btn = new JButton("전 송");
		confirm_btn.setFont(new Font("휴먼모음T", Font.BOLD, 12));
		confirm_btn.setBounds(226, 346, 97, 23);
		panel_2.add(confirm_btn);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setBounds(0, 0, 323, 337);
		panel_2.add(scrollPane);

		btn_makeRoom = new JButton("방 만들기");
		btn_makeRoom.setFont(new Font("휴먼모음T", Font.BOLD, 11));
		btn_makeRoom.setBounds(352, 93, 97, 23);
		main_pnl.add(btn_makeRoom);

		btn_outRoom = new JButton("방 나가기");
		btn_outRoom.setFont(new Font("휴먼모음T", Font.BOLD, 12));
		btn_outRoom.setBounds(352, 150, 97, 23);
		main_pnl.add(btn_outRoom);
		btn_outRoom.setEnabled(false);
		btn_end = new JButton("종료");
		btn_end.setFont(new Font("휴먼모음T", Font.BOLD, 12));
		btn_end.setBounds(352, 398, 97, 23);
		main_pnl.add(btn_end);
		setVisible(true);

	}

	private void connectServer() {
		try {
			// 서버에 접속합니다.
			socket = new Socket(ip, port); // 소켓 생성! --> 입력받은 ip와 port번호로 생성해 줄 것이다.
			network();  
		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(null, "연결실패!", "알림", 
					JOptionPane.ERROR_MESSAGE); // 오류나면 연결실패 알림 창 생성!
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "연결실패!", "알림",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void network() {
		
		try {
			is = socket.getInputStream(); //인풋스트림
			dis = new DataInputStream(is); // 데이터를 변환해주고 읽는 클래스 -> 다른 클라이언트에서 온 메세지 읽음
			os = socket.getOutputStream(); //아웃풋스트림
			dos = new DataOutputStream(os); // 데이터를 자동으로 변환해주고 쓰는 클래스 -> 서버로 메세지를 써서 보냄

			user_id = userID_tf.getText().trim(); //아이디는 텍스트필드에서 입력받은 아이디
			sendmessage(user_id); // userId 를 서버에 보내주기????

			// 벡터에 유저의 id 를 저장하고 리스트 화면에 추가시켜준다.
			user_Vclist.add(user_id);
			totalList_lst.setListData(user_Vclist); 

			// 스레드 이름 수정
			Thread cth = new Thread(new Runnable() {
				@Override
				public void run() {
					while (true) {
						try {
							// 서버로부터 수신된 메세지.
							String msg = dis.readUTF(); //데이터 인풋스트림에서 문자열을 읽을 때 사용!
							// 서버로부터 수신된 메세지를 읽고 msg에 넣어준다.
							inmessage(msg); //스트링토큰 
						} catch (IOException e) {
							try {
								user_Vclist.removeAll(user_Vclist);
								roomList_vc.removeAll(roomList_vc);
								totalList_lst.setListData(user_Vclist);
								roomList_lst.setListData(roomList_vc);
								viewChat_ta.setText("\n");
								is.close();
								os.close();
								dis.close();
								dos.close();
								socket.close();
								JOptionPane.showMessageDialog(null, "서버가 종료됨!", "알림",
										JOptionPane.ERROR_MESSAGE); // 오류 발생시 서버 종료 알림창
								break; 
							} catch (Exception e2) {
								return ;
							}
						}
					}
				}
			});
			cth.start(); // 스레드 실행시켜주기
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "연결실패!", "알림",
					JOptionPane.ERROR_MESSAGE);
		}// Stream 준비완료
		connect_btn.setEnabled(false); //연결 버튼 비활성화
	}

	private void inmessage(String str) {// str ->다른 클아이언트로부터 수신된 메세지
		
		// message = 사용자가 입력한 이름
		// msg = 사용자가 채팅창에 입력한 내용
		
		st = new StringTokenizer(str, "/"); // '/'로 구별 

		String protocol = st.nextToken(); // 멤버가 새로운멤번인지 후에 들어온 멤버인지
		String message = st.nextToken(); 

		System.out.println("프로토콜" + protocol); //프로토콜newUser or Olduser
		System.out.println("메세지" + message); 

		if (protocol.equals("NewUser")) { // 처음 들어온 유저
			user_Vclist.add(message);
			totalList_lst.setListData(user_Vclist); 
		} else if (protocol.equals("OldUser")) { // 후에 들어온 유저
			totalList_lst.setListData(user_Vclist);
		} else if (protocol.equals("Note")) { // 쪽지보내기
			st = new StringTokenizer(message, "@");
			String user = st.nextToken(); // 보낸 사람의 이름
			String note = st.nextToken(); // 쪽지를 보낸 메세지
			JOptionPane.showMessageDialog(null, note, user + "로 부터 온 메세지",
					JOptionPane.CLOSED_OPTION);
		} else if (protocol.equals("CreateRoom")) { 
			// 방만들기가 성공했을 경우(같은 이름의 방이 없는 걸 확인한 경우)
			my_roomName = message; // 다른 클라이언트에서 방 만들기를 선택한 후 적은 방 이름
			joinRomm_btn.setEnabled(false); // 방참여 비활성화
			btn_outRoom.setEnabled(true); // 방나가기 버튼 활성화
			btn_makeRoom.setEnabled(false); // 방 만들기 버튼 비활성화
		} else if (protocol.equals("CreateRoomFail")) { // 방 만들기 버튼 비활성화!
			JOptionPane.showMessageDialog(null, "같은 방 이름이 존재합니다.!", "알림",
					JOptionPane.ERROR_MESSAGE); // 알림창 생성
		} else if (protocol.equals("new_Room")) { // 방 만들기 가능한 것을 확인 후 여기서! 새로운 방 만들기 ->
			roomList_vc.add(message); 
			roomList_lst.setListData(roomList_vc); // 룸 리스트에 추가
		} else if (protocol.equals("Chatting")) { 
			String msg = st.nextToken();
			viewChat_ta.append(message + " : " + msg + "\n"); //채팅창에 메세지 출력
		} else if (protocol.equals("OldRoom")) { // 후에 들어온 유저가 생성한 방 만들어주기
			roomList_vc.add(message);
			roomList_lst.setListData(roomList_vc);
		} else if (protocol.equals("JoinRoom")) { // 방에 입장하기
			my_roomName = message; // 룸네임 -> 브로드캐스트의 두번째 문자열 
			JOptionPane.showMessageDialog(null, "채팅방 (  " + my_roomName
					+ " ) 에 입장완료", "알림", JOptionPane.INFORMATION_MESSAGE); //채팅방에 입장했다는 알림창
			viewChat_ta.setText(""); //?왜해주는거지
		} else if(protocol.equals("UserOut")) { // 방 나가기 한 경우
			user_Vclist.remove(message); // 닉네임 삭제
			sendmessage("OutRoom/"+my_roomName);
		} else if(protocol.equals("UserData_Updata")) { 
			totalList_lst.setListData(user_Vclist);
			roomList_lst.setListData(roomList_vc);
		} else if(protocol.equals("OutRoom")) {
			viewChat_ta.append("*** (( "+my_roomName+"에서 퇴장 ))***\n");
			my_roomName = null;
			btn_makeRoom.setEnabled(true);
			btn_outRoom.setEnabled(false);
		} else if(protocol.equals("EmptyRoom")) {
			roomList_vc.remove(message); // message = roomName
		//클라이언트가 강제 종료 되었고 방이 비었을때 방 목록에서 그 방을 없애준다.	
		} else if(protocol.equals("ErrorOutRoom") ) {
			roomList_vc.remove(message); // message = myCurrentRoomName
		}
	}

	private void sendmessage(String msg) { // 서버의 채팅창에 입력한 메세지를 보냄.
		try {
			dos.writeUTF(msg); //아웃풋스트림
			System.out.println("Client의 sendMessage");
			dos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// 이벤트리스너
	private void addListener() {
		connect_btn.addActionListener(this);
		confirm_btn.addActionListener(this);
		sendNote_btn.addActionListener(this);
		joinRomm_btn.addActionListener(this);
		chatting_tf.addActionListener(this);
		btn_end.addActionListener(this);
		btn_makeRoom.addActionListener(this);
		btn_outRoom.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == connect_btn) { //연결 버튼을 누른 경우
			if(hostIP_tf.getText().length() ==0) {
				hostIP_tf.setText("IP를 입력하세요");
				hostIP_tf.requestFocus();
			} else if(port_tf.getText().length() ==0) {
				port_tf.setText("포트번호를 입력하세요");
				port_tf.requestFocus();
			} else if(userID_tf.getText().length() == 0) {
				userID_tf.setText("id 를 입력하세요");
				userID_tf.requestFocus();
			} else {
				ip = hostIP_tf.getText(); //ip 번호 입력 받아서 넣어줌.
				try{
				port = Integer.parseInt(port_tf.getText().trim()); // 포트 번호 입력받아서 넣어줌
				}catch (Exception e2) {
					port_tf.setText("잘못 입력하였습니다.");
				}
				user_id = userID_tf.getText().trim(); // 아이디 입력 받은 아이디 넣어줌
				// 서버연결하기
				connectServer(); 
				setTitle("[" + user_id + " ] 님 깨알톡에 오신걸 환경합니다.");
			}
		} else if (e.getSource() == confirm_btn) { // 채팅창의 전송 버튼을 누른 경우
			System.out.println("전송버튼클릭"); 
			sendmessage("Chatting/" + my_roomName + "/" // 서버의 채팅창에 [[hj]]Chatting/null/안녕하세요 출력
					+ chatting_tf.getText().trim());
		} else if (e.getSource() == sendNote_btn) {
			System.out.println("쪽지보내기버튼 클릭");
			String user = (String) totalList_lst.getSelectedValue(); // 전체사용자리스트에서 선택한 것의 문자열을 담아줌
			if (user == null) { // 대상 선택이 없는 경우
				JOptionPane.showMessageDialog(null, "대상을 선택하세요", "알림",
						JOptionPane.ERROR_MESSAGE);
			}
			String note = JOptionPane.showInputDialog("보낼메세지"); // 옵션패널에서 사용자가 입력한 문자열을 담아줌.
			if (note != null) { // 보낼 메세지를 입력 한 경우
				sendmessage("Note/" + user + "@" + note); // 서버의 채팅창에 [[hj]]Note/hj@안농 
			}
		} else if (e.getSource() == joinRomm_btn) { 
			System.out.println("방입장버튼 클릭");
			String joinRoom = (String) roomList_lst.getSelectedValue();  // 방 리스트에서 선택된 것의 문자열을 넣어줌
			btn_outRoom.setEnabled(true); // 방에서 나가는 버튼 활성화
			btn_makeRoom.setEnabled(false); // 방 만들기 버튼 비활성화
			sendmessage("JoinRoom/" + joinRoom); // 서버의 채팅에 출력
		} else if (e.getSource() == chatting_tf) { // 채팅 텍스트 필드에 값을 넣은 경우
			if(chatting_tf.getText().length() == 0 ){ // 텍스트필드에서 엔터를 누른 경우
				System.out.println("이게 0값으로 들어가나?"); // 0 값으로 들어감. 서버화면 : [[hj]]Chatting/null/
				sendmessage("Chatting/" + my_roomName + "/"
						+ chatting_tf.getText()+"   ");
			}else {
				sendmessage("Chatting/" + my_roomName + "/" // 필드에 글을 적어 넣은 경우 서버화면에 보내진다.
						+ chatting_tf.getText());
			}
		} else if (e.getSource() == btn_makeRoom) { 
			System.out.println("방생성버튼클릭");
			String roomName = JOptionPane.showInputDialog("방 이름을 입력하세요"); //옵션화면 나옴. 
			if (roomName != null) { // 방 이름을 입력한 경우에만 
				sendmessage("CreateRoom/" + roomName); // 서버의 화면에 출력.
			}
		} else if(e.getSource() == btn_outRoom) { // 방 나가기 버튼 누른 경우
			System.out.println("방나가기버튼클릭.");
			sendmessage("OutRoom/"+my_roomName); //서버의 화면
		} else if(e.getSource() == btn_end) { // 종료 버튼을 누른 경우
			System.exit(0); // 프로그램 모두 종료
		}
		chatting_tf.setText(""); // 이건 왜 해준거집..
	}
	public static void main(String[] args) {
		new Client();
	}
}


