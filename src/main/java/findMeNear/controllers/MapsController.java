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

import findMeNear.model.request.GetPointRequest;
import findMeNear.model.request.RemovePointRequest;
import findMeNear.model.request.SavePointRequest;
import findMeNear.model.response.GetPointResponse;
import findMeNear.model.response.RemovePointResponse;
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
		
		try{
			services.savePoint(request.getUsername(), nome, citta, stato, lat, lng, tipo, descrizione);
		}catch(Exception e){
			logger.debug(String.format("%s - %s::errors[%s]",CLASS,method,e));
			response.setEsito(false);
			response.setDescrizione("ERRORE INTERNO");
			return response;
		}
		response.setEsito(true);
		response.setDescrizione("PUNTO SALVATO CON SUCCESSO");
 		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		logger.debug(String.format("%s - %s::           END",CLASS,method));
		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		return response;
	}
	
	@RequestMapping(value = "/getPoint", method = RequestMethod.POST)
	@ResponseBody
	public GetPointResponse getPoint(@Valid @RequestBody GetPointRequest request, Errors errors){
		
		String method = "getPoint";
		
		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		logger.debug(String.format("%s - %s::           START",CLASS,method));
		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		
		logger.debug(String.format("%s - %s::input[%s]",CLASS,method,request.toString()));
		
		GetPointResponse response = new GetPointResponse();
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
		try{
			response = services.getPoint(request.getUsername(),request.getCategoria(), Double.parseDouble(request.getLatitudine()), Double.parseDouble(request.getLongitudine()));
		}catch(Exception e){
			logger.debug(String.format("%s - %s::errors[%s]",CLASS,method,e));
			response.setEsito(false);
			response.setDescrizione("ERRORE INTERNO");
			return response;
		}
 		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		logger.debug(String.format("%s - %s::           END",CLASS,method));
		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		return response;
	}
	
	
	@RequestMapping(value = "/removePoint", method = RequestMethod.POST)
	@ResponseBody
	public RemovePointResponse removePoint(@Valid @RequestBody RemovePointRequest request, Errors errors){
		
		String method = "removePoint";
		
		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		logger.debug(String.format("%s - %s::           START",CLASS,method));
		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		
		logger.debug(String.format("%s - %s::input[%s]",CLASS,method,request.toString()));
		
		RemovePointResponse response = new RemovePointResponse();
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
		try{
			response = services.removePoint(request.getIdPoint());
		}catch(Exception e){
			logger.debug(String.format("%s - %s::errors[%s]",CLASS,method,e));
			response.setEsito(false);
			response.setDescrizione("ERRORE INTERNO");
			return response;
		}
 		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		logger.debug(String.format("%s - %s::           END",CLASS,method));
		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		return response;
	}
}
