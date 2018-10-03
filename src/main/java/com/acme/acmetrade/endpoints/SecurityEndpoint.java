package com.acme.acmetrade.endpoints;


import com.acme.acmetrade.domain.Security;
import com.acme.acmetrade.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/trading")
public class SecurityEndpoint {

	//@Autowired
	private SecurityService securityService = new SecurityService();

	@RequestMapping(path = "/securities" , method = RequestMethod.GET)
	public List<Security> listSecurities() {
		return new ArrayList<Security>();
	}

	@RequestMapping(path = "/securities/{symbol}" , method = RequestMethod.GET)
	public Security listSecurity(@PathParam("symbol") String symbol) {
		return new Security();
	}

	@RequestMapping(path = "/securities" , method = RequestMethod.POST)
	public Security addSecurity(@RequestBody Security security) {
		securityService.addSecurity(security);
		return security;
	}

	@RequestMapping(path = "/securities" , method = RequestMethod.PUT)
	public Security updateSecurity(@RequestBody Security security) {
		// securityService.addSecurity(security);
		return security;
	}

	@RequestMapping(path = "/securities/{symbol}" , method = RequestMethod.DELETE)
	public void deleteSecurity(@PathParam("symbol") String symbol) {

	}
}
