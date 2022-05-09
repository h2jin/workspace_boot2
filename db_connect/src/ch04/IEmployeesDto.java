package ch04;

import java.util.ArrayList;

public interface IEmployeesDto {
	
	// 
	ArrayList<EmployeeDto> innerJoinTitle();
	
	void innerJoinGender(String gender);
	
	void innerJoinMinDate();
	
	void leftJoinSalary();
	
	void innerdepartment(String dept);
	
}
