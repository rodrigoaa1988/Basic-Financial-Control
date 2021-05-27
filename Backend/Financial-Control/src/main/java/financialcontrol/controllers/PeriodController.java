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

import financialcontrol.dtos.PeriodInDto;
import financialcontrol.services.PeriodService;

@RequestMapping("periods")
@RestController
public class PeriodController {
	
	@Autowired
	private PeriodService service;
	
	@GetMapping("")
	public ResponseEntity<List<PeriodInDto>> getAll(){
		return ResponseEntity.ok().body(this.service.getAll());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<PeriodInDto> getOne(@PathVariable("id") Integer id){
		return ResponseEntity.ok().body(this.service.getOne(id));
	}
	
	@PostMapping("")
	public ResponseEntity<PeriodInDto> save(@Valid @RequestBody PeriodInDto newPeriod){
		return ResponseEntity.ok().body(this.service.save(newPeriod));
	}
	
	@PatchMapping("{id}")
	public ResponseEntity<PeriodInDto> update(@PathVariable Integer id, @RequestBody PeriodInDto newPeriod){
		return ResponseEntity.ok().body(this.service.update(id, newPeriod));
	}
	
	@DeleteMapping("{id}")
	public void deleteById(@PathVariable Integer id) {
		this.service.deleteById(id);
	}
	
}
