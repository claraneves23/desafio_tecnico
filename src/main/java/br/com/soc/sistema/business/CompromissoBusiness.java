package br.com.soc.sistema.business;

import java.util.List;

import br.com.soc.sistema.dao.AgendaDao;
import br.com.soc.sistema.dao.CompromissoDao;
import br.com.soc.sistema.dao.FuncionarioDao;
import br.com.soc.sistema.exception.BusinessException;
import br.com.soc.sistema.vo.AgendaVo;
import br.com.soc.sistema.vo.CompromissoVo;


public class CompromissoBusiness {
	
	private static final String FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO = "Foi informado um caracter no lugar de um numero";
	private CompromissoDao dao;
	private FuncionarioDao funcionarioDao;
	private AgendaDao agendaDao;
	private AgendaBusiness agendaBusiness;
	
	public CompromissoBusiness() {
		this.dao = new CompromissoDao();
		this.funcionarioDao = new FuncionarioDao();
		this.agendaDao = new AgendaDao();
		this.agendaBusiness = new AgendaBusiness();
	}
	
	
	public List<CompromissoVo> trazerTodosOsCompromissos(){
		return dao.findAllCompromissos();
	}	
	
	public void salvarCompromisso(CompromissoVo compromissoVo) {
		try {
			if (!validarCamposObrigatorios(compromissoVo)) {
	            throw new IllegalArgumentException("Campos obrigatórios não preenchidos");
	        }
			
			String horario = compromissoVo.getHorario();
	        if (horario != null && horario.length() == 5) {
	            compromissoVo.setHorario(horario + ":00");
	        }
			
	        
			if (!funcionarioExiste(compromissoVo.getIdFuncionario())) {
				throw new BusinessException("Funcionário não encontrado");
			}
			
			if (!agendaExiste(compromissoVo.getIdAgenda())) {
				throw new BusinessException("Agenda não encontrada");
			}
			
			if(!validarPeriodoAgenda(compromissoVo)) {
				throw new BusinessException("Compromisso fora do período disponível da agenda");
			}
			
	        dao.insertCompromisso(compromissoVo);
	        
		  } catch (BusinessException e) {
		        throw e; 
	        
	    } catch (Exception e) {
	        throw new BusinessException("Nao foi possivel realizar a inclusao do registro");
	    }
		
	}
	
	public CompromissoVo buscarCompromissoPorId(String codigo) {
		try {
			Integer cod = Integer.parseInt(codigo);
			return dao.findByCodigo(cod);
		}catch (NumberFormatException e) {
			throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
		}
	}
	
	
	public void editarCompromisso(CompromissoVo compromissoVo) {
		try {
			
			if (compromissoVo.getIdCompromisso() == null || compromissoVo.getIdCompromisso().trim().isEmpty()) {
	            throw new BusinessException("ID do compromisso não informado para edição");
	        }
			
			if (!validarCamposObrigatorios(compromissoVo)) {
	            throw new IllegalArgumentException("Campos obrigatórios não preenchidos");
	        }
			
			String horario = compromissoVo.getHorario();
	        if (horario != null && horario.length() == 5) {
	            compromissoVo.setHorario(horario + ":00");
	        }
			
			if (!funcionarioExiste(compromissoVo.getIdFuncionario())) {
				throw new BusinessException("Funcionário não encontrado");
			}
			
			if (!agendaExiste(compromissoVo.getIdAgenda())) {
				throw new BusinessException("Agenda não encontrada");
			}
			
			if(!validarPeriodoAgenda(compromissoVo)) {
				throw new BusinessException("Compromisso fora do período disponível da agenda");
			}
			
			dao.updateCompromisso(compromissoVo);
			
		  } catch (BusinessException e) {
		        throw e; 
			
		  } catch (Exception e) {
			  throw new BusinessException("Não foi possível fazer a inserção do registro");
		  }
	}
	
	public void excluirCompromisso(String idCompromisso) {
	    try {
	        if (idCompromisso == null || idCompromisso.trim().isEmpty()) {
	            throw new BusinessException("ID do compromisso não informado");
	        }
	        
	       try {
	            Long.parseLong(idCompromisso); 
	        } catch (NumberFormatException e) {
	            throw new BusinessException("ID do compromisso inválido");
	        }
	        
	 
	        if (Long.parseLong(idCompromisso) <= 0) {
	            throw new BusinessException("ID do compromisso inválido");
	        }
	        
	        if(!compromissoExiste(idCompromisso)) {
	        	throw new BusinessException("Compromisso não encontrado");
	        }
	     
	        dao.excluir(idCompromisso);
	        
	    } catch (BusinessException e) {
	        throw e;
	    } catch (Exception e) {
	        throw new BusinessException("Não foi possível excluir o compromisso");
	    }
	}
	
	public boolean validarCamposObrigatorios(CompromissoVo compromissoVo) {
	    return !(compromissoVo.getIdFuncionario() == null || compromissoVo.getIdFuncionario().trim().isEmpty()
	          || compromissoVo.getIdAgenda() == null || compromissoVo.getIdAgenda().trim().isEmpty()
	          || compromissoVo.getData() == null
	          || compromissoVo.getHorario() == null);
	}
	

	private boolean funcionarioExiste(String idFuncionario) {
		return funcionarioDao.existeFuncionarioPorId(idFuncionario);
	}
	
	private boolean agendaExiste(String idAgenda) {
		return agendaDao.existeAgendaPorId(idAgenda);
	}
	
	private boolean compromissoExiste(String idCompromisso) {
		try {
			 CompromissoVo compromissoVo = dao.findByCodigo(Integer.parseInt(idCompromisso));
			 return compromissoVo != null;
			
		} catch (Exception e) {
			return false;
		}
	}
	
	private boolean validarPeriodoAgenda(CompromissoVo compromissoVo) {
		try {
				AgendaVo agenda = agendaBusiness.buscarAgendaPorId(compromissoVo.getIdAgenda());
				if(agenda == null || agenda.getPeriodoDisponivel() == null) {
					return false;
				}
				
				PeriodoDisponivel periodo = PeriodoDisponivel.fromDescricao(agenda.getPeriodoDisponivel());
				
				if(periodo == null) {
					return false;
				}
				
				 String horarioCompromisso = compromissoVo.getHorario();
			     if (horarioCompromisso != null && horarioCompromisso.length() == 5) {
			            horarioCompromisso += ":00"; 
			      }
				
				return periodo.horarioDentroDoPeriodo(horarioCompromisso);
				
		} catch (Exception e) {
			return false;
		}
	}
}
