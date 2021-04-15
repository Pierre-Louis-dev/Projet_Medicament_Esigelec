<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	
	<title>Connexion</title>
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
                <form class="aligne" method="get" action="Identification" style="margin:150px auto; padding:20px; border:8px double #242424; background-color:#ffffff;">
                    <div class="row">                    
                        <img src="debut.png" alt="LOGO" width ="500" class="img-fluid">                                                           
                    </div>
                    
                    <br>
                    <h4>Veuillez saisir vos identifiants s'il vous plaît</h4>
                    <br><br>
                    <%if(session.getAttribute("MauvaisIdentifiants")!=null){ %>
	                     <div class="alert alert-danger" role="alert">
							  Veuillez saisir de bons identifiants.
						 </div>
					<%session.setAttribute("MauvaisIdentifants", null);
					}else if(session.getAttribute("refuse")!=null){ %>
						<div class="alert alert-danger" role="alert">
							  Votre compte n'a pas encore été validé.
						 </div>
					<%session.setAttribute("refuse", null);
					}%>

                    <div class="form-group col-md-12">
                        <label for="log">Login</label>
                        <input type="email" class="form-control" id="log" placeholder="Entrez votre login SVP" name="login" required autofocus>
                    </div>

                    <div class="form-group col-md-12">
                        <label for="mdp">Mot de passe</label>
                        <input type="password" class="form-control" id="mdp" placeholder="Entrez votre mot de passe SVP" name="password" required>
                    </div>
                    
                    <div class="row">
                        <button type="submit" class="btn btn-primary aligne" >Connexion</button>
                    </div>
                    <p class="aligne"><a title="Inscription" href="Inscription.jsp">Pas encore inscrit?</a></p>
                    <p class="aligne"><a title="Forgot" href="ReinitialisationMdp.jsp">Mot de passe oublié?</a></p>
                    <br>
                </form>
            </div>
            <div class="col-3">
            </div>
        </div>
    </div>

</body>
</html>