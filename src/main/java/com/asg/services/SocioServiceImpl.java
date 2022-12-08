package com.asg.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asg.entities.Socio;
import com.asg.repository.SocioRepository;

@Service
public class SocioServiceImpl implements ISocioService {
	
	@Autowired
	SocioRepository repo;	

	@Override
	public List<Socio> findAllSocios() {		
		return repo.findAll();
	}

	@Override
	public Socio saveSocio(Socio socio) {
		return repo.save(socio);
	}	

	@Override
	public Optional<Socio> findByIdSocio(Integer id) {		
		return repo.findById(id);
	}

	@Override
	public void deleteSocio(Integer id) {
		repo.deleteById(id);
	}

	@Override
	public Socio updateSocio(Socio socio) {		
		return repo.save(socio);
	}
	

}
