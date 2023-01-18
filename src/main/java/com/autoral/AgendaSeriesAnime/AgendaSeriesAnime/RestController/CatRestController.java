package com.autoral.AgendaSeriesAnime.AgendaSeriesAnime.RestController;

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
import com.autoral.AgendaSeriesAnime.AgendaSeriesAnime.Repository.CategoriaRepository;

@RestController
public class CatRestController {

	@Autowired
	private CategoriaRepository crep;
	
	@GetMapping("/cat")
	private List<Categoria> findCat() {
		return crep.findAll();
	}
	
	@GetMapping("/cat/criar")
	@ResponseStatus(code = HttpStatus.CREATED)
	private void catcriar() {
		crep.save(new Categoria("Anime"));
		crep.save(new Categoria("Shounen"));
		crep.save(new Categoria("Shoujo"));
		crep.save(new Categoria("Isekai"));
		crep.save(new Categoria("Ação"));
		crep.save(new Categoria("Aventura"));
	}
	
	@GetMapping("/cat/{id}")
	private ResponseEntity<Categoria> findbyId(@PathVariable("id") int id){
		Optional<Categoria> c = crep.findById(id);
		if (c.isPresent()) {
			return ResponseEntity.ok(c.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/cat/nome/{nome}")
	private ResponseEntity<Categoria> findbynome(@PathVariable("nome") String nome){
		Categoria c = crep.findBynomeIs(nome);
		try {
			return ResponseEntity.ok(c);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/cat")
	@ResponseStatus(code = HttpStatus.CREATED)
	private Categoria salvarcat(@RequestBody Categoria cat) {
		return crep.save(cat);
	}
	
	@PutMapping("/cat/{id}")
	private ResponseEntity<Categoria> atualizarCat(@PathVariable("id") int id, @RequestBody Categoria cat) {
		Optional<Categoria> c = crep.findById(id);
		
		if (c.isPresent()) {
			cat.setId(id);
			Categoria cc = crep.save(cat);
			return ResponseEntity.ok(cc);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/cat/{id}")
	private ResponseEntity<Categoria> deletarCat(@PathVariable("id") int id){
		Optional<Categoria> c = crep.findById(id);
		if (c.isPresent()) {
			crep.deleteById(id);
			return ResponseEntity.ok(c.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	
	
	
	
	
	
}
