package com.asg.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.asg.entities.Libro;
import com.asg.services.LibroServiceImpl;
import com.asg.services.SocioServiceImpl;

@Controller
@RequestMapping("/catalogo")
@SessionAttributes("libro")
public class LibroController {

	@Autowired
	LibroServiceImpl service;
	
	@Autowired
	SocioServiceImpl socioService;	
	
	
	@GetMapping("")
	public String list(Model model) {
		List<Libro> list = service.findAllLibros();
		model.addAttribute("list", list);
		model.addAttribute("create", false);
		model.addAttribute("edit", false);		
		return "libros/list";
	}

	@GetMapping("/expurgar/{id}")
	public String expurgar(@PathVariable("id") Integer id) {
		Libro libro = service.findByIdLibro(id).get();
		libro.setExpurgado(true);
		service.update(libro);
		return "redirect:/catalogo";
	}

	@GetMapping("/activar/{id}")
	public String activar(@PathVariable("id") Integer id) {
		Libro libro = service.findByIdLibro(id).get();
		libro.setExpurgado(false);
		service.update(libro);
		return "redirect:/catalogo";
	}

	@GetMapping("/create")
	public String create(Model model) {		
		model.addAttribute("libro", new Libro());
		model.addAttribute("create", true);
		return "libros/form";
	}

