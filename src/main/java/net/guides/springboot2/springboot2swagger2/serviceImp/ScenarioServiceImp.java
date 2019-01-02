package net.guides.springboot2.springboot2swagger2.serviceImp;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.guides.springboot2.springboot2swagger2.exception.ResourceNotFoundException;

import net.guides.springboot2.springboot2swagger2.model.Scenario;
import net.guides.springboot2.springboot2swagger2.repository.ScenarioRepository;
import net.guides.springboot2.springboot2swagger2.service.ScenarioService;

@Service
@Transactional
public class ScenarioServiceImp implements ScenarioService {

	@Autowired
	ScenarioRepository scenarioRepository;
	
	@Override
	public List<Scenario> getAllScenario() {
		// TODO Auto-generated method stub
		return scenarioRepository.findAll();
	}

	@Override
	public Scenario createScenario(Scenario scenario) {
		// TODO Auto-generated method stub
		return scenarioRepository.save(scenario);
	}

	@Override
	public Scenario updateScenario(Scenario scenario) {
		// TODO Auto-generated method stub
		return scenarioRepository.save(scenario);
	}

	@Override
	public void deleteScenario(Scenario scenario) {
		// TODO Auto-generated method stub
		scenarioRepository.delete(scenario);
	}

	@Override
	public Scenario getScenario(Integer scenarioId) {
		// TODO Auto-generated method stub
		Scenario scenario=null;
		try {
			scenario = scenarioRepository.findById(scenarioId)
					.orElseThrow(() -> new ResourceNotFoundException("scenario not found for this id :: " +scenarioId));
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return scenario;
	}

}
