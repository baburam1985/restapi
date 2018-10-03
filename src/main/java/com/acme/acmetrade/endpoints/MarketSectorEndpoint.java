package com.acme.acmetrade.endpoints;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.acme.acmetrade.domain.Sector;

@RestController
public class MarketSectorEndpoint {	
		
	@RequestMapping(value="/sectors", method = RequestMethod.GET)	
	public List<Sector> getSectors(){
		return new ArrayList<Sector>();
	}

	@RequestMapping(path = "/sectors" , method = RequestMethod.POST)
	public Sector addSector(@RequestBody Sector sector) {		
		return sector;
	}
	
	@RequestMapping(path = "/sectors/{id}" , method = RequestMethod.GET)
	public Sector listSector(@PathParam("id") int id) {
		return new Sector();
	}
	
	@RequestMapping(path = "/sectors/{id}" , method = RequestMethod.DELETE)
	public void deleteSector(@PathParam("id") int id) {
	}
}
