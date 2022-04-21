package file_io.project;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class MainActivity extends JFrame {

	JLabel label;
	JTextArea textArea;
	JButton button;
	String result = "";
	private BorderLayout borderLayout;

	private String printDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
		return dateFormat.format(Calendar.getInstance().getTime());
	}

	public MainActivity() {
		initData();
		addActionListener();
	}

	private void initData() {
		textArea = new JTextArea();
		label = new JLabel("오늘의 기록\n" + printDate());
		button = new JButton("저장하기");
		borderLayout = new BorderLayout();

		setSize(500, 500);
		setLayout(borderLayout);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(button, BorderLayout.SOUTH);
		add(label, BorderLayout.NORTH);
		add(textArea, BorderLayout.CENTER);
	}

	private void addActionListener() {
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("버튼이 눌러졌습니다.");
				result = String.valueOf(textArea.getText());

				try {
					BufferedWriter bw = new BufferedWriter(new FileWriter("기록저장.txt"));
					bw.write(result);
					bw.flush();
					bw.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
		});
	}
	
	public static void main(String[] args) {
		new MainActivity();
	}

}
