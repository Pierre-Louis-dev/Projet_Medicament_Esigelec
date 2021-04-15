package controllers;

import project.*;
import java.io.IOException;
import java.net.InetAddress;
import java.text.DateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 * Servlet implementation class Identification
 */
@WebServlet("/Identification")
public class Identification extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Identification() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * Connexion
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		PersonnelDAO dao = new PersonnelDAO();
		
		//Fichier de logs
		Enregistrement record = new Enregistrement();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
		String date = dateFormat.format(new Date());
		String ip = (String)InetAddress.getLocalHost().getHostAddress();
		
		
		session.setAttribute("MauvaisIdentifiants", null);
		session.setAttribute("refuse", null);
		session.setAttribute("PersonneConnectee", null);
		session.setAttribute("actif", null);
		String login = request.getParameter("login");
		String mdp = request.getParameter("password");
		
		@SuppressWarnings("unused")
		String crypt=BCrypt.hashpw(mdp, BCrypt.gensalt());
		
		
		if(dao.Connexion(login, mdp)) {
			DTO_Personnel connecte = dao.getUtilisateur(login);
			if(connecte.getAttributes().get("statut").contentEquals("autorise")) {
				session.setAttribute("actif", 1);
				session.setAttribute("PersonneConnectee", connecte);
				record.ajouterLigne(date, login, ip, "Connexion réussie");
				response.sendRedirect("Index.jsp");
			}else if(connecte.getAttributes().get("statut").contentEquals("refuse")) {
				session.setAttribute("refuse", 1);
				record.ajouterLigne(date, login, ip, "Connexion refusée: Compte non validé");
				response.sendRedirect("Connexion.jsp");
			}
			
		}else {
			session.setAttribute("MauvaisIdentifiants", 1);
			record.ajouterLigne(date, login, ip, "Connexion échouée : Mauvais identifiants");
			response.sendRedirect("Connexion.jsp");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * Deconnexion
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		//Fichier de logs
		Enregistrement record = new Enregistrement();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
		String date = dateFormat.format(new Date());
		String ip = (String)InetAddress.getLocalHost().getHostAddress();
		DTO_Personnel personne = (DTO_Personnel)session.getAttribute("PersonneConnectee");
		String login = personne.getAttributes().get("login");
		
		record.ajouterLigne(date, login, ip, "Deconnexion");
		session.invalidate();
		response.sendRedirect("Index.jsp");
	}

}
