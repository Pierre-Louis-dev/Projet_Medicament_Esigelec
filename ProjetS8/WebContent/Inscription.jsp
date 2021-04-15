<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	
	<title>Demande d'inscription</title>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="projet.css">
	<link rel="stylesheet" href="chatBox.css">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=yes">
</head>
<body class="body">
	<jsp:include page="nav.jsp"></jsp:include>
	<div class="container">
        <div class="row">
            <div class="col-3">
            </div>
            <div class="col-6">
            
            <form class="aligne" method="get" action="Inscription" style="margin:150px auto; padding:20px; border:8px double #242424; background-color:#ffffff;">
                <div class="row">                    
                    <img src="debut.png" alt="LOGO" width ="500" class="img-fluid">                                                           
                </div>                    
                <br>
                <h4>Veuillez saisir vos informations s'il vous plaît</h4>
                <br><br>
                <%if(session.getAttribute("LoginInvalide")!=null){ %>
	                <div class="alert alert-danger" role="alert">
						  Login invalide !.
					</div>
				 <%}else if(session.getAttribute("MdpNonConforme")!=null){ %>
				 	<div class="alert alert-danger" role="alert">
						  Les deux mots de passe doivent être identiques.
					</div>
				 <%}else if(session.getAttribute("inscrit")!=null) {%>
					<div class="alert alert-success" role="alert">
						Vous êtes inscrit. Veuillez attendre qu'un administrateur valide votre compte.Voici votre ID a connaître :
                       
		                 <%project.DTO_Personnel e = (project.DTO_Personnel) session.getAttribute("code"); %>
		                  <a> <%=e.getAttributes().get("id") %></a>
		                  <p class="aligne"><a title="Suivi" href="Suividemande.jsp">Suivi de votre demande</a></p>
					</div>
				 <%session.setAttribute("inscrit", null);  } %>
         
                <div class="form-group col-md-12 ">
                        <label for="nom">Nom</label>
                        <input type="text" class="form-control " id="nom" placeholder="Nom" name="nom" required>
                    </div>

                    <div class="form-group col-md-12">
                        <label for="prenom">Prenom</label>
                        <input type="text"  id="prenom" class="form-control" placeholder="Prenom" name="prenom" required>
                    </div>

                    <div class="form-group col-md-12">
                        <label for="email">Login</label>
                        <input type="email" class="form-control" id="email" placeholder="Login" name="login" required>
                    </div>

                    <div class="form-group col-md-12">
                        <label for="password">Mot de passe</label>
                        <input type="password" class="form-control" id="password" placeholder="Mot De Passe" name="password" required>
                    </div>

                    <div class="form-group col-md-12">
                        <label for="cpassword"> Confirmer le mot de passe</label>
                        <input type="password" class="form-control" id="cpassword" placeholder="Saisir à nouveau le mot de passe" name="cpassword" required>
                    </div>
                    <div class="form-group col-md-12">
                        <label for="profession">Profession</label>
                        <input type="text"  id="profession" class="form-control" placeholder="Profession" name="profession" required>
                    </div>
                    <div class="row">
                        <button type="submit" class="btn btn-primary aligne" >S'inscrire</button>
                    </div>
                    <p class="aligne"><a title="Connexion" href="Connexion.jsp">Déjà inscrit?</a></p>
                <br>
            </form>
            </div>
            <div class="col-3">
            </div>
        </div>
    </div>

</body>
</html>