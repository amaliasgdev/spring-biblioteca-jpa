package com.asg.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asg.entities.Libro;
import com.asg.entities.Socio;
import com.asg.repository.LibroRepository;

@Service
public class LibroServiceImpl implements ILibroService {

	@Autowired
	LibroRepository repo;

	@Override
	public List<Libro> findAllLibros() {
		return repo.findAll();
	}

	@Override
	public Libro saveLibro(Libro libro) {
		return repo.save(libro);
	}

	@Override
	public Optional<Libro> findByIdLibro(Integer id) {
		return repo.findById(id);
	}

	@Override
	public void deleteLibro(Integer id) {
		repo.deleteById(id);
	}

	@Override
	public Libro update(Libro libro) {
		return repo.save(libro);
	}	
	
	public List<Libro> findTop2ByOrderByFechaAdquisicionDesc(){
		return repo.findTop2ByOrderByFechaAdquisicionDesc();
	}
	
	
	@Override	
	public List<Libro> listaPrestados(){
		return repo.findBySocioNotNull();
	}

	@Override
	public void prestarLibro(Libro libro) {
		repo.save(libro);		
	}

	@Override
	public void devolverLibro(Integer id) {
		Libro libro = repo.findById(id).get();
		libro.setSocio(null);
		repo.save(libro);		
	}

	@Override
	public List<Libro> findBySocio(Socio socio) {
		return repo.findBySocio(socio);
	}	
		
}
