 package thread_ex;

class MyCustomThread2 extends Thread {
	
	@Override
	public void run() {
		
		for (int i = 0; i < 20; i++) {
			System.out.println(i + " : " + Thread.currentThread());
			
		}
		
	}
	
}

public class ThreadTest3 {
	
	public static void main(String[] args) {
		//priority --> 우선순위: 1 ~10 
		// 5 --> 중간, 개발자가 약간의 우선순위를 줄 수 있음 
		//그러나 실제로 거의 차이 없음...
		
		System.out.println(Thread.currentThread());
		MyCustomThread2 t1 = new MyCustomThread2();
		MyCustomThread2 t2 = new MyCustomThread2();
		MyCustomThread2 t3 = new MyCustomThread2();
		
		
		t1.start();
		t2.start();
		t3.start();
	}
	
	/*
	 *  Thread란?
	 *  process :  프로그램이 실행되면 OS로부터 메모리를 할당 받아 프로세스 상태가 된다.
	 *  Thread : 하나의 프로세스는 하나 이상의 thread를 가지게 되고,
	 *  실제 작업을 수행하는 단위는 thread 이다.
	 *  
	 *  multi Threading : 여러 thread가 동시에 수행되는 프로그래밍. 
	 *  여러 작업이 동시에 실행되는 효과를 낸다.
	 *  thread는 각각 자신만의 작업 공간을 가진다. (context - 전반적인 환경 정보. ex 멤버변수에 대한 정보 등등) 
	 *  
	 *  각 thread는 공유하는 자원이 생길 수 있다.--> 임계영역
	 *  여러 thread가 자원을 공유하여 작업이 수행되는 경우, 서로 자원을 차지하려는 
	 *  race condition(임계영역)이 발생할 수 있어서 
	 *  의도하지 않은 결과를 만들어 낼 수 있다.
	 *  
	 *  해결방법 --> 동기화 처리
	 *  
	 */
	
	
	
	
	
	

}
