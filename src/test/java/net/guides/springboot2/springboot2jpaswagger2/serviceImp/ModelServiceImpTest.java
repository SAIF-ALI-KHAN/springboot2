package net.guides.springboot2.springboot2jpaswagger2.serviceImp;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import net.guides.springboot2.springboot2swagger2.model.Model;
import net.guides.springboot2.springboot2swagger2.repository.ModelRepository;
import net.guides.springboot2.springboot2swagger2.serviceImp.ModelServiceImp;


public class ModelServiceImpTest {

	List<Model> models;
	Model model, updateModel;
	@InjectMocks
	ModelServiceImp modelServiceImp;
	@Mock
	ModelRepository modelRepository;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		models = new ArrayList<>();
		model = new Model();
		model.setId(1);
		model.setModelName("testModel");
		
		updateModel = new Model();
		updateModel.setId(1);
		updateModel.setModelName("testModel");
		models.add(model);
	}

	@Test
	public void testGetAllModels() {
    Mockito.when(modelRepository.findAll()).thenReturn(models);
    assertEquals(models,modelServiceImp.getAllModel());
	}
	@Test
	public void testCreateModel() {
		Mockito.when(modelRepository.save(model)).thenReturn(model);
		assertEquals(model,modelServiceImp.createModel(model));
	}
	@Test
	public void testUpdateModel() {
		
		Mockito.when(modelRepository.save(updateModel)).thenReturn(updateModel);
		assertEquals(updateModel,modelServiceImp.updateModel(updateModel));
	}
}
