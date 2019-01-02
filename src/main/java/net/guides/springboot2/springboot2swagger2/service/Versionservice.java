package net.guides.springboot2.springboot2swagger2.service;

import java.util.List;

import net.guides.springboot2.springboot2swagger2.model.Version;



public interface Versionservice {

	
	//Get All Version. 
			public List<Version> getAllVersion();
			
			//To create a Version
			public  Version createVersion(Version version);
			
			
			//To update a Version
			public Version updateVersion(Version version);
			
			//To Delete a Version
			
			public void deleteVersion(Version version);
			
			//To GetVersion By Id
			public Version getVersion(Integer versionId);
}
