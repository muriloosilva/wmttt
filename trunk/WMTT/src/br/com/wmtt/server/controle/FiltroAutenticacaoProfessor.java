package br.com.wmtt.server.controle;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class FiltroAutenticacaoProfessor implements Filter{

	public void init(FilterConfig config) throws ServletException {
	}
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("controle!!!!");
		HttpSession session = ((HttpServletRequest)req).getSession();
//		Usuario usuario = (Usuario)session.getAttribute("usuario");
//		if(usuario!=null && usuario.isLogado() && usuario.getPapel().equals(Usuario.papelParticipante)){
//			chain.doFilter(req, res);
//			
//		}else{
//			req.setAttribute("msg","Você não está logado no sistema! Faça Login.");
//			System.out.println(req.getParameter("cod"));
//			req.setAttribute("cod", req.getParameter("cod"));
//			RequestDispatcher view = req.getRequestDispatcher("../loginParticipante.jsp");
//			view.forward(req, res);
//		}
	}	 
	public void destroy() {
	}
	
}
