package br.com.soc.sistema.business;

import java.time.LocalDate;
import java.util.List;

import br.com.soc.sistema.dao.RelatorioDao;
import br.com.soc.sistema.exception.BusinessException;
import br.com.soc.sistema.vo.RelatorioCompromissoVo;

public class RelatorioBusiness {
	
	private RelatorioDao dao;

	public RelatorioBusiness() {
		this.dao = new RelatorioDao();
	}
	
	public List<RelatorioCompromissoVo> gerarRelatorio(LocalDate dataInicial, LocalDate dataFinal){
		if(dataInicial == null || dataFinal == null) {
			throw new BusinessException("Data inicial e data final são obrigatórias");		}
				if(dataFinal.isBefore(dataInicial)) {
			throw new BusinessException("Data final não pode ser anterior à data inicial");
		}
		
     	return dao.gerarRelatorioPorPeriodo(dataInicial, dataFinal);
	}
	
	 

}
