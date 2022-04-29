package ch03;

import java.lang.reflect.Type; //!!!
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken; // 자바가 기본으로 가지고 있는 자바의 타입을 활용하여 


public class MainTest3 {
	
	public static void main(String[] args) {
		Gson gson = new Gson();
		Student[] students = new Student[3];
		Student student1 = new Student("홍길동", 20, "부산");
		Student student2 = new Student("이순신", 33, "서울");
		Student student3 = new Student("세종대왕", 59, "세종시");
		
		students[0] = student1;
		students[1] = student2;
		students[2] = student3;
		
		String studentArr = gson.toJson(students); // 배열을 문자열로 변환함.
		
		// Object 를 형식이 있는 문자열 (Json) 으로 바꿈
//		String jsonStrArr = gson.toJson(student1);
		
		//arraylist(Object) --> JsonArray(Object)
		ArrayList<Student> list = new ArrayList<Student>();
		list.add(student1);
		list.add(student2);
		list.add(student3);
		
		// list --> String으로 변환
		String jsonArrayStr = gson.toJson(list);
		System.out.println(jsonArrayStr);
//		System.out.println("======================");
//		System.out.println(jsonArrayStr);
		
		
		// 자바에서 사용하는  Object ---> 문자열로 변환(json)
		// 서버측에 데이터를 보낼 때 서버가 이해할 수 있는 문자열 (json)
		
		//============================================
		// 서버에서 json을 던져준다면 우리가 자바에서 사용하기 위해서 class로 변환해야 한다.
		// 모델링 - dto 개념을 배웠다.
		
//		System.out.println(jsonStr);
//		// 받은 문자열을 자바에서 사용하기 위해서는 파싱을 해야한다. 문자열을 오브젝트화
//		// 파싱하는 방법 1
//		Student objs1 = gson.fromJson(jsonStr, Student.class); // 리플렉션
//		System.out.println(objs1.address);
//		System.out.println(objs1.name);
//		System.out.println(objs1.age);
		
		System.out.println("========================================");
		// 파싱하는 방법 2
//		Student[] objA1 = gson.fromJson(studentArr, Student[].class);
//		for (Student student : objA1) {
//			System.out.println(student);
//		}
//		System.out.println(objA1); //jsonStr이 오브젝트 타입이라 오류가 남.
		
		
		// 파싱하는 방법 
		// ArrayList로 만들고 싶다면?
		// 타입 명시함 !
		Type studentType = new TypeToken<ArrayList<Student>>(){}.getType();
		 ArrayList<Student> arrayList = gson.fromJson(jsonArrayStr, studentType);
		 
		 for (Student student : arrayList) {
			System.out.println(student);
		}
		
		 // 파싱하는 방법 세가지
		 //1. 단일 Object 로 만드는 방법
		 //2. 배열로 만드는 방법
		 //3. ArrayList로 만드는 방법
		 
		 
	}

}
