package net.guides.springboot2.springboot2swagger2.model;
import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "user")
@ApiModel(description="All details about the User. ")
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "user_id", unique = true, nullable = false)
    @ApiModelProperty(notes = "The database generated user ID")
    private Integer id;
    
    @Column(name = "user_name")
    @ApiModelProperty(notes = "The user name")
    private String userName;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id", referencedColumnName = "company_id")
    @ApiModelProperty(notes = "The company id")
    private Company companyId;

    
    
    
    
    
    public User() {
    	
    }
    
    
	public User(String userName, Company companyId) {
		
		this.userName = userName;
		this.companyId = companyId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Company getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Company companyId) {
		this.companyId = companyId;
	}
    
    
    
    
}
