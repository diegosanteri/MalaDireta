package br.com.escola.notification.exceptions;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class InternalErrorException extends GenericApiException {

	private static final long serialVersionUID = 1L;
	
	private static final HttpStatus HTTP_STATUS = HttpStatus.INTERNAL_SERVER_ERROR;
	
	public InternalErrorException(String msg) {
		super(HTTP_STATUS.value(), msg);
	}
}
