package tenco.com.data_structure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class DataStructureTest1 {
	
	public static void main(String[] args) {
		
		List list0;  //-->java.util패키지에 있음 최상위의 인터페이스로 선언
		//인터페이스로 new 안됨.하지만 다형성으로 new Arraylist는 가능..
		//list계열 특징: 중간에 데이터를 추가하거나 삭제가 용이하다.
		
		//순서가 있고 (인덱스가 있고), 중복이 가능하다
		//선언 방법
		ArrayList<Integer> list1 = new ArrayList<Integer>();
		ArrayList<Integer> list2 = new ArrayList<>();
		
		//선언과 동시에 초기화
		ArrayList<Integer> list3 = new ArrayList<>(Arrays.asList(1, 2, 3));//첫번째 인덱스에 1
		
		//값 추가 방법
		list3.add(4);
		list3.add(null);
		System.out.println(list3);
		
		list3.add(1, 10);
		System.out.println(list3); //--> 뒤의 숫자들 자동으로 밀림
		
		// 삭제방법
		list3.remove(5);
		System.out.println(list3);
		// 전체삭제
//		list3.clear();
//		System.out.println(list3);
		
		//출력방법
		System.out.println(list3.get(3));
		
		//추가적인 메서드 확인
		System.out.println(list3.size());
		System.out.println(list3.isEmpty()); // is~~ 
		
		for (Integer i : list3) {
			System.out.println("i : " + i);
		}
		
		//while 문에 사용
		//요소 순회(반복자) 컬렉션 프레임워크에 저장된 요소들을 하나 씩 차례로 참조한다.
		Iterator<Integer> iter = list3.iterator();
		while(iter.hasNext()) {
			System.out.println("while: " + iter.next());
		}
		
		System.out.println(list3.contains(10)); // 검색기능 
		// 인덱스
		System.out.println(list3.indexOf(0));
		System.out.println(list3.indexOf(1));
		System.out.println(list3.indexOf(4));
		// 인덱스 값이 있으면 인덱스 번호를 반환하거나
		//없으면 -1을 반환한다.
		System.out.println(list3.indexOf(100));
		
	}

}
