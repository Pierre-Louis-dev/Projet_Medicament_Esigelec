package controllers;

import project.*;
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

/**
 * Servlet implementation class Test
 */
@WebServlet("/ConditionsPrescriptionDelivrance")
public class ConditionsPrescriptionDelivrance extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConditionsPrescriptionDelivrance() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * Rechercher et affocher la liste des médicaments dont on veut connaitre les conditions de prescriptions et de délivrance
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOfactory dao = new DAOfactory();
		HttpSession session = request.getSession();		
		DTO_Personnel connecte = (DTO_Personnel)session.getAttribute("PersonneConnectee");
		PersonnelDAO pdao = new PersonnelDAO();
		DTO_Personnel personne = pdao.getUtilisateur(connecte.getAttributes().get("login"));
		Date now = new Date();
		DateFormat date = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
		HistoriqueRecherche his = new HistoriqueRecherche();
		
		//cas où le compte n'est pas désactivé
		if(personne != null && personne.getAttributes().get("statut").contentEquals("autorise")) {
			session.setAttribute("conditionsVide", null);
			int nb = Integer.parseInt(request.getParameter("num"));
			
			if(!request.getParameter("nom").isEmpty()) {
				String nom = request.getParameter("nom");
				ArrayList<DTO_CIS_bdpm> medicaments = dao.getListeSearchingMedicaments(nom,nb) ;
				if(medicaments.size()==0)
					session.setAttribute("conditionsVide", 1);		
				session.setAttribute("conditions", medicaments);
				his.ajouterLigneHist(date.format(now), personne.getAttributes().get("login"), "A Recherché "+nom);
				response.sendRedirect("Conditions.jsp");
				
			}else if(!request.getParameter("tri").isEmpty()) {
				String nom = request.getParameter("tri");
				ArrayList<DTO_CIS_bdpm> medicaments = dao.getListeSortedSearchingMedicaments(nom,nb) ;
				if(medicaments.size()==0)
					session.setAttribute("conditionsVide", 1);		
				session.setAttribute("conditions", medicaments);
				his.ajouterLigneHist(date.format(now), personne.getAttributes().get("login"), "A Recherché "+nom);
				response.sendRedirect("Conditions.jsp");
				
			}else {
				session.setAttribute("conditionsVide", 1);
				response.sendRedirect("Conditions.jsp");
			}
		}
		//Cas où le compte est désactivé
		else {
			session.invalidate();
			response.sendRedirect("Index.jsp");
		}
		
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * Rechercher et afficher les CPD d'un médicament donné
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session =request.getSession();
		DTO_Personnel connecte = (DTO_Personnel)session.getAttribute("PersonneConnectee");
		PersonnelDAO pdao = new PersonnelDAO();
		DTO_Personnel personne = pdao.getUtilisateur(connecte.getAttributes().get("login"));
		
		//cas où le compte n'est pas désactivé
		if(personne != null && personne.getAttributes().get("statut").contentEquals("autorise")) {
			int cis = Integer.parseInt(request.getParameter("cis"));
	        DAOfactory dao =new DAOfactory();
	        ArrayList<DTO_CIS_CPD_bdpm> conditions = dao.getConditionsPrescriptionDelivrance(cis);
	        session.setAttribute("affichageConditions", conditions);
	        response.sendRedirect("AffichageConditions.jsp");
		}
		//Cas où le compte est désactivé
		else {
			session.invalidate();
			response.sendRedirect("Index.jsp");
		}	        
	}

}
