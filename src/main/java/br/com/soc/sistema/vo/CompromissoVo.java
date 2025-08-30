package br.com.soc.sistema.vo;

import java.time.LocalDate;
import java.time.LocalTime;

public class CompromissoVo {
	
	private String idFuncionario;
	private String idAgenda;
	private LocalDate data;
	private LocalTime horario;
	
	public CompromissoVo(String idFuncionario, String idAgenda, LocalDate data, LocalTime horario) {
		this.idFuncionario = idFuncionario;
		this.idAgenda = idAgenda;
		this.data = data;
		this.horario = horario;
	}
	
	public String getIdFuncionario() {
		return idFuncionario;
	}
	public void setIdFuncionario(String idFuncionario) {
		this.idFuncionario = idFuncionario;
	}
	public String getIdAgenda() {
		return idAgenda;
	}
	public void setIdAgenda(String idAgenda) {
		this.idAgenda = idAgenda;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public LocalTime getHorario() {
		return horario;
	}
	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}

	
	

}
