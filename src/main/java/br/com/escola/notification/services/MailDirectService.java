package br.com.escola.notification.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.escola.notification.dtos.DirectMailDTO;
import br.com.escola.notification.dtos.SendEmailResponseDTO;
import br.com.escola.notification.dtos.StudentDTO;
import br.com.escola.notification.dtos.StudentGradesDTO;
import br.com.escola.notification.exceptions.InternalErrorException;
import br.com.escola.notification.repositories.NotificationRepository;
import br.com.escola.notification.repositories.StudentRepository;
import br.com.escola.notification.utils.Messages;

@Service
public class MailDirectService {
	
	@Autowired
	private StudentRepository registerRepository;
	
	@Autowired
	private NotificationRepository notificationRepository;
	
	private final String GRADE_STATUS_SUBJECT = "Boletim Status";
	private final String LIST_DISCIPLINES= "list";
	private final String APPROVED_MESSAGES = "approved";
	private final String REPROVED_MESSAGES = "reproved";
	private final Double GRADE_FOR_APPROVAL = 7.0;
	
	public SendEmailResponseDTO sendMessages(List<DirectMailDTO> body)	throws Exception {		
		SendEmailResponseDTO response = notificationRepository.sendEmail(body);		
		return response;
	}
	
	public List<DirectMailDTO> getMessages(String search) throws InternalErrorException {
		
		List<DirectMailDTO> response = new ArrayList<>();
		
		List<StudentDTO> students = registerRepository.getStudents();
		for(StudentDTO student : students) {
			StudentGradesDTO grade = registerRepository.getStudentGrade(student.getDocument());
	
			DirectMailDTO directMail = new DirectMailDTO();
			for (Map.Entry<String, Double> g : grade.getGrades().entrySet()) {
				if(APPROVED_MESSAGES.equals(search)) {
					if(g.getValue() >= GRADE_FOR_APPROVAL) {
						directMail.addDisciplines(g.getKey());
					}
				} else if(REPROVED_MESSAGES.equals(search)) {
					if(g.getValue() < GRADE_FOR_APPROVAL) {
						directMail.addDisciplines(g.getKey());
					}
				} else {
					directMail.addDisciplines(g.getKey());
				}
			}
			
			if(directMail.getDisciplines().size() > 0) {	
				directMail.setName(student.getName());
				directMail.setEmail(student.getEmail());
				directMail.setAddress(student.getAddress());
				directMail.setCep(student.getCep());
				directMail.setSubject(GRADE_STATUS_SUBJECT);
				
				switch (search) {
					case APPROVED_MESSAGES: {
						directMail.setMessage(Messages.formatEmail(directMail.getName(), 
												directMail.getDisciplines(), 
												Messages.MessagesType.APPROVED));
						break;
					}
					case REPROVED_MESSAGES: {
						directMail.setMessage(Messages.formatEmail(directMail.getName(), 
												directMail.getDisciplines(), 
												Messages.MessagesType.REPROVED));
						break;
					}
					
					case LIST_DISCIPLINES:
					default :
						directMail.setMessage(Messages.formatEmail(directMail.getName(), 
												directMail.getDisciplines(), 
												Messages.MessagesType.LIST));
				}
				
				response.add(directMail);
			}
		}
		
		return response;
	}

	
}
