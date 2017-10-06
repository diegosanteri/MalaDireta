package br.com.escola.notification.dtos;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DirectMailDTO {
	
	@JsonProperty("nome")
	private String name;
	
	@JsonProperty("email")
	private String email;
	
	@JsonProperty("endereco")
	private String address;
	
	@JsonProperty("cep")
	private String cep;
	
	@JsonProperty("assunto")
	private String subject;
	
	@JsonProperty("mensagem")
	private String message;
	
	@JsonIgnore
	private List<String> disciplines;
	
	public DirectMailDTO(){
		disciplines = new ArrayList<String>();
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
	
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

	public void addDisciplines(String discipline) {
		this.disciplines.add(discipline);
	}	
	
	public List<String> getDisciplines() {
		return disciplines;
	}
}
