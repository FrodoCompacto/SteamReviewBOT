package io.steamreviewbot.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.data.domain.Page;

import io.steamreviewbot.domain.App;
import io.steamreviewbot.dto.AddListDTO;
import io.steamreviewbot.services.AppService;
import io.steamreviewbot.services.exceptions.ForbiddenException;
import io.steamreviewbot.services.validation.CaptchaValidator;
import io.steamreviewbot.services.validation.GameValidator;

@RestController
@RequestMapping(value="/app")
public class AppResource {
	
	@Autowired
	private CaptchaValidator captchaValidator;
	
	@Autowired
	private GameValidator gameValidator;
	
	@Autowired
	private AppService service;
	
	@RequestMapping(value = "/addlist", method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody AddListDTO appList) {
	
		Boolean isValidCaptcha = captchaValidator.validateCaptcha(appList.getCaptchaResponse());
		//Boolean isValidCaptcha = true;
		
		
	    if(!isValidCaptcha){
	        throw new ForbiddenException("Captcha is not valid");
	    } else {
	    	if (appList.getAppids().size() > 0 && appList.getAppids().size() <= 10) {
	    	appList.getAppids().forEach(app -> {
	    		System.out.println(app.getAppid());
	    		if(!gameValidator.validateGame(app.getAppid())) {
	    			throw new ForbiddenException("Invalid game list");
	    		}
	    	});
	    } else throw new ForbiddenException("Empty List");
	    	
	    	service.insert(appList.getAppids());
	    	

	    	return ResponseEntity.noContent().build();
	    }
	}
	
	@RequestMapping(value="/list/page", method = RequestMethod.GET)
	public ResponseEntity<Page<App>> findPage(
			@RequestParam(name="page", defaultValue="0") Integer page, 
			@RequestParam(name="linesPerPage", defaultValue="15") Integer linesPerPage, 
			@RequestParam(name="orderBy", defaultValue="interestRate") String orderBy,
			@RequestParam(name="direction", defaultValue="DESC") String direction) {
			
			
			Page<App> pageApp = service.findPage(page, linesPerPage, orderBy, direction);
			
			return ResponseEntity.ok().body(pageApp);
	}
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public ResponseEntity<List<App>> findAll() {
		List<App> listApp = service.findAll();
		
		return ResponseEntity.ok().body(listApp);
	}
	
	@RequestMapping(value="/fetch/steamgamelist", method = RequestMethod.GET)
	public ResponseEntity<String> fetchSteamGamesList() {
		String response;
		
		response = service.fetchSteamGamesList();
		
		return ResponseEntity.ok().body(response);
	}
	
	@RequestMapping(value="/fetch/steamgame/{id}", method = RequestMethod.GET)
	public ResponseEntity<String> fetchSteamGame(@PathVariable Integer id) {
		String response;
		
		if (id < 0 ) throw new ForbiddenException("Invalid game id");
		response = service.fetchSteamGame(id);
		
		return ResponseEntity.ok().body(response);
	}

}
