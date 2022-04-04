package ch02_1;

public class Client {
	
	public static int serialNum;
	public UserInfo creatId(String id, String pw, String name, String phoneNum) {
		serialNum++;
		return new UserInfo(removeBlank(id), removeBlank(pw), removeBlank(name), removeBlank(phoneNum));
	}
	
	
	public static String removeBlank(String str) {
		String result1 = str.trim();
		String result2 = result1.replaceAll(" ", "");
		return result2;
	}

}
