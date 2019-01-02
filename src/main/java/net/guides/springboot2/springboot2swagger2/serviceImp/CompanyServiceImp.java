package net.guides.springboot2.springboot2swagger2.serviceImp;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.guides.springboot2.springboot2swagger2.exception.ResourceNotFoundException;
import net.guides.springboot2.springboot2swagger2.model.Company;
import net.guides.springboot2.springboot2swagger2.model.Employee;
import net.guides.springboot2.springboot2swagger2.repository.CompanyRepository;
import net.guides.springboot2.springboot2swagger2.service.CompanyService;


@Service
@Transactional
public class CompanyServiceImp implements CompanyService{

	
	@Autowired
	CompanyRepository companyRepository;
	
	
	@Override
	public List<Company> getAllCompanies() {
		// TODO Auto-generated method stub
		
		
		return companyRepository.findAll();
	}

	@Override
	public Company createCompany(Company company) {
		// TODO Auto-generated method stub
		return companyRepository.save(company);
	}

	@Override
	public Company updateCompany(Company company) {
		// TODO Auto-generated method stub
		return companyRepository.save(company);
	}

	@Override
	public void deleteCompany(Company company) {
		// TODO Auto-generated method stub
		companyRepository.delete(company);
		
		}
	
	
	@Override
	public Company getCompany(Integer companyId)  {
		// TODO Auto-generated method stub
		Company company=null;
		try {
			company = companyRepository.findById(companyId)
					.orElseThrow(() -> new ResourceNotFoundException("Company not found for this id :: " + companyId));
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return company;
		
	}


}
