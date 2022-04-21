package file_io.project;

import java.awt.Color;
import java.awt.Scrollbar;
import java.awt.TextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

// 호출자 (callee)
public class WritePanel extends JPanel{
	
	private TextArea textArea;
	private JButton button;
	
	private CallbackBtnAction callbackBtnAction;
	
	public WritePanel(CallbackBtnAction callbackBtnAction) {
		this.callbackBtnAction = callbackBtnAction;
		initObject();
		setInitLayout();
		initListener();
	}
	
	private void initObject() {
		textArea = new TextArea("글 쓰기", 30, 80, Scrollbar.VERTICAL);
		button = new JButton("저장");
	}
	private void setInitLayout() {
		setSize(600, 600);
		textArea.setSize(600, 600);
		button.setBackground(Color.green);
		add(textArea);
		add(button);
	}
	private void initListener() {
		
		button.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				callbackBtnAction.ClickedSaveBtn();
			}
			
		});
		
	}

	public TextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(TextArea textArea) {
		this.textArea = textArea;
	}

	public JButton getButton() {
		return button;
	}

	public void setButton(JButton button) {
		this.button = button;
	}

	public CallbackBtnAction getCallbackBtnAction() {
		return callbackBtnAction;
	}

	public void setCallbackBtnAction(CallbackBtnAction callbackBtnAction) {
		this.callbackBtnAction = callbackBtnAction;
	}
	

}
