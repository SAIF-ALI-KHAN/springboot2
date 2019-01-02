package net.guides.springboot2.springboot2swagger2.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import net.guides.springboot2.springboot2swagger2.exception.ResourceNotFoundException;
import net.guides.springboot2.springboot2swagger2.model.User;
import net.guides.springboot2.springboot2swagger2.serviceImp.UserServiceImp;

@RestController
@RequestMapping("/api/v3")
@Api(value="", description="Operations pertaining to User in this Part")
public class UserController {

	@Autowired
	UserServiceImp userServiceImp;
	
	
	
	@ApiOperation(value = "View a list of available Users", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping("/Users")
	public List<User> getAllUser() {
		return userServiceImp.getAllUser();
	}
	
	@ApiOperation(value = "Add a User")
	@PostMapping("/Users")
	public User createUser(
			@ApiParam(value = "User object store in database table", required = true)
			@Valid @RequestBody User user) {
		return userServiceImp.createUser(user);
	}
	
	
	@ApiOperation(value = "Update a User")
	@PutMapping("/Users/{id}")
	public ResponseEntity<User> updateUser(
			@ApiParam(value = "User Id to update company object", required = true)
			@PathVariable(value = "id") Integer userId,
			@ApiParam(value = "Update User object", required = true)
			@Valid @RequestBody User userDetails) throws ResourceNotFoundException {
		User user = userServiceImp.getUser(userId);

		user.setUserName(userDetails.getUserName());
        user.setCompanyId(userDetails.getCompanyId());		
		final User updatedUser = userServiceImp.updateUser(user);
		return ResponseEntity.ok(updatedUser);
	}
	
	
	@ApiOperation(value = "Delete a User")
	@DeleteMapping("/Users/{id}")
	public Map<String, Boolean> deleteUser(
			@ApiParam(value = "Users Id from which Users object will delete from database table", required = true)
			@PathVariable(value = "id") Integer userId)
			throws ResourceNotFoundException {
		User user = userServiceImp.getUser(userId);
		userServiceImp.deleteUser(user);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
}
