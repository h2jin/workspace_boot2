package ch03;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import dto.UserInfo;

public class MainTest1 {
	
	public static void main(String[] args) {
		
		JsonArray array = new JsonArray();
		JsonObject jsonObject1 = new JsonObject();
		
		jsonObject1.addProperty("name", "홍길동");
		jsonObject1.addProperty("age", 20);
		jsonObject1.addProperty("address", "부산");
		
		
		JsonObject jsonObject2 = new JsonObject();
		jsonObject2.addProperty("name", "이순신");
		jsonObject2.addProperty("age", 33);
		jsonObject2.addProperty("address", "서울");
		
		JsonObject jsonObject3 = new JsonObject();
		jsonObject3.addProperty("name", "세종대왕");
		jsonObject3.addProperty("age", 59);
		jsonObject3.addProperty("address", "세종시");
		
		array.add(jsonObject1);
		array.add(jsonObject2);
		array.add(jsonObject3);
		
		JsonObject jsonObject4 = new JsonObject();
		JsonObject t1 = new JsonObject();
		t1.addProperty("id", 1);
		t1.addProperty("title", "청소");
		t1.addProperty("complete", false);
		
		JsonObject t2 = new JsonObject();
		t2.addProperty("id", 2);
		t2.addProperty("title", "청소");
		t2.addProperty("comolete", true);
		
		JsonArray array1 = new JsonArray();
		array1.add(t1);
		array1.add(t2);
		
		jsonObject4.add("toDoList", array1);
		jsonObject4.addProperty("server_name", "server_1");
		
		System.out.println(array);
		System.out.println(jsonObject4);
		
		
		//모델링
		Gson gson = new Gson();
		UserInfo test = gson.fromJson(array.get(0), UserInfo.class);
		UserInfo test1 = gson.fromJson(array.get(1), UserInfo.class);
		UserInfo test2 = gson.fromJson(array.get(2), UserInfo.class);
		System.out.println(test);
		System.out.println(test1);
		System.out.println(test2);
		
		UserInfo test3 = gson.fromJson(array1.get(0), UserInfo.class);
		UserInfo test4 = gson.fromJson(array1.get(1), UserInfo.class);
//		UserInfo test5 = gson.fromJson(array1, UserInfo.class);
//		UserInfo test6 = gson.fromJson(jsonObject4, UserInfo.class);
		
//		System.out.println(test5);
//		System.out.println(test6);
		
		System.out.println(test3);
		
		
		
		
	}

}
