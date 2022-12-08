package com.asg.services;

import java.util.List;
import java.util.Optional;

import com.asg.entities.Socio;

public interface ISocioService {
	
	public List<Socio> findAllSocios();
	public Socio saveSocio(Socio socio);
	public Socio updateSocio(Socio socio);
	public Optional<Socio> findByIdSocio(Integer id);
	public void deleteSocio(Integer id);
	
	//public boolean socioTienePrestamos(Integer idSocio);
}
