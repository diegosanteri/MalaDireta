package br.com.escola.notification.repositories;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import br.com.escola.notification.dtos.StudentDTO;
import br.com.escola.notification.dtos.StudentGradesDTO;
import br.com.escola.notification.exceptions.InternalErrorException;
import br.com.escola.notification.utils.Formatter;

@Repository
public class StudentRepository {
	
	
	private final String GET_STUDENT_URL = "http://127.0.0.1:8080/alunos";
	private final String GET_GRADES_URL = "http://127.0.0.1:8080/alunos/{cpf}/notas";
	
	public List<StudentDTO> getStudents() throws InternalErrorException{
		ResponseEntity<StudentDTO[]>  r = null;
		try {
			RestTemplate restTemplate = new RestTemplate();
			r = restTemplate.getForEntity(GET_STUDENT_URL, StudentDTO[].class);
		} catch(Exception e) {
			throw new InternalErrorException("Ocorreu um erro ao obter os dados");
		}
		
		return Arrays.asList(r.getBody());
		
	}
	
	public StudentGradesDTO getStudentGrade(String document) throws InternalErrorException {
		ResponseEntity<StudentGradesDTO> r = null;
		try {
			RestTemplate restTemplate = new RestTemplate();
			String  url = GET_GRADES_URL.replace("{cpf}", Formatter.formatCpf(document));
			r = restTemplate.getForEntity(url, StudentGradesDTO.class);
		} catch(Exception e) {
			System.out.println(e);
			
			throw new InternalErrorException("Ocorreu um erro ao obter os dados");
		}
		
		return r.getBody();
	}
	
}
