package com.acme.acmetrade.endpoints;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.acme.acmetrade.domain.Order;
import com.acme.acmetrade.services.OrderService;

@RestController
public class OrderEndPoint {
	
	@Autowired
	private OrderService orderService ;
	
	@RequestMapping(value="/orders", method = RequestMethod.GET)	
	public List<Order> getOrders(){
		return orderService.getAllOrders();
	}	
	
	@RequestMapping(path = "/orders" , method = RequestMethod.POST)
	public Order addOrder(@RequestBody Order order) {	
		orderService.saveOrder(order);
		return order;
	}
}
