package file_io.ch01;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

public class MainTest1 {
	
	public static PrintStream out;
	public static InputStream in;
	
	public static void main(String[] args) {
		
		// 표준출력(모니터) 스트림
		// System.out
		System.out.println("출력 스트림 (모니터)");
		
		// 표준 입력(키보드) 스트림
		//System.in
		// read --> 추상메서드에 예외처리까지 되어있음.
		try {
			int k = System.in.read();
			System.out.println("k : " + k);
			System.out.println((char)k);
			// 형변환 하고싶지 않다면 InputStreamReader
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
