package ch03;

public class MySales {
	
	int totalSales;
	WriteSales onWriteSales;
	
	public MySales(int totalsales, WriteSales onWriteSales) {
		this.totalSales = totalsales;
		this.onWriteSales = onWriteSales;
	}
	
	public void complete() {
		onWriteSales.printTotalSaled(totalSales);
	}

}
