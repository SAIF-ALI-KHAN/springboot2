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
import net.guides.springboot2.springboot2swagger2.model.Company;
import net.guides.springboot2.springboot2swagger2.model.Employee;
import net.guides.springboot2.springboot2swagger2.serviceImp.CompanyServiceImp;

@RestController
@RequestMapping("/api/v2")
@Api(value="", description="Operations pertaining to Company in this Part")
public class CompanyController {
	@Autowired
	CompanyServiceImp companyServiceImp;
	
	@ApiOperation(value = "View a list of available Companies", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping("/companies")
	public List<Company> getAllCompanies() {
		return companyServiceImp.getAllCompanies();
	}
	 
	@ApiOperation(value = "Add a Company")
	@PostMapping("/companies")
	public Company createCompany(
			@ApiParam(value = "Company object store in database table", required = true)
			@Valid @RequestBody Company company) {
		return companyServiceImp.createCompany(company);
	}
	
	
	@ApiOperation(value = "Update a Company")
	@PutMapping("/companies/{id}")
	public ResponseEntity<Company> updateCompany(
			@ApiParam(value = "Company Id to update company object", required = true)
			@PathVariable(value = "id") Integer CompanyId,
			@ApiParam(value = "Update company object", required = true)
			@Valid @RequestBody Company companyDetails) throws ResourceNotFoundException {
		Company company = companyServiceImp.getCompany(CompanyId);

		company.setCompanyName(companyDetails.getCompanyName());
		
		final Company updatedCompany = companyServiceImp.updateCompany(company);
		return ResponseEntity.ok(updatedCompany);
	}
	
	
	@ApiOperation(value = "Delete a Company")
	@DeleteMapping("/companies/{id}")
	public Map<String, Boolean> deleteCompany(
			@ApiParam(value = "Company Id from which company object will delete from database table", required = true)
			@PathVariable(value = "id") Integer companyId)
			throws ResourceNotFoundException {
		Company company = companyServiceImp.getCompany(companyId);
		companyServiceImp.deleteCompany(company);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
}
