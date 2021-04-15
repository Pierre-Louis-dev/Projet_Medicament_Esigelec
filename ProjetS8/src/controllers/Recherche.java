package controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.DAOfactory;
import project.DTO_CIS_bdpm;
import project.DTO_Personnel;
import project.HistoriqueRecherche;
import project.PersonnelDAO;

/**
 * Servlet implementation class Recherche
 */
@WebServlet("/Recherche")
public class Recherche extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Recherche() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * Barre de recherches pour m�dicaments commercialis�s
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOfactory dao = new DAOfactory();
		HttpSession session = request.getSession();
		session.setAttribute("commercialisesVide", null);
		int nb = Integer.parseInt(request.getParameter("num"));
		Date now = new Date();
		DateFormat date = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
		HistoriqueRecherche his = new HistoriqueRecherche();
		
		//Cas non connecté
		if(session.getAttribute("actif")==null) {
			if(!request.getParameter("nom").isEmpty()) {
				String nom = request.getParameter("nom");
				ArrayList<DTO_CIS_bdpm> medicaments = dao.getListeSearchingMedicamentsCommercialises(nom,nb) ;
				if(medicaments.size()==0)
					session.setAttribute("commercialisesVide", 1);		
				session.setAttribute("commercialises", medicaments);
				his.ajouterLigneHist(date.format(now), "Malade", "A Recherché "+nom);
				response.sendRedirect("Commercialises.jsp");
					
			}else if(!request.getParameter("tri").isEmpty()) {
				String nom = request.getParameter("tri");
				ArrayList<DTO_CIS_bdpm> medicaments = dao.getListeSortedSearchingMedicamentsCommercialises(nom,nb) ;
				if(medicaments.size()==0)
					session.setAttribute("commercialisesVide", 1);		
				session.setAttribute("commercialises", medicaments);
				his.ajouterLigneHist(date.format(now), "Malade", "A Recherché "+nom);
				response.sendRedirect("Commercialises.jsp");
				
			}else {
				session.setAttribute("commercialisesVide", 1);
				response.sendRedirect("Commercialises.jsp");
			}
		}
		//Cas connecté
		else {
			DTO_Personnel connecte = (DTO_Personnel)session.getAttribute("PersonneConnectee");
			PersonnelDAO pdao = new PersonnelDAO();
			DTO_Personnel personne = pdao.getUtilisateur(connecte.getAttributes().get("login"));
			
			//cas où le compte n'est pas désactivé
			if(personne != null && personne.getAttributes().get("statut").contentEquals("autorise")) {
				if(!request.getParameter("nom").isEmpty()) {
					String nom = request.getParameter("nom");
					ArrayList<DTO_CIS_bdpm> medicaments = dao.getListeSearchingMedicamentsCommercialises(nom,nb) ;
					if(medicaments.size()==0)
						session.setAttribute("commercialisesVide", 1);		
					session.setAttribute("commercialises", medicaments);
					his.ajouterLigneHist(date.format(now), personne.getAttributes().get("login"), "A Recherché "+nom);
					response.sendRedirect("Commercialises.jsp");
						
				}else if(!request.getParameter("tri").isEmpty()) {
					String nom = request.getParameter("tri");
					ArrayList<DTO_CIS_bdpm> medicaments = dao.getListeSortedSearchingMedicamentsCommercialises(nom,nb) ;
					if(medicaments.size()==0)
						session.setAttribute("commercialisesVide", 1);		
					session.setAttribute("commercialises", medicaments);
					his.ajouterLigneHist(date.format(now), personne.getAttributes().get("login"), "A Recherché "+nom);
					response.sendRedirect("Commercialises.jsp");
					
				}else {
					session.setAttribute("commercialisesVide", 1);
					response.sendRedirect("Commercialises.jsp");
				}
			}
			//Cas où le compte est désactivé
			else {
				session.invalidate();
				response.sendRedirect("Index.jsp");
			}
		}			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * Barre de recherches pour m�dicaments non commercialis�s
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOfactory dao = new DAOfactory();
		HttpSession session = request.getSession();
		session.setAttribute("arretesVide", null);
		int nb = Integer.parseInt(request.getParameter("num"));
		Date now = new Date();
		DateFormat date = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
		HistoriqueRecherche his = new HistoriqueRecherche();
		
		//Cas non connecté
		if(session.getAttribute("actif")==null) {
			if(!request.getParameter("nom").isEmpty()) {
				String nom = request.getParameter("nom");
				ArrayList<DTO_CIS_bdpm> medicaments = dao.getListeSearchingMedicamentsNonCommercialises(nom,nb) ;
				if(medicaments.size()==0)
					session.setAttribute("arretesVide", 1);		
				session.setAttribute("arretes", medicaments);
				his.ajouterLigneHist(date.format(now), "Malade", "A Recherché "+nom);
				response.sendRedirect("Arret.jsp");
					
			}else if(!request.getParameter("tri").isEmpty()) {
				String nom = request.getParameter("tri");
				ArrayList<DTO_CIS_bdpm> medicaments = dao.getListeSortedSearchingMedicamentsNonCommercialises(nom,nb) ;
				if(medicaments.size()==0)
					session.setAttribute("arretesVide", 1);		
				session.setAttribute("arretes", medicaments);
				his.ajouterLigneHist(date.format(now), "Malade", "A Recherché "+nom);
				response.sendRedirect("Arret.jsp");
				
			}else {
				session.setAttribute("arretesVide", 1);
				response.sendRedirect("Arret.jsp");
			}
		}
		//Cas connecté
		else {
			DTO_Personnel connecte = (DTO_Personnel)session.getAttribute("PersonneConnectee");
			PersonnelDAO pdao = new PersonnelDAO();
			DTO_Personnel personne = pdao.getUtilisateur(connecte.getAttributes().get("login"));
			
			//cas où le compte n'est pas désactivé
			if(personne != null && personne.getAttributes().get("statut").contentEquals("autorise")) {
				if(!request.getParameter("nom").isEmpty()) {
					String nom = request.getParameter("nom");
					ArrayList<DTO_CIS_bdpm> medicaments = dao.getListeSearchingMedicamentsNonCommercialises(nom,nb) ;
					if(medicaments.size()==0)
						session.setAttribute("arretesVide", 1);		
					session.setAttribute("arretes", medicaments);
					his.ajouterLigneHist(date.format(now), personne.getAttributes().get("login"), "A Recherché "+nom);
					response.sendRedirect("Arret.jsp");
						
				}else if(!request.getParameter("tri").isEmpty()) {
					String nom = request.getParameter("tri");
					ArrayList<DTO_CIS_bdpm> medicaments = dao.getListeSortedSearchingMedicamentsNonCommercialises(nom,nb) ;
					if(medicaments.size()==0)
						session.setAttribute("arretesVide", 1);		
					session.setAttribute("arretes", medicaments);
					his.ajouterLigneHist(date.format(now), personne.getAttributes().get("login"), "A Recherché "+nom);
					response.sendRedirect("Arret.jsp");
					
				}else {
					session.setAttribute("arretesVide", 1);
					response.sendRedirect("Arret.jsp");
				}
			}
			//Cas où le compte est désactivé
			else {
				session.invalidate();
				response.sendRedirect("Index.jsp");
			}
		}			
	}

}
