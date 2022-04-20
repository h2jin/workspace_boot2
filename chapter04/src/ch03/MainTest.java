package ch03;

public class MainTest {
	
	public static void main(String[] args) {
		AccountBook accountBook= new AccountBook();
		MySales mySales = new MySales(1_000_000, accountBook);
		mySales.complete();
	}

}
