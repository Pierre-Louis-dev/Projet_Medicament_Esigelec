<%@page import="project.*"%>
<%@page import="java.util.ArrayList" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="Index.jsp">
	<img src = "pills.jpg" alt="Logo" width="100" height="50">
  </a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarText">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="Index.jsp">Home <span class="sr-only">(current)</span></a>
      </li>
      <%if(session.getAttribute("actif")!=null){ %>
      <li class="nav-item">
      <%project.DTO_Personnel e = (project.DTO_Personnel) session.getAttribute("PersonneConnectee"); %>
        <a class="nav-link">Connecté en tant que <%=e.getAttributes().get("login") %></a>
      </li>
      <%} %>
    </ul>
    <span class="navbar-text">
      	<div class="btn-group" role="group" aria-label="Basic example">
      		<% if (session.getAttribute("actif") == null) {%>
			<a href="Connexion.jsp" type="button" class="btn btn-outline-danger">Connexion</a>
			<a href="Inscription.jsp" type="button" class="btn btn-outline-primary">Inscription</a>
			<a href="Suividemande.jsp" type="button" class="btn btn-outline-secondary">Suivi de demande</a>
			<%}else { %>
				<form class="form-inline" action="Identification" method="post">
				  <button type="submit" class="btn btn-outline-danger">Deconnexion</button>
				</form>
				<a href="NouveauMdp.jsp" type="button" class="btn btn-outline-secondary">Changer Mot de passe</a>
			<%} %>
		</div>
    </span>
  </div>
</nav>
 
  
	<div id="chat-circle" class="btn btn-raised">
	    <div id="chat-overlay"></div>
		<i class="material-icons">Aide</i>
	</div>
	  
	 <div class="chat-box">
	   <div class="chat-box-header">
	     Questions
	     <span class="chat-box-toggle"><i class="material-icons">Quitter</i></span>
	     
	   </div>
	   
	   <div class="chat-box-body">
	     <div class="chat-box-overlay"></div>
	     
	   	<%
	   		if(session.getAttribute("messages")!=null){
	   			ArrayList<DTO_Messages> messages = (ArrayList<DTO_Messages>) session.getAttribute("messages");
	   			for(DTO_Messages m : messages){ %>
	   				<%if (m.getAttributes().get("provenance").equals("Malade")){ %>
	   					<div class="alert alert-primary" role="alert">
	   				<%}else{ %>
	   					<div class="alert alert-danger" role="alert">
	   				<%} %>
	   				<%=m.getAttributes().get("texte") %>
	   				</div>   				
	   	<%}} %>
	     
	   </div>
	   
	   <div class="chat-input">      
	      <form method="GET" action="Chat">
	      	<input type="text" name="url" value=<%=request.getRequestURL().toString() %> hidden>
	        <input type="text" id="chat-input" placeholder="Posez votre question ici" name="chatText" required>
	        <button type="submit" class="chat-submit">Send</button>
	      </form> 
	      <form method="post" action="Chat">
	      	<input type="text" name="url" value=<%=request.getRequestURL().toString() %> hidden>
	     	<button type="submit" class="btn btn-light">Refresh</button>
	     </form>   
	   </div>
	 </div>
		  
		  
	<script>
		  $(function() {
		  var INDEX = 0; 
		  $("#chat-submit").click(function(e) {
		    e.preventDefault();
		    var msg = $("#chat-input").val(); 
		    if(msg.trim() == ''){
		      return false;
		    }
		    generate_message(msg, 'self');
		    var buttons = [
		        {
		          name: 'Existing User',
		          value: 'existing'
		        },
		        {
		          name: 'New User',
		          value: 'new'
		        }
		      ];
		    setTimeout(function() {      
		      generate_message(msg, 'user');  
		    }, 1000)
		    
		  })
		  
		  function generate_message(msg, type) {
		    INDEX++;
		    var str="";
		    str += "<div id='cm-msg-"+INDEX+"' class=\"chat-msg "+type+"\">";
		    str += "          <span class=\"msg-avatar\">";
		    str += "            <img src=\"https:\/\/image.crisp.im\/avatar\/operator\/196af8cc-f6ad-4ef7-afd1-c45d5231387c\/240\/?1483361727745\">";
		    str += "          <\/span>";
		    str += "          <div class=\"cm-msg-text\">";
		    str += msg;
		    str += "          <\/div>";
		    str += "        <\/div>";
		    $(".chat-logs").append(str);
		    $("#cm-msg-"+INDEX).hide().fadeIn(300);
		    if(type == 'self'){
		     $("#chat-input").val(''); 
		    }    
		    $(".chat-logs").stop().animate({ scrollTop: $(".chat-logs")[0].scrollHeight}, 1000);    
		  }  
		  
		  function generate_button_message(msg, buttons){    
		    /* Buttons should be object array 
		      [
		        {
		          name: 'Existing User',
		          value: 'existing'
		        },
		        {
		          name: 'New User',
		          value: 'new'
		        }
		      ]
		    */
		    INDEX++;
		    var btn_obj = buttons.map(function(button) {
		       return  "              <li class=\"button\"><a href=\"javascript:;\" class=\"btn btn-primary chat-btn\" chat-value=\""+button.value+"\">"+button.name+"<\/a><\/li>";
		    }).join('');
		    var str="";
		    str += "<div id='cm-msg-"+INDEX+"' class=\"chat-msg user\">";
		    str += "          <span class=\"msg-avatar\">";
		    str += "            <img src=\"https:\/\/image.crisp.im\/avatar\/operator\/196af8cc-f6ad-4ef7-afd1-c45d5231387c\/240\/?1483361727745\">";
		    str += "          <\/span>";
		    str += "          <div class=\"cm-msg-text\">";
		    str += msg;
		    str += "          <\/div>";
		    str += "          <div class=\"cm-msg-button\">";
		    str += "            <ul>";   
		    str += btn_obj;
		    str += "            <\/ul>";
		    str += "          <\/div>";
		    str += "        <\/div>";
		    $(".chat-logs").append(str);
		    $("#cm-msg-"+INDEX).hide().fadeIn(300);   
		    $(".chat-logs").stop().animate({ scrollTop: $(".chat-logs")[0].scrollHeight}, 1000);
		    $("#chat-input").attr("disabled", true);
		  }
		  
		  $(document).delegate(".chat-btn", "click", function() {
		    var value = $(this).attr("chat-value");
		    var name = $(this).html();
		    $("#chat-input").attr("disabled", false);
		    generate_message(name, 'self');
		  })
		  
		  $("#chat-circle").click(function() {    
		    $("#chat-circle").toggle('scale');
		    $(".chat-box").toggle('scale');
		  })
		  
		  $(".chat-box-toggle").click(function() {
		    $("#chat-circle").toggle('scale');
		    $(".chat-box").toggle('scale');
		  })
		  
		})
	</script>
</div>