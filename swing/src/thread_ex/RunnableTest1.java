package thread_ex;

import javax.swing.JFrame;

// runnable 인터페이스를 구현해서 만들기 -> 두번째 방법
// 첫번째는 상속받아서 사용하기
// 왜 인터페이스를 구현해서 만드는 방법이 존재할까?
// 자바는 다중 상속이 허용되지 않으므로 다른 클래스를 상속한 
//경우 thread를 만들기 위해 Runnable interface를 구현하도록 한다.

class MyRunnable1 extends JFrame implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			System.out.print("↗");
		}

	}

}

public class RunnableTest1 {

	public static void main(String[] args) {
		// 사용하는 방법
		MyRunnable1 myRunable1 = new MyRunnable1(); 
		// Runnable을 구현한 객체는 Thread를 생성해서
		// 매개변수에 담고 Thread를 시작하면 된다.

		Thread thread1 = new Thread(myRunable1); // --> f1키 눌러서 생성자 확인하기
		thread1.start();

		Thread thread2 = new Thread(myRunable1);
		thread2.start();

	}

}
