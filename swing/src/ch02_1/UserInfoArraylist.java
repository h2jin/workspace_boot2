package ch02_1;

import java.util.ArrayList;

public class UserInfoArraylist implements UserInfoService {

	private ArrayList<UserInfo> userInfos = new ArrayList<UserInfo>();

	public UserInfoArraylist() {
		UserInfo userInfo1 = new UserInfo("1234", "abc", "홍길동", "010-1111-1111");
		UserInfo userInfo2 = new UserInfo("5678", "def", "이영희", "010-2222-2222");
		UserInfo userInfo3 = new UserInfo("0000", "ghi", "김철수", "010-3333-3333");
		userInfos.add(userInfo1);
		userInfos.add(userInfo2);
		userInfos.add(userInfo3);

	}

	@Override
	public void join(UserInfo userInfo) {
		System.out.println("회원등록이 완료되었습니다.");
		userInfos.add(userInfo);
	}

	@Override
	public void checkMyInfo(String id) {
		for (int i = 0; i < userInfos.size(); i++) {
			if (userInfos.get(i).getId().equals(id)) {
				System.out.println("========회원정보========\n" + userInfos.get(i) + "\n"
						+ "======================");
				return;
			}
		}
		System.out.println("정보를 찾을 수 없습니다.");
	}

	@Override
	public void updateMyInfo(String id, UserInfo userInfo) {
		int infoIndex = -1;
		for (int i = 0; i < userInfos.size(); i++) {
			if (userInfos.get(i).getId().equals(id)) {
				infoIndex = i;
			}
		}
		//
		if (infoIndex == -1) {
			System.out.println("정보를 찾을 수 없습니다.");
		} else {
			userInfos.set(infoIndex, userInfo);
		}
		showAllInfo();
	}

	@Override
	public void delete(String id) {
		boolean deleteOk = false;
		for (int i = 0; i < userInfos.size(); i++) {
			if (userInfos.get(i).getId().equals(id)) {
				userInfos.remove(i);
				deleteOk = true;
			}
		}
		if (deleteOk) {
			System.out.println("삭제하였습니다.");
		} else {
			System.out.println("정보를 찾을 수 없습니다.");
		}

	}

	@Override
	public void showAllInfo() {
		System.out.println("========== 현재 저장된 데이터 확인 ==========");
		for (UserInfo userInfo : userInfos) {
			System.out.println(userInfo);
		} System.out.println("============================================");
	}

	@Override
	public boolean checkId(String id) {
		for (int i = 0; i < userInfos.size(); i++) {
			if(id.equals(userInfos.get(i).getId())){
				return true;
			}
		}
		
		return false;
	}
	

	

}
