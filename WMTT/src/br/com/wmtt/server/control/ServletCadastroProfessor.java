package br.com.wmtt.server.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.wmtt.server.dao.ProfessorDAO;
import br.com.wmtt.shared.model.Professor;
import br.com.wmtt.shared.model._User;

/**
 * Servlet implementation class LoginAdminServlet
 */
public class ServletCadastroProfessor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCadastroProfessor() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String nome = request.getParameter("nome");
		String senha1 = request.getParameter("senha");
		
		Professor cadastroProfessor = new Professor();
		
		cadastroProfessor.setEmail(email);
		cadastroProfessor.setNome(nome);
		cadastroProfessor.setSenha(senha1);
		
		if(ProfessorDAO.professorCadastrado(email) != null){
			request.setAttribute("msg", "E-mail já cadastrado.");
			RequestDispatcher view = request.getRequestDispatcher("/cadastrar.jsp");
			view.forward(request, response);
		}
		else{
			
			ProfessorDAO.addProfessor(cadastroProfessor);
			RequestDispatcher view = request.getRequestDispatcher("/index.html");
			view.forward(request, response);
		}
	}

}
