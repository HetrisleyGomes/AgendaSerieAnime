package com.autoral.AgendaSeriesAnime.AgendaSeriesAnime.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.autoral.AgendaSeriesAnime.AgendaSeriesAnime.Model.Serie;

@Repository
public interface SerieRepository extends JpaRepository<Serie, Integer>{
	
	Serie findBynomeIs(String nome);
	
	Serie findByidIs(int i);
	
	List<Serie> findBynomeContaining(String nome);
	
	List<Serie> findByepisodioLessThanEqual(int i);
	
	List<Serie> findBytemporadaLessThanEqual(int i);

}
