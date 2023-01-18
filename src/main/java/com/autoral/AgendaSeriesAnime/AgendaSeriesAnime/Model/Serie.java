package com.autoral.AgendaSeriesAnime.AgendaSeriesAnime.Model;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;


@Entity
public class Serie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nome;
	private String descricao;
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private List<Categoria> categorias;
	@OneToOne(cascade = CascadeType.ALL)
	private Comentario comentario;
	private int episodio;
	private int temporada;
	private boolean seriefinalizada;



	
	public Serie() {}


	public Serie(String nome, String descricao, int episodio, int temporada, boolean seriefinalizada) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.episodio = episodio;
		this.temporada = temporada;
		this.seriefinalizada = seriefinalizada;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isSeriefinalizada() {
		return seriefinalizada;
	}

	public void setSeriefinalizada(boolean seriefinalizada) {
		this.seriefinalizada = seriefinalizada;
	}

	public int getEpisodio() {
		return episodio;
	}

	public void setEpisodio(int episodio) {
		this.episodio = episodio;
	}

	public int getTemporada() {
		return temporada;
	}

	public void setTemporada(int temporada) {
		this.temporada = temporada;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public Comentario getComentario() {
		return comentario;
	}

	public void setComentario(Comentario comentario) {
		this.comentario = comentario;
	}
	

	

	
}
