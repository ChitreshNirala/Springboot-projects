package net.javaguides.springboot.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobleException {

	//specific exception
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request){
		
		ErrorDetails errorDetails = new ErrorDetails(new Date(),ex.getMessage() , request.getDescription(false));
		
		return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
		
	}
	
	//globle exception
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGlobleException(Exception ex, WebRequest request){
		
		ErrorDetails errorDetails = new ErrorDetails(new Date(),ex.getMessage() , request.getDescription(false));
		
		return new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	//handling custom validation error
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> customValidationErrorHandler(MethodArgumentNotValidException exception){
		ErrorDetails errorDetail = new ErrorDetails(new Date(), "Validation Error", exception.getBindingResult().getFieldError().getDefaultMessage());
		return new ResponseEntity<>(errorDetail,HttpStatus.BAD_REQUEST);
	}
}
