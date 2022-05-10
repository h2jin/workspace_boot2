package ch05;

import java.util.ArrayList;

import ch04.EmployeeDto;

public interface IEmployeesDto {
	
	// 중첩 서브쿼리
	ArrayList<EmployeeDto> selectRetireManager(String date, String dept);
	
	//인라인뷰 서브쿼리
	ArrayList<EmployeeDto> selectRetireDeptManager(String date);
	
	// 스칼라 서브쿼리
	ArrayList<EmployeeDto> employeesSalary();
	
	// 직함 별 근무중인 직원 조회
	ArrayList<EmployeeDto> selectTitleEmployees(String title);
	
	// 입력한 액수 이상의 연봉을 받는 직원 조회
	ArrayList<EmployeeDto> selectSalaryEmployees(int salary);

}
