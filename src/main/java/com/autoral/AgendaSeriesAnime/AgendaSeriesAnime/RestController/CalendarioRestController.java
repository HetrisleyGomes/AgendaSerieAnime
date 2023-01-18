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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.autoral.AgendaSeriesAnime.AgendaSeriesAnime.Model.Calendario;
import com.autoral.AgendaSeriesAnime.AgendaSeriesAnime.Repository.CalendarioRepository;

@RestController
public class CalendarioRestController {

	@Autowired
	private CalendarioRepository crep;
	
	@GetMapping("/calendario")
	private List<Calendario> findall(){
		return crep.findAll();
	} 
	
	@GetMapping("/calendario/criar")
	@ResponseStatus(code = HttpStatus.CREATED)
	private void criardatas(){
		crep.save(new Calendario(20, 1, 2022, "Dia esoecua"));
		crep.save(new Calendario(1, 2, 2022, "Aniversary" ));
		crep.save(new Calendario(27, 2, 2022, "ladybug"));
	}
	
	@GetMapping("/calendario/{id}")
	private ResponseEntity<Calendario> salvarcalendario(@PathVariable("id") int id){
		Optional<Calendario> cc = crep.findById(id);
		if (cc.isPresent()) {
			return ResponseEntity.ok(cc.get());
		} else {
			return ResponseEntity.notFound().build();
		}

	} 
	
	@PostMapping("/calendario")
	@ResponseStatus(code = HttpStatus.CREATED)
	private Calendario salvarcalendario(@RequestBody Calendario c){
		return crep.save(c);
	} 
	
	@DeleteMapping("/calendario/{id}")
	private ResponseEntity<Calendario> deletecalendario(@PathVariable("id") int id){
		Optional<Calendario> cc = crep.findById(id);
		if (cc.isPresent()) {
			crep.deleteById(id);
			return ResponseEntity.ok(cc.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	
}
