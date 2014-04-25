package br.com.wmtt.server.control;

import br.com.wmtt.server.dao.ProfessorDAO;
import br.com.wmtt.shared.model.Professor;


public class ControleLogin {
	
	public static Professor loginProfessor(Professor professor){
		Professor professorBD = ProfessorDAO.professorCadastrado(professor.getEmail());
		if(professorBD != null){
			if(professorBD.getEmail().equals(professor.getEmail()) 
					&& professorBD.getSenha().equals(professor.getSenha())){
				professorBD.setLogado(true);
			}
			else
				professorBD.setLogado(false);
		}
		return professorBD;
	}
	
//	public static Usuario loginAdministrador(Usuario usuario){
//		Usuario usuarioBD = UsuarioDAO.pegarAdministrador(usuario);
//		if(usuarioBD != null){
//			if(usuarioBD.getEmail().equals(usuario.getEmail()) 
//					&& usuarioBD.getSenha().equals(usuario.getSenha())){
//				usuarioBD.setLogado(true);
//			}
//			else
//				usuarioBD.setLogado(false);
//		}
//		return usuarioBD;
//	}


}
