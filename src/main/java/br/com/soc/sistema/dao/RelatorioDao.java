package br.com.soc.sistema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.soc.sistema.vo.RelatorioCompromissoVo;

public class RelatorioDao extends Dao{
	
	public List<RelatorioCompromissoVo> gerarRelatorioPorPeriodo(LocalDate dataInicial, LocalDate dataFinal){
		
		StringBuilder query = new StringBuilder()
				.append("SELECT ")
				.append(" f.rowid as id_funcionario, ")
				.append(" f.nm_funcionario as nome_funcionario, ")
				.append(" a.id_agenda as id_agenda, ")
				.append(" a.nm_agenda as nome_agenda, ")
				.append(" c.data as data_compromisso, ")
				.append(" c.horario as hora_compromisso ")
				.append("FROM compromisso c ")
				.append("INNER JOIN funcionario f ON c.rowid = f.rowid ")
				.append("INNER JOIN agenda a ON c.id_agenda = a.id_agenda ")
				.append("WHERE c.data BETWEEN ? AND ? ")
				.append("ORDER BY c.data, c.horario");
		
		List<RelatorioCompromissoVo> relatorio = new ArrayList<>();
		
		try (Connection con = getConexao();
				PreparedStatement ps = con.prepareStatement(query.toString())) {
				int i = 1;
			
			    ps.setDate(i++, java.sql.Date.valueOf(dataInicial));
			    ps.setDate(i++, java.sql.Date.valueOf(dataFinal));
			    
			    try(ResultSet rs = ps.executeQuery()) {
			    	while(rs.next()) {
			    		RelatorioCompromissoVo vo = new RelatorioCompromissoVo();
			    		vo.setIdFuncionario(rs.getString("id_funcionario"));
			    		vo.setNomeFuncionario(rs.getString("nome_funcionario"));
			    		vo.setIdAgenda(rs.getString("id_agenda"));
			    		vo.setNomeAgenda(rs.getString("nome_agenda"));
			    		vo.setDataCompromisso(rs.getString("data_compromisso"));
			    		vo.setHoraCompromisso(rs.getString("hora_compromisso"));
			    		
			    		relatorio.add(vo);
			    	}
			    }
			    
			} catch(SQLException e) {
				e.printStackTrace();
			}
		
		  	return relatorio;
		}
	}
