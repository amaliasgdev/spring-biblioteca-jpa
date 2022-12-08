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
@RequestMapping("/inicio")
public class InicioController {

	@Autowired
	LibroServiceImpl service;
	
	@GetMapping("")
	public String inicio(Model model) {
		List<Libro> list = service.findTop2ByOrderByFechaAdquisicionDesc();
		model.addAttribute("list", list);
		return "index";
	}
	
	
}
