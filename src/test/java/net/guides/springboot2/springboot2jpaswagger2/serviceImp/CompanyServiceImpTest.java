package net.guides.springboot2.springboot2jpaswagger2.serviceImp;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import net.guides.springboot2.springboot2swagger2.model.Company;
import net.guides.springboot2.springboot2swagger2.repository.CompanyRepository;
import net.guides.springboot2.springboot2swagger2.serviceImp.CompanyServiceImp;



public class CompanyServiceImpTest {

	List<Company> companies;
	Company company, updatecompany;
	@InjectMocks
	CompanyServiceImp companyService;
	@Mock
	CompanyRepository companyrepository;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		companies = new ArrayList<>();
		company = new Company();
		company.setId(1);
		company.setCompanyName("TestComapany");
		updatecompany = new Company();
		updatecompany.setCompanyName("UpdateCompany");
		updatecompany.setId(1);
		companies.add(company);
	}

	@Test
	public void testGetAllCompanies() {
    Mockito.when(companyrepository.findAll()).thenReturn(companies);
    assertEquals(companies,companyService.getAllCompanies());
	}
	@Test
	public void testCreateCompany() {
		Mockito.when(companyrepository.save(company)).thenReturn(company);
		assertEquals(company,companyService.createCompany(company));
	}
	@Test
	public void testUpdateCompany() {
		
		Mockito.when(companyrepository.save(updatecompany)).thenReturn(updatecompany);
		assertEquals(updatecompany,companyService.updateCompany(updatecompany));
	}
	@Test
	public void testDeleteCompany() {

		
		
	}
}
