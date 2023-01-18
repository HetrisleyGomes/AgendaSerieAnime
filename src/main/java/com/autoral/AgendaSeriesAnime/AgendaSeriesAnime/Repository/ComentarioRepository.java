package com.autoral.AgendaSeriesAnime.AgendaSeriesAnime.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.autoral.AgendaSeriesAnime.AgendaSeriesAnime.Model.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Integer>{

	Comentario findByidIs(int idc);

}
