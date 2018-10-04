package com.acme.acmetrade.endpoints;

import java.util.List;

import javax.websocket.server.PathParam;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.acme.acmetrade.domain.ResponseStatus;
import com.acme.acmetrade.domain.Sector;
import com.acme.acmetrade.services.SectorService;

@RestController
public class MarketSectorEndpoint {
	
	Logger log = LoggerFactory.getLogger(MarketSectorEndpoint.class);

	@Autowired
	SectorService service;

	@RequestMapping(value = "/sectors", method = RequestMethod.GET)
	public List<Sector> getSectors() {
		return service.getAllMarketSectors();
	}

	@RequestMapping(path = "/sectors", method = RequestMethod.POST)
	public ResponseEntity<ResponseStatus> addSector(@RequestBody Sector sector) {
		
		log.info("Sector:\t{}", sector);
		int numRows = service.addMarketSector(sector);
		
		if (numRows == 1) {
			return new ResponseEntity<ResponseStatus>(
					new ResponseStatus(0, "Successfully added new sector"), HttpStatus.CREATED); 
		} else {
			return new ResponseEntity<ResponseStatus>(
					new ResponseStatus(1111, "Unable to add new sector"), HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	@RequestMapping(path = "/sectors/{id}", method = RequestMethod.GET)
	public Sector getMarketSectorByNameOrId(@PathVariable String id) {
		if (NumberUtils.isCreatable(id)) {
			return service.getMarketSectorById(NumberUtils.toInt(id));
		} else {
			return service.getMarketSectorByName(id);
		}
	}

	@RequestMapping(path = "/sectors/{id}", method = RequestMethod.DELETE)
	public ResponseStatus deleteSector(@PathVariable("id") int id) {
		int numRows = service.deleteMarketSector(new Sector(id, StringUtils.EMPTY, StringUtils.EMPTY));

		if (numRows == 1) {
			return new ResponseStatus(0, "Successfully deleted sector");
		} else {
			return new ResponseStatus(1111, "Unable to delete sector");
		}
	}

	@RequestMapping(path = "/sectors", method = RequestMethod.PATCH)
	public ResponseStatus updateMarketSector(Sector sector) {
		int numRows = service.updateMarketSector(sector);

		if (numRows == 1) {
			return new ResponseStatus(0, "Successfully updated sector");
		} else {
			return new ResponseStatus(1111, "Unable to updated sector");
		}
	}
}
