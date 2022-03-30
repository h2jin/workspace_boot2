package ch05;

public class UserInfo {
	
	private String userId;
	private String password;
	private String userName;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassward() {
		return password;
	}
	public void setPassward(String passward) {
		this.password = passward;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public String toString() {
		return "UserInfo [userId=" + userId + ", passward=" + password + ", userName=" + userName + "]";
	}
	

}
