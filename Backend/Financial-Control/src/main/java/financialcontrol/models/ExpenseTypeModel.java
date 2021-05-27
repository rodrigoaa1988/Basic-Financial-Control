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

import com.fasterxml.jackson.annotation.JsonIgnore;

import financialcontrol.dtos.ExpenseTypeInDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name ="type_tab")
public class ExpenseTypeModel {
	
	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	@JsonIgnore
	@OneToMany( mappedBy = "type" )
	private List<ExpenseModel> expenses = new ArrayList<>();
	
	public ExpenseTypeInDto toDto() {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(this, ExpenseTypeInDto.class);
	}
	
}
