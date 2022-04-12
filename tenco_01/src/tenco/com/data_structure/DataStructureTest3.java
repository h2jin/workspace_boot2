package tenco.com.data_structure;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class DataStructureTest3 {
	public static void main(String[] args) {
		
		Set set;
		
		HashSet<Integer> set1 = new HashSet<>();
		set1.add(1);
		set1.add(1); //중복
		set1.add(2);
		set1.add(3);
		set1.add(3); //중복
		set1.add(3); //중복
		set1.add(4);
		
		
		System.out.println(set1.size()); //자동으로 중복된 것 삭제해줌 \
		//중복된 값을 허용하지 않을 때 set계열 사용하면 됨.
		
		//값을 삭제하는 방법
		set1.remove(1);
//		set1.clear(); //전체 삭제
		System.out.println(set1);
		
		// while문 사용하기
		//순서없기 때문에 요소 순회 반복자
		Iterator<Integer> iter = set1.iterator();
		while(iter.hasNext()) { //값이 있으면 true, 없으면 false
			System.out.println("값 확인 : " + iter.next());
		}
		
		System.out.println("==================");
		// 
		HashSet<Integer> set2 = new HashSet<>();
		
		// 무조건 6개의 번호가 저장된 set2를 완성하시오.
		
		while(set2.size() < 6) {
			set2.add(getRandom());
		}
		System.out.println(set2);
		
//		set2.add(getRandom());
//		set2.add(getRandom());
//		set2.add(getRandom());
//		set2.add(getRandom());
//		set2.add(getRandom());
//		set2.add(getRandom());
//		System.out.println(set2);
//		System.out.println(set2.size());
		
	} // end of main
	
	public static int getRandom() {
		Random rd = new Random();
		int value = rd.nextInt(45) + 1;
		return value;
		}
	
	//데이터 저장이 필요할 때 적절한 dataStructure를 사용하기. 
	//

}
