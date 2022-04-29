package project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Server extends JFrame implements ActionListener {

	// GUI 변수
	private JPanel panel;
	private JPanel panel2;
	private JLabel setPortLabel;
	private JTextField setPortTextField;
	private JButton creatServerBtn;
	private JButton endServerBtn;
	private JTextArea chattingTextArea;
	private JButton saveBtn;
	private JLabel label;

	public JTextArea getChattingTextArea() {
		return chattingTextArea;
	}

	public void setChattingTextArea(JTextArea chattingTextArea) {
		this.chattingTextArea = chattingTextArea;
	}

	ServerSocket serverSocket;
	private int port;

	Server mContext = this;

	Vector<UserSocket> sockets = new Vector<UserSocket>();
	Vector<ChattingRoom> roomList = new Vector<ChattingRoom>();

	public Server() {
		initData();
		addListener();
	}

	private void initData() {
		setTitle("Happy챗 서버");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 600);
		setLocationRelativeTo(null);
		panel = new JPanel();
		add(panel);
		panel.setLayout(null);

//			label = new JLabel(new ImageIcon("images/background.png"));
//			label.setBounds(0, 0, 600, 600);
//			panel.add(label);

		chattingTextArea = new JTextArea();
		chattingTextArea.setBounds(12, 11, 560, 400);
		panel.add(chattingTextArea);

		setPortLabel = new JLabel("포트번호 : ");
		setPortLabel.setBounds(130, 423, 182, 15);
		panel.add(setPortLabel);

		setPortTextField = new JTextField();
		setPortTextField.setBounds(220, 420, 224, 21);
		panel.add(setPortTextField);
		setPortTextField.setColumns(10);

		creatServerBtn = new JButton("서버실행");
		creatServerBtn.setBounds(130, 450, 154, 23);
		panel.add(creatServerBtn);

		endServerBtn = new JButton("서버중지");
		endServerBtn.setBounds(290, 450, 154, 23);
		panel.add(endServerBtn);
		endServerBtn.setEnabled(false);

		saveBtn = new JButton("파일에 저장");
		saveBtn.setBounds(130, 475, 308, 23);
		panel.add(saveBtn);
		saveBtn.setEnabled(true);

		setVisible(true);
	}

	private void connectClient() {
		try {
			serverSocket = new ServerSocket(port);
			chattingTextArea.append("서버를 시작합니다\n");
			creatSocket();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void creatSocket() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						// 여기서 멈춰있음 계속 돌지 않음
						chattingTextArea.append("연결중...\n");
						Socket socket = serverSocket.accept();
						UserSocket userSocket = new UserSocket(mContext, socket);
						userSocket.start();
						sockets.add(userSocket);

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		}).start();
	}

	public void broadCast(String str) {
		for (int i = 0; i < sockets.size(); i++) {
			UserSocket userSocket = sockets.elementAt(i);
			userSocket.sendMessage(str);
			System.out.println("broadcast 실행");
		}
	}

	private void addListener() {
		creatServerBtn.addActionListener(this);
		setPortTextField.addActionListener(this);
		endServerBtn.addActionListener(this);
		saveBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == creatServerBtn) {
			if (setPortTextField.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "포트번호를 입력해주세요.", "알림", JOptionPane.ERROR_MESSAGE);
			} else {
				port = Integer.parseInt(setPortTextField.getText());
				// 서버 생성
				connectClient();
			}
		} else if (e.getSource() == endServerBtn) {
			try {
				serverSocket.close();
				sockets.removeAllElements();
				roomList.removeAllElements();
				setPortTextField.setEnabled(true);
				setPortTextField.setEditable(true);
				creatServerBtn.setEnabled(true);
				endServerBtn.setEnabled(false);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if (e.getSource() == saveBtn) {
			String result = chattingTextArea.getText();
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter("서버 채팅 내역.txt"));
				bw.write(result);
				bw.flush();
			} catch (IOException e1) {
				System.out.println("저장 버튼 오류");
				e1.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		new Server();
	}

}
