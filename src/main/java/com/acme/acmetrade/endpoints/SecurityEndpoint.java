package com.acme.acmetrade.endpoints;


import com.acme.acmetrade.domain.ResponseStatus;
import com.acme.acmetrade.domain.Security;
import com.acme.acmetrade.services.SecurityService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@RestController
public class SecurityEndpoint {

	//
	@Autowired
	private SecurityService securityService ;

	@RequestMapping(path = "/securities" , method = RequestMethod.GET)
	public List<Security> listSecurities(@RequestParam(required = false) String startsWith) {
		return  securityService.getAllSecurities(startsWith);
	}

	@RequestMapping(path = "/securities/{symbol}" , method = RequestMethod.GET)
	public Security listSecurity(@PathVariable("symbol") String symbol) throws NotFoundException {
		Security security = securityService.getSecurityBySymbol(symbol);
		return security;
	}

	@RequestMapping(path = "/securities" , method = RequestMethod.POST)
	public Security addSecurity(@Valid @RequestBody Security security) {
		securityService.addSecurity(security);
		return security;
	}

	@RequestMapping(path = "/securities/{symbol}" , method = RequestMethod.PUT)
	public Security updateSecurity(@PathVariable("symbol") String symbol, @RequestBody Security security) {
		security.setSymbol(symbol);
		securityService.updateSecurity(security);
		return security;
	}

	@RequestMapping(path = "/securities/{symbol}" , method = RequestMethod.DELETE)
	public ResponseEntity<ResponseStatus> deleteSecurityBySymbol(@PathVariable("symbol") String symbol) {
		securityService.deleteSecurityBySymbol(symbol);
		return new ResponseEntity<ResponseStatus>(
				new ResponseStatus("200", "Successfully deleted sector"), HttpStatus.OK); 
	}

//	@RequestMapping(path = "/securities/{sectorId}" , method = RequestMethod.DELETE)
//	public void deleteSecurityBySectorId(@PathVariable("sectorId") String sectorId) {
//		securityService.deleteSecurityBySectorId(sectorId);
//	}
}
