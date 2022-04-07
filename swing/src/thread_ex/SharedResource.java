package thread_ex;

class BankAccount {
	private int money = 100_000;

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	// 입금 기능
	public synchronized void saveMoney(int money) {  // 1. synchronized 메서드
		// 10만원
		int currentMoney = getMoney();
		try {
			Thread.sleep(4000);
		} catch (Exception e) {
			e.printStackTrace(); // 예외 발생할 수 있기 때문에
		}
		setMoney(currentMoney + money);
		System.out.println("입금 후 계좌 잔액: " + getMoney());

	}

	// 출금 기능
	public void withdraw(int money) {
		int currentMoney;

		synchronized (this) { // 2. syncronized block 방법
			currentMoney = getMoney();
		}
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}

		setMoney(currentMoney - money);
		System.out.println("출금 후 계좌 잔액: " + getMoney());

	}

}

// 아버지는 세종시에서 입금을 한다. (네트워크가 느려서 시간이 좀 걸림)
class Father extends Thread {
	BankAccount account;

	public Father(BankAccount account) {
		this.account = account; // 메인함수에서 new
	}

	@Override
	public void run() {
		account.saveMoney(10_000);
	}
}

class Mother extends Thread {
	BankAccount account;

	public Mother(BankAccount account) {
		this.account = account;
	}

	@Override
	public void run() {
		account.withdraw(5_000);
	}
}

public class SharedResource {

	public static void main(String[] args) {
		// 현재 10만원이 저금되어있음.
		BankAccount account = new BankAccount();

		Father father = new Father(account);
		Mother mother = new Mother(account);

		// 아버지가 입금합니다.
		father.start();
		// 어머니가 출금합니다.
		mother.start();

		// 총 Thread 3개
		// 정상 처리 --> 잔액은 105_000원
		// 오류 발생! 결과
		// 출금 후 계좌 잔액: 95000
		// 입금 후 계좌 잔액: 110000
		/*
		 * 코드의 흐름 아버지의 입금기능 시간이 4초 걸림. 그 사이에 어머니의 스레드 기능이 접근하여 출금하는 기능을 호출 입금 도중에 접근하여서
		 * 현재 잔액이 10만원으로 측정됨. 그리고 입금하는 스레드 기능도 실행 당시에는 현재 잔액이 10만원이었기 때문에 11만원으로 결과값이 나온
		 * 것이다.
		 * 
		 * **결국, 공유되는 자원을 활용할 때 의도치 않은 결과를 생성할 수 있다. 해결방법 --> 동기화! (공유하는 자원이 있을 때 사용) 1.
		 * synchronized 메서드 --> lock 을 하는 방법 2. synchronizedBlock
		 */

	}

}
