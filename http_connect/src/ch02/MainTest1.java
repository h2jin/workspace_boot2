package ch02;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import dto.Test;

public class MainTest1 {
	
	public static void main(String[] args) {
		
		// 이전까지 Json을 받는 것을 확인 (응답)
		// Json 형식으로 서버측에 데이터 보낼 수 있다.
		// 자바 문법에서 Json형식을 만드는 방법
//		String a = "{"\a\"} : { b }"; --> 가능하지만 하지 않음
		
		JsonObject jsonObject1 = new JsonObject();
		
		jsonObject1.addProperty("이름", "홍길동");
		jsonObject1.addProperty("나이", 23);
		jsonObject1.addProperty("직업", "CEO");
		jsonObject1.addProperty("취미", "노래");
		jsonObject1.addProperty("결혼여부", false);
		
		// jsonObject1의 값을 꺼내기
		System.out.println(jsonObject1);
		System.out.println("=======================================");
		System.out.println(jsonObject1.get("이름")); // 클리게 쓰면 null 출력됨
		System.out.println(jsonObject1.get("나이"));
		System.out.println(jsonObject1.get("직업"));
		System.out.println(jsonObject1.get("취미"));
		System.out.println(jsonObject1.get("결혼여부"));
		
		// 깊은 복사와 얕은 복사 개념 이해해보기 --> 자바문법
		 JsonObject b = jsonObject1; // 얕은 복사 - 래퍼런스를 전달
		 System.out.println();
		 
		 b.addProperty("안녕", "hi"); // 객체에 추가
		 System.out.println("==============");
		 System.out.println(jsonObject1); // 변경됨
		 
		 JsonObject c = jsonObject1.deepCopy(); // 깊은 복사 --> c라는 객체 하나가 생성됨
		 // ㅓjsonObject1에서는 값이 담기지 않음
		 c.addProperty("test", "1234");
		 
		 System.out.println("========================");
		 System.out.println(jsonObject1); // 변화 없음.
		 System.out.println(c);
		 
		 JsonArray array1 = new JsonArray();
		 array1.add(b);
		 array1.add(c);
		 
		 System.out.println("===============================");
		 System.out.println(array1);
		 
		 // 값을 꺼내는 방법
		 System.out.println("===============================");
		 System.out.println(array1.get(0));
		 System.out.println(array1.get(1));
		 System.out.println("===============================");
		 
		 // 모델링 -> 클래스를 만들어내서 값을 담아줌
		 Gson gson = new Gson();
		 //  하나의 오브젝트로 만들어줌
		 Test test = gson.fromJson(array1.get(0), Test.class);
		 System.out.println(test);
		 
		 
		 // { arr : [ { }, { } ] }
		 
		 JsonObject j1 = new JsonObject();
		 // 
		 JsonArray a1 = new JsonArray();
		 // 두개의 object 타입이 필요 (문자열)
		 // { "name" : "홍길동" , "age" : 10 }
		 JsonObject t1 = new JsonObject();
		 t1.addProperty("name", "홍길동");
		 t1.addProperty("age", 10);
		 // { "name" : "이순신" , "age" : 30 }
		 JsonObject t2 = new JsonObject();
		 t2.addProperty("name", "이순신");
		 t2.addProperty("age", 30);
		 
		 a1.add(t1);
		 a1.add(t2);
		 
		 // { arr : [ { "name" : "홍길동" , "age" : 10 }, { "name" : "이순신" , "age" : 30 } ] }
		 j1.add("arr" , a1); // 배열 - 오브젝트를 넣을 때는 그냥 add
		 System.out.println("=================================");
		 System.out.println(j1);
		 
		 // 데이터를 서버측에 던질 때 서버측에서 이해할 수 있게 
		 // Json같은 형식을 만들어 보내야 한다
		
		 
		 
	}

}
