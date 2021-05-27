package financialcontrol.dtos;

import java.util.Date;

import org.modelmapper.ModelMapper;

import financialcontrol.models.ExpenseModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExpenseInDto {
	
	private Integer id;
	private String name;
	private String description;
	private Boolean monthly;
	private Double value;
	private Date duoDate;
	private ExpenseTypeInDto type;
	
	private PeriodOutDto period;
	
	public ExpenseModel toEntity() {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(this, ExpenseModel.class);
	}
	
}
