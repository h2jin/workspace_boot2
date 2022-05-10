package ch05;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ch04.DataBaseClient;
import ch04.EmployeeDto;

public class EmployeesInfoDto implements IEmployeesDto {

	private DataBaseClient client;
	private Connection conn;
	ResultSet resultSet;

	public EmployeesInfoDto() {
		client = DataBaseClient.getInstance();
		conn = client.getConnection();
	}

	ArrayList<EmployeeDto> employeeDtos = new ArrayList<EmployeeDto>();

	@Override
	public ArrayList<EmployeeDto> selectRetireManager(String date, String dept) {

		try {
			String whereQuery = "SELECT * FROM employees WHERE emp_no = (SELECT emp_no FROM dept_manager WHERE to_date < ? AND dept_no = (SELECT dept_no FROM departments WHERE dept_name = ?) )";
			PreparedStatement preparedStatement = conn.prepareStatement(whereQuery);
			preparedStatement.setString(1, date);
			preparedStatement.setString(2, dept);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				EmployeeDto dto = new EmployeeDto();
				dto.setEmp_no(resultSet.getInt("emp_no"));
				dto.setBirth_date(resultSet.getString("birth_date"));
				dto.setFirst_name(resultSet.getString("first_name"));
				dto.setLast_name(resultSet.getString("last_name"));
				dto.setGender(resultSet.getString("gender"));
				dto.setHire_date(resultSet.getString("hire_date"));
				employeeDtos.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return employeeDtos;
	}

	@Override
	public ArrayList<EmployeeDto> selectRetireDeptManager(String date) {
		try {
			String fromQuery = "SELECT * FROM employees AS e, (SELECT * FROM dept_manager WHERE to_date < ?) AS d WHERE e.emp_no = d.emp_no";
			PreparedStatement preparedStatement = conn.prepareStatement(fromQuery);
			preparedStatement.setString(1, date);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				EmployeeDto dto = new EmployeeDto();
				dto.setEmp_no(resultSet.getInt("emp_no"));
				dto.setBirth_date(resultSet.getString("birth_date"));
				dto.setFirst_name(resultSet.getString("first_name"));
				dto.setLast_name(resultSet.getString("last_name"));
				dto.setGender(resultSet.getString("gender"));
				dto.setHire_date(resultSet.getString("hire_date"));
				dto.setDept_no(resultSet.getString("dept_no"));
				dto.setFrom_date(resultSet.getString("from_date"));
				dto.setTo_date(resultSet.getString("to_date"));
				employeeDtos.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return employeeDtos;
	}

	@Override
	public ArrayList<EmployeeDto> employeesSalary() {
		try {
			String selectQuery = "SELECT e.emp_no, e.last_name, (SELECT salary FROM salaries AS s WHERE e.emp_no = s.emp_no GROUP BY s.emp_no) AS '연봉' FROM employees AS e LIMIT 100";
			PreparedStatement preparedStatement = conn.prepareStatement(selectQuery);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				EmployeeDto dto = new EmployeeDto();
				dto.setEmp_no(resultSet.getInt("emp_no"));
				dto.setLast_name(resultSet.getString("last_name"));
				dto.setSalary(resultSet.getInt("연봉"));
				employeeDtos.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return employeeDtos;
	}
	
	@Override
	public ArrayList<EmployeeDto> selectTitleEmployees(String title) {
		try {
			String titleQuery = "SELECT * FROM employees as e WHERE e.emp_no in(SELECT emp_no FROM titles WHERE title = ?) LIMIT 100";
			PreparedStatement preparedStatement = conn.prepareStatement(titleQuery);
			preparedStatement.setString(1, title);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				EmployeeDto dto = new EmployeeDto();
				dto.setEmp_no(resultSet.getInt("emp_no"));
				dto.setBirth_date(resultSet.getString("birth_date"));
				dto.setFirst_name(resultSet.getString("first_name"));
				dto.setLast_name(resultSet.getString("last_name"));
				dto.setGender(resultSet.getString("gender"));
				dto.setHire_date(resultSet.getString("hire_date"));
				employeeDtos.add(dto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employeeDtos;
	}
	
	@Override
	public ArrayList<EmployeeDto> selectSalaryEmployees(int salary) {
		try {
			String salaryQuery = "SELECT e.emp_no, e.last_name, e.gender, s.salary FROM employees AS e, (SELECT * FROM salaries WHERE salary >= ? GROUP BY emp_no) AS s WHERE e.emp_no = s.emp_no LIMIT 100";
			PreparedStatement preparedStatement = conn.prepareStatement(salaryQuery);
			preparedStatement.setInt(1, salary);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				EmployeeDto dto = new EmployeeDto();
				dto.setEmp_no(resultSet.getInt("emp_no"));
				dto.setLast_name(resultSet.getString("last_name"));
				dto.setGender(resultSet.getString("gender"));
				dto.setSalary(resultSet.getInt("salary"));
				employeeDtos.add(dto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employeeDtos;
	}

	public static void main(String[] args) {
		EmployeesInfoDto employeesInfoDto = new EmployeesInfoDto();
		ArrayList<EmployeeDto> data;
		
		data = employeesInfoDto.selectRetireManager("2000-01-01", "development");
		data = employeesInfoDto.selectRetireDeptManager("2000-01-01");
		data = employeesInfoDto.employeesSalary();
		data = employeesInfoDto.selectTitleEmployees("Staff");
		data = employeesInfoDto.selectSalaryEmployees(80000);

		for (int i = 0; i < data.size(); i++) {
			System.out.println(data.get(i));
		}

	}



}
