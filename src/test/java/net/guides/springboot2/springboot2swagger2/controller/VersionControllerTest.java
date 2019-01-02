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

import net.guides.springboot2.springboot2swagger2.model.User;
import net.guides.springboot2.springboot2swagger2.model.Version;
import net.guides.springboot2.springboot2swagger2.serviceImp.VersionServiceImp;


import org.codehaus.jackson.map.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value = VersionController.class, secure = false)
public class VersionControllerTest {
@Autowired
private MockMvc mockMvc;

@MockBean
VersionServiceImp versionServiceImp; 

 List<Version> versions=new ArrayList<>();
 Version version,updateVersion;

 @Test
	public void testGetAllVersions() throws Exception {
		
		Mockito.when(
				versionServiceImp.getAllVersion()).thenReturn(versions);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/api/v5/Versions").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		
		

		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
	}
	
	@Test
	public void saveVersion()throws Exception{
		
		version=new Version();
		version.setId(1);
		version.setVersionName("testVersion");
		 ObjectMapper mapperObj = new ObjectMapper();
		 String jsonStr = mapperObj.writeValueAsString(version);
	
		Mockito.when(versionServiceImp.createVersion(version)).thenReturn(version);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v5/Versions").accept(
				MediaType.APPLICATION_JSON).content(jsonStr)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
	}
	
	
	
	
	@Test
	public void updateVersion()throws Exception{
		version=new Version();
		version.setId(1);
		version.setVersionName("testVersion");
		updateVersion=new Version();
		updateVersion.setId(1);
		updateVersion.setVersionName("updateVersion");
		 ObjectMapper mapperObj = new ObjectMapper();
		 String jsonStr = mapperObj.writeValueAsString(version);
		Mockito.when(versionServiceImp.updateVersion(version)).thenReturn(updateVersion);
		Mockito.when(versionServiceImp.getVersion(1)).thenReturn(version);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/v5/Versions/{id}",1).accept(
				MediaType.APPLICATION_JSON).content(jsonStr)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse().getContentAsString());
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
	}
	
	@Test
	public void deleteVersion()throws Exception{
		version=new Version();
		version.setId(1);
		version.setVersionName("testVersion");
		Mockito.when(versionServiceImp.getVersion(1)).thenReturn(version);
		versionServiceImp.deleteVersion(version);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v5/Versions/{id}",1).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse().getContentAsString());
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
	}
}
