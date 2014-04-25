<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>EventoSchool</title>
<link href="css/configurarEvento.css" rel="stylesheet" type="text/css" />
</head>
<body> 
	<div id="principal">
		 <div id="top"></div>
		 <div id="menuHeader">
         	<a id="eventos" class="fontMenu" href="#">Eventos</a>
           	<a id="contato" class="fontMenu" href="#">Contato</a>
         </div>
         <div id="user">
         	<div id="name" class="fontUser">Olá, Admin</div> 
         	<div id="conta" class="fontUser">Conta</div>
         	<div id="sair" class="fontUser">Sair</div>
         </div>
         <div id="corpo">
         	<div align="center" id=menuLateral>
         		<div id="atividade" class="fontMenuLateral">Atividades</div>
         		<div id="configuracaoEvento" class="fontMenuLateral">Config. Evento</div>
         		<div id="inscritos" class="fontMenuLateral">Inscritos</div>
         	</div>
         	<div id="loginUser">
         		
         		<div id="acessar" align="center" class="fontTitulo">SECITEC 2014</div> 
         		
         		<div id="acessar" align="center" class="fontTitulo">Configurar Evento</div>
         		
         		
         		<div class="center">
         			<div id="erro">*Evento já cadastrado</div>
	         		<div class="fontTitulo">Nome:</div>
		         	<input class="caixa" type="text" name="nome"/>
		         	
		         	<div class="fontTitulo">Descrição:</div>
		         	<input class="textArea" type="text" name="descricao"/>
	         	
		         	<div class="fontTitulo">Local:</div>
		         	<input class="caixa" type="text" name="local"/>
		         	
		         	<div class="fontTitulo">Data Inicio:</div>
		         	<input class="caixa" type="text" name="inicio"/>
		         	
		         	<div class="fontTitulo">Data Fim:</div>
		         	<input class="caixa" type="text" name="fim"/>
		         	
		         	<div class="fontTitulo">Data inicio inscrições:</div>
		         	<input class="caixa" type="text" name="inicioInscricoes"/>
		         	
		         	<div class="fontTitulo">Data fim inscrições:</div>
		         	<input class="caixa" type="text" name="fimInscricoes"/>
		         	
		         	<div class="fontTitulo">Telefone:</div>
		         	<input class="caixa" type="text" name="telefone"/>
		         	
		         	<div class="fontTitulo">E-mail:</div>
		         	<input class="caixa" type="text" name="email"/>
	         	</div>
	         	<div id="login" align="center"><input type="button" value="Cancelar"/><input type="button" value="Alterar"/></div>
	         </div>
         </div>
	</div>
</body>
</html>