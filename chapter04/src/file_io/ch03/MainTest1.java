package file_io.ch03;

import java.io.FileOutputStream;

/**
 * 
 * 
 * @author ITPS
 * 바이트 단위 출력
 * 파일에 한 바이트씩 쓰기 - 1
 * 
 * 코드를 실행시켰을 때 기존의 데이터 무시하고 덮어써진다. --> 생성자에 true 넣어주면 추가로 넣어줌.
 */
public class MainTest1 {
	
	public static void main(String[] args) {
		//FileOutPutStream은 파일이 없다면 자동으로 파일 생성해줌
		try(FileOutputStream fos = new FileOutputStream("output_a.txt", true)) {
			/*
			 * - 코드의 흐름을 이해하기
			 * 
			 */
			fos.write(65);
			fos.write(66);
			
			fos.write('C');
			fos.write('D');
			fos.write('E');
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(">>>출력처리가 끝났습니다<<<");
	}

}
