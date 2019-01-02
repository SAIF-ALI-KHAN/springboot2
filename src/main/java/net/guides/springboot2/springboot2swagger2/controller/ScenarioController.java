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
import net.guides.springboot2.springboot2swagger2.model.Scenario;
import net.guides.springboot2.springboot2swagger2.serviceImp.ScenarioServiceImp;


@RestController
@RequestMapping("/api/v6")
@Api(value="", description="Operations pertaining to Scenario in this Part")
public class ScenarioController {

@Autowired
ScenarioServiceImp scenarioserviceImp; 


@ApiOperation(value = "View a list of available Scenario", response = List.class)
@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
		@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
		@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
		@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
@GetMapping("/Scenario")
public List<Scenario> getAllScenario() {
	return scenarioserviceImp.getAllScenario();
}

@ApiOperation(value = "Add a Scenario")
@PostMapping("/Scenario")
public Scenario createScenario(
		@ApiParam(value = "Scenario object store in database table", required = true)
		@Valid @RequestBody Scenario scenario) {
	return scenarioserviceImp.createScenario(scenario);
}


@ApiOperation(value = "Update a Scenario")
@PutMapping("/Scenario/{id}")
public ResponseEntity<Scenario> updateScenario(
		@ApiParam(value = "Scenario Id to update Scenario object", required = true)
		@PathVariable(value = "id") Integer scenarioId,
		@ApiParam(value = "Update Scenario object", required = true)
		@Valid @RequestBody Scenario scenarioDetails) throws ResourceNotFoundException {
	Scenario scenario = scenarioserviceImp.getScenario(scenarioId);

	scenario.setScenarioName(scenarioDetails.getScenarioName());	
	scenario.setVersionId(scenarioDetails.getVersionId());
	final Scenario updatedUser = scenarioserviceImp.updateScenario(scenario);
	return ResponseEntity.ok(updatedUser);
}


@ApiOperation(value = "Delete a Scenario")
@DeleteMapping("/Scenario/{id}")
public Map<String, Boolean> deleteUser(
		@ApiParam(value = "Scenario Id from which Scenario object will delete from database table", required = true)
		@PathVariable(value = "id") Integer scenarioId)
		throws ResourceNotFoundException {
	Scenario scenario = scenarioserviceImp.getScenario(scenarioId);
	scenarioserviceImp.deleteScenario(scenario);
	Map<String, Boolean> response = new HashMap<>();
	response.put("deleted", Boolean.TRUE);
	return response;
}

}
