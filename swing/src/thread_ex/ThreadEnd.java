package thread_ex;

import java.util.Scanner;

class MyThread1 extends Thread {
	
	boolean flag = true;

		//'-' 찍는 일 -> 마이스레드1 이라는 서브 작업자가
	@Override
	public void run() {
		while (flag) {
			System.out.println("-");
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

public class ThreadEnd {

	public static void main(String[] args) {
		
		System.out.println("작업자를 생성합니다.");
		MyThread1 myThread1 = new MyThread1();
		myThread1.start();
		
		// 스캐너 받는 일 -> 메인 스레드
		System.out.println("중지 0을 입력");
		Scanner sc = new Scanner(System.in);
		int userInput = sc.nextInt();
		
		if(userInput == 0) {
//			myThread1.stop(); // deprecated (가능한 사용하지 마시오 -권장)
			myThread1.flag = false;
		}
		
		// 반대로 시작도 시킬 수 있음.
	}

}
