package br.com.soc.sistema.business;

import java.util.ArrayList;
import java.util.List;

import br.com.soc.sistema.dao.FuncionarioDao;
import br.com.soc.sistema.exception.BusinessException;
import br.com.soc.sistema.filter.FuncionarioFilter;
import br.com.soc.sistema.vo.FuncionarioVo;

public class FuncionarioBusiness {

	private static final String FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO = "Foi informado um caracter no lugar de um numero";
	private FuncionarioDao dao;
	
	public FuncionarioBusiness() {
		this.dao = new FuncionarioDao();
	}
	
	public List<FuncionarioVo> trazerTodosOsFuncionarios(){
		return dao.findAllFuncionarios();
	}	
	
	public void salvarFuncionario(FuncionarioVo funcionarioVo) {
		try {
			if(funcionarioVo.getNome().isEmpty())
				throw new IllegalArgumentException("Nome nao pode ser em branco");
			
			dao.insertFuncionario(funcionarioVo);
		} catch (Exception e) {
			throw new BusinessException("Nao foi possivel realizar a inclusao do registro");
		}
		
	}	
	
	
	public List<FuncionarioVo> filtrarFuncionarios(FuncionarioFilter filter){
		List<FuncionarioVo> funcionarios = new ArrayList<>();
		
		switch (filter.getOpcoesCombo()) {
			case ID:
				try {
					Integer codigo = Integer.parseInt(filter.getValorBusca());
					funcionarios.add(dao.findByCodigo(codigo));
				}catch (NumberFormatException e) {
					throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
				}
			break;

			case NOME:
				funcionarios.addAll(dao.findAllByNome(filter.getValorBusca()));
			break;
		}
		
		return funcionarios;
	}
	
	public FuncionarioVo buscarFuncionarioPor(String codigo) {
		try {
			Integer cod = Integer.parseInt(codigo);
			return dao.findByCodigo(cod);
		}catch (NumberFormatException e) {
			throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
		}
	}
	
	
	public void editarFuncionario(FuncionarioVo funcionarioVo) {
		try {
			if(funcionarioVo.getNome().isEmpty())
				throw new IllegalArgumentException("Nome nao pode ser em branco");
			
			dao.updateFuncionario(funcionarioVo);
		} catch (Exception e) {
			throw new BusinessException("a função update não está funcionando");
		}
	}
	
	public void excluirFuncionario(String rowid) {
	    try {
	        if (rowid == null || rowid.trim().isEmpty()) {
	            throw new BusinessException("ID do funcionario não informado");
	        }
	        
	       try {
	            Long.parseLong(rowid); 
	        } catch (NumberFormatException e) {
	            throw new BusinessException("ID do funcionario inválido");
	        }
	        
	 
	        if (Long.parseLong(rowid) <= 0) {
	            throw new BusinessException("ID do funcionario inválido");
	        }
	        
	        if(!funcionarioExiste(rowid)) {
	        	throw new BusinessException("Funcionario não encontrado");
	        }
	     
	        dao.excluirFuncionario(rowid);
	        
	    } catch (BusinessException e) {
	        throw e;
	    } catch (Exception e) {
	        throw new BusinessException("Não foi possível excluir o compromisso");
	    }
	}
	
	private boolean funcionarioExiste(String rowid) {
		try {
			 FuncionarioVo funcionarioVo = dao.findByCodigo(Integer.parseInt(rowid));
			 return funcionarioVo != null;
			
		} catch (Exception e) {
			return false;
		}
	}
}
