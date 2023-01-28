package br.com.laborcode.angularcrud.utils;

public class ResponseMessagesThrowable extends Throwable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String message;

	public ResponseMessagesThrowable() {
		
	}

	public ResponseMessagesThrowable(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
