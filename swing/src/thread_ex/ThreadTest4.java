package thread_ex;

class PriorityThread extends Thread {
	
	
	
	@Override
	public void run() {
		int sum = 0;
		
		Thread t = Thread.currentThread();
		System.out.println(t + "start");
		
		for(int i = 0; i < 1000000; i++) {
			sum +=i;
		}
		
		System.out.println(t.getPriority() + "end");
	}
}




public class ThreadTest4 {
	public static void main(String[] args) {
		int i;
		// 1부터 10까지
		for(i = Thread.MIN_PRIORITY; i <= Thread.MAX_PRIORITY; i++) {
			
			PriorityThread pt = new PriorityThread();
			pt.setPriority(i);
			pt.start();
		}
	}
	
	// Thread 우선순위는 잘 작동되지 않는다.
}
