package br.com.escola.notification.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.escola.notification.dtos.ErrorDTO;
import br.com.escola.notification.dtos.StudentDTO;
import br.com.escola.notification.dtos.StudentGradesDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value="/alunos")
@Api(value = "/alunos")
@ApiIgnore
public class MockController {
	
	/**
	 * Obtem os alunos
	 * 
	 * @return ResponseEntity<Response<StudentDTO>>
	 * @throws Exception 
	 * @throws InternalServerErrorException
	 */
	@ApiOperation(value = "Obtem os alunos", 
				  responseContainer = "Response<ContainerDTO<StudentDTO>>")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 500, message = "Ocorreu um erro interno", 
							response = ErrorDTO.class) })
	@GetMapping
	public ResponseEntity<List<StudentDTO>> getStudent() throws Exception{
		List<StudentDTO> response =  new ArrayList<>();
		
		StudentDTO student = new StudentDTO();
		student.setDocument("12345678901");
		student.setName("Diego");
		student.setEmail("diegosanteri@gmail.com");
		student.setCep("13012450");
		student.setAddress("Rua de teste");
		response.add(student);
		
		student = new StudentDTO();
		student.setDocument("09876543201");
		student.setName("pubja");
		student.setEmail("admin@pubja.com");
		student.setCep("13012451");
		student.setAddress("Rua de teste 2");
		response.add(student);
		
		student = new StudentDTO();
		student.setDocument("45645993101");
		student.setName("Teste");
		student.setEmail("diego@pubja.com");
		student.setCep("13012410");
		student.setAddress("Rua de teste 3");
		response.add(student);
		
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Obtem as notas de um aluno
	 * 
	 * @return ResponseEntity<Response<StudentDTO>>
	 * @throws Exception 
	 * @throws InternalServerErrorException
	 */
	@ApiOperation(value = "Obtem os alunos", 
				  response = StudentGradesDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 500, message = "Ocorreu um erro interno", 
							response = ErrorDTO.class) })
	@GetMapping(value="/{cpf}/notas")
	public ResponseEntity<StudentGradesDTO> getStudentGrades(
			@ApiParam(value = "cpf do aluno", required = true) @PathVariable("cpf") String cpf) throws Exception{
		StudentGradesDTO response = new StudentGradesDTO();
		
		if (cpf.equals("123.456.789-01")) {
			HashMap<String, Double> grades = new HashMap<String, Double>();
			grades.put("Fisica", 9.0);
			grades.put("Matematica", 9.0);
			grades.put("Ingles", 7.0);
			grades.put("Portugues", 8.0);
			grades.put("Educação Fisica", 10.0);
			grades.put("Historia", 6.0);
			
			response.setCpf("123.456.789-01");
			response.setGrades(grades);
		} else if (cpf.equals("098.765.432-01")) {
			HashMap<String, Double> grades = new HashMap<String, Double>();
			grades.put("Fisica", 6.0);
			grades.put("Matematica", 9.0);
			grades.put("Ingles", 7.0);
			grades.put("Portugues", 8.0);
			grades.put("Educação Fisica", 10.0);
			grades.put("Historia", 7.0);
			
			response.setCpf(cpf);
			response.setGrades(grades);
		} else if (cpf.equals("456.459.931-01")) {
			HashMap<String, Double> grades = new HashMap<String, Double>();
			grades.put("Fisica", 9.0);
			grades.put("Matematica", 9.0);
			grades.put("Ingles", 5.0);
			grades.put("Portugues", 8.0);
			grades.put("Educação Fisica", 4.0);
			grades.put("Historia", 2.0);
			
			response.setCpf(cpf);
			response.setGrades(grades);
		}
		
		return ResponseEntity.ok(response);
	}
	
}
