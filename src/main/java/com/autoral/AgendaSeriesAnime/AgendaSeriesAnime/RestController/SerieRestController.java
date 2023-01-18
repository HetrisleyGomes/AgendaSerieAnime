package com.autoral.AgendaSeriesAnime.AgendaSeriesAnime.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.autoral.AgendaSeriesAnime.AgendaSeriesAnime.Model.Categoria;
import com.autoral.AgendaSeriesAnime.AgendaSeriesAnime.Model.Comentario;
import com.autoral.AgendaSeriesAnime.AgendaSeriesAnime.Model.Serie;
import com.autoral.AgendaSeriesAnime.AgendaSeriesAnime.Repository.CategoriaRepository;
import com.autoral.AgendaSeriesAnime.AgendaSeriesAnime.Repository.ComentarioRepository;
import com.autoral.AgendaSeriesAnime.AgendaSeriesAnime.Repository.SerieRepository;


@RestController
public class SerieRestController {
	
	@Autowired
	private SerieRepository srep;
	@Autowired
	private CategoriaRepository crep;
	@Autowired
	private ComentarioRepository comrep;
	
	
	
	@GetMapping("/serie")
	private List<Serie> findSeries(){
		return srep.findAll();
	}
	
	@GetMapping("/serie/criar")
	@ResponseStatus(code = HttpStatus.CREATED)
	private void criarSeries() {
		srep.save(new Serie("Jojo Bizzare Adventures","Tem vampiro, tem briga, tem Stand, tem Fantasma, tem Ets", 8, 6, true));
		srep.save(new Serie("The Umbrella Academy", "Hérois não convencionais, porem muito legais :)", 0, 3, true));
	}
	
	@GetMapping("serie/{id}")
	private ResponseEntity<Serie> findById(@PathVariable("id") int id){
		Optional<Serie> s = srep.findById(id);
		if (s.isPresent()) {
			return ResponseEntity.ok(s.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/serie")
	@ResponseStatus(code = HttpStatus.CREATED)
	private Serie salvarSerie(@RequestBody Serie serie) {
		List<Categoria> c = new ArrayList<Categoria>();
		
		for (Categoria categoria : serie.getCategorias()) {
			c.add(crep.findBynomeIs(categoria.getNome()));
		}
		
		serie.setCategorias(c);
		
		return srep.save(serie);
	}
	
	@PutMapping("/serie/{id}")
	private ResponseEntity<Serie> atualizarSerie(@PathVariable("id") int id, @RequestBody Serie serie){
		Optional<Serie> s = srep.findById(id);
		
		List<Categoria> c = new ArrayList<Categoria>();
		Serie ss = s.get();
		int idc = ss.getComentario().getId();
		String coment = serie.getComentario().getTexto();
		
		for (Categoria categoria : serie.getCategorias()) {
			c.add(crep.findBynomeIs(categoria.getNome()));
		}

		Comentario cmt = comrep.findByidIs(idc);
		cmt.setTexto(coment);
		
		serie.setComentario(cmt);
		
		
		serie.setCategorias(c);
		
		if (s.isPresent()) {
			serie.setId(id);
			srep.save(serie);
			
			return ResponseEntity.ok(s.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("serie/{id}")
	private ResponseEntity<Serie> deletSerie(@PathVariable("id") int id){
		Optional<Serie> s = srep.findById(id);
		if (s.isPresent()) {
			srep.deleteById(id);
			return ResponseEntity.ok(s.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	/*
	 * 
	 * 	<form th:action="@{/serie/mostrar/__${Serie.id}__}" method="post" th:object="${comm}">
		<input type="text" th:field="*{comentario}">
		<button type="submit"> Salvar </button>
	</form> </td></tr>
	 * 
	 * 
	 * */

}
