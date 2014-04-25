package br.com.wmtt.server.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.wmtt.shared.model.Professor;

/**
 * Servlet implementation class LoginAdminServlet
 */

public class ServletLoginProfessor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLoginProfessor() {
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
		
		HttpSession session = ((HttpServletRequest)request).getSession();
		Professor professor = (Professor)session.getAttribute("professor");
		
		if(professor == null){
			
			String email = request.getParameter("email");
			String passwd = request.getParameter("senha");
			
			professor = new Professor();
			professor.setEmail(email);
			professor.setSenha(passwd);
			
			professor = ControleLogin.loginProfessor(professor);
			if(professor != null){
				if(professor.isLogado()){
					request.getSession().setAttribute("professor", professor);
					response.sendRedirect("/professor/wmtt.html?gwt.codesvr=127.0.0.1:9997");
					//view.forward(request, response);
				}
				else{
					request.setAttribute("msg","Email ou senha inválido");
					RequestDispatcher view = request.getRequestDispatcher("/acessoProfessor.jsp");
					view.forward(request, response);
				}
			}
			else{
				
				request.setAttribute("msg","Email inexistente");
				RequestDispatcher view = request.getRequestDispatcher("acessoProfessor.jsp");
				view.forward(request, response);

			}
		}
		else{
			if(professor.isLogado()){
				request.getSession().setAttribute("professor", professor);
				response.sendRedirect("/professor/wmtt.html?gwt.codesvr=127.0.0.1:9997");
				//view.forward(request, response);
			}
			else{
				request.setAttribute("msg","Usuário ou senha inválido");
				RequestDispatcher view = request.getRequestDispatcher("acessoProfessor.jsp");
				view.forward(request, response);
			}
		}
	}

}
