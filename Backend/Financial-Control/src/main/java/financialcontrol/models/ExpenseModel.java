package financialcontrol.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.annotation.JsonIgnore;

import financialcontrol.dtos.ExpenseInDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "expense_tab")
public class ExpenseModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String description;
	private Boolean monthly;
	private Double value;
	private Date duoDate;
	
	@ManyToOne
	@JoinColumn(name = "type_tab_id")
	private ExpenseTypeModel type;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "period_tab_id")
	private PeriodModel period;
	
	public ExpenseInDto toDto() {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(this, ExpenseInDto.class);
	}
	
}
