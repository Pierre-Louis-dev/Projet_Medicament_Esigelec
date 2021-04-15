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

import project.*;

/**
 * Servlet implementation class CompositionController
 */
@WebServlet("/CompositionController")
public class CompositionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompositionController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * Compositon d'un médicament donné
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		DAOfactory dao = new DAOfactory();
		session.setAttribute("substanceVide", null);
		int cis = Integer.parseInt(request.getParameter("cis"));
		boolean sa = request.getParameter("substanceActive") != null;
		boolean ft = request.getParameter("fractionTherapeutique") != null;
				
		//Cas non connecté
		if(session.getAttribute("actif")==null) {
			if(sa && ft) {
				ArrayList<DTO_CIS_COMPO_bdpm> compositions = dao.getCompositions(cis);
				session.setAttribute("listeCompositions", compositions);
			}else if(sa) {
				ArrayList<DTO_CIS_COMPO_bdpm> compositions = dao.getSubstances(cis,"SA");
				session.setAttribute("listeCompositions", compositions);
			}else if(ft) {
				ArrayList<DTO_CIS_COMPO_bdpm> compositions = dao.getSubstances(cis,"FT");
				session.setAttribute("listeCompositions", compositions);
			}else {
				session.setAttribute("listeCompositions", null);
			}
			response.sendRedirect("Substances.jsp");
		}
		//Cas connecté
		else {
			DTO_Personnel connecte = (DTO_Personnel)session.getAttribute("PersonneConnectee");
			PersonnelDAO pdao = new PersonnelDAO();
			DTO_Personnel personne = pdao.getUtilisateur(connecte.getAttributes().get("login"));
			
			//cas où le compte n'est pas désactivé
			if(personne != null && personne.getAttributes().get("statut").contentEquals("autorise")) {
				if(sa && ft) {
					ArrayList<DTO_CIS_COMPO_bdpm> compositions = dao.getCompositions(cis);
					session.setAttribute("listeCompositions", compositions);
				}else if(sa) {
					ArrayList<DTO_CIS_COMPO_bdpm> compositions = dao.getSubstances(cis,"SA");
					session.setAttribute("listeCompositions", compositions);
				}else if(ft) {
					ArrayList<DTO_CIS_COMPO_bdpm> compositions = dao.getSubstances(cis,"FT");
					session.setAttribute("listeCompositions", compositions);
				}else {
					session.setAttribute("listeCompositions", null);
				}
				response.sendRedirect("Substances.jsp");
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
	 * Rechercher et afficher la liste des médicaments dont on veut afficher la composition
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOfactory dao = new DAOfactory();
		HttpSession session = request.getSession();
		session.setAttribute("compositionsVide", null);
		int nb = Integer.parseInt(request.getParameter("num"));
		Date now = new Date();
		DateFormat date = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
		HistoriqueRecherche his = new HistoriqueRecherche();
		
		//Cas non connecté
		if(session.getAttribute("actif")==null) {
			if(!request.getParameter("nom").isEmpty()) {
				String nom = request.getParameter("nom");
				ArrayList<DTO_CIS_bdpm> medicaments = dao.getListeSearchingMedicaments(nom,nb) ;
				if(medicaments.size()==0)
					session.setAttribute("compositionsVide", 1);		
				session.setAttribute("compositions", medicaments);
				his.ajouterLigneHist(date.format(now), "Malade", "A Recherché "+nom);
				response.sendRedirect("Compositions.jsp");
					
			}else if(!request.getParameter("tri").isEmpty()) {
				String nom = request.getParameter("tri");
				ArrayList<DTO_CIS_bdpm> medicaments = dao.getListeSortedSearchingMedicaments(nom,nb) ;
				if(medicaments.size()==0)
					session.setAttribute("compositionsVide", 1);		
				session.setAttribute("compositions", medicaments);
				his.ajouterLigneHist(date.format(now), "Malade", "A Recherché "+nom);
				response.sendRedirect("Compositions.jsp");
				
			}else {
				session.setAttribute("compositionsVide", 1);
				response.sendRedirect("Compositions.jsp");
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
					ArrayList<DTO_CIS_bdpm> medicaments = dao.getListeSearchingMedicaments(nom,nb) ;
					if(medicaments.size()==0)
						session.setAttribute("compositionsVide", 1);		
					session.setAttribute("compositions", medicaments);
					his.ajouterLigneHist(date.format(now), personne.getAttributes().get("login"), "A Recherché "+nom);
					response.sendRedirect("Compositions.jsp");
						
				}else if(!request.getParameter("tri").isEmpty()) {
					String nom = request.getParameter("tri");
					ArrayList<DTO_CIS_bdpm> medicaments = dao.getListeSortedSearchingMedicaments(nom,nb) ;
					if(medicaments.size()==0)
						session.setAttribute("compositionsVide", 1);		
					session.setAttribute("compositions", medicaments);
					his.ajouterLigneHist(date.format(now), personne.getAttributes().get("login"), "A Recherché "+nom);
					response.sendRedirect("Compositions.jsp");
					
				}else {
					session.setAttribute("compositionsVide", 1);
					response.sendRedirect("Compositions.jsp");
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
