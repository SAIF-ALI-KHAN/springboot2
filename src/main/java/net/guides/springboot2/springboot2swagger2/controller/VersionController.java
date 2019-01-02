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
import net.guides.springboot2.springboot2swagger2.model.Version;
import net.guides.springboot2.springboot2swagger2.serviceImp.VersionServiceImp;

@RestController
@RequestMapping("/api/v5")
@Api(value="", description="Operations pertaining to Version in this Part")
public class VersionController {

	@Autowired
	VersionServiceImp serviceImp;
	
	
	@ApiOperation(value = "View a list of available Version", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping("/Versions")
	public List<Version> getAllVersion() {
		return serviceImp.getAllVersion();
		}
	
	@ApiOperation(value = "Add a Version")
	@PostMapping("/Versions")
	public Version createVersion(
			@ApiParam(value = "Version object store in database table", required = true)
			@Valid @RequestBody Version version) {
		return serviceImp.createVersion(version);
	}
	
	
	@ApiOperation(value = "Update a Version")
	@PutMapping("/Versions/{id}")
	public ResponseEntity<Version> updateVersions(
			@ApiParam(value = "Versions Id to update company object", required = true)
			@PathVariable(value = "id") Integer versionId,
			@ApiParam(value = "Update Versions object", required = true)
			@Valid @RequestBody Version versionDetails) throws ResourceNotFoundException {
		Version version = serviceImp.getVersion(versionId);

			version.setVersionName(versionDetails.getVersionName());
			version.setModelId(versionDetails.getModelId());
		final Version updateVersion = serviceImp.updateVersion(version);
		return ResponseEntity.ok(updateVersion);
	}
	
	
	@ApiOperation(value = "Delete a Version")
	@DeleteMapping("/Versions/{id}")
	public Map<String, Boolean> deleteVersion(
			@ApiParam(value = "Versions Id from which Users object will delete from database table", required = true)
			@PathVariable(value = "id") Integer versionId)
			throws ResourceNotFoundException {
		Version version =  serviceImp.getVersion(versionId);
		serviceImp.deleteVersion(version);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
}
