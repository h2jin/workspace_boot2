package file_io.project;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;

public class WriteFrame extends JFrame implements CallbackBtnAction{
	
	private WritePanel writePanel;
	private CheckDate checkDate;

	private String text;
	
	public WriteFrame() {
		initObject();
		initSetting();
	}
	
	
	
	private void initObject() {
		checkDate = new CheckDate();
		writePanel = new WritePanel(this);		
	}

	private void initSetting() {
		setTitle("기록 작성");
		setSize(650, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		add(writePanel);
		
		setVisible(true);
		
	}



	@Override
	public void ClickedSaveBtn() {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("오늘의 기록.txt"));
			text = writePanel.getTextArea().getText();
			bw.write("오늘 날짜 : " + checkDate.checkDate());
			bw.write("\n=========================================\n");
			bw.write(text);
			bw.write("\n=========================================");
			bw.flush();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		new WriteFrame();
	}

}
