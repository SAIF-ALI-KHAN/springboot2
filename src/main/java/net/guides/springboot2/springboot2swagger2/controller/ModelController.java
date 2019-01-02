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
import net.guides.springboot2.springboot2swagger2.model.Model;
import net.guides.springboot2.springboot2swagger2.serviceImp.ModelServiceImp;

@RestController
@RequestMapping("/api/v4")
@Api(value="", description="Operations pertaining to Model in this Part")
public class ModelController {

	
	@Autowired
	ModelServiceImp modelServiceImp;
	
	@ApiOperation(value = "View a list of available Models", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping("/Models")
	public List<Model> getAllModels() {
		return modelServiceImp.getAllModel();
	}
	
	@ApiOperation(value = "Add a Model")
	@PostMapping("/Models")
	public Model createModel(
			@ApiParam(value = "model object store in database table", required = true)
			@Valid @RequestBody Model model) {
		return modelServiceImp.createModel(model);
	}
	
	
	@ApiOperation(value = "Update a model")
	@PutMapping("/Models/{id}")
	public ResponseEntity<Model> updateModel(
			@ApiParam(value = "User Id to update Model object", required = true)
			@PathVariable(value = "id") Integer modelId,
			@ApiParam(value = "Update Model object", required = true)
			@Valid @RequestBody Model modelDetails) throws ResourceNotFoundException {
		Model model = modelServiceImp.getModel(modelId);
       model.setModelName(modelDetails.getModelName());
       model.setUserId(modelDetails.getUserId());
				
		final Model updateModel = modelServiceImp.updateModel(model);
		return ResponseEntity.ok(updateModel);
	}
	
	
	@ApiOperation(value = "Delete a Model")
	@DeleteMapping("/Models/{id}")
	public Map<String, Boolean> deleteModel(
			@ApiParam(value = "Model Id from which Users object will delete from database table", required = true)
			@PathVariable(value = "id") Integer modelId)
			throws ResourceNotFoundException {
		Model model = modelServiceImp.getModel(modelId);
		modelServiceImp.deleteModel(model);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
