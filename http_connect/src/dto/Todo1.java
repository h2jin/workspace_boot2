package dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Todo1 {
	
	private int userId;
	private int id;
	private String title;
	private boolean complete;

}
