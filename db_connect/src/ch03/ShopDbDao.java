package ch03;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ch02.DBClient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

public class ShopDbDao implements IShopDbDao {

	private DBClient client;
	private Connection connection;
	ResultSet resultSet;

	public ShopDbDao() {
		client = DBClient.getInstance();
		connection = client.getConnection();
	}

	@Override
	public ArrayList<CustomerDto> innerJoin1() {

		ArrayList<CustomerDto> customerData = new ArrayList<CustomerDto>();

		try {
			String innerQuery = "SELECT * FROM userTbl AS u INNER JOIN buyTbl AS b on u.userName = b.userName";
			PreparedStatement preparedStatement = connection.prepareStatement(innerQuery);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				CustomerDto dto = new CustomerDto();
				dto.setUserName(resultSet.getString("userName"));
				dto.setBirthYear(resultSet.getString("birthYear"));
				dto.setAddr(resultSet.getString("addr"));
				dto.setMobile(resultSet.getString("mobile"));
				dto.setProudName(resultSet.getString("prodName"));
				dto.setPrice(resultSet.getString("price"));
				dto.setAmount(resultSet.getString("amount"));
				customerData.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return customerData;
	}

	//usertbl, buytbl null 제거, 결과 *
	@Override
	public void leftJoin1() {
		try {
			String leftQuery1 = "SELECT * FROM usertbl AS u LEFT JOIN buyTbl AS b ON u.userName = b.userName WHERE b.userName IS NOT NULL";
			PreparedStatement preparedStatement = connection.prepareStatement(leftQuery1);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				System.out.println(resultSet.getString("userName"));
				System.out.println(resultSet.getString("prodName"));
				System.out.println(resultSet.getString("price"));
				System.out.println(resultSet.getString("amount"));
				System.out.println(resultSet.getString("userName"));
				System.out.println(resultSet.getString("birthYear"));
				System.out.println(resultSet.getString("addr"));
				System.out.println(resultSet.getString("mobile"));
				System.out.println("==================================");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void leftJoin2() {
		try {
			String leftQuery2 = "SELECT * FROM buytbl AS b LEFT JOIN userTbl AS u ON b.userName = u.userName";
			PreparedStatement preparedStatement = connection.prepareStatement(leftQuery2);
			
resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				System.out.println(resultSet.getString("userName"));
				System.out.println(resultSet.getString("prodName"));
				System.out.println(resultSet.getString("price"));
				System.out.println(resultSet.getString("amount"));
				System.out.println(resultSet.getString("userName"));
				System.out.println(resultSet.getString("birthYear"));
				System.out.println(resultSet.getString("addr"));
				System.out.println(resultSet.getString("mobile"));
				System.out.println("==================================");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}
	
	// 사용자의 (전화번호, 주소)
	@Override
	public void buyInfo(String userName) {
		try {
			String buyInfoQuery = "SELECT * FROM buytbl AS b INNER JOIN userTbl AS u ON b.userName = u.userName WHERE b.userName = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(buyInfoQuery);
			preparedStatement.setString(1, userName);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				System.out.println(resultSet.getString("userName"));
				System.out.println(resultSet.getString("prodName"));
				System.out.println(resultSet.getString("price"));
				System.out.println(resultSet.getString("amount"));
				System.out.println(resultSet.getString("userName"));
				System.out.println(resultSet.getString("birthYear"));
				System.out.println(resultSet.getString("addr"));
				System.out.println(resultSet.getString("mobile"));
				System.out.println("==================================");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	// 내부클래스
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@ToString
	class CustomerDto {
		@NonNull
		private String userName;
		private String birthYear;
		private String addr;
		private String mobile;
		private String proudName;
		private String price;
		private String amount;
	}

	public static void main(String[] args) {
		ShopDbDao employeesDao = new ShopDbDao();
		ArrayList<CustomerDto> data = employeesDao.innerJoin1();
		for (int i = 0; i < data.size(); i++) {
			System.out.println(data.get(i));
		}
		employeesDao.leftJoin1();
		employeesDao.leftJoin2();
		employeesDao.buyInfo("이승기");
	}


}
