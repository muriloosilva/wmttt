package br.com.wmtt.server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.wmtt.shared.model._User;

public class _LoginDAO {
	
	public static final int DESLOGADO = 0;
	public static final int LOGADO = 1;
	public static final int INATIVO = 2;
	public static final int ATIVO = 3;
	
	public static _User loginUsuario(String login, String senha){
		
		_User user = null;
		
		try {
			Connection con = _ConnectionMannager.getConnetion();
			PreparedStatement stmt = con
					.prepareStatement("select * from participantes "
							+ "where email_partic = ? and senha_partic = ?");
			stmt.setString(1, login);
			stmt.setString(2, senha);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				user = new _User();
				user.setId_partic(rs.getInt("id_partic"));
				user.setNome_partic(rs.getString("nome_partic"));
				user.setCpf_partic(rs.getString("cpf_partic"));
				user.setEmail_partic(rs.getString("email_partic"));
				user.setSenha_partic(rs.getString("senha_partic"));
				user.setMatr_aluno_partic(rs.getString("matr_aluno_partic"));
				user.setAtivo(rs.getInt("ativo"));
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (user != null) {
			System.out.println("email: " + user.getEmail_partic().trim());
			System.out.println("senha: " + user.getSenha_partic());
			System.out.println("email1: " + login);
			System.out.println("senha1: " + senha);
			System.out.println("usuario existe");
			if (user.getEmail_partic().trim().equals(login)
					&& user.getSenha_partic().equals(
							senha)) {
				System.out.println("Usuário autenticado");
				user.setLogado(true);
			} else {
				System.out.println("Usuário não autenticado");
				user.setLogado(false);
			}
		}
		else{
			System.out.println("User null");
		}
		return user;
	}
}
