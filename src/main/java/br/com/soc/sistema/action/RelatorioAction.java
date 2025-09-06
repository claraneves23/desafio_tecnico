package br.com.soc.sistema.action;

import java.io.InputStream;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import br.com.soc.sistema.business.RelatorioBusiness;
import br.com.soc.sistema.filter.RelatorioFilter;
import br.com.soc.sistema.infra.Action;
import br.com.soc.sistema.util.ExcelDownloadService;
import br.com.soc.sistema.util.ExcelGenerator;
import br.com.soc.sistema.vo.RelatorioCompromissoVo;

public class RelatorioAction extends Action {
	
	private RelatorioFilter filtro = new RelatorioFilter();
	private List<RelatorioCompromissoVo> relatorio;
	private RelatorioBusiness business = new RelatorioBusiness();
	private InputStream inputStream;
	
	public String relatorio() {
		return INPUT;
	}
	
	public String gerar() {
		
		if(!filtro.isValid()) {
			addActionError("Período inválido. Verifique as datas");
			return INPUT;
		}
		
		try {
			relatorio = business.gerarRelatorio(
					filtro.converterParaLocalDate(filtro.getDataInicial()),
					filtro.converterParaLocalDate(filtro.getDataFinal())
					);
			
			if (relatorio.isEmpty()) {
				addActionMessage("Nenhum compromisso encontrado para o periodo selecione");
			}
			
			return SUCCESS;
			
		} catch (Exception e) {
			addActionError("Erro ao gerar relatório: " + e.getMessage());
			return INPUT;
		}
	}

	public String exportar() {
		try {
			relatorio = business.gerarRelatorio(filtro.converterParaLocalDate(filtro.getDataInicial()),
					filtro.converterParaLocalDate(filtro.getDataFinal()));
			
			byte[] excelBytes = ExcelGenerator.generateExcel(relatorio);
			HttpServletResponse response = ServletActionContext.getResponse();
		    ExcelDownloadService downloadService = new ExcelDownloadService(response);
			downloadService.download("relatorio_compromissos.xlsx", excelBytes);
		    
			return null;
			
			
		} catch (Exception e) {
			addActionError("Erro ao gerar Excel: " + e.getMessage());
			return INPUT;
		}
	}
	
	
	public RelatorioFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(RelatorioFilter filtro) {
		this.filtro = filtro;
	}

	public List<RelatorioCompromissoVo> getRelatorio() {
		return relatorio;
	}

	public void setRelatorio(List<RelatorioCompromissoVo> relatorio) {
		this.relatorio = relatorio;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	
	
}
