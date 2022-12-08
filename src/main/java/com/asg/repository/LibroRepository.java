package com.asg.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.asg.entities.Libro;
import com.asg.entities.Socio;


@Repository
public interface LibroRepository extends JpaRepository<Libro, Integer> {	
	
	public List<Libro> findTop2ByOrderByFechaAdquisicionDesc();
	public List<Libro> findBySocioNotNull();
	public List<Libro> findBySocio(Socio socio);
	
	
		
}
