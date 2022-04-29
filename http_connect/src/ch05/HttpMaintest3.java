package ch05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import dto.Todo1;

public class HttpMaintest3 {

	public static void main(String[] args) {

		try {
			//Http통신을 하기위한
			// 준비물 1 : 
			URL url = new URL("https://yts.mx/api/v2/list_movies.json"); // 통신을 하기 위해 주소를 만들어주는 클래스.
			// 준비물 2 : 
			HttpURLConnection connection = (HttpURLConnection) url.openConnection(); // 다운캐스팅
			
			// 부가적인 정보를 추가해서 보내기.
			connection.setRequestMethod("GET"); //보통 정보를 전달해달라는 의미
			connection.connect(); // --> 래퍼런스 가지고 있다.
			
			// 100, 200, 300, 400, 500 번대 종류가 있다.
			int statusCode = connection.getResponseCode();
//			System.out.println("statusCode : " + statusCode); // 성공을 의미. 코드
			
			// http 통신할 때 스트림을 달아야한다. 주소에 있는 문자열을 읽어주는 코드
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			StringBuffer sb = new StringBuffer();
			String line = null;
			
			if(statusCode == 200) {
				while( (line = reader.readLine()) != null ) {
					sb.append(line + "\n");
				}
			}
			
			
			String str = sb.toString(); // 데이터타입 다르므로 toString하여 String으로 바꿔줌.
			// ArrayList로 변환하기
			// 파싱처리하기
			Yts yts = new Gson().fromJson(str, Yts.class);
			System.out.println(yts.getData().getMovieCount());
			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
