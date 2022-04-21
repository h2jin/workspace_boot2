package file_io.ch04;

import java.io.FileWriter;

/**
 * 
 * @author ITPS 문자 단위 출력 스트림 파일에 문자 쓰기 -1
 * 
 */
public class MainTest1 {

	public static void main(String[] args) {

		try (FileWriter fw = new FileWriter("write_1.txt")) {
			
			fw.write('A'); // 문자 하나. 출력 파일에 스트림 연결하여 글자를 쓸 수 있다.
			char buf[] = {'B', 'C', 'D', 'E', 'F', 'G' };
			fw.write(buf);
			
			fw.write("\t안녕하세요 ~ 가나다라마"); // String
			fw.write(buf, 1, 2); // 1번 배열부터 2개까지
			fw.write("65");
			fw.write("/");
			fw.write(65); // 정수값을 넣는 경우 자동으로 부호화퍼리 되어 A가 출력된다.
			

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
