package com.autoral.AgendaSeriesAnime.AgendaSeriesAnime.Controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.autoral.AgendaSeriesAnime.AgendaSeriesAnime.Model.Calendario;
import com.autoral.AgendaSeriesAnime.AgendaSeriesAnime.Model.Categoria;
import com.autoral.AgendaSeriesAnime.AgendaSeriesAnime.Model.Comentario;
import com.autoral.AgendaSeriesAnime.AgendaSeriesAnime.Model.Serie;
import com.autoral.AgendaSeriesAnime.AgendaSeriesAnime.Repository.CalendarioRepository;
import com.autoral.AgendaSeriesAnime.AgendaSeriesAnime.Repository.CategoriaRepository;
import com.autoral.AgendaSeriesAnime.AgendaSeriesAnime.Repository.SerieRepository;


@Controller
public class MainController {
	
	@Autowired
	private SerieRepository srep;
	@Autowired
	private CategoriaRepository crep;
	@Autowired
	private CalendarioRepository drep;

	
	@GetMapping("/")
	public String main(Model modelo) {
		modelo.addAttribute("listaSeries", srep.findAll());
		modelo.addAttribute("main", true);
		return "Index";
	}
	
	@GetMapping("/Cal")
	public String calendário(Model modelo) {
		Calendar g = Calendar.getInstance();
		int dia = g.get(Calendar.DAY_OF_MONTH);
		int mes = g.get(Calendar.MONTH) + 1 ;
		int ano = g.get(Calendar.YEAR);
		String dataatual = (dia<10?("0"+dia):dia) + "/" + (mes<10?("0"+mes):(mes)) + "/" + ano;
		List<Calendario> hoje = new ArrayList<Calendario>();
		List<Calendario> estemes = new ArrayList<Calendario>();
		List<Calendario> proxmes = new ArrayList<Calendario>();
		List<Calendario> esteano = new ArrayList<Calendario>();
		
		for (Calendario c : drep.findAll()) {
			//drep.findBydiaIs(dia) && drep.findBymesIs(mes) && drep.findByanoIs(ano)
			if ((c.getDia() == dia) && (c.getMes() == mes) && (c.getAno() == ano)) {
				hoje.add(c);
			} else if ((c.getMes() == mes) && (c.getAno() == ano)) {
				estemes.add(c);
			} else if ((c.getMes() == mes+1) && (c.getAno() == ano)) {
				proxmes.add(c);
			} else if (c.getAno() == ano){
				esteano.add(c);
			}
		}
		
		
		modelo.addAttribute("cal", drep.findAll());
		modelo.addAttribute("hoje", hoje);
		modelo.addAttribute("proxmes", proxmes);
		modelo.addAttribute("estemes", estemes);
		modelo.addAttribute("esteano", esteano);
		modelo.addAttribute("data", dataatual);
		
		return "Calendario";
	}
	
	@GetMapping("/serie/mostrar/{id}")
	public String mostrarserie(@PathVariable("id") int id, Model modelo) {
		Serie s = srep.findByidIs(id);
		modelo.addAttribute("serie", s);
		if (s.getComentario() != null) {
			modelo.addAttribute("com", s.getComentario());
		} else {
		modelo.addAttribute("com", new Comentario());}
		modelo.addAttribute("series", s);
		return "serie/mostrar";
	}
	
	
	@GetMapping("/serie/novaserie")
	public String criarnovaserier(Model modelo) {
		modelo.addAttribute("serie", new Serie());
		modelo.addAttribute("listacat", crep.findAll());
		return "serie/novaserie";
	}
	
	@PostMapping("/")
	public String salvarseriecriada(@ModelAttribute Serie serie,
			@RequestParam(value = "categoriasss" , required = false) String[] cats
			) {

		List<Categoria> c = new ArrayList<Categoria>();
		for (String cat : cats) {
			c.add(crep.findBynomeIs(cat));
		}

		serie.setCategorias(c);
		
		srep.save(serie);
		
		return "redirect:/";
	}
	
	@GetMapping("/serie/apagar/{id}")
	public String apagarserie(@PathVariable("id") int id) {
		srep.deleteById(id);
		return "redirect:/";
	}
	
	@GetMapping("/serie/editar/{id}")
	public String editarserie(@PathVariable("id") int id, Model modelo) {
		Serie s = srep.findByidIs(id);
		modelo.addAttribute("serie", s);
		modelo.addAttribute("listacat", crep.findAll());
		return "serie/editarserie";
	}

	@GetMapping("/categoria")
	public String category(Model modelo) {
		modelo.addAttribute("listaCats", crep.findAll());
		modelo.addAttribute("cat", new Categoria());
		return "outros/categoriass";
	}
	
	@PostMapping("/categoria")
	public String salvarnovacat(@ModelAttribute Categoria cat) {
		crep.save(cat);
		return "redirect:/categoria";
	}
	
	@GetMapping("/serie/comentar/{id}")
	public String comentar(@PathVariable("id") int id, Model modelo) {
		Serie s = srep.findByidIs(id);
		modelo.addAttribute("serie", s);
		if (s.getComentario() != null) {
			modelo.addAttribute("com", s.getComentario());
		} else {
		modelo.addAttribute("com", new Comentario());}
		return "outros/comentar";
	}
	@PostMapping("/serie/comentar/{id}")
	public String salvarcomentario(@PathVariable("id") int id, @ModelAttribute Comentario cmt) {
		Serie s = srep.findByidIs(id);
		Boolean ss = s.getComentario() == null;
		
		Comentario c = new Comentario();
		if (ss) {
			
		} else {
			 c.setId(s.getComentario().getId());
		}
		
		c.setTexto(cmt.getTexto());
		s.setComentario(c); 
		s.setId(id);
		srep.save(s);
		return "redirect:/";
	}
	
	@GetMapping("/cal/novo")
	public String novolembrete(Model modelo) {
		
		Calendar g = Calendar.getInstance();
		int dia = g.get(Calendar.DAY_OF_MONTH);
		int mes = g.get(Calendar.MONTH) + 1 ;
		int ano = g.get(Calendar.YEAR);
		String dataatual = (dia<10?("0"+dia):dia) + "/" + (mes<10?("0"+mes):(mes)) + "/" + ano;

		modelo.addAttribute("cale", new Calendario());
		modelo.addAttribute("data", dataatual);
		
		return "outros/lembrete";
	}
	
	@PostMapping("/cal/novo")
	public String salvarlembrete(Model modelo, @ModelAttribute Calendario cale) {
		
		drep.save(cale);
		
		return "redirect:/Cal";
	}
	
	@GetMapping("/calendario/apagar/{id}")
	public String apagarlembrete(@PathVariable("id") int id) {
		drep.deleteById(id);
		return "redirect:/Cal";
	}
	
	
}
