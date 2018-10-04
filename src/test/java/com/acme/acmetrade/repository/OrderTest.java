package com.acme.acmetrade.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.acme.acmetrade.TradeApplication;
import com.acme.acmetrade.domain.Order;

@RunWith(SpringRunner.class) 
@SpringBootTest(classes = {TradeApplication.class})

@Transactional
public class OrderTest {
	
	@Autowired
	OrderRepository orderRepository;
	
	@Test
	public void addNewOrder() {
		Order order = new Order(1, 7, "APPL", "SELL", "MARKET", (float)13.46, 5, "2010-01-01 11:00:00+03");
		int count = orderRepository.saveOrder(order);
		assertThat(count, equalTo(1));
	}
	
	@Test
	public void getAllOrders() {
		List<Order> orders = orderRepository.getAllOrders();
		assertThat(orders.size(), greaterThan(0));
	}
}
	
