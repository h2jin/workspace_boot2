package dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
class Todo {
	
	private int userId;
	private int id;
	private String title;
	private boolean complete;
	
}
public class Plan {
	
	private List<Todo> toDoList = new ArrayList<>(); // arraylist, vector, null 등 상관없음 nullpoint~~ 만나지 않도록 미리하는 것이 좋다.
	private String server_name;
	
	// todoList, get, set
	
	public List<Todo> getToDoList() {
		return this.toDoList;
	}
	
	public void setToDoList(List<Todo> toDoList) {
		this.toDoList = toDoList;
	}

}
