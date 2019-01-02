package net.guides.springboot2.springboot2swagger2.api;


import static io.restassured.RestAssured.get;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.minidev.json.JSONObject;


public class CompanyApiTest {

	int companyId;
		   @Test
		   public void givenUrl_whenSuccessAndJsonHasAllCompanies() {
			   get("/api/v2/companies").then().statusCode(200).assertThat();
		       RequestSpecification httpRequest = RestAssured.given();
		       Response response = httpRequest.get("/api/v2/companies");
		       System.out.println("Response Body is =>  " + response.asString());
		   }
		   @Test
		   public void givenUrl_ForCreateCompany()
		   {		
		  
		   	RequestSpecification request = RestAssured.given();
		    
		  JSONObject requestParams = new JSONObject();
		   	requestParams.put("companyName", "TestFromRESTAssured"); // Cast
		   	requestParams.put("id", "1");
		   
		   	request.body(requestParams.toJSONString()).contentType("application/json");
		   	Response response = request.post("/api/v2/companies");
		    
		   	int statusCode = response.getStatusCode();
		   	Assert.assertEquals(statusCode, 200);
		   	
		   	System.out.println(response.getBody().asString());
		   
		   }
		   
		   @Test
		   public void givenUrl_ForUpdateCompany()
		   {		
		  
		   	RequestSpecification request = RestAssured.given();
		    
		  JSONObject requestParams = new JSONObject();
		   	requestParams.put("companyName", "UpdateTestFromRESTAssured"); // Cast
		   	requestParams.put("id", "1");
		    request.pathParam("id", "3");
		   	request.body(requestParams.toJSONString()).contentType("application/json");
		   
		   	Response response = request.put("/api/v2/companies/{id}");
		    
		   	int statusCode = response.getStatusCode();
		   	Assert.assertEquals(statusCode, 200);
		   	System.out.println(response.getBody().asString());
		   
		   }
		   @Test
		   @AfterAll
		   public void givenUrl_FordeleteCompany()
		   {		
		  
		   	RequestSpecification request = RestAssured.given();
		    
		  JSONObject requestParams = new JSONObject();
		   	
		    request.pathParam("id", "55").contentType(ContentType.JSON);;
		  
		   
		   	Response response = request.delete("/api/v2/companies/{id}");
		    
		   	int statusCode = response.getStatusCode();
		   	Assert.assertEquals(statusCode, 200);
		   	System.out.println(response.getBody().asString());
		   
		   }
}
