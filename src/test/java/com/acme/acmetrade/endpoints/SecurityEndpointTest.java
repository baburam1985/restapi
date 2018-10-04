package com.acme.acmetrade.endpoints;

import com.acme.acmetrade.domain.Sector;
import com.acme.acmetrade.domain.Security;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.acme.acmetrade.TradeApplication;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = { TradeApplication.class })
public class SecurityEndpointTest {

	@LocalServerPort
	private int serverPort;

	@Before
	public void init() {
		RestAssured.port = serverPort;

	}
	
	@Test
	public void listSecurities() {
		Response response =  given().accept(MediaType.APPLICATION_JSON_VALUE)
				.auth().basic("bill", "bill")
				.when().get("/securities")
				.then().statusCode(HttpStatus.SC_OK)
				.and().extract().response();

		Security[] jsonResponse = response.as(Security[].class);
		assertThat(jsonResponse.length, equalTo(2));
	}


	@Test
	@Sql(scripts= {"classpath:/testtableSecurities.sql"})
	public void listSecurity() {
		Response response =  given().accept(MediaType.APPLICATION_JSON_VALUE)
				.auth().basic("bill", "bill")
				.pathParam("securityId","FB")
				.when().get("/securities/{securityId}")
				.then().statusCode(HttpStatus.SC_OK)
				.and().extract().response();

		Security jsonResponse = response.as(Security.class);
		assertThat(jsonResponse.getCompanyName(), equalTo("Facebook, Inc"));
	}
}
