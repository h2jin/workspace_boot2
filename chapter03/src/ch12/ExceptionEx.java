package ch12;

public class ExceptionEx {
	public static void main(String[] args) {

		int[] arr = { 1, 2, 3, 4, 5 };

		try {
			for (int i = 0; i < 10; i++) {
				System.out.println(arr[i]);
			} // 런타임 시점의 오류
		} catch (Exception e) {
			System.out.println("예외발생");
		}
		System.out.println("코드가 여기까지 실행되나요?");
	}

}
