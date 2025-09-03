package br.com.soc.sistema.filter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class RelatorioFilter {
	private String dataInicial;
	private String dataFinal;
	
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	public String getDataInicial() {
		return dataInicial;
	}
	public void setDataInicial(String dataInicial) {
		this.dataInicial = dataInicial;
	}
	public String getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(String dataFinal) {
		this.dataFinal = dataFinal;
	}
	
	public LocalDate converterParaLocalDate(String dataStr) {
		if (dataStr == null || dataStr.trim().isEmpty()) {
            return null;
        }
		
		try {
			return LocalDate.parse(dataStr, FORMATTER);
		} catch (DateTimeParseException e) {
			return null;
		}
	}
	
	 public boolean isValid() { 
	        LocalDate inicio = converterParaLocalDate(dataInicial);
	        LocalDate fim = converterParaLocalDate(dataFinal);
	        
	        return inicio != null && 
	               fim != null && 
	               !fim.isBefore(inicio);
	    }
}
