package br.com.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CepNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public CepNotFoundException( String exception) {
		super(exception);
	
	}

}
