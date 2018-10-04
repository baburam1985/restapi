package com.acme.acmetrade.endpoints;


import com.acme.acmetrade.domain.Security;
import com.acme.acmetrade.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@RestController
public class SecurityEndpoint {

	//
	@Autowired
	private SecurityService securityService ;

	@RequestMapping(path = "/securities" , method = RequestMethod.GET)
	public List<Security> listSecurities() {
		return  securityService.getAllSecurities();
	}

	@RequestMapping(path = "/securities/{symbol}" , method = RequestMethod.GET)
	public Security listSecurity(@PathVariable("symbol") String symbol) {
		Security security = securityService.getSecurityBySymbol(symbol);
		return security;
	}

	@RequestMapping(path = "/securities" , method = RequestMethod.POST)
	public Security addSecurity(@RequestBody Security security) {
		securityService.updateSecurity(security);
		return security;
	}

	@RequestMapping(path = "/securities" , method = RequestMethod.PUT)
	public Security updateSecurity(@RequestBody Security security) {
		securityService.updateSecurity(security);
		return security;
	}

	@RequestMapping(path = "/securities/{symbol}" , method = RequestMethod.DELETE)
	public void deleteSecurityBySymbol(@PathVariable("symbol") String symbol) {
		securityService.deleteSecurityBySymbol(symbol);
	}

//	@RequestMapping(path = "/securities/{sectorId}" , method = RequestMethod.DELETE)
//	public void deleteSecurityBySectorId(@PathVariable("sectorId") String sectorId) {
//		securityService.deleteSecurityBySectorId(sectorId);
//	}
}
