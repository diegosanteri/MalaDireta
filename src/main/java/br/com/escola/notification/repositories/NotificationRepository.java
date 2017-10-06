package br.com.escola.notification.repositories;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sendgrid.*;

import br.com.escola.notification.dtos.DirectMailDTO;
import br.com.escola.notification.dtos.SendEmailResponseDTO;
import br.com.escola.notification.exceptions.InternalErrorException;

@Repository
public class NotificationRepository {

	private final String TOKEN_EMAIL_API = "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";
	private final String WHO_SENDS = "teste@teste.com";
	
	public SendEmailResponseDTO sendEmail(List<DirectMailDTO> body) throws InternalErrorException {
		
		int sendedEmails = 0;
		SendEmailResponseDTO response = new SendEmailResponseDTO();
		for(DirectMailDTO b : body) {
			if(this.send(b.getEmail(), WHO_SENDS, b.getSubject(), b.getMessage())) {
				sendedEmails ++;
			}
		}
		
		response.setSendedEmail(sendedEmails);
		
		return response;
	}
	
	
	private boolean send(String to, String from, String subject, String message) throws InternalErrorException{
		
		boolean ret = false;
		Email fromObject = new Email(from);
	    Email toObject = new Email(to);
	    Content content = new Content("text/html", message);
	    Mail mail = new Mail(fromObject, subject, toObject, content);
		
		SendGrid sg = new SendGrid(TOKEN_EMAIL_API);
	    Request request = new Request();
	    try {
	      request.setMethod(Method.POST);
	      request.setEndpoint("/mail/send");
	      request.setBody(mail.build());
	      Response response = sg.api(request);
	      if(response.getStatusCode() < 400) {
	    	  ret = true;
	      }
	    } catch (IOException ex) {
	      throw new InternalErrorException("Erro ao enviar email");
	    }
		
		return ret;
	}

}
