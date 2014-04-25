package br.com.wmtt.server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.wmtt.shared.model.Categoria;
import br.com.wmtt.shared.model.Prova;

public class CategoriaDAO {
	public static boolean addCategoria(Categoria categoria){
		
		String sql = "insert into categoria " +
				"(descricao)" +
				" values (?)";
		try {
			// prepared statement para insersão
			Connection con = ConnectionMannager.getConnetion();
			PreparedStatement stmt = con.prepareStatement(sql);
			// seta os valores
			stmt.setString(1,categoria.getDescricao());
			// executa
			stmt.execute();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
	
	public static Categoria getCategoria(int id){
		
		PreparedStatement stmt;
		Categoria categoria = null;
		try {
			Connection con = ConnectionMannager.getConnetion();
			stmt = con.prepareStatement("select * from categoria where idCategoria = "+ id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				// criando o objeto Contato
				categoria = new Categoria();
				categoria.setIdCategoria(rs.getInt("idCategoria"));
				categoria.setDescricao(rs.getString("descricao"));
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return categoria;
		
	}
	
	public static boolean deleteCategoria(Categoria categoria){
		
		try {
			Connection con = ConnectionMannager.getConnetion();
			PreparedStatement stmt = con.prepareStatement("delete * from categoria where "
					+ "idCategoria=?");
			stmt.setInt(1, categoria.getIdCategoria());
			stmt.execute();
			stmt.close();
			con.close();
			} catch (SQLException e) {
				return false;
			}
		return true;
	}	
	
}
