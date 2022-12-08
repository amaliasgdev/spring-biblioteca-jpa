package com.asg.services;

import java.util.List;
import java.util.Optional;

import com.asg.entities.Libro;
import com.asg.entities.Socio;

public interface ILibroService {
	
	public List<Libro> findAllLibros();
	public Libro saveLibro(Libro libro);
	public Optional<Libro> findByIdLibro(Integer id);
	public void deleteLibro(Integer id);
	public Libro update(Libro libro);	
	public List<Libro> findTop2ByOrderByFechaAdquisicionDesc();
	public List<Libro> listaPrestados();			
	public void prestarLibro(Libro libro);
	public void devolverLibro(Integer id);	
	public List<Libro> findBySocio(Socio socio);	
	
	
	
}
