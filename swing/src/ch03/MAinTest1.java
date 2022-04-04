package ch03;

import java.util.Scanner;

public class MAinTest1 {

	public static void main(String[] args) {
		// 왜 이게 가능한지 생각해보기..
		
		//1. MyComponent 화면에 띄우기
		MyComponents components = new MyComponents();
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("글자를 입력하세요");
		String userInfo = scanner.nextLine();
		
		components.textField.setText(userInfo);
		
		//2. textField  안녕하세요 라는 글자를 코드로 세팅해주기.
		// 멤버변수에 접근해서 변경해줘야함.
		
		

	}

}
