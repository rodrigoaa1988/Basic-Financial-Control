package financialcontrol.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import financialcontrol.dtos.ExpenseInDto;
import financialcontrol.exceptions.ObjectNotFoundException;
import financialcontrol.models.ExpenseModel;
import financialcontrol.models.ExpenseTypeModel;
import financialcontrol.models.PeriodModel;
import financialcontrol.repositories.ExpenseRepository;
import financialcontrol.repositories.ExpenseTypeRepository;
import financialcontrol.repositories.PeriodRepository;

@Service
public class ExpenseService {
	
	@Autowired
	private ExpenseRepository expenseRepository;
	
	@Autowired
	private ExpenseTypeRepository typeRepository;
	
	@Autowired
	private PeriodRepository periodRepository;
	
	public List<ExpenseInDto> getAll() {
		List<ExpenseModel> list = this.expenseRepository.findAll();
		return list.stream().map(x -> x.toDto()).collect(Collectors.toCollection(ArrayList::new));
	}
	
	public ExpenseInDto getOne(int id) {
		return this.expenseRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("expense not found!")).toDto();
	}
	
	public ExpenseInDto save(ExpenseInDto newExpense) {
		List<PeriodModel> periods = this.periodRepository.findAll();
		Boolean found = false;
		for(PeriodModel pm : periods) {
			if(newExpense.getPeriod().getMonth().equals(pm.getMonth()) && newExpense.getPeriod().getYear().equals(pm.getYear())) {
				newExpense.getPeriod().setId(pm.getId());
				found = true;
			}
		}
		if(!found) {
			throw new RuntimeException("Period not found!");
		}
		ExpenseTypeModel etm = this.typeRepository.findByName(newExpense.getType().getName());
		if(etm != null) {
			newExpense.getType().setId(etm.getId());
			return this.expenseRepository.save(newExpense.toEntity()).toDto();
		}else {
			throw new RuntimeException("Type of exepense not found");
		}	
	}
	
	public ExpenseInDto update(Integer id, ExpenseInDto newExpense) {
		Optional<ExpenseModel> optObj = this.expenseRepository.findById(id);
		if(optObj.isPresent()) {
			ExpenseModel objFromDatabase = optObj.get();
			objFromDatabase.setName(newExpense.getName());
			objFromDatabase.setDescription(newExpense.getDescription());
			objFromDatabase.setMonthly(newExpense.getMonthly());
			objFromDatabase.setValue(newExpense.getValue());
			objFromDatabase.setDuoDate(newExpense.getDuoDate());
			objFromDatabase.setType(newExpense.getType().toEntity());
			return this.expenseRepository.save(objFromDatabase).toDto();
		}else {
			throw new RuntimeException("Expense not found!");
		}
	}
	
	public void deleteById(Integer id) {
		this.expenseRepository.deleteById(id);
	}
	
}
