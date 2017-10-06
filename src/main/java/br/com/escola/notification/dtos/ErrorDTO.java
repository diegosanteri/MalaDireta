package br.com.escola.notification.dtos;


import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorDTO {

	private Integer status;
	private String message;

	/**
	 * Construtor.
	 * 
	 * @param code
	 * @param message
	 */
	public ErrorDTO(Integer code, String message) {
		this.status = code;
		this.message = message;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
