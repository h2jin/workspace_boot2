package ch01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

import dto.Post;

public class HttpMainTest3 {
	
	public static void main(String[] args) {
		
		//문자열을 
		try { 
			URL url = new URL("https://jsonplaceholder.typicode.com/posts/25"); // 네이버로 주소를 바꾸면 파싱할 때 오류가 난다
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
			
			
			Gson gson = new Gson();
			Post post = gson.fromJson(str, Post.class); //리플렉션 기법..?
			
			System.out.println(post.getUserId());
			System.out.println(post.getId());
			System.out.println(post.getTitle());
			System.out.println(post.getBody());
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}

}
