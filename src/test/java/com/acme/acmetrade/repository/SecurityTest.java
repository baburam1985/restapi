package com.acme.acmetrade.repository;
 
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.acme.acmetrade.TradeApplication;
import com.acme.acmetrade.domain.Security;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

@Sql(scripts= {"classpath:/testtableSecurities.sql"})
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TradeApplication.class})
@Transactional
public class SecurityTest {
	
	@Autowired
	SecurityRepository securityRepository;

	@Test
	public void saveSecurity() {
		Security security = new Security();
		security.setSymbol("TSLA");
		security.setCompanyName("Tesla Motors, Inc");
		security.setDescription("American automotive and energy company");
		security.setSectorId(2);
		int count = securityRepository.saveSecurity(security);
		assertThat(count, equalTo(1));
	}

	@Test
	public void updateSecurity() {
		Security security = new Security();
		security.setSymbol("FB");
		security.setCompanyName("Facebook, Incc");
		security.setDescription("Internet-related services and productss");
		security.setSectorId(1);
		int count = securityRepository.updateSecurity(security);
		assertThat(count, equalTo(1));
		Security sec = securityRepository.retrieveSecurityBySymbol("FB");
		assertThat("Facebook, Incc", equalTo(sec.getCompanyName()));

	}

	@Test
	public void deleteSecurityBySymbol() {
		int count = securityRepository.deleteSecurityBySymbol("FB");
		assertThat(count, equalTo(1));
	}
	@Test
	public void deleteSecurityBySectorId() {
		int count = securityRepository.deleteSecurityBySectorId(1);
		assertThat(count, greaterThan(1));
	}

	@Test
	public void retrieveSecurityBySymbol() {
		Security security = securityRepository.retrieveSecurityBySymbol("FB");
		assertThat("Facebook, Inc", equalTo(security.getCompanyName()));
	}

	@Test
	public void retrieveSecuritiesBySectorId() {
		List<Security> securities = securityRepository.retrieveSecuritiesBySectorId(1);
		assertThat(securities.size(), greaterThan(0));
	}

	@Test
	public void retrieveAllSecurities() {
		List<Security> securities = securityRepository.retrieveAllSecurities();
		assertThat(securities.size(), greaterThan(0));
	}

}
