package financialcontrol.dtos;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.annotation.JsonIgnore;

import financialcontrol.models.ExpenseModel;
import financialcontrol.models.PeriodModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PeriodInDto {
	
	private Integer id;
	@NotNull
	private String month;
	@NotNull
	private Integer year;
	
	private Double totalWinnings;
	@JsonIgnore
	private List<ExpenseModel> expenses;
	private Double totalExpenses;
	private Double leftover;
	
	
	public PeriodModel toEntity() {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(this, PeriodModel.class);
	}
	

}
