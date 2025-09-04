package br.com.soc.sistema.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.soc.sistema.business.AgendaBusiness;
import br.com.soc.sistema.exception.BusinessException;
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
		{
			 return INPUT;
		}
		
		if(agendaVo.getPeriodoDisponivel() == null) {
			return INPUT;
		}
		
		business.salvarAgenda(agendaVo);
		
		return REDIRECT;
	}
	
	public String editar() {
	
		if(agendaVo.getIdAgenda() == null)
			return REDIRECT;
		
		AgendaVo agendaCompleta = business.buscarAgendaPorId(agendaVo.getIdAgenda());
		
		if (agendaCompleta != null) {
			agendaVo.setNomeAgenda(agendaCompleta.getNomeAgenda());
			agendaVo.setPeriodoDisponivel(agendaCompleta.getPeriodoDisponivel());
		}
		
		return INPUT;
	}
	
	public String atualizar() {
		
		if(agendaVo.getIdAgenda() == null || agendaVo.getNomeAgenda() == null || agendaVo.getPeriodoDisponivel() == null ) {
			return REDIRECT;
		}
		
		
		business.editarAgenda(agendaVo);
		return REDIRECT;
	}
	
	public String excluir() {
		
		 try {
		        String idAgenda = agendaVo.getIdAgenda();
		        if (idAgenda == null || idAgenda.trim().isEmpty()) {
		        		return REDIRECT;
		        }
		        
		        business.excluirAgenda(idAgenda);
		        
		    } catch (BusinessException e) {
		        addActionError(e.getMessage());
		    }
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
