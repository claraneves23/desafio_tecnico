package br.com.soc.sistema.util;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStream;

public class ExcelDownloadService {
	
	private HttpServletResponse response;
    
    public ExcelDownloadService(HttpServletResponse response) {
        this.response = response;
    }
    
    public void download(String filename, byte[] excelBytes) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=" + filename);
        response.setContentLength(excelBytes.length);
        
        try (OutputStream out = response.getOutputStream()) {
            out.write(excelBytes);
            out.flush();
        }
    }
}
