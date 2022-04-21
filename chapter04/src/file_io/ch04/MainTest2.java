package file_io.ch04;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/*
 * BufferedWriter를 이용한 파일에 문자 쓰기
 * 
 */
public class MainTest2 {
	
	public static void main(String[] args) {
		String text = "File writer Test";
		String filename = "result.txt";
		// 버퍼는 자기 공간이 다 채워지면 자동으로 전달한다.
		
		try {
			// 기능을 확장하는 보조 스트림.
			// 실제로 파일에다 글을 쓰는 기능은 없음.
			BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true));
			bw.write(text);
			// 버퍼이기 때문에 임시공간에 저장만 되어있고 보내지 않은 상태
			bw.flush(); // 흘려보내기. buffer를 사용하는 경우 flush 해줘야 전달 된다.
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
