package ch03;

public class StringConcatInpl implements IStringConcat{
	
	
	// 기존 방식 인터페이스를 
	@Override
	public void makeString(String s1, String s2) {
		System.out.println("***" + s1 + s2 + "***");
	}
	

}
