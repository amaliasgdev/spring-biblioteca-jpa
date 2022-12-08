package com.asg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.asg.entities.Libro;
import com.asg.services.LibroServiceImpl;



@Controller
@RequestMapping("/prestamos")
public class PrestamosController {
	
	@Autowired
	LibroServiceImpl service;	
	
	@GetMapping("")
	public String list(Model model) {	
		List<Libro> prestados = service.listaPrestados();
		model.addAttribute("aviso", "Atención. No hay libros prestados que mostrar.");
		model.addAttribute("list", prestados);	
		return "prestamos/list";
	}

}
