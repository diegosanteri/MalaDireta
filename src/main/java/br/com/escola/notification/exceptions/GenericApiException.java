package br.com.escola.notification.exceptions;

public class GenericApiException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	private int code;

	public GenericApiException (int code, String msg) {
		super(msg);
		this.code = code;
	}

	public GenericApiException (int code, Throwable cause) {
		super(cause);
		this.code = code;
	}	
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
}
