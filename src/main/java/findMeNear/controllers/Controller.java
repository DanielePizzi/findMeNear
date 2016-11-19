package findMeNear.controllers;



import javax.validation.Valid;

import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import findMeNear.model.Login;
import findMeNear.model.Registrazione;
import findMeNear.persistent.entity.User;
import findMeNear.services.IServices;
import findMeNear.services.impl.ServicesImpl;
import findMeNear.utils.CryptPassword;

@RestController
public class Controller {
	
	private IServices services = new ServicesImpl();

	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public boolean register(@Valid @RequestBody Registrazione registrazione, Errors errors){
		if(errors.hasErrors()){
			return false;
		}
		
		String name = registrazione.getName();
		String email = registrazione.getEmail();
		String password = registrazione.getPassword();
		if(!services.isEmailexist(email)){
			services.registrazione(name, email, password);
			return true;
		}		
		return false;
	}
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public User login(@RequestBody Login login){
		String email = login.getEmail();
		String password = login.getPassword();
		
		User result = services.login(email);
		if(CryptPassword.cryptWithMD5(password).equals(result.getPassword())){
			return result;
		}
		return null; 
	}
}
