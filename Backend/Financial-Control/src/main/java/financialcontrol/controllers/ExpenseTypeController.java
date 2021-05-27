package financialcontrol.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import financialcontrol.dtos.ExpenseTypeInDto;
import financialcontrol.services.ExpenseTypeService;

@RequestMapping("expensetypes")
@RestController
public class ExpenseTypeController {
	
	@Autowired
	private ExpenseTypeService service;
	
	@GetMapping("")
	public ResponseEntity<List<ExpenseTypeInDto>> getAll(){
		return ResponseEntity.ok().body(this.service.getAll());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<ExpenseTypeInDto> getOne(@PathVariable("id") int id){
		return ResponseEntity.ok().body(this.service.getOne(id));
	}
	
	@PostMapping("")
	public ResponseEntity<ExpenseTypeInDto> save(@Valid @RequestBody ExpenseTypeInDto newType){
		return ResponseEntity.ok().body(this.service.save(newType));
	}
	
	@PatchMapping("{id}")
	public ResponseEntity<ExpenseTypeInDto> update(@PathVariable("id") int id, @RequestBody ExpenseTypeInDto newType){
		return ResponseEntity.ok().body(this.service.update(id, newType));
	}
	
	@DeleteMapping("{id}")
	public void deleteById(@PathVariable int id) {
		this.service.deleteById(id);
	}
	
}
