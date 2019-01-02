package net.guides.springboot2.springboot2swagger2.serviceImp;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.guides.springboot2.springboot2swagger2.exception.ResourceNotFoundException;

import net.guides.springboot2.springboot2swagger2.model.Model;
import net.guides.springboot2.springboot2swagger2.repository.ModelRepository;
import net.guides.springboot2.springboot2swagger2.service.Modelservice;

@Service
@Transactional
public class ModelServiceImp implements Modelservice {

	@Autowired
	ModelRepository modelRepositry;
	
	
	


	@Override
	public Model updateModel(Model model) {
		// TODO Auto-generated method stub
		return modelRepositry.save(model);
	}

	@Override
	public void deleteModel(Model model) {
		// TODO Auto-generated method stub
		modelRepositry.delete(model);
	}

	@Override
	public Model getModel(Integer modelId) {
		// TODO Auto-generated method stub
		Model model=null;
		try {
			model = modelRepositry.findById(modelId)
					.orElseThrow(() -> new ResourceNotFoundException("model not found for this id :: " + modelId));
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return model;
		
	}
	

	@Override
	public List<Model> getAllModel() {
		// TODO Auto-generated method stub
		return modelRepositry.findAll();
	}

	@Override
	public Model createModel(Model model) {
		// TODO Auto-generated method stub
		return modelRepositry.save(model);
	}

}
