package br.com.soc.sistema.business;

import java.util.List;

import br.com.soc.sistema.dao.CompromissoDao;
import br.com.soc.sistema.exception.BusinessException;
import br.com.soc.sistema.vo.CompromissoVo;


public class CompromissoBusiness {
	
	private static final String FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO = "Foi informado um caracter no lugar de um numero";
	private CompromissoDao dao;
	
	public CompromissoBusiness() {
		this.dao = new CompromissoDao();
	}
	
	
	public List<CompromissoVo> trazerTodosOsCompromissos(){
		return dao.findAllCompromissos();
	}	
	
	public void salvarCompromisso(CompromissoVo compromissoVo) {
		try {
			if (!validarCamposObrigatorios(compromissoVo)) {
	            throw new IllegalArgumentException("Campos obrigatórios não preenchidos");
	        }
	        dao.insertCompromisso(compromissoVo);
	    } catch (Exception e) {
	        throw new BusinessException("Nao foi possivel realizar a inclusao do registro");
	    }
		
	}
	
	public boolean validarCamposObrigatorios(CompromissoVo compromissoVo) {
	    return !(compromissoVo.getIdFuncionario() == null || compromissoVo.getIdFuncionario().trim().isEmpty()
	          || compromissoVo.getIdAgenda() == null || compromissoVo.getIdAgenda().trim().isEmpty()
	          || compromissoVo.getData() == null
	          || compromissoVo.getHorario() == null);
	}

	
}
