package ch01;

import java.util.Scanner;

public class MainTest {

	public static void main(String[] args) {
		int num1 = 0;
		int num2 = 0;
		
		System.out.println("숫자를 입력해 주세요");
		Scanner sc = new Scanner(System.in);
		num1 = sc.nextInt();
		System.out.println("숫자를 입력해 주세요");
		num2 = sc.nextInt();
		System.out.println(num1 + num2);

	}

}
