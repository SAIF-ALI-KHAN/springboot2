package net.guides.springboot2.springboot2swagger2.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import net.guides.springboot2.springboot2swagger2.model.Company;
import net.guides.springboot2.springboot2swagger2.serviceImp.CompanyServiceImp;




@RunWith(SpringRunner.class)
@WebMvcTest(value = CompanyController.class, secure = false)

public class CompanyControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	CompanyServiceImp companyserviceImp;
   List<Company> companies=new ArrayList<>();
   Company company,updatecompany;
   
   
	@Test
	public void getAllCompanies() throws Exception {
		Mockito.when(
				companyserviceImp.getAllCompanies()).thenReturn(companies);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/api/v2/companies").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		
		

		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
	}
	@Test
	public void saveCompany()throws Exception{
		company=new Company();
		company.setCompanyName("testCompany");
		company.setId(1);
		String json="{\r\n" + 
				"  \"companyName\": \"testCompany\",\r\n" + 
				"  \"id\": 1\r\n" + 
				"}";
		Mockito.when(companyserviceImp.createCompany(company)).thenReturn(company);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v2/companies").accept(
				MediaType.APPLICATION_JSON).content(json)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
	}
	
	@Test
	public void updateCompany()throws Exception{
		company=new Company();
		company.setCompanyName("testCompany");
		company.setId(1);
		updatecompany=new Company();
		updatecompany.setCompanyName("updatecompsny");
		updatecompany.setId(1);
		String json="{\r\n" + 
				"  \"companyName\": \"updateCompany\",\r\n" + 
				"  \"id\": 1\r\n" + 
				"}";
		Mockito.when(companyserviceImp.createCompany(company)).thenReturn(updatecompany);
		Mockito.when(companyserviceImp.getCompany(1)).thenReturn(company);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/v2/companies/{id}",1).accept(
				MediaType.APPLICATION_JSON).content(json)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse().getContentAsString());
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
	}
	
	@Test
	public void deleteCompany()throws Exception{
		company=new Company();
		company.setCompanyName("testCompany");
		company.setId(1);
			
		Mockito.when(companyserviceImp.getCompany(1)).thenReturn(company);
		companyserviceImp.deleteCompany(company);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v2/companies/{id}",1).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse().getContentAsString());
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
	}
	
}
