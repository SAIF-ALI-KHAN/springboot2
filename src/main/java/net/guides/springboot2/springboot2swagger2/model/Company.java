package net.guides.springboot2.springboot2swagger2.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "company")
@ApiModel(description="All details about the Company. ")
public class Company {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "company_id", unique = true, nullable = false)
    @ApiModelProperty(notes = "The database generated company ID")
    private Integer id;
    
    @Column(name = "company_name")
    @ApiModelProperty(notes = "The company name")
    private String companyName;

	
   
    
    public Company() {

	}
    
   public Company(String companyName)
   {
	   this.companyName=companyName;
   }
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
   
    
}
