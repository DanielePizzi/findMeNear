package findMeNear.controllers;

import java.util.HashMap;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import findMeNear.model.request.SavePointRequest;
import findMeNear.model.response.SavePointResponse;
import findMeNear.services.IServices;
import findMeNear.services.impl.ServicesImpl;

@RequestMapping(value = "/maps")
@RestController
public class MapsController {
	
	private IServices services = new ServicesImpl();
	
	private static final String CLASS = "MapsController";
	
	Logger logger = Logger.getLogger("FINDMENEAR");

	@RequestMapping(value = "/savePoint", method = RequestMethod.POST)
	@ResponseBody
	public SavePointResponse savePoint(@Valid @RequestBody SavePointRequest request, Errors errors){
		
		String method = "savePoint";
		
		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		logger.debug(String.format("%s - %s::           START",CLASS,method));
		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		
		logger.debug(String.format("%s - %s::input[%s]",CLASS,method,request.toString()));
		
		SavePointResponse response = new SavePointResponse();
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
		HashMap<String,Object> pointLocation = ((HashMap<String, Object>) request.getPointOfInterest());
		String nome = (String) pointLocation.get("nome");
		String citta = (String) pointLocation.get("citta");
		String stato = (String) pointLocation.get("stato");
		HashMap<String,Object> geometry = (HashMap<String, Object>) pointLocation.get("geometry");
		HashMap<String,Object> location = (HashMap<String, Object>) geometry.get("location");
		String lat = location.get("lat").toString();
		String lng = location.get("lng").toString();
		String tipo = (String) pointLocation.get("tipo");
		String descrizione = (String) pointLocation.get("descrizione");
		
 		services.savePoint(request.getUsername(), nome, citta, stato, lat, lng, tipo, descrizione);
 		response.setEsito(true);
 		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		logger.debug(String.format("%s - %s::           END",CLASS,method));
		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		return response;
	}
}
