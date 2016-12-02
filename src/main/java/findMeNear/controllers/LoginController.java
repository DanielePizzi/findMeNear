package findMeNear.controllers;

import javax.validation.Valid;

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
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public LoginResponse login(@Valid @RequestBody LoginRequest login, Errors errors){
		LoginResponse response = new LoginResponse();
		if(errors.hasErrors()){
			response.setEsito(false);
			return response;
		}
		
		String email = login.getEmail();
		String password = login.getPassword();
		
		User result = services.login(email);
		if(result == null){
			response.setEsito(false);
			return response;
		}
		if(CryptPassword.cryptWithMD5(password).equals(result.getPassword())){
			response.setEsito(true);
			response.setToken_sessione(SessionToken.getSessionToken());
			response.setNome(result.getNome());
			return response;
		}
		response.setEsito(false);
		return response; 
	}
	
}
