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

import net.guides.springboot2.springboot2swagger2.model.Model;
import net.guides.springboot2.springboot2swagger2.model.Scenario;
import net.guides.springboot2.springboot2swagger2.serviceImp.ScenarioServiceImp;
@RunWith(SpringRunner.class)
@WebMvcTest(value = ScenarioController.class, secure = false)
public class ScenarioControllerTest {

	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	ScenarioServiceImp scenarioServiceImp;
	 List<Scenario> Scenarios=new ArrayList<>();
	 Scenario scenario,updateScenario;
	
		@Test
		public void testGetScenarioList() throws Exception {
			
			Mockito.when(
					scenarioServiceImp.getAllScenario()).thenReturn(Scenarios);

			RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
					"/api/v6/Scenario").accept(
					MediaType.APPLICATION_JSON);

			MvcResult result = mockMvc.perform(requestBuilder).andReturn();

			
			

			assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
		}
		
		@Test
		public void saveScenario()throws Exception{
			
			scenario=new Scenario();
		scenario.setScenarioId(1);
		scenario.setScenarioName("TestScenario");
			String json="{\r\n" + 
					"  \"scenarioId\": 1,\r\n" + 
					"  \"scenarioName\": \"testscenario\",\r\n" + 
					"  \"versionId\": {\r\n" + 
					"    \"id\": 1,\r\n" + 
					"    \"modelId\": {\r\n" + 
					"      \"id\": 1,\r\n" + 
					"      \"modelName\": \"testModel\",\r\n" + 
					"      \"userId\": {\r\n" + 
					"        \"companyId\": {\r\n" + 
					"          \"companyName\": \"testCompany\",\r\n" + 
					"          \"id\": 1\r\n" + 
					"        },\r\n" + 
					"        \"id\": 1,\r\n" + 
					"        \"userName\": \"testUser\"\r\n" + 
					"      }\r\n" + 
					"    },\r\n" + 
					"    \"versionName\": \"testVersion\"\r\n" + 
					"  }\r\n" + 
					"}";
			Mockito.when(scenarioServiceImp.createScenario(scenario)).thenReturn(scenario);
			RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v6/Scenario").accept(
					MediaType.APPLICATION_JSON).content(json)
					.contentType(MediaType.APPLICATION_JSON);
			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
		}
		
		
		
		
		@Test
		public void updateScenario()throws Exception{
			scenario=new Scenario();
			scenario.setScenarioId(1);
			scenario.setScenarioName("TestScenario");
			updateScenario=new Scenario();
			updateScenario.setScenarioId(1);
			updateScenario.setScenarioName("updateScenario");
			String json="{\r\n" + 
					"  \"scenarioId\": 1,\r\n" + 
					"  \"scenarioName\": \"updateScenario\",\r\n" + 
					"  \"versionId\": {\r\n" + 
					"    \"id\": 1,\r\n" + 
					"    \"modelId\": {\r\n" + 
					"      \"id\": 1,\r\n" + 
					"      \"modelName\": \"testModel\",\r\n" + 
					"      \"userId\": {\r\n" + 
					"        \"companyId\": {\r\n" + 
					"          \"companyName\": \"testCompany\",\r\n" + 
					"          \"id\": 1\r\n" + 
					"        },\r\n" + 
					"        \"id\": 1,\r\n" + 
					"        \"userName\": \"testUser\"\r\n" + 
					"      }\r\n" + 
					"    },\r\n" + 
					"    \"versionName\": \"testVersion\"\r\n" + 
					"  }\r\n" + 
					"}";
			Mockito.when(scenarioServiceImp.updateScenario(scenario)).thenReturn(updateScenario);
			Mockito.when(scenarioServiceImp.getScenario(1)).thenReturn(scenario);
			RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/v6/Scenario/{id}",1).accept(
					MediaType.APPLICATION_JSON).content(json)
					.contentType(MediaType.APPLICATION_JSON);
			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			System.out.println(result.getResponse().getContentAsString());
			assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
		}
		
		@Test
		public void deleteScenario()throws Exception{
			scenario=new Scenario();
			scenario.setScenarioId(1);
			scenario.setScenarioName("TestScenario");
			Mockito.when(scenarioServiceImp.getScenario(1)).thenReturn(scenario);
			scenarioServiceImp.deleteScenario(scenario);
			RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v6/Scenario/{id}",1).accept(MediaType.APPLICATION_JSON);
			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			System.out.println(result.getResponse().getContentAsString());
			assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
		}
}
