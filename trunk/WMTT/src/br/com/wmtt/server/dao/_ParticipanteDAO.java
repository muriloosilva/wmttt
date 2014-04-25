package br.com.wmtt.server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.wmtt.shared.model._User;

public class _ParticipanteDAO {
	public static boolean getCPF(String cpf){
		boolean b;
		try {
			Connection con = _ConnectionMannager.getConnetion();
			PreparedStatement stmt = con.prepareStatement("select * from participantes where cpf_partic='"+cpf+"'");

			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				rs.close();
				stmt.close();
				con.close();
				b = false;
			} else {
				rs.close();
				stmt.close();
				con.close();
				b = true;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return b;
	}
	
	public static boolean getEmail(String email){
		boolean b;
		try {
			Connection con = _ConnectionMannager.getConnetion();
			PreparedStatement stmt = con.prepareStatement("select * from participantes where email_partic='"+email+"'");

			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				rs.close();
				stmt.close();
				con.close();
				b = false;
			} else {
				rs.close();
				stmt.close();
				con.close();
				b = true;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return b;
	}
	
	public static boolean loginDisponivel(String login){
		boolean b;
		try {
			Connection con = _ConnectionMannager.getConnetion();
			PreparedStatement stmt = con.prepareStatement("select * from participantes where email_partic='"+login+"'");

			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				rs.close();
				stmt.close();
				con.close();
				System.out.println("Login indisponível");
				b = false;
			} else {
				rs.close();
				stmt.close();
				con.close();
				System.out.println("Login disponível");
				b = true;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return b;
	}
	
	public static void cadastraParticipante(_User user){
		Connection con = _ConnectionMannager.getConnetion();
		try {
			PreparedStatement stmt = con.prepareStatement("insert into participantes (nome_partic,cpf_partic,email_partic,"
					+ "senha_partic,matr_aluno_partic,ativo) values (?,?,?,?,?,?)");
			stmt.setString(1, user.getNome_partic());
			stmt.setString(2, user.getCpf_partic());
			stmt.setString(3, user.getEmail_partic().trim());
			stmt.setString(4, user.getSenha_partic());
			stmt.setString(5, user.getMatr_aluno_partic());
			//stmt.setInt(6, 0);
			stmt.setInt(6, 1);
			
			stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static boolean alterarDados(String email, _User user, boolean confirmar){
		Connection con = _ConnectionMannager.getConnetion();
		try {
			PreparedStatement stmt;
			if(confirmar){
				stmt = con.prepareStatement("update participantes set nome_partic=?,cpf_partic=?,email_partic=?,"
					+ "senha_partic=?,matr_aluno_partic=?,ativo='2' where email_partic=?");
			}
			else{
				stmt = con.prepareStatement("update participantes set nome_partic=?,cpf_partic=?,email_partic=?,"
					+ "senha_partic=?,matr_aluno_partic=?,ativo='1' where email_partic=?");
			}
			stmt.setString(1, user.getNome_partic());
			stmt.setString(2, user.getCpf_partic());
			stmt.setString(3, user.getEmail_partic().trim());
			stmt.setString(4, user.getSenha_partic());
			stmt.setString(5, user.getMatr_aluno_partic());
			stmt.setString(6, email);
			stmt.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static _User getParticipante(String login){
		Connection con = _ConnectionMannager.getConnetion();
		try {
			PreparedStatement stmt = con.prepareStatement("select * from participantes where email_partic=?");
			stmt.setString(1, login);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				
				_User user = new _User();
				
				user.setNome_partic(rs.getString("nome_partic"));
				user.setCpf_partic(rs.getString("cpf_partic"));
				user.setEmail_partic(rs.getString("email_partic"));
				user.setSenha_partic(rs.getString("senha_partic"));
				user.setMatr_aluno_partic(rs.getString("matr_aluno_partic"));
				user.setAtivo(rs.getInt("ativo"));
				
				rs.close();
				stmt.close();
				con.close();
				return user;
			} else {
				rs.close();
				stmt.close();
				con.close();
				return null;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean setParticipanteAtivo(String login){
		Connection con = _ConnectionMannager.getConnetion();
		try {
			PreparedStatement stmt = con.prepareStatement("update participantes set ativo='1' where email_partic=?");
			stmt.setString(1, login);
			stmt.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
