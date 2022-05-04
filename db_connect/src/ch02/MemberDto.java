package ch02;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor // 멤버변수 다 가지는 생성자. 지금은 필요없음.
@NoArgsConstructor
@ToString
public class MemberDto {
	
	
	
	@NonNull
	private String memberId;
	private String memberName;
	private String memberAddress;

}
