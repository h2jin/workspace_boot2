package Manager;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ManagerDto {
	
	private String title;
	private String date;
	private float starScore;
	private String genre;
	private String imageFileName;
	private int audience;
	private BigDecimal sales;
	
	

}
