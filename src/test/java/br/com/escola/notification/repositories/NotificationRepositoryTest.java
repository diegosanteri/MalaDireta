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

import br.com.escola.notification.dtos.DirectMailDTO;
import br.com.escola.notification.dtos.SendEmailResponseDTO;
import br.com.escola.notification.exceptions.InternalErrorException;

@RunWith(SpringRunner.class)
@SpringBootTest( webEnvironment=WebEnvironment.DEFINED_PORT)
public class NotificationRepositoryTest {
	@Autowired
	private NotificationRepository repository;
	
	@Test
	public void testSendEmail() throws InternalErrorException {
		List<DirectMailDTO> body = new ArrayList<DirectMailDTO>();
		DirectMailDTO obj = new DirectMailDTO();
		obj.setName("Teste");
		obj.setEmail("diego@pubja.com");
		obj.setAddress("Rua de teste 3");
		obj.setCep("13012410");
		obj.setSubject("Boletim Status");
		obj.setMessage("<html><body><h2>Prezado Teste essas são suas materias</h2><ul><li>Fisica</li><li>Ingles</li><li>Educação Fisica</li><li>Historia</li><li>Matematica</li><li>Portugues</li></ul></body></html>");
		body.add(obj);
		
		SendEmailResponseDTO response = this.repository.sendEmail(body);
		assertNotEquals(response, null);
		assertEquals(response.getSendedEmail().intValue(), 1);
	}
}
