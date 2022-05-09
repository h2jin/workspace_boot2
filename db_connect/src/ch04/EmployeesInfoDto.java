package ch04;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeesInfoDto implements IEmployeesDto{
	
	private DataBaseClient client;
	private Connection conn;
	ResultSet resultSet;
	
	public EmployeesInfoDto() {
		client = DataBaseClient.getInstance();
		conn = client.getConnection();
	}
	

	@Override
	public ArrayList<EmployeeDto> innerJoinTitle() {
		
		ArrayList<EmployeeDto> employeeDtos = new ArrayList<EmployeeDto>();
		
		
		try {
			String titleQuery = "SELECT e.*, t.title FROM employees AS e INNER JOIN titles AS t ON e.emp_no = t.emp_no LIMIT 10";
			PreparedStatement preparedStatement = conn.prepareStatement(titleQuery);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				EmployeeDto dto = new EmployeeDto();
				dto.setEmp_no(resultSet.getInt("emp_no"));
				dto.setBirth_date(resultSet.getString("birth_date"));
				dto.setFirst_name(resultSet.getString("first_name"));
				dto.setLast_name(resultSet.getString("last_name"));
				dto.setGender(resultSet.getString("gender"));
				dto.setHire_date(resultSet.getString("hire_date"));
				dto.setTitle(resultSet.getString("title"));
				employeeDtos.add(dto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employeeDtos;
	}

	@Override
	public void innerJoinGender(String gnder) {
		try {
			String genderQuery = "SELECT d.*, e.gender FROM dept_emp AS d INNER JOIN employees AS e ON d.emp_no = e.emp_no WHERE e.gender = 'F' LIMIT 50";
			PreparedStatement preparedStatement = conn.prepareStatement(genderQuery);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	@Override
	public void innerJoinMinDate() {
		
	}

	@Override
	public void leftJoinSalary() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void innerdepartment(String dept) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		EmployeesInfoDto employeesInfoDto = new EmployeesInfoDto();
		ArrayList<EmployeeDto> data;
		data = employeesInfoDto.innerJoinTitle();
		
		for (int i = 0; i < data.size(); i++) {
			System.out.println(data.get(i));
		}
	}
	
	

}
