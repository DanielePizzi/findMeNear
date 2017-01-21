package findMeNear.controllers;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import findMeNear.model.request.LoginRequest;
import findMeNear.model.response.LoginResponse;
import findMeNear.persistent.entity.User;
import findMeNear.services.IServices;
import findMeNear.services.impl.ServicesImpl;
import findMeNear.utils.CryptPassword;
import findMeNear.utils.SessionToken;

@RestController
public class LoginController {
	
private IServices services = new ServicesImpl();
	
	private static final String CLASS = "LoginController";
	
	Logger logger = Logger.getLogger("FINDMENEAR");

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public LoginResponse login(@Valid @RequestBody LoginRequest login, Errors errors){
		
		String method = "login";
		
		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		logger.debug(String.format("%s - %s::           START",CLASS,method));
		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		
		logger.debug(String.format("%s - %s::input[%s]",CLASS,method,login.toString()));
		
		LoginResponse response = new LoginResponse();
		if(errors.hasErrors()){
			response.setEsito(false);
			response.setDescrizione("INPUT NON VALIDI");
			logger.debug(String.format("%s - %s::errors[%s]",CLASS,method,errors.getAllErrors()));
			logger.debug(String.format("%s - %s::response[%s]",CLASS,method,response.toString()));
			logger.debug(String.format("%s - %s::*****************************",CLASS,method));
			logger.debug(String.format("%s - %s::           END",CLASS,method));
			logger.debug(String.format("%s - %s::*****************************",CLASS,method));
			
			return response;
		}
		
		String email = login.getEmail();
		String password = login.getPassword();
		
		User result = services.login(email);
		if(result == null){
			response.setEsito(false);
			response.setDescrizione("UTENTE NON ESISTENTE");
			logger.debug(String.format("%s - %s::user null",CLASS,method));
			logger.debug(String.format("%s - %s::response[%s]",CLASS,method,response.toString()));
			logger.debug(String.format("%s - %s::*****************************",CLASS,method));
			logger.debug(String.format("%s - %s::           END",CLASS,method));
			logger.debug(String.format("%s - %s::*****************************",CLASS,method));
			
			return response;
		}
		if(CryptPassword.cryptWithMD5(password).equals(result.getPassword())){
			response.setEsito(true);
			response.setDescrizione("LOGIN AVVENUTO CON SUCCESSO");
			response.setToken_sessione(SessionToken.getSessionToken());
			response.setNome(result.getNome());
			logger.debug(String.format("%s - %s::user[%s]",CLASS,method,result.toString()));
			logger.debug(String.format("%s - %s::response[%s]",CLASS,method,response.toString()));
			logger.debug(String.format("%s - %s::*****************************",CLASS,method));
			logger.debug(String.format("%s - %s::           END",CLASS,method));
			logger.debug(String.format("%s - %s::*****************************",CLASS,method));
			return response;
		}
		response.setEsito(false);
		response.setDescrizione("PASSWORD NON CORRETTA");
		logger.debug(String.format("%s - %s::response[%s]",CLASS,method,response.toString()));
		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		logger.debug(String.format("%s - %s::           END",CLASS,method));
		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		return response; 
	}
	
}
