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
@Table(name = "version")
@ApiModel(description="All details about the Vesion. ")
public class Version {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "version_id", unique = true, nullable = false)
    @ApiModelProperty(notes = "The database generated vesion ID")
    private Integer id;

    @Column(name = "version_name")
    @ApiModelProperty(notes = "The version name")
    private String versionName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "model_id", referencedColumnName = "model_id")
    @ApiModelProperty(notes = "The model id")
    private Model modelId;
    
    
    public Version() {
    	
    }


    
    
    
	public Version(String versionName, Model modelId) {
		
		this.versionName = versionName;
		this.modelId = modelId;
	}





	public Integer getId() {
		return id;
	}





	public void setId(Integer id) {
		this.id = id;
	}





	public String getVersionName() {
		return versionName;
	}


	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}


	public Model getModelId() {
		return modelId;
	}


	public void setModelId(Model modelId) {
		this.modelId = modelId;
	}
    
    
    
}