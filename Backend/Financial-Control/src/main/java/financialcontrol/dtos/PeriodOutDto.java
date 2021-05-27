package financialcontrol.dtos;

import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnore;

import financialcontrol.models.PeriodModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PeriodOutDto {
	
	@JsonIgnore
	private Integer id;
	@NotNull
	private Integer year;
	@NotNull
	private String month;
	
	public PeriodModel toEntity() {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(this, PeriodModel.class);
	}

}
