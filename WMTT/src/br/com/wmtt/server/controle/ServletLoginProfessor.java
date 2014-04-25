package br.com.wmtt.server.controle;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.wmtt.shared.model.Professor;

/**
 * Servlet implementation class LoginAdminServlet
 */
@WebServlet(name = "loginProfessor", urlPatterns = { "/loginProfessor" })
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
		Professor user = (Usuario)session.getAttribute("usuario");
		
		if(user == null){
			
			String name = request.getParameter("email");
			String passwd = request.getParameter("senha");
			
			Usuario usuario = new Usuario();
			usuario.setEmail(name);
			usuario.setSenha(passwd);
			
			System.out.println("oi " + request.getParameter("cod"));
	 		System.out.println("oih " + request.getAttribute("cod"));
			
			usuario = ControleLogin.loginParticipante(usuario);
			if(usuario != null){
				if(usuario.isLogado()){
					request.getSession().setAttribute("usuario", usuario);
					RequestDispatcher view = request.getRequestDispatcher("/provas");
					view.forward(request, response);
				}
				else{
					request.setAttribute("msg","Usuário ou senha inválido");
					RequestDispatcher view = request.getRequestDispatcher("loginProfessor.jsp");
					view.forward(request, response);
				}
			}
			else{
				if(name!=null){
					request.setAttribute("msg","Usuário ou senha inválido");
					RequestDispatcher view = request.getRequestDispatcher("loginProfessor.jsp");
					view.forward(request, response);
				}
				else{
					request.setAttribute("msg","Para acessar o sistema é necessário realizar login");
					RequestDispatcher view = request.getRequestDispatcher("loginParticipante.jsp");
					view.forward(request, response);
				}
			}
		}
		else{
			if(user.isLogado()){
				request.getSession().setAttribute("usuario", user);
				RequestDispatcher view = request.getRequestDispatcher("/participante/home.jsp");
				view.forward(request, response);
			}
			else{
				request.setAttribute("msg","Usuário ou senha inválido");
				RequestDispatcher view = request.getRequestDispatcher("loginParticipante.jsp");
				view.forward(request, response);
			}
		}
	}

}
