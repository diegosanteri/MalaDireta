package br.com.escola.notification.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StudentDTO {
	@JsonProperty("documento")
	private String document;
	
	@JsonProperty("nome")
	private String name;
	
	@JsonProperty("email")
	private String email;
	
	@JsonProperty("endereco")
	private String address;
	
	@JsonProperty("cep")
	private String cep;

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}
}
