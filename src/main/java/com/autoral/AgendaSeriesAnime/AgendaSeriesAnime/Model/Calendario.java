package com.autoral.AgendaSeriesAnime.AgendaSeriesAnime.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Calendario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	int dia;
	int mes;
	int ano;
	String agendamento;
	
	public Calendario() {}

	public Calendario(int dia, int mes, int ano, String agendamento) {
		super();
		this.dia = dia;
		this.mes = mes;
		this.ano = ano;
		this.agendamento = agendamento;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public String getAgendamento() {
		return agendamento;
	}

	public void setAgendamento(String agendamento) {
		this.agendamento = agendamento;
	}
	
	
	
	
	
}
