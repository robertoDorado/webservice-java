package br.com.laborcode.angularcrud.utils;

public class ResponseMessageSuccess {
	
	private String message;
	
	private String method;
	
	private int statusCode;

	/**
	 * 
	 */
	public ResponseMessageSuccess() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param method
	 * @param statusCode
	 */
	public ResponseMessageSuccess(String message, String method, int statusCode) {
		super();
		this.message = message;
		this.method = method;
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
}
