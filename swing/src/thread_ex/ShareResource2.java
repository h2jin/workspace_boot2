package thread_ex;

class TicketSystem {
	private int ticket = 50;

	public int getTicket() {
		return ticket;
	}

	public void setTicket(int ticket) {
		this.ticket = ticket;
	}
	
	// 티켓 출력 기능
	public synchronized void ticketPrint(int ticket) {
		int currentTicket = getTicket();
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		setTicket(currentTicket - ticket);
		System.out.println("티켓이 출력되었습니다.\n"
				+ "현재 남은 티켓 수: " + getTicket());
	}
	// 티켓 충전 기능
	public void addTicket(int ticket) {
		int currentTicket;
		synchronized (this) {
			currentTicket = getTicket();
		}
		
		setTicket(currentTicket + ticket);
		System.out.println("티켓이 충전되었습니다.\n"
				+ "현재 남은 티켓 수: " + getTicket());
	}
	
	
}

class Customer extends Thread{
	TicketSystem ticketSystem;
	public Customer(TicketSystem ticketSystem) {
		this.ticketSystem = ticketSystem;
	}
	@Override
	public void run() {
		ticketSystem.ticketPrint(1);
	}
	
}

class Staff extends Thread{
	TicketSystem ticketSystem;
	public Staff(TicketSystem ticketSystem) {
		this.ticketSystem = ticketSystem;
	}
	
	@Override
	public void run() {
		ticketSystem.addTicket(10);
	}
}


public class ShareResource2 {

	public static void main(String[] args) {
		TicketSystem ticketSystem = new TicketSystem();
		Customer customer = new Customer(ticketSystem);
		Staff staff = new Staff(ticketSystem);
		
		customer.start();
		staff.start();

	}

}
