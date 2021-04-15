<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<meta charset="UTF-8">
	<link rel="stylesheet" href="projet.css">
	<link rel="stylesheet" href="chatBox.css">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=yes">
	<title>Statistiques des médicaments</title>
</head>
<body>
<jsp:include page="nav.jsp"></jsp:include>
<br>
<div class="container aligne">
<div>Voici des statistiques sur les caractéristiques des médicaments :</div>
<iframe width="600" height="450" src="file:///C:/Users/minho/Desktop/groupe4_medicament2/Workspace_Projet/ProjetS8/Stats_Medoc.pdf"></iframe>
<iframe width="600" height="450" src="file:///C:/Users/minho/Desktop/groupe4_medicament2/Workspace_Projet/ProjetS8/Stat_Prix.pdf"></iframe>

<div>Voici des statistiques intéractive sur les caractéristiques des médicaments (vous devez possèder un compte pour pouvoir voir les rapports intéractifs)</div>

<iframe width="600" height="450" src="https://app.powerbi.com/reportEmbed?reportId=2d12760a-51f4-4b4f-8191-91d39ce8f8c8&autoAuth=true&ctid=371cb156-9558-4286-a3cd-3059699b890c&config=eyJjbHVzdGVyVXJsIjoiaHR0cHM6Ly93YWJpLWV1cm9wZS1ub3J0aC1iLXJlZGlyZWN0LmFuYWx5c2lzLndpbmRvd3MubmV0LyJ9" frameborder="0" allowFullScreen="true"></iframe>
<iframe width="600" height="450" src="https://app.powerbi.com/reportEmbed?reportId=cc48e75f-75f1-471f-bb94-fd7c78e69dda&autoAuth=true&ctid=371cb156-9558-4286-a3cd-3059699b890c&config=eyJjbHVzdGVyVXJsIjoiaHR0cHM6Ly93YWJpLWV1cm9wZS1ub3J0aC1iLXJlZGlyZWN0LmFuYWx5c2lzLndpbmRvd3MubmV0LyJ9" frameborder="0" allowFullScreen="true"></iframe>
<iframe width="1140" height="541.25" src="https://app.powerbi.com/reportEmbed?reportId=cc48e75f-75f1-471f-bb94-fd7c78e69dda&autoAuth=true&ctid=371cb156-9558-4286-a3cd-3059699b890c&config=eyJjbHVzdGVyVXJsIjoiaHR0cHM6Ly93YWJpLWV1cm9wZS1ub3J0aC1iLXJlZGlyZWN0LmFuYWx5c2lzLndpbmRvd3MubmV0LyJ9" frameborder="0" allowFullScreen="true"></iframe>
</div>
</body>
</html>








