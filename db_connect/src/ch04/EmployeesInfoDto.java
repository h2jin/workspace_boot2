package ch04;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
	public ArrayList<EmployeeDto> selectEmployeesTitle() {

		try {
			String titleQuery = "SELECT e.*, t.title FROM employees AS e INNER JOIN titles AS t ON e.emp_no = t.emp_no LIMIT 100";
			PreparedStatement preparedStatement = conn.prepareStatement(titleQuery);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
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
	public ArrayList<EmployeeDto> selectGenderDept(String gnder) {
		try {
			String genderQuery = "SELECT d.*, e.gender FROM dept_emp AS d INNER JOIN employees AS e ON d.emp_no = e.emp_no WHERE e.gender = 'F' LIMIT 100";
			PreparedStatement preparedStatement = conn.prepareStatement(genderQuery);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				EmployeeDto dto = new EmployeeDto();
				dto.setEmp_no(resultSet.getInt("emp_no"));
				dto.setDept_no(resultSet.getString("dept_no"));
				dto.setFrom_date(resultSet.getString("from_date"));
				dto.setTo_date(resultSet.getString("to_date"));
				dto.setGender(resultSet.getString("gender"));
				employeeDtos.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employeeDtos;
	}

	@Override
	public ArrayList<EmployeeDto> selectMinHireDate() {
		try {
			String minDateQuery = "SELECT a.emp_no, b.dept_no, a.hire_date FROM employees AS a INNER JOIN dept_emp AS b ON a.emp_no = b.emp_no GROUP BY b.dept_no HAVING MIN(a.hire_date)";
			PreparedStatement preparedStatement = conn.prepareStatement(minDateQuery);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				EmployeeDto dto = new EmployeeDto();
				dto.setEmp_no(resultSet.getInt("emp_no"));
				dto.setDept_no(resultSet.getString("dept_no"));
				dto.setHire_date(resultSet.getString("hire_date"));
				employeeDtos.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employeeDtos;
	}

	

	@Override
	public ArrayList<EmployeeDto> selectDeptEmployees(String dept) {
		try {
			String deptQuery = "SELECT e.*, d.dept_name FROM dept_emp AS e INNER JOIN departments AS d ON e.dept_no = d.dept_no WHERE d.dept_name = 'Development' LIMIT 100";
			PreparedStatement preparedStatement = conn.prepareStatement(deptQuery);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				EmployeeDto dto = new EmployeeDto();
				dto.setEmp_no(resultSet.getInt("emp_no"));
				dto.setDept_no(resultSet.getString("dept_no"));
				dto.setFrom_date(resultSet.getString("from_date"));
				dto.setTo_date(resultSet.getString("to_date"));
				dto.setDept_name(resultSet.getString("dept_name"));
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
		data = employeesInfoDto.selectEmployeesTitle();
		data = employeesInfoDto.selectGenderDept("F");
		data = employeesInfoDto.selectMinHireDate();
		data = employeesInfoDto.selectDeptEmployees("Finance");

		for (int i = 0; i < data.size(); i++) {
			System.out.println(data.get(i));
		}
	}

}
