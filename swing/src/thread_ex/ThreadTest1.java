package thread_ex;

public class ThreadTest1 {
	
	// 메인함수 코드의 시작점
	//메인 쓰레드가 동작하는 부분
	
	public static void main(String[] args) {
		for (int i = 0; i < 50; i++) {
			System.out.println(i + "\t");
			
			try {
				Thread.sleep(200); // 0.2초마다 
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
