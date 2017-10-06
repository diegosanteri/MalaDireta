package br.com.escola.notification.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SendEmailResponseDTO {
	@JsonProperty("enviados")
	private Integer sendedEmail;

	public Integer getSendedEmail() {
		return sendedEmail;
	}

	public void setSendedEmail(Integer sendedEmail) {
		this.sendedEmail = sendedEmail;
	}
}
