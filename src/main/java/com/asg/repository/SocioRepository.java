package com.asg.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.asg.entities.Socio;

@Repository
public interface SocioRepository extends JpaRepository<Socio, Integer> {
	
}
