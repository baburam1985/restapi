package com.acme.acmetrade.repository;
 
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.acme.acmetrade.TradeApplication;
import com.acme.acmetrade.domain.Security;

@RunWith(SpringRunner.class) 
@SpringBootTest(classes = {TradeApplication.class})
public class SecurityTest {
	
	@Autowired
	SecurityRepository dao;

	@Test
	public void addSecurity(Security security) {
		
	}
}
