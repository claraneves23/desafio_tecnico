package br.com.soc.sistema.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.soc.sistema.business.FuncionarioBusiness;
import br.com.soc.sistema.exception.BusinessException;
import br.com.soc.sistema.filter.FuncionarioFilter;
import br.com.soc.sistema.infra.Action;
import br.com.soc.sistema.infra.OpcoesComboBuscar;
import br.com.soc.sistema.vo.FuncionarioVo;

public class FuncionarioAction extends Action {
	
	private List<FuncionarioVo> funcionarios = new ArrayList<>();
	private FuncionarioBusiness business = new FuncionarioBusiness();
	private FuncionarioFilter filtrar = new FuncionarioFilter();
	private FuncionarioVo funcionarioVo = new FuncionarioVo();
	
	public String todos() {
		funcionarios.addAll(business.trazerTodosOsFuncionarios());	

		return SUCCESS;
	}
	
	public String filtrar() {
		if(filtrar.isNullOpcoesCombo())
			return REDIRECT;
		
		funcionarios = business.filtrarFuncionarios(filtrar);
		
		return SUCCESS;
	}
	
	public String novo() {
		if(funcionarioVo.getNome() == null)
			return INPUT;
		
		business.salvarFuncionario(funcionarioVo);
		
		return REDIRECT;
	}
	
	public String editar() {
		if(funcionarioVo.getRowid() == null)
			return REDIRECT;
		
		FuncionarioVo funcionarioCompleto = business.buscarFuncionarioPor(funcionarioVo.getRowid());
		
		if (funcionarioCompleto != null) {
			funcionarioVo.setNome(funcionarioCompleto.getNome());
		}
		
		return INPUT;
	}
	
	public String excluir() {
		
		 try {
		        String rowid = funcionarioVo.getRowid();
		        if (rowid == null || rowid.trim().isEmpty()) {
		        		return REDIRECT;
		        }
		        
		        business.excluirFuncionario(rowid);;
		        
		    } catch (BusinessException e) {
		        addActionError(e.getMessage());
		    }
		 return REDIRECT;
	}
	
	public String atualizar() {
		if(funcionarioVo.getRowid() == null || funcionarioVo.getNome() == null) {
			return REDIRECT;
		}
		
		business.editarFuncionario(funcionarioVo);
		return REDIRECT;
	}
	
	public List<OpcoesComboBuscar> getListaOpcoesCombo(){
		return Arrays.asList(OpcoesComboBuscar.values());
	}
	
	public List<FuncionarioVo> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<FuncionarioVo> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public FuncionarioFilter getFiltrar() {
		return filtrar;
	}

	public void setFiltrar(FuncionarioFilter filtrar) {
		this.filtrar = filtrar;
	}

	public FuncionarioVo getFuncionarioVo() {
		return funcionarioVo;
	}

	public void setFuncionarioVo(FuncionarioVo funcionarioVo) {
		this.funcionarioVo = funcionarioVo;
	}
}
