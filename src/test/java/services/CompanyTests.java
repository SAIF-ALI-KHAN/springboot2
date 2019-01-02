package services;

import static io.restassured.RestAssured.given;


import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import net.guides.springboot2.springboot2swagger2.model.Company;
import net.minidev.json.JSONObject;

class CompanyTests {

	private static String ENDPOINT_GET_BOOK_BY_ISBN = "http://localhost:8080/api/v2/companies";

	
	@Test
	public void testGetAllCompnies(){
		

		given().
		
		when().
		get(ENDPOINT_GET_BOOK_BY_ISBN)
		.then().
		statusCode(HttpStatus.SC_OK);
		
	}
	
	@Test
	public void testCreateCompnay() {
		 JSONObject requestParams = new JSONObject();
		   	requestParams.put("companyName", "companyCucmberTest"); // Cast
		   	requestParams.put("id", "1");
		
		given().body(requestParams.toJSONString()).contentType("application/json").
		when().post(ENDPOINT_GET_BOOK_BY_ISBN)
		.then().
		statusCode(HttpStatus.SC_OK);
	}
	@Test
	public void testUpdateCompnay() {
		 JSONObject requestParams = new JSONObject();
		   	requestParams.put("companyName", "updatecompanyCucmberTest"); // Cast
		   	requestParams.put("id", "1");
		
		given().param("id", "62").body(requestParams.toJSONString()).contentType("application/json").
		when().put(ENDPOINT_GET_BOOK_BY_ISBN)
		.then().
		statusCode(HttpStatus.SC_OK);
	}

}
