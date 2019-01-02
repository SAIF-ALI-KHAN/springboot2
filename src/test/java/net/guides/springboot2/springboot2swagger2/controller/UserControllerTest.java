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
import net.guides.springboot2.springboot2swagger2.serviceImp.UserServiceImp;
@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class, secure = false)
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	UserServiceImp userserviceImp; 
	
	 List<User> users=new ArrayList<>();
	 User user,updateUser;
	
	 @Test
		public void testGetAllUser() throws Exception {
			
			Mockito.when(
					userserviceImp.getAllUser()).thenReturn(users);

			RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
					"/api/v3/Users").accept(
					MediaType.APPLICATION_JSON);

			MvcResult result = mockMvc.perform(requestBuilder).andReturn();

			
			

			assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
		}
		
		@Test
		public void saveUser()throws Exception{
			
			user=new User();
		    user.setId(1);
		    user.setUserName("testUser");
		   
			String json="{\r\n" + 
					"  \"companyId\": {\r\n" + 
					"    \"companyName\": \"testCompany\",\r\n" + 
					"    \"id\": 1\r\n" + 
					"  },\r\n" + 
					"  \"id\": 1,\r\n" + 
					"  \"userName\": \"testUser\"\r\n" + 
					"}";
			Mockito.when(userserviceImp.createUser(user)).thenReturn(user);
			RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v3/Users").accept(
					MediaType.APPLICATION_JSON).content(json)
					.contentType(MediaType.APPLICATION_JSON);
			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
		}
		
		
		
		
		@Test
		public void updateUser()throws Exception{
			user=new User();
		    user.setId(1);
		    user.setUserName("testUser");
		    updateUser=new User();
		    updateUser.setId(1);
		    updateUser.setUserName("updateUser");
			String json="{\r\n" + 
					"  \"companyId\": {\r\n" + 
					"    \"companyName\": \"testCompany\",\r\n" + 
					"    \"id\": 1\r\n" + 
					"  },\r\n" + 
					"  \"id\": 1,\r\n" + 
					"  \"userName\": \"UpdateUser\"\r\n" + 
					"}";
			Mockito.when(userserviceImp.updateUser(user)).thenReturn(updateUser);
			Mockito.when(userserviceImp.getUser(1)).thenReturn(user);
			RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/v3/Users/{id}",1).accept(
					MediaType.APPLICATION_JSON).content(json)
					.contentType(MediaType.APPLICATION_JSON);
			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			System.out.println(result.getResponse().getContentAsString());
			assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
		}
		
		@Test
		public void deleteUser()throws Exception{
			user=new User();
		    user.setId(1);
		    user.setUserName("testUser");
		    Mockito.when(userserviceImp.getUser(1)).thenReturn(user);
		    userserviceImp.deleteUser(user);
			RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v3/Users/{id}",1).accept(MediaType.APPLICATION_JSON);
			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			System.out.println(result.getResponse().getContentAsString());
			assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
		}

}
