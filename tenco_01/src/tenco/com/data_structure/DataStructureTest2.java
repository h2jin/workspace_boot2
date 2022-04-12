package tenco.com.data_structure;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;//인터페이스
import java.util.Map.Entry;

public class DataStructureTest2 {
	public static void main(String[] args) {
		Map map;
		// 인터페이스기 때문에 바로 new 안됨, 상속받은 클래스로 불러온다.
		// key 와 value 구조로 데이터를 저장한다.
		
		HashMap<String, String> map1 = new HashMap<>(); // null 값 저장 가능.
		
		Hashtable<String , String> map2 = new Hashtable<>(); // null 값을 허용하지 않는다.
		
		map1.put("A01", "김포공항정문");
		map1.put("A02", "김포공항후문");
		map1.put("A03", "김포공항로비");
		map1.put("B01", "인천공항정문");
		map1.put("B02", "인천공항후문");
		map1.put("B03", "인천공항로비");
		map1.put("C01", null);
		
		System.out.println(map1);
 		System.out.println(map1.get("A03")); //출력-> 김포공항로비
 		System.out.println(map1.get("C01"));
 		
 		//삭제방법
 		map1.remove("c01"); //키값은 대소문자를 확실하게 구분함. 소문자로 바꿔주는 순간 제거되지 않음.
// 		map1.clear(); // 전체삭제 
 		System.out.println(map1);
 		
 		//사이즈 확인 방법
 		System.out.println(map1.size());
 		
 		//map 계열에서 for문을 사용하는 방법
 		// 첫번째 방법 - java.utill.Map.Entry
 		// for문 돌면서 map1.entrySet이 entry에 담기고 Entry<String,String>의 형태로 담긴다.
 		for(Entry<String, String> entry : map1.entrySet()) {
 			System.out.println("Key : " + entry.getKey());
 			System.out.println("Value" + entry.getValue());
 		}
 		System.out.println("==============================");
 		// 두번째 방법 - keySet 메서드 활용 --> Map 계열의 메서드
 		for(String key : map1.keySet()) {
 			System.out.println("[key]" + key);
 			System.out.println("[value]" + map1.get(key));
 		}
 		
 		
 		
	}

}
