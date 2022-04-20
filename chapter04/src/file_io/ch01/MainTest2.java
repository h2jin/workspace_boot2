package file_io.ch01;

import java.io.IOException;

public class MainTest2 {
	
	public static void main(String[] args) {
		System.out.println("알파벳 여러개 쓰고 엔터");
		
		int i;
		try {
			// 알파벳 여러개를 쓰고 화면에 출력할 수 있도록 코드를 변경
//			i = System.in.read();
//			System.out.println(i);
			// 엔터키를 입력하지 않으면 계속 반복식이 돌아가고, 엔터키를 누르면 연산의 결과가 false가 되어 반복믄이 종료됨.
			
			while( (i = System.in.read()) != '\n' ) {
				System.out.print("i : " + i + "");
				System.out.print((char)i);
				System.out.println("\t");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
