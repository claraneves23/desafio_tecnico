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
	
	
	public AgendaVo buscarAgendaPorId(String codigo) {
		try {
			Integer cod = Integer.parseInt(codigo);
			return dao.findByCodigo(cod);
		}catch (NumberFormatException e) {
			throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
		}
	}
	
	
	public void editarAgenda(AgendaVo agendaVo) {
		try {
			 if(!agendaExiste(agendaVo.getIdAgenda())) {
		        	throw new BusinessException("Agenda não encontrada");
		     }
			 
			if(agendaVo.getNomeAgenda().isEmpty())
				throw new IllegalArgumentException("Nome nao pode ser em branco");
			
			if(agendaVo.getPeriodoDisponivel().isEmpty())
				throw new IllegalArgumentException("O período disponível nao pode ser em branco");
			
			dao.updateAgenda(agendaVo);
		} catch (Exception e) {
			throw new BusinessException("Não foi possível realizar a inclusão do registro");
		}
	}
	
	public void excluirAgenda(String idAgenda) {
	    try {
	        if (idAgenda == null || idAgenda.trim().isEmpty()) {
	            throw new BusinessException("ID da agenda não informado");
	        }
	        
	       try {
	            Long.parseLong(idAgenda); 
	        } catch (NumberFormatException e) {
	            throw new BusinessException("ID da agenda inválido");
	        }
	        
	 
	        if (Long.parseLong(idAgenda) <= 0) {
	            throw new BusinessException("ID da agenda inválido");
	        }
	        
	        if(!agendaExiste(idAgenda)) {
	        	throw new BusinessException("Agenda não encontrada");
	        }
	     
	        dao.excluirAgenda(idAgenda);
	        
	    } catch (BusinessException e) {
	        throw e;
	    } catch (Exception e) {
	        throw new BusinessException("Não foi possível excluir o compromisso");
	    }
	}
	
	private boolean agendaExiste(String idAgenda) {
		try {
			 AgendaVo agendaVo = dao.findByCodigo(Integer.parseInt(idAgenda));
			 return agendaVo != null;
			
		} catch (Exception e) {
			return false;
		}
	}
}
