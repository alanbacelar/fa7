<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
    <title>Contadores</title>

	<link rel="stylesheet" href="./css/bootstrap.min.css">
	<link rel="stylesheet" href="./css/bootstrap-theme.min.css">

  </head>
  
  <body>
	<jsp:include page="menu.xhtml" />
	
    <div class="container">
      <h1>Contadores de acesso</h1>

      <!-- Main component for a primary marketing message or call to action -->
      <div class="jumbotron" style="margin-top: 10px">
        <h1><%= application.getAttribute("countXHTMLRequest") %> <small>acesso(s) a páginas ".xhtml".</small></h1>
        <p>Clique <a href="/trabalho/teste.xhtml">aqui</a> para acessar a página de teste.xhtml</p>
      </div>
      
      <h3>Lista de acessos:</h3>
 
      <ul>
      	<c:forEach items='${mapXHTMLCounters}' var="entry">
      		<li><b>${entry.key}:</b> ${entry.value} acesso(s).</li>
		</c:forEach>
      </ul>
      
      <p style="margin-top: 30px;">
        <a class="btn btn-lg btn-primary" href="/trabalho" role="button">Página inicial</a>
      </p>

    </div> <!-- /container -->

	<jsp:include page="footer.xhtml" />
  </body>
</html>
