package br.com.soc.sistema.vo;


public class CompromissoVo {
	
	private String idCompromisso;
	private String idFuncionario;
	private String idAgenda;
	private String data;
	private String horario;
	
	public CompromissoVo() {}
	

	public CompromissoVo(String idCompromisso, String idFuncionario, String idAgenda, String data, String horario) {
		super();
		this.idCompromisso = idCompromisso;
		this.idFuncionario = idFuncionario;
		this.idAgenda = idAgenda;
		this.data = data;
		this.horario = horario;
	}


	public String getIdCompromisso() {
		return idCompromisso;
	}

	public void setIdCompromisso(String idCompromisso) {
		this.idCompromisso = idCompromisso;
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

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}
	
	

	
	

}
