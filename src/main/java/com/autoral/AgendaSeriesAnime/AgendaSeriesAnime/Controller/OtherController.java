package com.autoral.AgendaSeriesAnime.AgendaSeriesAnime.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.autoral.AgendaSeriesAnime.AgendaSeriesAnime.Model.Serie;
import com.autoral.AgendaSeriesAnime.AgendaSeriesAnime.Repository.SerieRepository;

@Controller
public class OtherController {
	
	@Autowired
	private SerieRepository srep;
	
	@GetMapping("/acabadas")
	public String acabadas(Model modelo) {
		List<Serie> s = srep.findAll();
		List<Serie> ss = new ArrayList<Serie>();
		for (Serie serie : s) {
			if (serie.isSeriefinalizada()) {
				ss.add(serie);
			}
		}
				
		modelo.addAttribute("listaSeries", ss);
		modelo.addAttribute("main", false);
		return "Index";
	}

	@GetMapping("/naoacabadas")
	public String naoacabadas(Model modelo) {
		List<Serie> s = srep.findAll();
		List<Serie> ss = new ArrayList<Serie>();
		for (Serie serie : s) {
			if (!serie.isSeriefinalizada()) {
				ss.add(serie);
			}
		}
				
		modelo.addAttribute("listaSeries", ss);
		modelo.addAttribute("main", false);
		return "Index";
	}
}
