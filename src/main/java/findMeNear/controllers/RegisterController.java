package findMeNear.controllers;
import javax.validation.Valid;

import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import findMeNear.model.request.RegisterRequest;
import findMeNear.model.response.RegisterResponse;
import findMeNear.services.IServices;
import findMeNear.services.impl.ServicesImpl;
import findMeNear.utils.SessionToken;

@RestController
public class RegisterController {
	
	private IServices services = new ServicesImpl();

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public RegisterResponse register(@Valid @RequestBody RegisterRequest registrazione, Errors errors){
		RegisterResponse response = new RegisterResponse();
		if(errors.hasErrors()){
			response.setEsito(false);
			return response;
		}
		
		String name = registrazione.getName();
		String email = registrazione.getEmail();
		String password = registrazione.getPassword();
		if(!services.isUserExist(email,name)){
			services.registrazione(name, email, password);
			response.setEsito(true);
			response.setToken_sessione(SessionToken.getSessionToken());
			response.setNome(name);
			return response;
		}
		response.setEsito(false);
		return response;
	}
}
