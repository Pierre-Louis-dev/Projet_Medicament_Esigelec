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
@WebServlet("/HistoRecherche")
public class HistoRecherche extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HistoRecherche() {
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
		HistoriqueRecherche record = new HistoriqueRecherche();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
		String date = dateFormat.format(new Date());
		String ip = (String)InetAddress.getLocalHost().getHostAddress();
		
		
		session.setAttribute("RechercheCommercialise", null);
		session.setAttribute("RechercheArretCommercialise", null);
		session.setAttribute("RechercheCompo", null);
		session.setAttribute("RechercheGroupeGener", null);
		session.setAttribute("RechercheAvis", null);
		session.setAttribute("RechercheInfos", null);
		session.setAttribute("RechercheListe", null);
		session.setAttribute("RechercheCondition", null);
		session.setAttribute("RechercheStatistique", null);
		
		String login = request.getParameter("login");
		
		
		/*if(session.getAttribute("RechercheCommercialise")!=null) {
			record.ajouterLigneHist(date, login, ip, "Recherche d'un médicament commercialisé");
			session.setAttribute("RechercheCommercialise", null);
		}else if(session.getAttribute("RechercheArretCommercialise")!=null) {
			record.ajouterLigneHist(date, login, ip, "Recherche d'un médicament en arret de commercialisation");
			session.setAttribute("RechercheArretCommercialise", null);
		}else if(session.getAttribute("RechercheCompo")!=null) {
			record.ajouterLigneHist(date, login, ip, "Recherche de la composition d'un medicament");
			session.setAttribute("RechercheCompo", null);
		}else if(session.getAttribute("RechercheGroupeGener")!=null) {
			record.ajouterLigneHist(date, login, ip, "Recherche du groupe générique d'un medicament");
			session.setAttribute("RechercheGroupeGener", null);
		}else if(session.getAttribute("RechercheAvis")!=null) {
			record.ajouterLigneHist(date, login, ip, "Recherche d'un avis d'un medicament");
			session.setAttribute("RechercheAvis", null);
		}else if(session.getAttribute("RechercheInfos")!=null) {
			record.ajouterLigneHist(date, login, ip, "Recherche d'informations d'un medicament");
			session.setAttribute("RechercheInfos", null);
		}else if(session.getAttribute("RechercheListe")!=null) {
			record.ajouterLigneHist(date, login, ip, "Recherche de la liste des liens d'un medicament");
			session.setAttribute("RechercheListe", null);
		}else if(session.getAttribute("RechercheCondition")!=null) {
			record.ajouterLigneHist(date, login, ip, "Recherche des conditions d'un medicament");
			session.setAttribute("RechercheCondition", null);
		}else if(session.getAttribute("RechercheStatistique")!=null) {
			record.ajouterLigneHist(date, login, ip, "Affichage des statistiques sur les medicaments");
			session.setAttribute("RechercheStatistique", null);
		}*/
	}

}
