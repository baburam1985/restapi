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
import com.acme.acmetrade.domain.OrderTrans;

@RunWith(SpringRunner.class) 
@SpringBootTest(classes = {TradeApplication.class})

@Transactional
public class OrderTransTest {
	
	@Autowired
	OrderTransRepository orderTransRepository;
	
	@Test
	public void getAllOrderTransactions() {
		List<OrderTrans> orderTrans = orderTransRepository.getAllOrderTrans();
		assertThat(orderTrans.size(), greaterThan(0));
	}
	
	@Test
	public void saveNewOrderTrans() {
		OrderTrans orderTrans = new OrderTrans(999, 1, "2018-10-05 08:22:51.19", "Completed", "2018-10-05 08:22:51.19"); 
		int count = orderTransRepository.saveOrderTrans(orderTrans);
		assertThat(count, equalTo(1));
	}

}
