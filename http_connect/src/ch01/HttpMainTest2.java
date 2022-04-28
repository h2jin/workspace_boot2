package ch01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import dto.Post;

public class HttpMainTest2 {
	
	public static void main(String[] args) {
		
		//문자열을 
		try {
			URL url = new URL("https://jsonplaceholder.typicode.com/posts/20");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET"); // --> REST API 
//			connection.setRequestProperty("Content-type","application/json");
			connection.connect();
			
			int statusCode = connection.getResponseCode();
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			StringBuffer buffer = new StringBuffer();
			String line = null;
			
			if(statusCode == 200) {
				while( (line = reader.readLine()) != null ) {
					buffer.append(line + "\n");
				}
			} else if (statusCode == 404) { // 방어적 코드
				System.out.println("네트워트 연결이 불안정합니다.");
			}
			
			String str = buffer.toString();
			System.out.println(str);
			System.out.println("==============================");
			
			Post post = new Post();
			// 도전과제 문자열을 파싱해서 post 객체에 값을 담아보자.
			// 잘 담겼는지 출력까지 하기
			
			post.setUserId(Integer.parseInt(str.substring(14, 15)));
			post.setId(Integer.parseInt(str.substring(25, 27)));
			post.setTitle(str.substring(40, 76));
			post.setBody(str.substring(88, 306));
			
			System.out.println(post.getUserId());
			System.out.println(post.getId());
			System.out.println(post.getTitle());
			System.out.println(post.getBody());
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}

}
