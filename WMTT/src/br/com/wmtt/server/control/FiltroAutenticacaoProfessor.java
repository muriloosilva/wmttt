package br.com.wmtt.server.control;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.wmtt.shared.model.Professor;

public class FiltroAutenticacaoProfessor implements Filter{

	public void init(FilterConfig config) throws ServletException {
	}
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {

		HttpSession session = ((HttpServletRequest)req).getSession();
		Professor professor = (Professor)session.getAttribute("professor");
		if(professor!=null && professor.isLogado()){
			chain.doFilter(req, res);
			
		}else{
			req.setAttribute("msg","Você não está logado no sistema! Faça Login.");
			RequestDispatcher view = req.getRequestDispatcher("../acessoProfessor.jsp");
			view.forward(req, res);
		}
	}	 
	public void destroy() {
	}
	
}
