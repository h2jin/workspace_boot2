
package thread_ex;

import javax.swing.JFrame;

class MyRunnable2 extends JFrame {

	// 자바 문법 - 내부 익명 구현 객체를 변수에 담기
	Runnable runnable = new Runnable() {
		int grade; // run도 이것처럼 멤버변수와 같음!

		@Override
		public void run() {
			for (int i = 0; i < 10; i++) {
				System.out.println("-");
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}; // implements Runnable이랑 똑같음

	// 생성자
	public MyRunnable2() {
//		runnable.run(); //메인에서 메모리에 올이자 마자 run을 실행시키는
		// 방법 첫번째. + 선언과 동시에 초기화
	}
}

public class RunnableTest2 {

	public static void main(String[] args) {
		MyRunnable2 myRunnable2 = new MyRunnable2();
		// 여기서 run 실행 (두번째) -->
		myRunnable2.runnable.run();

	}
}
