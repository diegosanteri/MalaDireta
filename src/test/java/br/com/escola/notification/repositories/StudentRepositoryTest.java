package br.com.escola.notification.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.escola.notification.dtos.StudentDTO;
import br.com.escola.notification.dtos.StudentGradesDTO;
import br.com.escola.notification.exceptions.InternalErrorException;

@RunWith(SpringRunner.class)
@SpringBootTest( webEnvironment=WebEnvironment.DEFINED_PORT)
public class StudentRepositoryTest {
	@Autowired
	private StudentRepository repository;
	
	@Test
	public void testGetStudents() throws InternalErrorException {
		List<StudentDTO> students = repository.getStudents();
		assertEquals(students.size(), 3);
	}
	
	@Test
	public void testGetGrade() throws InternalErrorException {		
		StudentGradesDTO grade = repository.getStudentGrade("12345678901");
		assertEquals(grade.getCpf(), "123.456.789-01");	
		assertEquals(grade.getGrades().size(), 6);
	}
}
