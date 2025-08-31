package br.com.soc.sistema.business;

import java.util.ArrayList;
import java.util.List;

import br.com.soc.sistema.dao.AgendaDao;
import br.com.soc.sistema.exception.BusinessException;
import br.com.soc.sistema.vo.AgendaVo;


public class AgendaBusiness {

	private static final String FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO = "Foi informado um caracter no lugar de um numero";
	private AgendaDao dao;
	
	public AgendaBusiness() {
		this.dao = new AgendaDao();
	}
	
	public List<AgendaVo> trazerTodasAsAgendas(){
		return dao.findAllAgendas();
	}	
	
	public void salvarAgenda(AgendaVo agendaVo) {
		try {
			if(agendaVo.getNomeAgenda().isEmpty())
				throw new IllegalArgumentException("O nome da agenda nao pode ser em branco");
			
			if(agendaVo.getPeriodoDisponivel().isEmpty())
				throw new IllegalArgumentException("O período disponível nao pode ser em branco");
			
			dao.insertAgenda(agendaVo);
		} catch (Exception e) {
			throw new BusinessException("Nao foi possivel realizar a inclusao do registro");
		}
		
	}
	
	
	public AgendaVo buscarAgendaPor(String codigo) {
		try {
			Integer cod = Integer.parseInt(codigo);
			return dao.findByCodigo(cod);
		}catch (NumberFormatException e) {
			throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
		}
	}
	
	
	public void editarAgenda(AgendaVo agendaVo) {
		try {
			if(agendaVo.getNomeAgenda().isEmpty())
				throw new IllegalArgumentException("Nome nao pode ser em branco");
			
			if(agendaVo.getPeriodoDisponivel().isEmpty())
				throw new IllegalArgumentException("O período disponível nao pode ser em branco");
			
			dao.updateAgenda(agendaVo);
		} catch (Exception e) {
			throw new BusinessException("a função update não está funcionando");
		}
	}
	
}
