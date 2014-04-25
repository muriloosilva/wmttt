package br.com.wmtt.server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.wmtt.shared.model.Prova;
import br.com.wmtt.shared.model.Questao;

public class QuestaoDAO {
	public static boolean addQuestao(Questao questao){
		
		String sql = "insert into questao " +
				"(descricao, resposta, idCategoria)" +
				" values (?,?,?)";
		try {
			// prepared statement para insersão
			Connection con = ConnectionMannager.getConnetion();
			PreparedStatement stmt = con.prepareStatement(sql);
			// seta os valores
			stmt.setString(1,questao.getDescricao());
			stmt.setString(2,questao.getResposta());
			stmt.setInt(3,questao.getCategoria().getIdCategoria());
			// executa
			stmt.execute();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
	
	public static List<Questao> listQuestoes(){
		
		PreparedStatement stmt;
		List<Questao> questoes = new ArrayList<Questao>();
		try {
			Connection con = ConnectionMannager.getConnetion();
			stmt = con.prepareStatement("select * from questao");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				
				Questao questao = new Questao();
				questao.setIdQuestao(rs.getInt("idQuestao"));
				questao.setDescricao(rs.getString("descricao"));
				questao.setResposta(rs.getString("resposta"));
				questao.setCategoria(CategoriaDAO.getCategoria(rs.getInt("idCategoria")));

				questoes.add(questao);
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(questoes.isEmpty())
			return null;
		else
			return questoes;
		
	}
	
	public static List<Questao> listQuestoesCategoria(int idCategoria){
		
		PreparedStatement stmt;
		List<Questao> questoes = new ArrayList<Questao>();
		try {
			Connection con = ConnectionMannager.getConnetion();
			stmt = con.prepareStatement("select * from questao where idCategoria="+idCategoria);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				
				Questao questao = new Questao();
				questao.setIdQuestao(rs.getInt("idQuestao"));
				questao.setDescricao(rs.getString("descricao"));
				questao.setResposta(rs.getString("resposta"));
				questao.setCategoria(CategoriaDAO.getCategoria(rs.getInt("idCategoria")));

				questoes.add(questao);
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(questoes.isEmpty())
			return null;
		else
			return questoes;
		
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
