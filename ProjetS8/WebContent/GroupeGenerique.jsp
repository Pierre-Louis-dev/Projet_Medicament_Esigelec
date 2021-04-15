<%@ page import="project.*"  %>
<%@ page import="java.util.*"  %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 

<!DOCTYPE html>
<html lang="fr">
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="projet.css">
	<link rel="stylesheet" href="chatBox.css">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=yes">
	<title>Liste des Médicaments</title>
</head>
<body>
	<jsp:include page="nav.jsp"></jsp:include>
	<br>
	<form action="GroupeGenerMed" method="GET">
	  <div class="row">
	    <div class="col col-4">
	      <input type="text" class="form-control" placeholder="Rechercher par nom" name="nom">
	      
	    </div>
	    
	    <div class="col col-4">
	      <input type="text" class="form-control" placeholder="Rechercher et trier par ordre alphabétique" name="tri">
	    </div>
	    <div class="col col-3">
	      <input type="number" class="form-control" placeholder="Nombre maximum de lignes à afficher" name="num" min="0" max="50" required="true">
	    </div>
	    <div class="input-group-append">
		    <button class="btn btn-outline-dark" type="submit">Rechercher</button>
		</div>
	  </div>
	  </form>
	  
	  
	  
	  <%if (session.getAttribute("groupes") != null && session.getAttribute("groupesVide")==null){ %>
   		<div class="accordion" id="accordionExample">
   		
	  	  <%for (DTO_CIS_GENER_bdpm e : (ArrayList<DTO_CIS_GENER_bdpm>)session.getAttribute("groupes")){ %>		
			    <div class="card">
				    <div class="card-header" id=<%=e.getAttributes().get("cis")%>>
				      <h2 class="mb-0">
				        <button class="btn btn-link collapsed" type="button" data-toggle="collapse" data-target=<%="#"+e.getAttributes().get("libelleGroup") %> aria-expanded="true" aria-controls=<%=e.getAttributes().get("libelleGroup") %>>
				          <%=e.getAttributes().get("libelleGroup") %>
				        </button>
				      </h2>
				    </div>
				
				    <div id=<%=e.getAttributes().get("libelleGroup") %> class="collapse" aria-labelledby=<%=e.getAttributes().get("cis") %> data-parent="#accordionExample">
				      <div class="card-body">
				  	      
				  	      Identifiant du groupe : <%=e.getAttributes().get("idGroup") %><br>
				  	      Type de groupe : <%=e.getAttributes().get("typeGener") %><br>
				  	      <form class="form-inline" action="GroupeGenerMed" method="post">
							  <div class="form-group mb-2">
							    <input type="text" readonly class="form-control plaintext" name="id" value=<%=e.getAttributes().get("idGroup") %> hidden>
							  </div>
							  <button type="submit" class="btn btn-outline-dark">Voir les médicaments de ce groupe</button>
							</form>
				      </div>
				    </div>
				  </div>
	  	    <%} %>
	  	   </div>
	  	<%}else if(session.getAttribute("groupesVide")==null){ %>
	  		<br>
	  		<div class="alert alert-info" role="alert">
				  Ici seront affichés les résultats de votre recherche.
			 </div>
		<%}else{ %>
		<br>
			<div class="alert alert-danger" role="alert">
				  Aucun médicament ne correspond à votre recherche.
			 </div>
		<%} %>
</body>
</html>