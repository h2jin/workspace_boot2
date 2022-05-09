package ch03;

import java.util.ArrayList;

import ch03.ShopDbDao.CustomerDto;


public interface IShopDbDao {
	
	
	// usertbl, buytbl 결과 *
	ArrayList<CustomerDto> innerJoin1();
	
	//usertbl, buytbl null 제거, 결과 *
	void leftJoin1();
	
	//buytbl, usertbl, 결과 *
	void leftJoin2();
	
	// 사용자의 (전화번호, 주소)
	void buyInfo(String userName);
	
	

}
