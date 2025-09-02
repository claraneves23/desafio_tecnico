package br.com.soc.sistema.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import br.com.soc.sistema.vo.CompromissoVo;


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
				ps.setTime(i++, java.sql.Time.valueOf(horario));
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
		
		
		public CompromissoVo findByCodigo(Integer codigo){
			StringBuilder query = new StringBuilder("SELECT id_compromisso, rowid, id_agenda, data, horario FROM compromisso ")
									.append("WHERE id_compromisso = ?");
			
			try(Connection con = getConexao();
				PreparedStatement ps = con.prepareStatement(query.toString())){
				int i = 1;
				
				ps.setInt(i, codigo);
				
				try(ResultSet rs = ps.executeQuery()){
					CompromissoVo compromissoVo =  null;
					
					while (rs.next()) {
						compromissoVo = new CompromissoVo();
						compromissoVo.setIdFuncionario(rs.getString("rowid"));
						compromissoVo.setIdAgenda(rs.getString("id_agenda"));
						compromissoVo.setData(rs.getDate("data").toString());
						compromissoVo.setHorario(rs.getTime("horario").toString());
						compromissoVo.setIdCompromisso(rs.getString("id_compromisso"));
					}
					return compromissoVo;
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}		
			return null;
		} 
		
		
		public void updateCompromisso(CompromissoVo compromissoVo){
			
			 if (compromissoVo.getIdCompromisso() == null || compromissoVo.getIdCompromisso().trim().isEmpty()) {
			        throw new IllegalArgumentException("ID do compromisso não pode ser nulo para update");
			 }
			
			StringBuilder query = new StringBuilder("UPDATE compromisso SET rowid = ?, id_agenda = ?, data = ?, horario = ? ")
					.append("where id_compromisso = ?");
			try(
				Connection con = getConexao();
				PreparedStatement  ps = con.prepareStatement(query.toString())){
				
				  	System.out.println("DEBUG - Valores recebidos no DAO:");
			        System.out.println("rowid: " + compromissoVo.getIdFuncionario());
			        System.out.println("id_agenda: " + compromissoVo.getIdAgenda());
			        System.out.println("data: " + compromissoVo.getData());
			        System.out.println("horario: " + compromissoVo.getHorario());
			        System.out.println("id_compromisso: " + compromissoVo.getIdCompromisso());
				
				
				int i=1;
				ps.setString(i++, compromissoVo.getIdFuncionario());
				ps.setString(i++, compromissoVo.getIdAgenda());
				ps.setDate(i++, Date.valueOf(compromissoVo.getData()));
				String horario = compromissoVo.getHorario();
				ps.setTime(i++, java.sql.Time.valueOf(horario));  
				ps.setString(i++, compromissoVo.getIdCompromisso());
				ps.executeUpdate();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		//CREATE TABLE compromisso (rowid bigint, id_agenda bigint, data date, horario time, foreign key(rowid) references funcionario(rowid),
		//foreign key(id_agenda) references agenda(id_agenda));
		
		public void excluir(String idCompromisso) {
			StringBuilder query =  new StringBuilder("DELETE FROM compromisso WHERE id_compromisso = ?");
			
			try(    Connection con = getConexao();
					PreparedStatement  ps = con.prepareStatement(query.toString())){
					int i=1;
					ps.setString(i++, idCompromisso);
					ps.executeUpdate();
					
				}catch (SQLException e) {
						e.printStackTrace();
			}
		}
		
		
		
}
