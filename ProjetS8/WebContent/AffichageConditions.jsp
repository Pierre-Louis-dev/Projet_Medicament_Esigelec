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
	<title>Conditions</title>
</head>
<body>
	<jsp:include page="nav.jsp"></jsp:include>
	<br><br>
	<h1 class="aligne">Conditions de ce médicament</h1>
	<table class="table table-striped">
	  <thead>
	    <tr>
	      <th scope="col">Code cis</th>
	      <th scope="col">Conditions</th>
	    </tr>
	    </thead>
	    <tbody>
	    <%if(session.getAttribute("affichageConditions")!=null){ %>
			<%for (DTO_CIS_CPD_bdpm e : (ArrayList<DTO_CIS_CPD_bdpm>)session.getAttribute("affichageConditions")){ %>
			<tr>
		      <th scope="row"><%=e.getAttributes().get("cis") %></th>
		      <td><%=e.getAttributes().get("cpd") %></td>
		    </tr>
			<%} %>
		<%} %>
	  </tbody>
	</table>
</body>
</html>

 
  
    
