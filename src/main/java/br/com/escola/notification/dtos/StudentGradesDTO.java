package br.com.escola.notification.dtos;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StudentGradesDTO {
	
	@JsonProperty("cpf")
	private String cpf;
	
	@JsonProperty("notas")
	private Map<String, Double> grades;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Map<String, Double> getGrades() {
		return grades;
	}

	public void setGrades(HashMap<String, Double> grades) {
		this.grades = grades;
	}		
}
