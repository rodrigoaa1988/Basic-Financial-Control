package financialcontrol.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import financialcontrol.dtos.ExpenseInDto;
import financialcontrol.services.ExpenseService;

@RequestMapping("expenses")
@RestController
public class ExpenseController {
	
	@Autowired
	private ExpenseService service;
	
	@GetMapping("")
	public ResponseEntity<List<ExpenseInDto>> getAll(){
		return ResponseEntity.ok().body(this.service.getAll());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<ExpenseInDto> getOne(@PathVariable("id") int id){
		return ResponseEntity.ok().body(this.service.getOne(id));
	}
	
	@PostMapping("")
	public ResponseEntity<ExpenseInDto> save(@Valid @RequestBody ExpenseInDto newExpense){
		return ResponseEntity.ok().body(this.service.save(newExpense));
	}
	
	@DeleteMapping("{id}")
	public void deleteById(@PathVariable Integer id) {
		this.service.deleteById(id);
	}
	
}
