package ch04;

import java.util.ArrayList;

public interface IEmployeesDto {
	
	// 직원의 직함 조회
	ArrayList<EmployeeDto> selectEmployeesTitle();
	
	// 성별 별 부서 직원 조회 
	ArrayList<EmployeeDto> selectGenderDept(String gender);
	
	// 입사일이 가장 빠른 직원 조회
	ArrayList<EmployeeDto> selectMinHireDate();
	
	// 연봉이 가장 높은 직원 조회
	ArrayList<EmployeeDto> selectMaxSalary();
	
	// 부서별 근무한 직원 조회
	ArrayList<EmployeeDto> selectDeptEmployees(String dept);
	
}
