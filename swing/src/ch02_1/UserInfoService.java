package ch02_1;

public interface UserInfoService {
	
	
	void join(UserInfo userInfo);
	
	void checkMyInfo(String id);
	
	void updateMyInfo(String id, UserInfo userInfo);
	
	void delete(String id);
	
	void showAllInfo();
	
	boolean checkId(String id);
	
	

}
