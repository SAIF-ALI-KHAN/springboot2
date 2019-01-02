package net.guides.springboot2.springboot2swagger2.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import net.guides.springboot2.springboot2swagger2.model.Company;
import net.guides.springboot2.springboot2swagger2.model.Model;
import net.guides.springboot2.springboot2swagger2.serviceImp.ModelServiceImp;
@RunWith(SpringRunner.class)
@WebMvcTest(value = ModelController.class, secure = false)
public class ModelControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	ModelServiceImp modelServiceImp;
	
	 List<Model> models=new ArrayList<>();
	   Model model,updateModel;
	
	@Test
	public void testGetModelList() throws Exception {
		
		Mockito.when(
				modelServiceImp.getAllModel()).thenReturn(models);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/api/v4/Models").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		
		

		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
	}
	
	@Test
	public void saveModel()throws Exception{
		
	model=new Model();
	model.setId(1);
    model.setModelName("TestModel");
		String json="{\r\n" + 
				"  \"id\": 1,\r\n" + 
				"  \"modelName\": \"TestModel\",\r\n" + 
				"  \"userId\": {\r\n" + 
				"    \"companyId\": {\r\n" + 
				"      \"companyName\": \"TestCompany\",\r\n" + 
				"      \"id\": 1\r\n" + 
				"    },\r\n" + 
				"    \"id\": 1,\r\n" + 
				"    \"userName\": \"TestUser\"\r\n" + 
				"  }\r\n" + 
				"}";
		Mockito.when(modelServiceImp.createModel(model)).thenReturn(model);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v4/Models").accept(
				MediaType.APPLICATION_JSON).content(json)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
	}
	
	
	
	
	@Test
	public void updateModel()throws Exception{
		model=new Model();
		model.setId(1);
	    model.setModelName("TestModel");
	    updateModel=new Model();
	    updateModel.setId(1);
	    updateModel.setModelName("updateModel");
		String json="{\r\n" + 
				"  \"id\": 1,\r\n" + 
				"  \"modelName\": \"UpdateModel\",\r\n" + 
				"  \"userId\": {\r\n" + 
				"    \"companyId\": {\r\n" + 
				"      \"companyName\": \"string\",\r\n" + 
				"      \"id\": 0\r\n" + 
				"    },\r\n" + 
				"    \"id\": 0,\r\n" + 
				"    \"userName\": \"string\"\r\n" + 
				"  }\r\n" + 
				"}";
		Mockito.when(modelServiceImp.updateModel(model)).thenReturn(updateModel);
		Mockito.when(modelServiceImp.getModel(1)).thenReturn(model);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/v4/Models/{id}",1).accept(
				MediaType.APPLICATION_JSON).content(json)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse().getContentAsString());
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
	}
	
	@Test
	public void deleteModel()throws Exception{
		model=new Model();
		model.setId(1);
	    model.setModelName("TestModel");
	    Mockito.when(modelServiceImp.getModel(1)).thenReturn(model);
	    modelServiceImp.deleteModel(model);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v4/Models/{id}",1).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse().getContentAsString());
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
	}
	

}
