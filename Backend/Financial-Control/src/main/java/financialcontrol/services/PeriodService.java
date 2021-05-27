package financialcontrol.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import financialcontrol.dtos.PeriodInDto;
import financialcontrol.exceptions.ObjectNotFoundException;
import financialcontrol.models.ExpenseModel;
import financialcontrol.models.PeriodModel;
import financialcontrol.repositories.ExpenseRepository;
import financialcontrol.repositories.PeriodRepository;

@Service
public class PeriodService {
	
	@Autowired
	private PeriodRepository periodRepository;
	
	@Autowired
	private ExpenseRepository expenseRepository;
	
	public List<PeriodInDto> getAll(){
		List<PeriodModel> list = this.periodRepository.findAll();
		for(PeriodModel pm : list) {
			refreshValues(pm.getId());
		}
		return list.stream().map(x -> x.toDto()).collect(Collectors.toCollection(ArrayList::new));
	}
	
	public PeriodInDto getOne(Integer id) {
		refreshValues(id);
		return this.periodRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("period not found!")).toDto();
	}
	
	public PeriodInDto save(PeriodInDto newPeriod) {
		List<PeriodModel> periods = this.periodRepository.findAll();
		Boolean exists = false;
		for(PeriodModel pm: periods) {
			if(pm.getMonth().equals(newPeriod.getMonth()) && pm.getYear().equals(newPeriod.getYear())) {
				exists = true;
			}
		}
		if(exists) {
			throw new RuntimeException("period already exists!");
		}else {
			return this.periodRepository.save(newPeriod.toEntity()).toDto();
		}	
	}
	
	public PeriodInDto update(Integer id, PeriodInDto newPeriod) {
		Optional<PeriodModel> optObj = this.periodRepository.findById(id);
		if(optObj.isPresent()) {
			PeriodModel objFromDatabase = optObj.get();
			List<ExpenseModel> expenses = this.expenseRepository.findAll();
			Double sum = 0.00;
			for(ExpenseModel em : expenses) {
				if(em.getPeriod().getId().equals(id)) {
					sum += em.getValue();
				}
			}
			objFromDatabase.setTotalWinnings(newPeriod.getTotalWinnings());
			objFromDatabase.setTotalExpenses(sum);
			objFromDatabase.setLeftover(objFromDatabase.getTotalWinnings() - objFromDatabase.getTotalExpenses());
			return this.periodRepository.save(objFromDatabase).toDto();
		}else {
			throw new ObjectNotFoundException("period not found!");
		}
	}
	
	private void refreshValues(Integer id) {
		List<ExpenseModel> list = this.expenseRepository.findAll();
		Double sum = 0.00;
		for(ExpenseModel em: list) {
			if(em.getPeriod().getId().equals(id)) {
				sum += em.getValue();
			}
		}
		Optional<PeriodModel> optObj = this.periodRepository.findById(id);
		if(optObj.isPresent()) {
			PeriodModel pm = optObj.get();
			pm.setTotalExpenses(sum);
			pm.setLeftover(pm.getTotalWinnings() - pm.getTotalExpenses());
			update(id, pm.toDto());
		}
	}
	
	public void deleteById(Integer id) {
		this.periodRepository.deleteById(id);
	}
	
}
