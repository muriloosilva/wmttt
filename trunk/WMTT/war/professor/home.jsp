<%@page import="models.Usuario"%>
<%@page import="java.util.Iterator"%>
<%@page import="DB.EventoDAO"%>
<%@page import="models.Evento"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>EventoSchool</title>
<link href="/EventSchool/css/homeLogado.css" rel="stylesheet" type="text/css" />
</head>
<body>

	<% 
	Usuario usuario = (Usuario)session.getAttribute("usuario");
    
  	%>

	<div id="principal">
		 <div id="top"></div>
		 <div id="menuHeader">
         	<a id="eventos" class="fontMenu" href="#">Eventos</a>
           	<a id="contato" class="fontMenu" href="#">Contato</a>
         </div>
         <div id="user">
         	<div id="name" class="fontUser">Olá, <%=usuario.getNome() %></div> 
         	<div id="conta" class="fontUser">Conta</div>
         	<div id="sair" class="fontUser">Sair</div>
         </div>
         <div id="corpo">
         	<div align="center" id="lista">
	         	<div class="fontTitulo">Programação</div>
	         	<div id="tabelaProgramacao">
	         		<table>
	         		<tr><td class="col1"><div class="fontTituloTabela">Nome</div></td><td class="col2"><div class="fontTituloTabela">Local</div></td><td class="col3"><div class="fontTituloTabela">Data Início</div></td></tr>
	         		<%
	         			List<Evento> listaDeEventos = ProvaDAO.listaDeEventos();
	         		                   if(listaDeEventos != null){
	         		                 	  Iterator<Evento> ic = listaDeEventos.iterator();
	         		                 	  while(ic.hasNext()){
	         		                 		  Evento evento = ic.next();
	         		%>
	         			
	         			<tr><td class="col1"><%=evento.getNome()%></td> <td class="col2"><%=evento.getLocal()%></td><td class="col3"><%=evento.getDataInicioFormatada()%></td> <td class="col4"><a href="/EventSchool/participante/evento.jsp?cod=<%=evento.getId_evento()%>">acessar</a></td></tr>
	         		<%}}
                 	 else{
                     	out.println("Não existe evento cadastrado");               
                     }  
                   	%>	
                   		
	         		</table>
	         	
	         		
	         	</div> 
	         </div>	
         </div>
	</div>
</body>
</html>