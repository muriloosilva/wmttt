<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WMTT</title>
<link href="/css/estiloGeral.css" rel="stylesheet" type="text/css" />
<link href="/css/cadastrar.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div id="pagina">
		 <div id="cabecalho">
		 	<div id="logo">
		 		<div id="logoC">
		 			<div class="fonteLogo">WMTT</div>
		 		</div>
		 	</div>
			<div id="menu">
				<div id="menuC">
					<a class="fonteMenu">Provas</a>
					<a class="fonteMenu">Sobre</a>
					<a class="fonteMenu">Contato</a>
				</div>
			</div>
		 </div>
		 
         <div id="corpo">
         	<div id="corpoC">
     			
       			<form method="POST" action="/cadastrarProfessor">
		         	<div id="caixaAutenticacao">
		         		
		         		<div id="acessar" align="center" class="fontePadrao">Cadastro para acesso ao WMTT</div><br>
		         		
			         	<%  
						String erro = (String)request.getAttribute("msg");
						if(erro!=null){
						%>
		         		<div id="erro"><%=erro%></div><br>
		         		<% } %>
		         		
		         		<div class="fontePadrao">Nome:</div>
			         	<input id="caixaEmail" type="text" name="nome"/><br><br>
		         		
			         	<div class="fontePadrao">E-mail:</div>
			         	<input id="caixaEmail" type="text" name="email"/><br><br>
			         	
			         	<div class="fontePadrao">Senha:</div>
			         	<input id="caixaSenha" type="password" name="senha"/><br><br>
			         	
			         	<div id="login" align="center"><input type="submit" value="Cadastrar"/></div>
			         </div>
 				</form>   
         	</div>
         	<div id="rodape"></div>
         </div>
       </div>   
         
</body>
</html>