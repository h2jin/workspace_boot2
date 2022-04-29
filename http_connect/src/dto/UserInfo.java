package dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@ToString
@RequiredArgsConstructor
public class UserInfo {
	
	private String name;
	private int age;
	private String address;
	

}
