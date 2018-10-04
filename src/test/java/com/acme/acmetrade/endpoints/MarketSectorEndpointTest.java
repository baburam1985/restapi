package com.acme.acmetrade.endpoints;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.acme.acmetrade.TradeApplication;
import com.acme.acmetrade.domain.ResponseStatus;
import com.acme.acmetrade.domain.Sector;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = { TradeApplication.class })
public class MarketSectorEndpointTest {

	@LocalServerPort
	private int serverPort;

	@Before
	public void init() {
		RestAssured.port = serverPort;
	}

	@Test
	public void allSectorsReturned() {
		Response response =  given().accept(MediaType.APPLICATION_JSON_VALUE)
		.when().get("/sectors")
		.then().statusCode(HttpStatus.SC_OK)
		.and().extract().response(); 
		
		Sector[] jsonResponse = response.as(Sector[].class); 
		assertThat(jsonResponse.length, equalTo(2));
	}
	
	@Test
	public void getSectorById() throws JSONException {

		Response response = given().accept(MediaType.APPLICATION_JSON_VALUE)
				.pathParam("sectorId", 1)
				.when().get("/sectors/{sectorId}")
				.then().statusCode(HttpStatus.SC_OK)
				.and().extract().response();
			
		Sector jsonResponse = response.as(Sector.class);
		assertThat(jsonResponse.getSectorName(), equalTo("Financial"));		
	}
	
	@Test
	public void getSectorByName() throws JSONException {

		Response response = given().accept(MediaType.APPLICATION_JSON_VALUE)
				.pathParam("sectorName", "Financial")
				.when().get("/sectors/{sectorName}")
				.then().statusCode(HttpStatus.SC_OK)
				.and().extract().response();
			
		Sector jsonResponse = response.as(Sector.class);
		assertThat(jsonResponse.getSectorName(), equalTo("Financial"));		
	}
	
	@Test
	public void addSector() throws JSONException {
		JSONObject json = new JSONObject();
		
		json.put("sectorName", "Test");
		json.put("sectorDesc", "Test sector");
		
		Response response = given().contentType(ContentType.JSON)
				.auth().basic("bill", "bill")
				.body(json.toString())
				.when().post("/sectors")
				.then().statusCode(HttpStatus.SC_CREATED)
				.and().extract().response();
		ResponseStatus status = response.as(ResponseStatus.class);
		
		assertThat(status.getStatusCode(), equalTo(0));
		
	}
	
	@Test
	@Ignore
	public void addSectorFailure() throws JSONException {
		JSONObject json = new JSONObject();
		
		json.put("sectorName", null);
		json.put("sectorDesc", "Test sector");
		
		Response response = given().contentType(ContentType.JSON)
				.auth().basic("bill", "bill")
				.body(json.toString())
				.when().post("/sectors")
				.then().statusCode(HttpStatus.SC_UNPROCESSABLE_ENTITY)
				.and().extract().response();
		ResponseStatus status = response.as(ResponseStatus.class);
		
		assertThat(status.getStatusCode(), not(0));
		
	}
	
	@Test
	@Sql(scripts= {"classpath:testableSector.sql"})
	public void deleteSector() {
		
		Response response = given().accept(MediaType.APPLICATION_JSON_VALUE)
				.pathParam("sectorId", 3)
				.auth().basic("admin", "admin")
				.when().delete("/sectors/{sectorId}")
				.then().statusCode(HttpStatus.SC_OK)
				.and().extract().response();
		ResponseStatus status = response.as(ResponseStatus.class);
		
		assertThat(status.getStatusCode(), equalTo(0));
		
	}
	
	@Test
	@Sql(scripts= {"classpath:testableSector.sql"})
	public void updateSector() throws JSONException {
		JSONObject json = new JSONObject();
		
		json.put("sectorName", "Test");
		json.put("sectorDesc", "Test sector");
		
		Response response = given().contentType(ContentType.JSON)
				.auth().basic("bill", "bill")
				.body(json.toString())
				.when().patch("/sectors")
				.then().statusCode(HttpStatus.SC_OK)
				.and().extract().response();
		ResponseStatus status = response.as(ResponseStatus.class);
		
		assertThat(status.getStatusCode(), equalTo(0));
	}

}