	@PostMapping("/create")
	public String save(@Valid Libro libro, BindingResult result,
			@RequestParam(name = "file", required = false) MultipartFile file, Model model) {

		if (result.hasErrors()) {
			model.addAttribute("create", true);
			return "libros/form";
		}

		if (!file.isEmpty()) {
			Path dirRecursos = Paths.get("src/main/resources/static/uploads");
			String rootPath = dirRecursos.toFile().getAbsolutePath();
			try {
				byte[] bytes = file.getBytes();
				Path rutaCompleta = Paths.get(rootPath + "/" + file.getOriginalFilename());
				Files.write(rutaCompleta, bytes);
				libro.setFotoPortada(file.getOriginalFilename());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		service.saveLibro(libro);
		return "redirect:/catalogo";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, Model model) {
		Libro libro = service.findByIdLibro(id).get();	
		model.addAttribute("libro", libro);
		model.addAttribute("edit", true);			
		return "libros/form";
	}

	@PostMapping("/edit")
	public String saveEdit(@Valid Libro libro, BindingResult result,
			@RequestParam(name = "file", required = false) MultipartFile file,SessionStatus status, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("edit", true);		
			return "libros/form";
		}		

		if (!file.isEmpty()) {
			Path dirRecursos = Paths.get("src/main/resources/static/uploads");
			String rootPath = dirRecursos.toFile().getAbsolutePath();
			try {
				byte[] bytes = file.getBytes();
				Path rutaCompleta = Paths.get(rootPath + "/" + file.getOriginalFilename());
				Files.write(rutaCompleta, bytes);
				libro.setFotoPortada(file.getOriginalFilename());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		service.saveLibro(libro);
		status.setComplete();
		return "redirect:/catalogo";
	}

	@GetMapping("/prestar/{id}")
	public String prestar(@PathVariable("id") Integer id, Model model) {
		Libro libro = service.findByIdLibro(id).get();
		model.addAttribute("libro", libro);	
		model.addAttribute("socios", socioService.findAllSocios());
		model.addAttribute("prestar", true);		
		return "libros/form";
	}
	
	@PostMapping("/prestar")
	public String prestarBd(Libro libro, Model model) {			
		service.prestarLibro(libro);
		return "libros/list";
	}
	
	@GetMapping("/devolver/{id}")
	public String devolver(@PathVariable("id") Integer id, Model model) {			
		service.devolverLibro(id);
		return "redirect:/catalogo";
	}
	

	@PostConstruct
	private void post() {
		Libro l1 = new Libro();
		l1.setAutor("Bram Stocker");
		l1.setTitulo("Dr??cula");
		l1.setSinopsis("El inicio de uno de los mitos m??s populares: Dracula. Presenta el papel de la mujer en la ??poca victoriana, la sexualidad, la inmigraci??n, el colonialismo y el folclore. Jonathan Harker viaja a Transilvania para cerrar un negocio inmobiliario con un misterioso conde que acaba de comprar varias propiedades en Londres. Despu??s de un viaje pre??ado de ominosas se??ales, Harker es recogido en el Paso de Borgo por un siniestro carruaje que le lleva, acunado por el canto de los lobos, a un castillo en ruinas.");
		l1.setExpurgado(false);					
		l1.setFechaAdquisicion(new Date());
		l1.setFotoPortada("dracula.jpg");
		service.saveLibro(l1);

		Libro l2 = new Libro();
		l2.setAutor("Edgar Allan Poe");
		l2.setTitulo("Narraciones Extraordinarias");
		l2.setSinopsis("Poe explora la locura, la muerte, el dolor, la crueldad, el instinto asesino, la desintegraci??n f??sica y moral, la soledad, el aislamiento y la duplicidad de la naturaleza humana. En un alarde de dominio de la creaci??n de atm??sferas, el escritor perfila la psicolog??a de personajes angustiados por las pesadillas, las fantas??as y temores que, sin duda, preludian las contradicciones del ser humano contempor??neo.");
		l2.setExpurgado(false);		
		l2.setFotoPortada("extraordinarias.jpeg");
		l2.setFechaAdquisicion(new Date());	
		service.saveLibro(l2);
		
		Libro l3 = new Libro();
		l3.setAutor("Howard Philips Lovecraft");
		l3.setTitulo("El Necronomic??n");
		l3.setSinopsis("Un volumen blasfemo de conocimiento prohibido escrito por el ??rabe loco Abdul Alhazred. Incluso hoy, a pesar de las tentativas por destruir todas las copias en cualquier idioma a lo largo de los siglos, a??n existen algunos ejemplares, escondidos. Dentro de este libro encontrar??s historias, ensayos y diferentes versiones acerca del libro blasfemo. A trav??s de los testimonios de Robert Silverberg, Frederick Pohl, John Brunner y el propio Lovecraft, entre otros, ahora t?? tambi??n puedes aprender el verdadero saber de Abdul Alhazred y conocer de primera mano el ignoto poder que se encuentra entre sus p??ginas.");
		l3.setExpurgado(false);		
		l3.setFotoPortada("necronomicon.jpg");
		l3.setFechaAdquisicion(new Date());	
		service.saveLibro(l3);
		
		Libro l4 = new Libro();
		l4.setAutor("Matthew Gregory Lewis");
		l4.setTitulo("El Monje");
		l4.setSinopsis("Historias entrelazadas para crear una tensi??n entre su envoltura sentimental y su trasfondo g??tico. El 23 de septiembre de 1794, el joven Matthew Lewis con tan s??lo diecinueve a??os, anunciaba en una carta a su madre que hab??a escrito en s??lo diez semanas una novela, entre 300 y 400 p??ginas en octava. Acababa de nacer una de las obras cumbre de la novela g??tica, la forma m??s le??da de literatura popular en Gran Betra??a y buena parte de Europa desde finales del siglo XVIII hasta bien mediado el siguiente. La opini??n p??blica se dabati?? entre declarar El monje como obra de ingenio o tacharla de blasfema y obscena. Situada en un decadente, hip??crita y m??tico Madrid g??tico, Lewis mezcla dos tramas bastante espeluznates: la del libidinoso y blasfemo monje Ambrosio y la historia de Ram??n y su fat??dico amor por la desdichada In??s.");
		l4.setExpurgado(false);		
		l4.setFotoPortada("monje.jpg");
		l4.setFechaAdquisicion(new Date());	
		service.saveLibro(l4);
		
		Libro l5 = new Libro();
		l5.setAutor("Arthur Machen");
		l5.setTitulo("El Gran Dios Pan");
		l5.setSinopsis("Arthur Machen (1863-1947), al igual que su contempor??neo Lord Dunsany , fue un obstinado so??ador que cre?? una de las obras m??s l??ricas y exquisitas que ha dado hasta la fecha el denominado g??nero de terror. La presente antolog??a recoge catorce relatos (algunos de ellos in??ditos en castellano), lo m??s granado y significativo de la ingente obra fant??stica de Machen, que tanto influy?? en el maestro del horror sobrenatural, H.P. Lovecraft.");
		l5.setExpurgado(false);		
		l5.setFotoPortada("pan.jpg");
		l5.setFechaAdquisicion(new Date());			
		service.saveLibro(l5);
		
		Libro l6 = new Libro();
		l6.setAutor("Henry James");
		l6.setTitulo("Otra Vuelta de Tuerca");
		l6.setSinopsis("Un relato que conduce al lado m??s siniestro de la condici??n humana. Cuando la nueva institutriz lleg?? a la hermosa mansi??n de Bly para hacerse cargo de los peque??os Miles y Flora, crey?? haber traspasado el umbral del mundo de los cuentos de hadas. Pero, poco a poco, la joven comprendi?? que el viejo caser??n y sus dulces e inocentes habitantes guardaban demasiados secretos como para poder confiar en su pl??cida apariencia.");
		l6.setExpurgado(false);		
		l6.setFotoPortada("tuerca.png");
		l6.setFechaAdquisicion(new Date());	
		service.saveLibro(l6);
		
		Libro l7 = new Libro();
		l7.setAutor("Frankenstein o El Moderno Prometeo");
		l7.setTitulo("Mary Shelly");
		l7.setSinopsis("Novela sobre la humanidad en su relaci??n con Dios, la moral cient??fica, la creaci??n y destrucci??n de vida. El monstruo creado por V??ctor Frankenstein, pone en su voz los sentimientos de muchos seres humanos. A su vez, este cient??fico inescrupuloso que no tiene l??mites para la experimentaci??n, bien podr??a ser una met??fora de la ciencia moderna y sus maravillosos pero a la vez terribles avances. La versi??n de Franco Vaccarini reproduce, en un lenguaje claro y una narraci??n ??gil, los temas fundamentales de una obra que no pierde vigencia.");
		l7.setFotoPortada("frankenstein.png");
		l7.setFechaAdquisicion(new Date());	
		service.saveLibro(l7);
		
		Libro l8 = new Libro();
		l8.setAutor("Poppy Z. Britte");
		l8.setTitulo("El Arte M??s ??ntimo");
		l8.setSinopsis("Para el asesino en serie brit??nico Andrew Compton, matar es un arte: el arte m??s ??ntimo. Tras fingir su propia muerte para escapar de prisi??n, Compton se encamina hacia EE. UU. con la intenci??n de seguir perfeccionando su talento. Torturado por sus perversos deseos, llevado a poseer y asesinar a chicos j??venes, Compton se une a Jay Byrne, un playboy disoluto que ha llevado su arte a l??mites que ni siquiera ??l hab??a imaginado. Juntos, dirigen sus miradas hacia un chico de la calle, Tran, americano de ascendencia vietnamita, y de belleza exquisita, a quien consideran la v??ctima perfecta. Esta novela va dirigida a aquellos que se atreven a entrar en donde lo sagrado y lo profano se convierten en uno.");
		l8.setFotoPortada("arte-intimo.png");
		l8.setFechaAdquisicion(new Date());	
		service.saveLibro(l8);
		
		Libro l9 = new Libro();
		l9.setAutor("Clive Barker");
		l9.setTitulo("Hellraiser: El Coraz??n Condenado");
		l9.setSinopsis("En esta nueva versi??n al espa??ol de Hellraiser, que ahora publicamos con su t??tulo original, El coraz??n condenado, los lectores podr??n acercarse a esta ya cl??sica novela de terror con una perspectiva nueva. Nominada como la mejor novela del a??o en el Reino Unido, treinta a??os despu??s se ha convertido en una obra de culto tras la primera versi??n cinematogr??fica que se hizo de ella en 1987. Clive Barker aborda en sus p??ginas cuestiones cruciales como el amor y la desesperaci??n, el deseo, la muerte y la sangre, mediante met??foras sugerentes, reflejando el hedonismo desenfrenado hasta l??mites trascendentes.");
		l9.setFotoPortada("hellraiser2.jpg");
		l9.setFechaAdquisicion(new Date());	
		service.saveLibro(l9);
	}

	@ModelAttribute("list")
	private List<Libro> list() {
		List<Libro> list = service.findAllLibros();
		return list;
	}

}
