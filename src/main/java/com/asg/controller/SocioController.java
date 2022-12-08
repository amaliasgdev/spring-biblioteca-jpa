package com.asg.controller;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.asg.entities.Libro;
import com.asg.entities.Socio;
import com.asg.services.LibroServiceImpl;
import com.asg.services.SocioServiceImpl;

@Controller
@RequestMapping("/socios")
public class SocioController {

	@Autowired
	SocioServiceImpl service;
	
	@Autowired
	LibroServiceImpl libroService;
	

	@GetMapping("/list")
	public String list(Model model) {
		model.addAttribute("create", false);
		model.addAttribute("edit", false);
		return "socios/list";
	}

	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("socio", new Socio());
		model.addAttribute("create", true);
		model.addAttribute("edit", false);
		return "socios/list";
	}

	@PostMapping("/create")
	public String save(@Valid Socio socio, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("create", true);
			model.addAttribute("edit", false);
			return "socios/list";			
		}
		service.saveSocio(socio);
		model.addAttribute("create", false);
		model.addAttribute("edit", false);
		return "redirect:/socios/list";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, Model model) {
		Socio socio = service.findByIdSocio(id).get();
		model.addAttribute("socio", socio);
		model.addAttribute("create", false);
		model.addAttribute("edit", true);
		return "socios/list";
	}	

	@PostMapping("/update")
	public String saveEdit(@Valid Socio socio, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("create", false);
			model.addAttribute("edit", true);
			return "socios/list";
		}
		service.updateSocio(socio);
		model.addAttribute("create", false);
		model.addAttribute("edit", true);
		return "redirect:/socios/list";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id, Model model) {	
		Socio socio = service.findByIdSocio(id).get();
		List<Libro> libros = libroService.findBySocio(socio);
		if(!libros.isEmpty()) {			
			model.addAttribute("aviso", "Atención. No puede eliminar al socio " + socio.getNombre().toUpperCase() + ' ' + socio.getApellidos().toUpperCase() + " porque tiene préstamos.");
			model.addAttribute("prestamos", true);
			model.addAttribute("create", false);
			model.addAttribute("edit", false);
			return "socios/list";
		}
		service.deleteSocio(id);
		model.addAttribute("create", false);
		model.addAttribute("edit", false);
		return "redirect:/socios/list";
	}

	@PostConstruct
	private void post() {
		Socio s1 = new Socio();
		s1.setNombre("Vincent");
		s1.setApellidos("Price");
		
		Socio s2 = new Socio();
		s2.setNombre("Boris");
		s2.setApellidos("Karloff");
		
		Socio s3 = new Socio();
		s3.setNombre("Bela");
		s3.setApellidos("Lugoshi");			
		
		Socio s4 = new Socio();
		s4.setNombre("Jeffrey");
		s4.setApellidos("Combs");
		
		Socio s5 = new Socio();
		s5.setNombre("Robert");
		s5.setApellidos("Englund");
		
		Socio s6 = new Socio();
		s6.setNombre("Angus");
		s6.setApellidos("Scrimm");
		
		Socio s7 = new Socio();
		s7.setNombre("Conrad");
		s7.setApellidos("Veidt");
		
		Socio s8 = new Socio();
		s8.setNombre("Anthony");
		s8.setApellidos("Perkins");
		
		Socio s9 = new Socio();
		s9.setNombre("Doug");
		s9.setApellidos("Bradley");
		
		Socio s10 = new Socio();
		s10.setNombre("Christopher");
		s10.setApellidos("Lee");
		

		service.saveSocio(s1);
		service.saveSocio(s2);
		service.saveSocio(s3);
		service.saveSocio(s4);
		service.saveSocio(s5);
		service.saveSocio(s6);
		service.saveSocio(s7);
		service.saveSocio(s8);
		service.saveSocio(s9);
		service.saveSocio(s10);
	}

	@ModelAttribute("list")
	private List<Socio> list() {
		List<Socio> list = service.findAllSocios();
		return list;
	}

}
