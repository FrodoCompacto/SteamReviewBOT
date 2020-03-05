package io.steamreviewbot.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/revive")
public class ReviveResource {
	
	@RequestMapping(method=RequestMethod.GET)
	public String pulse() {
		return "blz.";
	}
	
}
