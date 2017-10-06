package br.com.escola.notification.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.escola.notification.dtos.DirectMailDTO;
import br.com.escola.notification.dtos.ErrorDTO;
import br.com.escola.notification.dtos.SendEmailResponseDTO;
import br.com.escola.notification.exceptions.InternalErrorException;
import br.com.escola.notification.services.MailDirectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value="/maladireta")
@Api(value = "/maladireta")
public class DirectMailController {
	
	@Autowired
	private MailDirectService mailDirectService;
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)  
    @ExceptionHandler(value = InternalErrorException.class)  
    public ErrorDTO handleBaseException(InternalErrorException e){  
		ErrorDTO error = new ErrorDTO(e.getCode(), e.getMessage());
        return error;  
    }
	
	/**
	 * Obtem os registros de alunos reprovados que deverão receber o alerta de reprova
	 * 
	 * @return ResponseEntity<Response<DirectMailDTO>>
	 * @throws InternalServerErrorException
	 */
	@ApiOperation(value = "Obtem as mensagens a serem enviadas para os usuarios podendo ser mensagem de aprovação, reprovação ou apenas listar as matérias dos alunos", 
				  responseContainer = "Response<ContainerDTO<DirectMailDTO>>")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 500, message = "Ocorreu um erro interno", 
							response = ErrorDTO.class) })
	@GetMapping
	public ResponseEntity<List<DirectMailDTO>> getMessages(
			@ApiParam(required=false, allowableValues="approved,reproved,list") @RequestParam(value="search", defaultValue="") String search
		) throws InternalErrorException{
		List<DirectMailDTO> response =  new ArrayList<>();
		
		response = mailDirectService.getMessages(search);
	
		return ResponseEntity.ok(response);
	}	
	
	/**
	 * Envia emails para os alunos
	 * @param  List<DirectMailDTO>
	 * @return ResponseEntity<Response<SendEmailResponseDTO>>
	 * @throws InternalServerErrorException
	 */
	@ApiOperation(value = "Envia emails", 
				  responseContainer = "Response<SendEmailResponseDTO>")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 500, message = "Ocorreu um erro interno", 
							response = ErrorDTO.class) })
	@PostMapping
	public ResponseEntity<SendEmailResponseDTO> sendDirectEMails(@RequestBody List<DirectMailDTO> body) 
																							throws Exception{
		 
		SendEmailResponseDTO response = mailDirectService.sendMessages(body);
		
		return ResponseEntity.ok(response);
	}	
	
}
