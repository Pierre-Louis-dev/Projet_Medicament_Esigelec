<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, user-scalable=no">
	<title>Demande d'inscription</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="projet.css">
	<link rel="stylesheet" href="chatBox.css">	
</head>
<body class="body">
	<jsp:include page="nav.jsp"></jsp:include>
	<div class="container">
        <div class="row">
            <div class="col-3">
            </div>
            <div class="col-6">
            
            <form class="aligne" method="get" action="MdpOublier" style="margin:150px auto; padding:20px; border:8px double #242424; background-color:#ffffff;">
                <div class="row">                    
                    <img src="mdp.PNG" alt="LOGO" width ="500" class="img-fluid">                                                           
                </div>                    
                <br>
                <%if(session.getAttribute("IdInvalide")!=null){ %>
	                <div class="alert alert-danger" role="alert">
						  Cet Id existe pas.
					</div>
				 <%}else if(session.getAttribute("MdpNonConform")!=null){ %>
				 	<div class="alert alert-danger" role="alert">
						  Les deux mots de passe doivent être identiques.
					</div>
				 <%}else if(session.getAttribute("modifier")!=null) {%>
					<div class="alert alert-success" role="alert">
						Votre mot de passe a été modifier avec succes
					</div>
				 <%session.setAttribute("modifier", null);  } %>
         
                <div class="form-group col-md-12 ">
                       

                    <div class="form-group col-md-12">
                        <label>IDENTIFIANT</label>
                        <input type="int" class="form-control" id="ID" placeholder="ID" name="ID" required>
                    </div>

                    <div class="form-group col-md-12">
                        <label for="password">Mot de passe</label>
                        <input type="password" class="form-control" id="password" placeholder="Mot De Passe" name="password" required>
                    </div>

                    <div class="form-group col-md-12">
                        <label for="cpassword"> Confirmer le mot de passe</label>
                        <input type="password" class="form-control" id="cpassword" placeholder="Saisir à nouveau le mot de passe" name="cpassword" required>
                    </div>
                   
                    <div class="row">
                        <button type="submit" class="btn btn-primary aligne" >Valider</button>
                    </div>
                    
                <br>
            </form>
            </div>
            <div class="col-3">
            </div>
        </div>
    </div>

</body>
</html>