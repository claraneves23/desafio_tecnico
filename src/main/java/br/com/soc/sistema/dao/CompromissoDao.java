package br.com.soc.sistema.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.soc.sistema.vo.CompromissoVo;

//CREATE TABLE compromisso (rowid bigint, id_agenda bigint, data date, horario time, foreign key(rowid) references funcionario(rowid),
//foreign key(id_agenda) references agenda(id_agenda));

//private LocalDate data;
//private LocalTime horario;

public class CompromissoDao extends Dao{
		
		public void insertCompromisso(CompromissoVo compromissoVo){
			StringBuilder query = new StringBuilder("INSERT INTO compromisso (rowid, id_agenda, data, horario) values (?, ?, ?, ?)");
			try(
				Connection con = getConexao();
				PreparedStatement  ps = con.prepareStatement(query.toString())){
				
				int i=1;
				ps.setString(i++, compromissoVo.getIdFuncionario());
				ps.setString(i++, compromissoVo.getIdAgenda());
				ps.setDate(i++, Date.valueOf(compromissoVo.getData()));
				String horario = compromissoVo.getHorario();
				if (horario.length() == 5) { 
				    horario += ":00"; 
				}
				ps.setTime(4, java.sql.Time.valueOf(horario));
				ps.executeUpdate();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		public List<CompromissoVo> findAllCompromissos(){
			 StringBuilder query = new StringBuilder("SELECT id_compromisso, rowid, id_agenda, data, horario FROM compromisso");
		        
			try(
				Connection con = getConexao();
				PreparedStatement  ps = con.prepareStatement(query.toString());
				ResultSet rs = ps.executeQuery()){
				
				CompromissoVo compromissoVo =  null;
				List<CompromissoVo> compromissos = new ArrayList<>();
				while (rs.next()) {
					compromissoVo = new CompromissoVo();
					compromissoVo.setIdCompromisso(rs.getString("id_compromisso"));
					compromissoVo.setIdFuncionario(rs.getString("rowid"));
					compromissoVo.setIdAgenda(rs.getString("id_agenda"));
					compromissoVo.setData(rs.getDate("data").toString());
					compromissoVo.setHorario(rs.getTime("horario").toString());
					compromissos.add(compromissoVo);
				}
				return compromissos;
			}catch (SQLException e) {
				e.printStackTrace();
			}
			
			return Collections.emptyList();
		
		}
}
