<%@page import="models.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>EventoSchool</title>
<link href="/EventSchool/css/cadastrarAtividade.css" rel="stylesheet" type="text/css" />
</head>
<body> 
	<% 
	Usuario usuario = (Usuario)session.getAttribute("usuario");
	int idEvento = Integer.parseInt(request.getParameter("cod"));
    
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
         		<div id="inscritos"><a  class="fontMenuLateral" href="/EventSchool/admin/inscritos.jsp?cod=<%=idEvento%>">Inscritos</a></div><br>
         	</div>
         	<form method="POST" action="/EventSchool/cadastrarAtividade?cod=<%=idEvento%>">
	         	<div id="loginUser">
	         		
	         		<div id="acessar" align="center" class="fontTitulo">SECITEC 2014</div> 
	         		
	         		<div id="acessar" align="center" class="fontTitulo">Cadastrar Atividade</div>
	         		
	         		
	         		<div class="center">
	         			
		         			<%
			         		String erro = (String)request.getAttribute("msg");
							if(erro!=null){
							%>
			         		<div id="erro"><%=erro%></div>
			         		<% } %>
			         		<div class="fontTitulo">Nome:</div>
				         	<input class="caixa" type="text" name="nome"/>
				         	
				         	<div class="fontTitulo">Descrição:</div>
				         	<input class="caixa" type="text" name="descricao"/>
			         	
				         	<div class="fontTitulo">Tipo:</div>
				         	<select class="caixa" name="tipo">
				         		<option value="palestra">Palestra</option>
				         		<option value="oficina">Oficina</option>
				         		<option value="minicurso">Minicurso</option>
				         	</select>
				         	
				         	<div class="fontTitulo">Data:</div>
				         	<input class="caixa" type="text" name="data"/>
				         	
				         	<div class="fontTitulo">Hora inicio:</div>
				         	<input class="caixa" type="text" name="horaInicio"/>
				         	
				         	<div class="fontTitulo">Hora fim:</div>
				         	<input class="caixa" type="text" name="horaFim"/>
				         	
				         	<div class="fontTitulo">Vagas:</div>
				         	<input class="caixa" type="text" name="vagas"/>
			         	
		         	</div>
		         	<div id="login" align="center"><input type="button" value="Cancelar"/><input type="submit" value="Cadastrar"/></div>
		         </div>
	         </form>
         </div>
	</div>
</body>
</html>