package br.com.soc.sistema.vo;

public class AgendaVo {
	
	private String idAgenda;
	private String nomeAgenda;
	private String periodoDisponivel;
	
	public AgendaVo() {}


	public AgendaVo(String idAgenda, String nomeAgenda, String periodoDisponivel) {
		this.idAgenda = idAgenda;
		this.nomeAgenda = nomeAgenda;
		this.periodoDisponivel = periodoDisponivel;
	}
	
	public String getIdAgenda() {
		return idAgenda;
	}

	public void setIdAgenda(String idAgenda) {
		this.idAgenda = idAgenda;
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
