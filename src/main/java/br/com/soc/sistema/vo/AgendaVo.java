package br.com.soc.sistema.vo;

public class AgendaVo {
	
	private String id_agenda;
	private String nomeAgenda;
	private String periodoDisponivel;
	
	public AgendaVo() {}
	
	public AgendaVo(String id_agenda, String nomeAgenda, String periodoDisponivel) {
		this.id_agenda = id_agenda;
		this.nomeAgenda = nomeAgenda;
		this.periodoDisponivel = periodoDisponivel;
	}
	
	public String getId_agenda() {
		return id_agenda;
	}

	public void setId_agenda(String id_agenda) {
		this.id_agenda = id_agenda;
	}

	public String getNomeAgenda() {
		return nomeAgenda;
	}

	public void setNomeAgenda(String nomeAgenda) {
		this.nomeAgenda = nomeAgenda;
	}
	
	public String getPeriodoDisponivel() {
		return periodoDisponivel;
	}
	public void setPeriodoDisponivel(String periodoDisponivel) {
		this.periodoDisponivel = periodoDisponivel;
	}




}
