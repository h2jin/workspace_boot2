package stream_ex;

import java.io.InputStream;

public class StreamEx01 {

	public static void main(String[] args) {
		InputStream in = System.in; // 키보드에 연결됨
		//키보드를 통해 데이터를 받고 잠시 저장
		
		// 1. 키보드에 A를 인코딩하여 01000001로 컴퓨터에게 전송.
		// 2. Byte Stream으로 흘러 들어간다.(Imput Stream)
		// 3. read() 메서드로 이진수65(01000001)를 --> 65로 디코딩 한다.
		// 4. 부호화 65를 문자 A로 변환
		// InputStream 단점: 1byte만 받음. 
		// --> ABC를 입력해도 A만 받을 수 있음.
		
		try {
			int data = in.read(); // 1byte(8bit)
			System.out.println(data);
			//A입력하면 65 출력됨.
			System.out.println((char)data); // 부호화
		} catch (Exception e) {
			System.out.println("예외" + e.getMessage());
		}
	}
	//한글로 입력시, 깨져서 나옴. 영어권은 1byte로 표현가능하지만, 한글은 불가하기 때문에 깨져서 나옴.
	
	
	

}
