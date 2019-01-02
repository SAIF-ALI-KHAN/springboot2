package net.guides.springboot2.springboot2swagger2.service;

import java.util.List;

import net.guides.springboot2.springboot2swagger2.model.Scenario;


public interface ScenarioService {

	//Get All Scenario. 
		public List<Scenario> getAllScenario();
		
		//To create a Scenario
		public  Scenario createScenario(Scenario scenario);
		
		
		//To update a Scenario
		public Scenario updateScenario(Scenario scenario);
		
		//To Delete a Scenario
		
		public void deleteScenario(Scenario scenario);
		
		//To GetScenario By Id
		public Scenario getScenario(Integer scenarioId);

}
