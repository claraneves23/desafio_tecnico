package br.com.soc.sistema.action;

import java.util.ArrayList;
import java.util.List;

import br.com.soc.sistema.business.AgendaBusiness;
import br.com.soc.sistema.business.CompromissoBusiness;
import br.com.soc.sistema.business.FuncionarioBusiness;
import br.com.soc.sistema.infra.Action;
import br.com.soc.sistema.vo.AgendaVo;
import br.com.soc.sistema.vo.CompromissoVo;
import br.com.soc.sistema.vo.FuncionarioVo;

public class CompromissoAction extends Action{

	private List<CompromissoVo> compromissos = new ArrayList<>();
	private List<FuncionarioVo> funcionarios = new ArrayList<>();
	private List<AgendaVo> agendas = new ArrayList<>();
	
	private CompromissoBusiness business = new CompromissoBusiness();
	private FuncionarioBusiness funcionarioBusiness = new FuncionarioBusiness();
	private AgendaBusiness agendaBusiness = new AgendaBusiness();
	
	private CompromissoVo compromissoVo = new CompromissoVo();
	
	public String todos() {
		compromissos.addAll(business.trazerTodosOsCompromissos());	

		return SUCCESS;
	}
	
	public String novo() {
        funcionarios.addAll(funcionarioBusiness.trazerTodosOsFuncionarios());
        agendas.addAll(agendaBusiness.trazerTodasAsAgendas());
        
        return INPUT;
    }
	
	public String salvar() {
		System.out.format("Salvando compromisso: ID=%s, Agenda=%s, Funcionario=%s", compromissoVo.getIdCompromisso(), 
                compromissoVo.getIdAgenda(), 
                compromissoVo.getIdFuncionario());
		if(!(business.validarCamposObrigatorios(compromissoVo)))
			return INPUT;
		
		business.salvarCompromisso(compromissoVo);
		
		return REDIRECT;
	}

	public List<CompromissoVo> getCompromissos() {
		return compromissos;
	}

	public void setCompromissos(List<CompromissoVo> compromissos) {
		this.compromissos = compromissos;
	}

	public CompromissoBusiness getBusiness() {
		return business;
	}

	public void setBusiness(CompromissoBusiness business) {
		this.business = business;
	}

	public CompromissoVo getCompromissoVo() {
		return compromissoVo;
	}

	public void setCompromissoVo(CompromissoVo compromissoVo) {
		this.compromissoVo = compromissoVo;
	}

	public List<FuncionarioVo> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<FuncionarioVo> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public List<AgendaVo> getAgendas() {
		return agendas;
	}

	public void setAgendas(List<AgendaVo> agendas) {
		this.agendas = agendas;
	}
	
	
	
}
