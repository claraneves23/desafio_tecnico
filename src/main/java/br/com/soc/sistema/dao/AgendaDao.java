package br.com.soc.sistema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.soc.sistema.exception.BusinessException;
import br.com.soc.sistema.vo.AgendaVo;


public class AgendaDao extends Dao{
	
	public void insertAgenda(AgendaVo agendaVo){
		StringBuilder query = new StringBuilder("INSERT INTO agenda (nm_agenda, pd_disponivel) values (?, ?)");
		try(
			Connection con = getConexao();
			PreparedStatement  ps = con.prepareStatement(query.toString())){
			
			int i=1;
			ps.setString(i++, agendaVo.getNomeAgenda());
			ps.setString(i++, agendaVo.getPeriodoDisponivel());
			ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<AgendaVo> findAllAgendas(){
		StringBuilder query = new StringBuilder("SELECT id_agenda, nm_agenda, pd_disponivel FROM agenda");
		try(
			Connection con = getConexao();
			PreparedStatement  ps = con.prepareStatement(query.toString());
			ResultSet rs = ps.executeQuery()){
			
			AgendaVo agendaVo =  null;
			List<AgendaVo> agendas = new ArrayList<>();
			while (rs.next()) {
				agendaVo = new AgendaVo();
				agendaVo.setIdAgenda(rs.getString("id_agenda"));
				agendaVo.setNomeAgenda(rs.getString("nm_agenda"));
				agendaVo.setPeriodoDisponivel(rs.getString("pd_disponivel"));
				
				agendas.add(agendaVo);
			}
			return agendas;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return Collections.emptyList();
	}
	

	public AgendaVo findByCodigo(Integer codigo){
		StringBuilder query = new StringBuilder("SELECT id_agenda, nm_agenda, pd_disponivel FROM agenda ")
								.append("WHERE id_agenda = ?");
		
		try(Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())){
			int i = 1;
			
			ps.setInt(i, codigo);
			
			try(ResultSet rs = ps.executeQuery()){
				AgendaVo agendaVo =  null;
				
				while (rs.next()) {
					agendaVo = new AgendaVo();
					agendaVo.setIdAgenda(rs.getString("id_agenda"));
					agendaVo.setNomeAgenda(rs.getString("nm_agenda"));
					agendaVo.setPeriodoDisponivel(rs.getString("pd_disponivel"));
				}
				return agendaVo;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}		
		return null;
	} 
	
	public void updateAgenda(AgendaVo agendaVo) {
		StringBuilder query = new StringBuilder("UPDATE agenda SET nm_agenda = ? , pd_disponivel = ?")
				.append("where id_agenda = ?");   
		try(
				Connection con = getConexao();
				PreparedStatement  ps = con.prepareStatement(query.toString())){
				
				int i=1;
				ps.setString(i++, agendaVo.getNomeAgenda());
				ps.setString(i++, agendaVo.getPeriodoDisponivel());
				ps.setLong(i++, Long.parseLong(agendaVo.getIdAgenda()));
				ps.executeUpdate();
				
			}catch (SQLException e) {
				e.printStackTrace();
			}
		
	}
	
	public boolean existeAgendaPorId(String idAgenda) {
		StringBuilder query = new StringBuilder ("SELECT COUNT(*) FROM agenda WHERE id_agenda = ?");
		try (Connection con = getConexao();
				PreparedStatement ps = con.prepareStatement(query.toString())) {
			ps.setString(1, idAgenda);
			try(ResultSet rs = ps.executeQuery()){
				if(rs.next()) {
					return rs.getInt(1) > 0;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 return false;	
	}
	
	public void excluirAgenda(String idAgenda) {
		StringBuilder query = new StringBuilder("DELETE FROM agenda WHERE id_agenda = ?");
		
		try(Connection con = getConexao();
				PreparedStatement ps = con.prepareStatement(query.toString())) {
				
				ps.setString(1, idAgenda);
				ps.executeUpdate();
				
			} catch (SQLException e) {
				if(e.getMessage().contains("referential integrity constraint")) {
					throw new BusinessException("Não é possível excluir agenda com compromissos cadastrados");
				}
				throw new BusinessException("Erro ao excluir agenda: " + e.getMessage());
			}
		}
	
}
