package financialcontrol.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import financialcontrol.dtos.ExpenseTypeInDto;
import financialcontrol.exceptions.ObjectNotFoundException;
import financialcontrol.models.ExpenseTypeModel;
import financialcontrol.repositories.ExpenseTypeRepository;

@Service
public class ExpenseTypeService {
	
	@Autowired
	private ExpenseTypeRepository repository;
	
	public List<ExpenseTypeInDto> getAll(){
		List<ExpenseTypeModel> list = this.repository.findAll();
		return list.stream().map(x -> x.toDto()).collect(Collectors.toCollection(ArrayList::new));
	}
	
	public ExpenseTypeInDto getOne(int id) {
		return this.repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Type not found")).toDto();
	}
	
	public ExpenseTypeInDto save(ExpenseTypeInDto newType) {
		return this.repository.save(newType.toEntity()).toDto();
	}
	
	public ExpenseTypeInDto update(int id, ExpenseTypeInDto newType) {
		Optional<ExpenseTypeModel> optObj = this.repository.findById(id);
		if(optObj.isPresent()) {
			ExpenseTypeModel objFromDatabase = optObj.get();
			objFromDatabase.setName(newType.getName());
			return this.repository.save(objFromDatabase).toDto();
		}else {
			throw new RuntimeException("Type of exepense not found");
		}
	}
	
	public void deleteById(int id) {
		this.repository.deleteById(id);
	}

}
