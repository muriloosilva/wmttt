<%@page import="DB.InscricoesDAO"%>
<%@page import="models.Inscricao"%>
<%@page import="java.util.Iterator"%>
<%@page import="DB.AtividadeDAO"%>
<%@page import="models.Atividade"%>
<%@page import="java.util.List"%>
<%@page import="DB.EventoDAO"%>
<%@page import="models.Evento"%>
<%@page import="models.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>EventoSchool</title>
<link href="/EventSchool/css/inscritos.css" rel="stylesheet" type="text/css" />
</head>
<body>

<% 
	Usuario usuario = (Usuario)session.getAttribute("usuario");
    int idEvento = Integer.parseInt(request.getParameter("cod"));
    Evento evento = EventoDAO.pegarEvento(idEvento);
    
  	%>

	<div id="principal">
		 <div id="top"></div>
		 <div id="menuHeader">
         	<a id="eventos" class="fontMenu" href="/EventSchool/admin/eventos.jsp">Eventos</a>
           	<a id="contato" class="fontMenu" href="#">Contato</a>
         </div>
         <div id="user">
         	<div id="name" class="fontUser">Olá, <%=usuario.getNome() %></div> 
         	<div id="conta" class="fontUser">Conta</div>
         	<div id="sair" class="fontUser">Sair</div>
         </div>
         <div id="corpo">
         	<div align="center" id=menuLateral>
         		<div id="atividade"><a  class="fontMenuLateral" href="/EventSchool/admin/gerencia.jsp?cod=<%=idEvento%>">Atividades</a></div><br>
         		<div id="configuracaoEvento"><a  class="fontMenuLateral">Config. Evento</a></div><br>
         		<div id="inscritos"><a  class="fontMenuLateral" href="#">Inscritos</a></div><br>
         	</div>
         	<div id="loginUser">
         		
         		<div id="acessar" align="center" class="fontTitulo"><%=evento.getNome() %></div> 
         		
         		<div id="acessar" align="center" class="fontTitulo">Lista de Inscritos</div>
         		
         		      		
         		
         		<div class="fontTitulo">Palestras:</div>
         		<div id="tabelaAtividade">
         		<%
         			List<_Atividade> listaPalestrasEvento = _AtividadeDAO.listaDePalestrasEvento(idEvento);
         		         				if(listaPalestrasEvento != null){
         		%>
				   	
	         	
	         		<table>
	         			<%
	         				Iterator<_Atividade> it = listaPalestrasEvento.iterator();
	         				         								while(it.hasNext()){
	         				         									_Atividade atividade = it.next();
	         				         									out.println("<br><br>Palestra: " + atividade.getNome());
	         				         									List<Inscricao> listaInscricaoAtividade =  InscricoesDAO.listaDeInscricaoAtividade(atividade);
	         				         									if(listaInscricaoAtividade!= null){
	         				         										Iterator<Inscricao> i = listaInscricaoAtividade.iterator();
	         				         										while(i.hasNext()){
	         				         											Inscricao inscricao = i.next();
	         				         											out.println("<br>Inscrito: " + inscricao.getParticipante().getNome());
	         				         										}
	         				         									}
	         				         									else{
	         				         										out.println("sem inscrições");
	         				         									}

	         				         				         			}
	         			%>
	         		</table>
	         		<%
	         			}else{
	         			         			         			out.println("Não existe inscrito");
	         			         			         		}
	         		%>
	         	</div>
	         	
	         	<div class="fontTitulo">Minicursos:</div>
	         	<div id="tabelaAtividade">
	         	
	         	<%
	         		         		List<_Atividade> listaPalestrasMinicurso = _AtividadeDAO.listaDeMinicursoEvento(idEvento);
	         		         		         		         			if(listaPalestrasMinicurso != null){
	         		         	%>
	         	
	         	
	         		<table>
	         			<%
	         				Iterator<_Atividade> it = listaPalestrasMinicurso.iterator();
	         				         								while(it.hasNext()){
	         				         									_Atividade atividade = it.next();
	         				         									out.println("<br><br>Palestra: " + atividade.getNome());
	         				         									List<Inscricao> listaInscricaoAtividade =  InscricoesDAO.listaDeInscricaoAtividade(atividade);
	         				         									if(listaInscricaoAtividade!= null){
	         				         										Iterator<Inscricao> i = listaInscricaoAtividade.iterator();
	         				         										while(i.hasNext()){
	         				         											Inscricao inscricao = i.next();
	         				         											out.println("<br>Inscrito: " + inscricao.getParticipante().getNome());
	         				         										}
	         				         									}
	         				         									else{
	         				         										out.println("sem inscrições");
	         				         									}

	         				         				         			}
	         			%>
	         		</table>
	         		<%
	         			}else{
	         			         			         			out.println("Não existe inscrito");
	         			         			         		}
	         		%>
	         	</div>
	         	
	         	<div class="fontTitulo">Oficinas:</div>
	         	<div id="tabelaAtividade">
	         	<%
	         		List<_Atividade> listaPalestrasOficinas = _AtividadeDAO.listaDeOficinaEvento(idEvento);
	         		         			if(listaPalestrasOficinas != null){
	         	%>
	         	
	         	
	         		<table>
	         			<%
	         				Iterator<_Atividade> it = listaPalestrasEvento.iterator();
	         								while(it.hasNext()){
	         									_Atividade atividade = it.next();
	         									out.println("<br><br>Palestra: " + atividade.getNome());
	         									List<Inscricao> listaInscricaoAtividade =  InscricoesDAO.listaDeInscricaoAtividade(atividade);
	         									if(listaInscricaoAtividade!= null){
	         										Iterator<Inscricao> i = listaInscricaoAtividade.iterator();
	         										while(i.hasNext()){
	         											Inscricao inscricao = i.next();
	         											out.println("<br>Inscrito: " + inscricao.getParticipante().getNome());
	         										}
	         									}
	         									else{
	         										out.println("sem inscrições");
	         									}

	         				         			}
	         			%>
	         		</table>
	         		<%}else{
	         			out.println("Não existe inscrito");
	         		}%>
	         	</div>
	         	
	         	<div id="login" align="center"><input type="button" value="Criar nova atividade"/></div>
	         </div>
         </div>
	</div>
</body>
</html>