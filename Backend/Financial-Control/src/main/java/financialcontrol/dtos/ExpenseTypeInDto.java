package financialcontrol.dtos;

import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.annotation.JsonIgnore;

import financialcontrol.models.ExpenseTypeModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExpenseTypeInDto {
	
	@JsonIgnore
	private Integer id;
	@NotNull (message = "this field can't be null")
	private String name;
	
	public ExpenseTypeModel toEntity() {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(this, ExpenseTypeModel.class);
	}
	
}
