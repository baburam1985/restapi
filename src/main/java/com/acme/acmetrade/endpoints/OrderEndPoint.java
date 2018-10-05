package com.acme.acmetrade.endpoints;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.acme.acmetrade.domain.Order;
import com.acme.acmetrade.services.OrderService;

@RestController
public class OrderEndPoint {
	
	@Autowired
	private OrderService orderService ;
	public static String str ;
	
	@RequestMapping(value="/orders", method = RequestMethod.GET)	
	public List<Order> getOrders(){
		return orderService.getAllOrders();
	}	
	
	@ResponseBody
	@RequestMapping(path = "/orders" , method = RequestMethod.POST)
	public String addOrder(@Valid @RequestBody Order order) {
		str= orderService.saveOrder(order);
		return str;
	}
}
