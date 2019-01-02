package net.guides.springboot2.springboot2swagger2.service;

import java.util.List;

import net.guides.springboot2.springboot2swagger2.model.Company;

public interface CompanyService {

	//Get All Companies. 
	public List<Company> getAllCompanies();
	
	//To create a Company
	public  Company createCompany(Company company);
	
	
	//To update a company
	public Company updateCompany(Company company);
	
	//To Delete a company
	
	public void deleteCompany(Company comany);
	
	//To GetCompany By Id
	public Company getCompany(Integer comanyID);
}
