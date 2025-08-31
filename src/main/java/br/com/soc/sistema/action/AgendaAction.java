package br.com.soc.sistema.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.soc.sistema.business.AgendaBusiness;
import br.com.soc.sistema.infra.Action;
import br.com.soc.sistema.vo.AgendaVo;


public class AgendaAction extends Action {
	
	private List<AgendaVo> agendas = new ArrayList<>();
	private AgendaBusiness business = new AgendaBusiness();
	private AgendaVo agendaVo = new AgendaVo();
	
	public String todas() {
		agendas.addAll(business.trazerTodasAsAgendas());	

		return SUCCESS;
	}
	
	public String nova() {
		if(agendaVo.getNomeAgenda() == null)
			return INPUT;
		
		business.salvarAgenda(agendaVo);
		
		return REDIRECT;
	}
	
	public String editar() {
	
		if(agendaVo.getIdAgenda() == null)
			return REDIRECT;
		
		System.out.println("DEBUG - IDAGENDA editar: " + agendaVo.getIdAgenda());
		AgendaVo agendaCompleta = business.buscarAgendaPor(agendaVo.getIdAgenda());
		
		if (agendaCompleta != null) {
			agendaVo.setNomeAgenda(agendaCompleta.getNomeAgenda());
			agendaVo.setPeriodoDisponivel(agendaCompleta.getPeriodoDisponivel());
		}
		
		return INPUT;
	}
	
	public String atualizar() {
		System.out.println("DEBUG - IDAGENDA atualizar: " + agendaVo.getIdAgenda());
		
		if(agendaVo.getIdAgenda() == null || agendaVo.getNomeAgenda() == null || agendaVo.getPeriodoDisponivel() == null ) {
			return REDIRECT;
		}
		
		System.out.println("DEBUG - IDAGENDA atualizar: " + agendaVo.getIdAgenda());
		
		business.editarAgenda(agendaVo);
		return REDIRECT;
	}
	
	
	public List<AgendaVo> getAgendas() {
		return agendas;
	}
	public void setAgendas(List<AgendaVo> agendas) {
		this.agendas = agendas;
	}
	public AgendaBusiness getBusiness() {
		return business;
	}
	public void setBusiness(AgendaBusiness business) {
		this.business = business;
	}
	public AgendaVo getAgendaVo() {
		return agendaVo;
	}
	public void setAgendaVo(AgendaVo agendaVo) {
		this.agendaVo = agendaVo;
	}
	
	

	

}
