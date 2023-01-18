package com.autoral.AgendaSeriesAnime.AgendaSeriesAnime.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.autoral.AgendaSeriesAnime.AgendaSeriesAnime.Model.Calendario;

@Repository
public interface CalendarioRepository extends JpaRepository<Calendario, Integer>{

	List<Calendario> findBydiaIs(int dia);
	List<Calendario> findBymesIs(int mes);
	List<Calendario> findByanoIs(int ano);
}
