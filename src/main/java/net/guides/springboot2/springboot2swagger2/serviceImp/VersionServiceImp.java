package net.guides.springboot2.springboot2swagger2.serviceImp;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.guides.springboot2.springboot2swagger2.exception.ResourceNotFoundException;
import net.guides.springboot2.springboot2swagger2.model.User;
import net.guides.springboot2.springboot2swagger2.model.Version;
import net.guides.springboot2.springboot2swagger2.repository.Versionrepository;
import net.guides.springboot2.springboot2swagger2.service.Versionservice;

@Service
@Transactional
public class VersionServiceImp implements Versionservice {

	@Autowired
	Versionrepository versionRepository;
	
	@Override
	public List<Version> getAllVersion() {
		// TODO Auto-generated method stub
		return versionRepository.findAll();
	}

	@Override
	public Version createVersion(Version version) {
		// TODO Auto-generated method stub
		return versionRepository.save(version);
	}

	@Override
	public Version updateVersion(Version version) {
		// TODO Auto-generated method stub
		return versionRepository.save(version);
	}

	@Override
	public void deleteVersion(Version version) {
		// TODO Auto-generated method stub
		versionRepository.delete(version);
	}

	@Override
	public Version getVersion(Integer versionId) {
		// TODO Auto-generated method stub
		Version version=null;
		try {
			version = versionRepository.findById(versionId)
					.orElseThrow(() -> new ResourceNotFoundException("scenario not found for this id :: " +versionId));
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return version;
	}

}
