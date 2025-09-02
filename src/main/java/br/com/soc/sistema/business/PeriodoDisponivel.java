package br.com.soc.sistema.business;

import java.time.LocalTime;

public enum PeriodoDisponivel {
	MANHA("Manhã", "06:00", "12:00"),
	TARDE("Tarde", "12:00", "18:00"),
	NOITE("Ambos", "06:00", "18:00");
	
	private final String descricao;
	private final String horaInicio;
	private final String horaFim;
	
	private PeriodoDisponivel(String descricao, String horaInicio, String horaFim) {
		this.descricao = descricao;
		this.horaInicio = horaInicio;
		this.horaFim = horaFim;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public String getHoraFim() {
		return horaFim;
	}
	
	public static PeriodoDisponivel fromDescricao(String descricao) {
		for(PeriodoDisponivel periodo : values()) {
			if(periodo.descricao.equalsIgnoreCase(descricao)) {
				return periodo;
			}
		}
		return null;
	}
	
	public boolean horarioDentroDoPeriodo(String horario) {
		try {
			LocalTime hora = LocalTime.parse(horario);
			LocalTime inicio = LocalTime.parse(this.horaInicio);
			LocalTime fim = LocalTime.parse(this.horaFim);
			
			return !hora.isBefore(inicio) && !hora.isAfter(fim);
			
		} catch (Exception e) {
			return false;
		}
	}
}
