package br.com.wmtt.server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.wmtt.shared.model.Prova;
import br.com.wmtt.shared.model.Questao;

public class QuestoesProvaProfessorDAO {
	public static boolean addQuestaNaProvaDoProfessor(Questao questao, Prova prova){
		
		String sql = "insert into questoesProvaProfessor " +
				"(idProva, idQuestao)" +
				" values (?,?)";
		try {
			// prepared statement para insersão
			Connection con = ConnectionMannager.getConnetion();
			PreparedStatement stmt = con.prepareStatement(sql);
			// seta os valores
			stmt.setInt(1,prova.getIdProva());
			stmt.setInt(2,questao.getIdQuestao());
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
	
	
	public static boolean deleteQuestaoProvaDoProfessor(int idQuestaoProvaProfessor){
		
		try {
			Connection con = ConnectionMannager.getConnetion();
			PreparedStatement stmt = con.prepareStatement("delete questoesProvaProfessor where "
					+ "idQuestoesProvaProfessor=?"+idQuestaoProvaProfessor);
			stmt.execute();
			stmt.close();
			con.close();
			} catch (SQLException e) {
				System.out.println(e);
				return false;
			}
		return true;
	}	
	
	public static boolean verificarQuestaoInserida(Questao questao, Prova prova){
		
		try {
			Connection con = ConnectionMannager.getConnetion();
			PreparedStatement stmt = con.prepareStatement("select * from questoesProvaProfessor where idProva=" + prova.getIdProva() + 
					" and idQuestao =" +  questao.getIdQuestao());
			stmt.execute();
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				rs.close();
				stmt.close();
				con.close();
				return true;
			}
			else{
				return false;
			}
			
			} catch (SQLException e) {
				System.out.println(e);
				return false;
			}
	}
	
}
