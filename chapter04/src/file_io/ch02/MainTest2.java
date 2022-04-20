package file_io.ch02;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 
 * @author ITPS 입력 스트림 파일에서 한 바이트씩 데이터를 읽기 - 1 파일의 장점 -> 컴퓨터가 꺼지더라도(전류가 흐르지
 *         않더라도) 계속 존재함. 영속성 try-with-resources : try(...) 자바 1.7 이상부터 -> 오토 클로즈
 *         제공
 */
public class MainTest2 {

	public static void main(String[] args) {
		System.out.println("시작 : 파일에서 데이터를 읽어서 화면에 출력");
		// while문 사용해서 모니터 출력
		int i;
//		FileInputStream fis = null;

		try (FileInputStream fis = new FileInputStream("boot_a.txt")) {
			while ((i = fis.read()) != -1) {
				System.out.print((char) i);
			} // 값을 저장해줘야 함. 하지 않으면 다음으로 포커스가 이동.
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

//		try {
//			fis = new FileInputStream("boot_a.txt"); // 파일과 연결
//			while( (i = fis.read()) != -1) {
//				System.out.print((char)i);
//			} // 값을 저장해줘야 함. 하지 않으면 다음으로 포커스가 이동.
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				fis.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}

		// System.in --> 키보드 연결(스트림 연결)
		System.out.println("끝");

	}

}
