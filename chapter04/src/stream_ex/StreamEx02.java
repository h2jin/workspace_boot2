package stream_ex;

import java.io.InputStream;
import java.io.InputStreamReader;

public class StreamEx02 {

	public static void main(String[] args) {
		InputStream in = System.in; // 키보드에 연결됨
		// 단점 해결
		InputStreamReader ir = new InputStreamReader(in); // 기능의 확장만 있음 --> 데코레이터 패턴 방식
		// 65 --> A 자동으로 부호화 해줌. 추가적인 기능 더 존재
		// ir 도 단점 존재. 사용자가 어떤 키보드버튼을 누를 지 알 수 없음. 가변적.
		
		
		try {
//			int data = in.read(); // 1byte(8bit)
			char[] data = new char[3];
			ir.read(data);
			System.out.println(data);
			// new char[1000] 배열을 만들어줌. 그러나 단점으로
			// A --> 999개의 공간 낭비, 또한 1000글자 이상은 받을 수 없음.
			// 그래서 잘 사용하지 않음. 특히 통신에서 --> 이를 해결하는 방안이 Buffer!
			
		} catch (Exception e) {
			System.out.println("예외" + e.getMessage());
		}
	}
	
	
	

}
