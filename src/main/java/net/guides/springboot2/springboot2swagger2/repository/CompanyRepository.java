package net.guides.springboot2.springboot2swagger2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.guides.springboot2.springboot2swagger2.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer>{
 
	
	
}
