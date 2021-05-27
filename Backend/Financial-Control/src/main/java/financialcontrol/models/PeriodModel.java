package financialcontrol.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import financialcontrol.dtos.PeriodInDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "period_tab")
public class PeriodModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String month;
	private Integer year;
	private Double totalWinnings;
	private Double totalExpenses;
	private Double leftover;
	

	@OneToMany( mappedBy = "period")
	List<ExpenseModel> expenses = new ArrayList<>();
	
	public PeriodInDto toDto() {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(this, PeriodInDto.class);
	}

}
