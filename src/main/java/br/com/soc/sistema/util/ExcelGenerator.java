package br.com.soc.sistema.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import br.com.soc.sistema.vo.RelatorioCompromissoVo;

public class ExcelGenerator {
	
	public static byte[] generateExcel(List<RelatorioCompromissoVo> relatorio) throws IOException{
		try(Workbook workbook = new XSSFWorkbook();
				ByteArrayOutputStream out = new ByteArrayOutputStream()){
			
			Sheet sheet = workbook.createSheet("Relatório de Compromissos");
			
			Row headerRow = sheet.createRow(0);
			String[] headers = {"Código Funcionário", "Nome Funcionário", "Código Agenda", "Nome Agenda", "Data", "Hora"};
			CellStyle headerStyle = workbook.createCellStyle();
			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerStyle.setFont(headerFont);
			headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
			headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			
			for (int i = 0; i < headers.length; i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(headers[i]);
				cell.setCellStyle(headerStyle);			
			}
			
			int rowNum = 1;
			for (RelatorioCompromissoVo item : relatorio) {
				Row row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(item.getIdFuncionario());
				row.createCell(1).setCellValue(item.getNomeFuncionario());
				row.createCell(2).setCellValue(item.getIdAgenda());
				row.createCell(3).setCellValue(item.getNomeAgenda());
				row.createCell(4).setCellValue(item.getDataCompromisso());
				row.createCell(5).setCellValue(item.getHoraCompromisso());
			}
			
			for (int i = 0; i < headers.length; i++) {
				sheet.autoSizeColumn(i);
			}
			
			workbook.write(out);
			return out.toByteArray();
		}
	}

}
