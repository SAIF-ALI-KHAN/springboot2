package stepdefs;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import net.guides.springboot2.springboot2swagger2.model.Company;
import net.minidev.json.JSONObject;

public class CompanyStepDefinitions {


	private Response response;
	private ValidatableResponse json;
	private RequestSpecification request;

	private String ENDPOINT_GET_BOOK_BY_ISBN = "http://localhost:8080/api/v2/companies";

	@Given("get All Companies")
	public void get_All_Companies(){
		request = given();
	}
	
	@When("a user retrieves all companies")
	public void a_user_retrieves_all_companies(){
		response = request.when().get(ENDPOINT_GET_BOOK_BY_ISBN);
		System.out.println("response: " + response.prettyPrint());
	}

	@Then("the status code is (\\d+)")
	public void verify_status_code(int statusCode){
		json = response.then().statusCode(statusCode);
	}
	
	@Given("get the companyDetail for (.*)")
	public void get_the_companyDetail(Company  company){
//		 JSONObject requestParams = new JSONObject();
//		   	requestParams.put("companyName", "companyCucmberTest"); // Cast
//		   	requestParams.put("id", "1");
		request = given().body(company).contentType("application/json");
	}
	
	@When("I save the new company")
	public void I_save_the_new_company() {
		response = request.when().post(ENDPOINT_GET_BOOK_BY_ISBN);
	}
	
	
	@Then("verify status code is (\\d+)")
	public void verify_status_codes(int statusCode){
		json = response.then().statusCode(statusCode);
	}
	
	
	@And("response includes the following")
	public void response_contains_in_any_order(Map<String,String> responseFields){
		
		for (Map.Entry<String, String> field : responseFields.entrySet()) {
			if(StringUtils.isNumeric(field.getValue())){
				json.body(field.getKey(), equalTo(Integer.parseInt(field.getValue())));
			}
			else{
				json.body(field.getKey(), equalTo(field.getValue()));
			}
		}
				
	}
	
	@Given("get the updated companyDetail by id of (.*)")
	public void get_the_updated_companyDetail_by_id(Company  company){
		 JSONObject requestParams = new JSONObject();
		   	requestParams.put("companyName", "updatecompanyCucmberTest"); // Cast
		   	requestParams.put("id", "58");
		request = given().body(requestParams).contentType("application/json").param("id", "id:" + "58");
	}
	@When("I save the updated company")
	public void I_save_the_updated_company() {
		response = request.when().put(ENDPOINT_GET_BOOK_BY_ISBN);
	}
	@Then("check status code is (\\d+)")
	public void check_status_code(int statusCode){
		json = response.then().statusCode(statusCode);
	}
	@And("response includes the following update")
	public void response_contains_in(Map<String,String> responseFields){
		
		for (Map.Entry<String, String> field : responseFields.entrySet()) {
			if(StringUtils.isNumeric(field.getValue())){
				json.body(field.getKey(), equalTo(Integer.parseInt(field.getValue())));
			}
			else{
				json.body(field.getKey(), equalTo(field.getValue()));
			}
		}
				
	}
}
