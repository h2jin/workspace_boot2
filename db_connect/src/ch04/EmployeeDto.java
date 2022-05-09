package ch04;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeDto {
	
	private int emp_no;
	private String birth_date;
	private String first_name;
	private String last_name;
	private String gender;
	private String hire_date;
	
	private String title;
	private String from_date;
	
	

}
