package br.com.wmtt.server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.wmtt.shared.model.Prova;

public class ProvaDAO {
	public static boolean addProva(Prova prova){
		
		String sql = "insert into prova " +
				"(nome, dataCriacao, valor, disciplina)" +
				" values (?,?,?,?,?,?,?,?,?)";
		try {
			// prepared statement para insersão
			Connection con = ConnectionMannager.getConnetion();
			PreparedStatement stmt = con.prepareStatement(sql);
			// seta os valores
			stmt.setString(1,prova.getNome());
			stmt.setDate(2,prova.getDataCriacao());
			stmt.setDouble(3,prova.getValor());
			stmt.setString(4,prova.getDisciplina());
			// executa
			stmt.execute();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
	
	public static List<Prova> listProva(int idProfessor){
		
		PreparedStatement stmt;
		List<Prova> provas = new ArrayList<Prova>();
		try {
			Connection con = ConnectionMannager.getConnetion();
			stmt = con.prepareStatement("select * from prova where idProfessor="+idProfessor);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				
				Prova prova = new Prova();
				prova.setIdProva(rs.getInt("idProva"));
				prova.setNome(rs.getString("nome"));
				prova.setDataCriacao(rs.getDate("dataCriacao"));
				prova.setValor(rs.getDouble("valor"));
				prova.setDisciplina(rs.getString("disciplina"));
				prova.setProfessor(ProfessorDAO.getProfessor(rs.getInt("idProfessor")));

				provas.add(prova);
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(provas.isEmpty())
			return null;
		else
			return provas;
		
	}
	
	public static Prova getProva(int id){
		
		PreparedStatement stmt;
		Prova prova = null;
		try {
			Connection con = ConnectionMannager.getConnetion();
			stmt = con.prepareStatement("select * from prova where idProva = "+ id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				// criando o objeto Contato
				prova = new Prova();
				prova.setIdProva(rs.getInt("idProva"));
				prova.setNome(rs.getString("nome"));
				prova.setDataCriacao(rs.getDate("dataCriacao"));
				prova.setValor(rs.getDouble("valor"));
				prova.setDisciplina(rs.getString("disciplina"));
				prova.setProfessor(ProfessorDAO.getProfessor(rs.getInt("idProfessr")));
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return prova;
		
	}
	
	public static boolean alterarProva(Prova prova){
		
		String sql = "update prova set nome=?, dataCriacao=?, valor=?," +
				"disciplina=? where idProva=?";
		try {
			Connection con = ConnectionMannager.getConnetion();
			PreparedStatement stmt = con.prepareStatement(sql);
			
			// seta os valores
			stmt.setString(1,prova.getNome());
			stmt.setDate(2,prova.getDataCriacao());
			stmt.setDouble(3,prova.getValor());
			stmt.setString(4,prova.getDisciplina());
			stmt.execute();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			return false;
		}
		return true;

		
	}
	
	public static boolean deleteProva(Prova prova){
		
		try {
			Connection con = ConnectionMannager.getConnetion();
			PreparedStatement stmt = con.prepareStatement("delete * from prova where "
					+ "idProva=?");
			stmt.setInt(1, prova.getIdProva());
			stmt.execute();
			stmt.close();
			con.close();
			} catch (SQLException e) {
				return false;
			}
		return true;
	}	
	
}
