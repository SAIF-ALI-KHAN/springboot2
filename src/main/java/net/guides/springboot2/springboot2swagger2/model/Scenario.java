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
@Table(name = "scenario")
@ApiModel(description="All details about the Scenario. ")
public class Scenario {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "scenario_id", unique = true, nullable = false)
    @ApiModelProperty(notes = "The database generated scenario ID")
    private Integer scenarioId;
    
    @Column(name = "scenario_name")
    @ApiModelProperty(notes = "The scenario name")
    private String scenarioName;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "version_id", referencedColumnName = "version_id")
    @ApiModelProperty(notes = "The version id")
    private Version versionId;

	public Scenario() {
		
	}
    
    public Scenario(String scenarioName, Version versionId) {
		
		this.scenarioName = scenarioName;
		this.versionId = versionId;
	}

	public Integer getScenarioId() {
		return scenarioId;
	}

	public void setScenarioId(Integer scenarioId) {
		this.scenarioId = scenarioId;
	}

	public String getScenarioName() {
		return scenarioName;
	}

	public void setScenarioName(String scenarioName) {
		this.scenarioName = scenarioName;
	}

	public Version getVersionId() {
		return versionId;
	}

	public void setVersionId(Version versionId) {
		this.versionId = versionId;
	}
    
    
    
    
    
    
}
