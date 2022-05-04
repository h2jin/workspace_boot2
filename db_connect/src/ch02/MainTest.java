package ch02;

import java.util.ArrayList;

public class MainTest {
	
	public static void main(String[] args) {
		
		MemberInfoDao memberInfoDao = new MemberInfoDao();
		
//		ArrayList<MemberDto> data = memberInfoDao.select();
//		System.out.println(data);
		
		
		// object -->
		MemberDto dto = new MemberDto("aaa" , "강감찬", "서울시 동작구");
//		memberInfoDao.insert(dto);
//		memberInfoDao.update(dto);
		int returnRow = memberInfoDao.delete("aaa");
		System.out.println("returnRow : " + returnRow);
	}

}
