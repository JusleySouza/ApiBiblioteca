package br.com.library.exception.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.library.exception.CepNotFoundException;
import br.com.library.exception.DuplicateDocumentsException;
import br.com.library.exception.ExceptionResponse;
import lombok.Generated;

@Generated
@ControllerAdvice
@RestController
public class CustomizeResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception exception, WebRequest request){
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				new Date(), exception.getMessage(), request.getDescription(false));
		return new  ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(CepNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handleCepNotFoundExceptions(Exception exception, WebRequest request){
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				new Date(), exception.getMessage(), request.getDescription(false));
		return new  ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(DuplicateDocumentsException.class)
	public final ResponseEntity<ExceptionResponse> handleDuplicateDocumentsExceptions(Exception exception, WebRequest request){
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				new Date(), exception.getMessage(), request.getDescription(false));
		return new  ResponseEntity<>(exceptionResponse, HttpStatus.UNPROCESSABLE_ENTITY);
	}

}
