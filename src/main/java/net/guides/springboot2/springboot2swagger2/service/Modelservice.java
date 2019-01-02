package net.guides.springboot2.springboot2swagger2.service;

import java.util.List;

import net.guides.springboot2.springboot2swagger2.model.Model;


public interface Modelservice {

	
	//Get All Model. 
		public List<Model> getAllModel();
		
		//To create a Model
		public  Model createModel(Model model);
		
		
		//To update a Model
		public Model updateModel(Model model);
		
		//To Delete a Model
		
		public void deleteModel(Model model);
		
		//To GetModel By Id
		public Model getModel(Integer modelId);
}
