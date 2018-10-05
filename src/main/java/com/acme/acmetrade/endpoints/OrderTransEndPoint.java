package com.acme.acmetrade.endpoints;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.acme.acmetrade.domain.OrderTrans;
import com.acme.acmetrade.services.OrderTransService;

@RestController
public class OrderTransEndPoint {
	
	@Autowired
	private OrderTransService orderTransService ;
	public static String str ;
		
	@RequestMapping(value="/orderhistory", method = RequestMethod.GET)	
	public List<OrderTrans> getOrderTrans(){
		return orderTransService.getAllOrderTrans();
	}	
	
}
