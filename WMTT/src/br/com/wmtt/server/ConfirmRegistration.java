package br.com.wmtt.server;




import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.wmtt.server.dao._ParticipanteDAO;
import br.com.wmtt.server.util.HashUtil;
import br.com.wmtt.shared.model._User;

/**
 * Servlet implementation class Horas
 */
public class ConfirmRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String key = request.getParameter("key");
		String cad = request.getParameter("cad");
		System.out.println("id: "+id);
		_User user = _ParticipanteDAO.getParticipante(id);
		
		
		int res = 2;
		
		if(user!=null){
			if(user.getAtivo() == 1){
				res = 0;
				//cadastro já ativado
			}
			else if(HashUtil.stringHexa(HashUtil.gerarHash(user.getCpf_partic()+user.getLogin_partic()+user.getSenha_partic(), "SHA-1")).equals(key)){
				res = 1;
				if(!_ParticipanteDAO.setParticipanteAtivo(id))
					res = 2;
				//confirmar cadastro
			}
			else{
				res = 2;
				//Cadastro inválido
			}
			
		}
		else{
			//usuário inexistente
		}
		RequestDispatcher view;
		if(cad != null){
			view = request.getRequestDispatcher("confirmRegistration.jsp?res="+res+"&cad=1");
		}
		else{
			view = request.getRequestDispatcher("confirmRegistration.jsp?res="+res);
		}
		view.forward(request, response);
		System.out.println("ahhhhhhhhhhhh");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
