package ch01;

// 1. 인터페이스를 선언 한다.
/**
 * 
 * @author 임희진
 * 콜백 메서드 만드는 연습
 */
public interface ICallbackBtnAction {
	
	public abstract void ClickedAddBtn();
	// 추상메서드 추가
	public abstract void ClickedMinusButton();
	void passValue(int result);
	
}
