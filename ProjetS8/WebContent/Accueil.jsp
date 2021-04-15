<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Accueil</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">


	<nav class="navbar navbar-dark bg-dark">
     <a  class="btn btn-outline-danger"href="Connexion.jsp" >Connexion</a>
    <a  class="btn btn-outline-danger" href="Inscription.jsp">Demande de Compte</a>
	</nav>

</head>


<body style="background-color:coral;color:white">
	<div class="text-center">
        <h1 class="display-4" style="text-shadow:20px 12px 2px#000000">Bienvenue sur France|Médicaments</h1>
        Tout savoir sur vos médicaments,vous avez le choix:
    </div><br>

	<div class="container" style="background-color:withe;color:white;width:1000px;text-align:center">
	<br>
		<a class="btn btn-outline-dark" style="width:550px" href="Commercialises.jsp">Médicaments commercialisés cette année</a><br>
		<a class="btn btn-outline-dark" style="width:550px"href="Arret.jsp">Médicaments en arrêt de commercialisation depuis moins de trois ans</a><br>
		<a class="btn btn-outline-dark"style="width:550px" href="Presentations.jsp">Presentations disponibles pour les médicaments </a><br>
		<a class="btn btn-outline-dark" style="width:550px" href="Compositions.jsp">Composition des médicaments(qualitative/quantitative)</a><br>
		
	<br><br>
	</div>
</body>
</html>


