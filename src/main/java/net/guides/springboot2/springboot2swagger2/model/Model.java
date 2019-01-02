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
@Table(name = "model")
@ApiModel(description="All details about the Model. ")
public class Model {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "model_id", unique = true, nullable = false)
    @ApiModelProperty(notes = "The database generated model ID")
    private Integer id;

    @Column(name = "model_name")
    @ApiModelProperty(notes = "The model name")
    private String modelName;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ApiModelProperty(notes = "The user id")
    private User userId;
    
    
    public Model() {
    	
    }
    
    
    

	public Model(String modelName, User userId) {
		
		this.modelName = modelName;
		this.userId = userId;
	}







	public Integer getId() {
		return id;
	}






	public void setId(Integer id) {
		this.id = id;
	}






	public String getModelName() {
		return modelName;
	}


	public void setModelName(String modelName) {
		this.modelName = modelName;
	}


	public User getUserId() {
		return userId;
	}


	public void setUserId(User userId) {
		this.userId = userId;
	}
    
    

}
