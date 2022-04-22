package file_io.ch02;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 
 * @author ITPS
 * 입력 스트림 
 * 파일에서 한 바이트씩 데이터를 읽기 - 1
 *  파일의 장점 -> 컴퓨터가 꺼지더라도(전류가 흐르지 않더라도) 계속 존재함. 영속성
 */
public class MainTest1 {
	
	public static void main(String[] args) {
		
		FileInputStream fis = null;
		try {
			fis = new FileInputStream("boot_a.txt");
			System.out.println((char)fis.read());
			System.out.println((char)fis.read());
			System.out.println((char)fis.read());
			System.out.println(fis.read()); // 더이상 읽을 것이 없으면 -1 출력됨.
			
		} catch (FileNotFoundException e) {
			System.out.println("파일이 없습니다.");
		} catch (IOException e) {
			System.out.println("입출력 오류 ");
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
