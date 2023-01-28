package br.com.laborcode.angularcrud.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.laborcode.angularcrud.utils.ResponseMessagesThrowable;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BadRequest extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param cause
	 */
	public BadRequest(ResponseMessagesThrowable cause) {
		super(cause);
	}
}
