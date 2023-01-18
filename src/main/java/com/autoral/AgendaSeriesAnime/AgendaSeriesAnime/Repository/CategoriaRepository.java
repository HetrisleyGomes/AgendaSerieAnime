package com.autoral.AgendaSeriesAnime.AgendaSeriesAnime.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.autoral.AgendaSeriesAnime.AgendaSeriesAnime.Model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
	
	Categoria findBynomeIs(String nome);

}
