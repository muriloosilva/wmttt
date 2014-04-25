package br.com.wmtt.server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.wmtt.shared.model.Professor;

public class ProfessorDAO {
	
	public static boolean addProfessor(Professor professor){
		
		String sql = "insert into professor " +
				"(nome, email, senha)" +
				" values (?,?,?)";
		try {
			// prepared statement para insersão
			Connection con = ConnectionMannager.getConnetion();
			PreparedStatement stmt = con.prepareStatement(sql);
			// seta os valores
			stmt.setString(1,professor.getNome());
			stmt.setString(2,professor.getEmail());
			stmt.setString(3,professor.getSenha());
			// executa
			stmt.execute();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}
		return true;
	}
	
	public static Professor getProfessor(int id){
		
		PreparedStatement stmt;
		Professor professor = null;
		try {
			Connection con = ConnectionMannager.getConnetion();
			stmt = con.prepareStatement("select * from professor where idProfessor = "+ id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				// criando o objeto Contato
				professor = new Professor();
				professor.setIdProfessor(rs.getInt("idProfessor"));
				professor.setNome(rs.getString("nome"));
				professor.setEmail(rs.getString("email"));
				professor.setSenha(rs.getString("senha"));
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return professor;
		
	}
	
}
