package movieProject;

import com.google.gson.Gson;

public class ManagePro {
	
	private ManagerDao dao;
	private Gson gson;
	
	
	public ManagePro() {
		dao = new ManagerDao();
		gson = new Gson();
	}
	
	
	public static void main(String[] args) {
		
	}

}
