package financialcontrol.handlers;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import financialcontrol.exceptions.FieldMessageList;
import financialcontrol.exceptions.ObjectNotFoundException;
import financialcontrol.exceptions.StandardError;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validator(MethodArgumentNotValidException e, HttpServletRequest request) {

		FieldMessageList error = new FieldMessageList(
				new Date(),
				HttpStatus.UNPROCESSABLE_ENTITY.value(),
				"Exceção",
				"Erro ao validar dados",
				request.getRequestURI()
		);
		
		for (FieldError fe : e.getBindingResult().getFieldErrors() ) {
			 error.addError(fe.getField(), fe.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY.value()).body(error);
	}
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		StandardError error = new StandardError(
				new Date(),
				HttpStatus.NOT_FOUND.value(),
				"Object Not Found",
				e.getMessage(),
				request.getRequestURI()
		);
		return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(error);
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<StandardError> runtimeEx(RuntimeException e, HttpServletRequest request){
		StandardError error = new StandardError(
				new Date(),
				500,
				"Contate o suporte",
				e.getMessage(),
				request.getRequestURI()
		);
		return ResponseEntity.status(500).body(error);
	}
	
	
}
