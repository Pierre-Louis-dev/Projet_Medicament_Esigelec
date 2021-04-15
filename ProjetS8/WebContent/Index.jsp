<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="projet.css">
	<link rel="stylesheet" href="chatBox.css">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=yes">
	<title>Base de données publique des médicaments</title>
</head>
<body class="body">
	
	<jsp:include page="nav.jsp"></jsp:include>
	
	<div class="container aligne">
		<br><br><br>
		
		<a href="Commercialises.jsp" type="button" class="btn btn-info">Médicaments commercialisés</a>		
		<br><br>
		<a href="Arret.jsp" type="button" class="btn btn-info">Médicaments en arrêt de commercialisation</a>		
		<br><br>
		<a href="Compositions.jsp" type="button" class="btn btn-info">Compositions des médicaments</a>		
		<br><br>
		<%if(session.getAttribute("actif")!=null){ %>
		<a class="btn btn-info" href="GroupeGenerique.jsp">Groupes génériques des médicaments</a>
		<br><br>
		<a class="btn btn-info" href="ConsultationAvisHAS_SMR.jsp">Avis SMR et ASMR sur un médicament</a>
		<br><br>
		<a class="btn btn-info" href="InfosImportantes.jsp">Liste des infos importantes</a>
		<br><br>
		<a class="btn btn-info" href="ListeLiens.jsp">Liste des liens</a>
		<br><br>
		<a class="btn btn-info" href="Conditions.jsp">Conditions de prescription et de délivrance</a>
		<br><br>
		<a class="btn btn-info" href="Statistiques.jsp">Afficher les statistiques de connexion</a>
		<br><br>
		<%} %>
	</div>
	
	
</body>
</html>