package br.com.escola.notification.utils;

import java.util.List;


public class Messages {
	
	private static final String EMAIL_BODY = "<html><body>{{body}}</body></html>";
	public enum MessagesType {
	    APPROVED, REPROVED, LIST
	};

	
	public static String formatEmail(String name, List<String> disciplines, MessagesType type) {
		
		String body = "";
		
		switch(type) {
			case APPROVED: {
				body += "<h2>Prezado " +  name + " você foi Aprovado(a) ";
				body += disciplines.size() > 1 ? "nas seguintes materias:" : " na seguinte materia:";
				body += "</h2>";
				break;
			}
			case REPROVED: {
				body += "<h2>Prezado " +  name + " você foi reprovado(a) ";
				body += disciplines.size() > 1 ? " nas seguintes materias:" : " na seguinte materia:";
				body += "</h2>";
				break;
			}
			default: {
				body += "<h2>Prezado " +  name + " essas são suas materias</h2>";
				break;
			}
		}
		
		body += "<ul>";
		for (String r : disciplines) {
			body += "<li>" + r + "</li>";
		}
		body += "</ul>";
		
		return EMAIL_BODY.replace("{{body}}", body);
	}
}