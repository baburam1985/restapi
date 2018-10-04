package com.acme.acmetrade.endpoints;
	
	import java.util.ArrayList;
	import java.util.List;

	import javax.websocket.server.PathParam;

	import org.springframework.web.bind.annotation.RequestBody;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RequestMethod;
	import org.springframework.web.bind.annotation.RestController;

	import com.acme.acmetrade.domain.Trader;

	@RestController
	public class TraderEndpoint {	
			
		@RequestMapping(value="/traders", method = RequestMethod.GET)	
		public List<Trader> getTraders(){
			return new ArrayList<Trader>();
		}

		@RequestMapping(path = "/traders" , method = RequestMethod.POST)
		public Trader addTrader(@RequestBody Trader trader) {		
			return trader;
		}
		
		@RequestMapping(path = "/traders/{id}" , method = RequestMethod.GET)
		public Trader listTraders(@PathParam("id") int id) {
			return new Trader();
		}
		
		@RequestMapping(path = "/traders/{id}" , method = RequestMethod.DELETE)
		public void deleteTrader(@PathParam("id") int id) {
		}

}
