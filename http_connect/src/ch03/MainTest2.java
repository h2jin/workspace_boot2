package ch03;

import java.util.ArrayList;

import com.google.gson.Gson;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
class Student {
	
	String name;
	int age;
	String address;
	
}


public class MainTest2 {
	
	public static void main(String[] args) {
		
		Student student1 = new Student("홍길동", 20, "부산");
		Student student2 = new Student("이순신", 33, "서울");
		Student student3 = new Student("세종대왕", 59, "세종시");
		
		// Object 를 형식이 있는 문자열 (Json) 으로 바꿈
		Gson gson = new Gson();
		String jsonStr = gson.toJson(student1);
		System.out.println(jsonStr);
		
		//arraylist(Object) --> JsonArray(Object)
		ArrayList<Student> list = new ArrayList<Student>();
		list.add(student1);
		list.add(student2);
		list.add(student3);
		
		String jsonArrayStr = gson.toJson(list);
		System.out.println("======================");
		System.out.println(jsonArrayStr);
		
		
	}

}
