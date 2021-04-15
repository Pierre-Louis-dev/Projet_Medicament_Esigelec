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
 * Servlet implementation class Test2
 */
@WebServlet("/Test2")
public class Test2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Test2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * Avoir les avis SMR d'un médicaments sélectionné
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session =request.getSession();
		DTO_Personnel connecte = (DTO_Personnel)session.getAttribute("PersonneConnectee");
		PersonnelDAO pdao = new PersonnelDAO();
		DTO_Personnel personne = pdao.getUtilisateur(connecte.getAttributes().get("login"));
		
		
		//cas où le compte n'est pas désactivé
		if(personne != null && personne.getAttributes().get("statut").contentEquals("autorise")) {
			int cis = Integer.parseInt(request.getParameter("cis"));
			DAOfactory dao =new DAOfactory();
			ArrayList<DTO_CIS_HAS_SMR_bdpm> avis = dao.getAvisSMR(cis);
			session.setAttribute("avisSMR", avis);
			response.sendRedirect("AvisSMR.jsp");
		}
		//Cas où le compte est désactivé
		else {
			session.invalidate();
			response.sendRedirect("Index.jsp");
		}		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * Recherche pour avoir la liste des médicaments dont on pourra afficher les avis
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			session.setAttribute("avisSMRVide", null);
			int nb = Integer.parseInt(request.getParameter("num"));
			if(!request.getParameter("nom").isEmpty()) {
				String nom = request.getParameter("nom");
				ArrayList<DTO_CIS_bdpm> medicaments = dao.getListeSearchingMedicaments(nom,nb) ;
				if(medicaments.size()==0)
					session.setAttribute("avisSMRVide", 1);		
				session.setAttribute("avis", medicaments);
				his.ajouterLigneHist(date.format(now), personne.getAttributes().get("login"), "A Recherché "+nom);
				response.sendRedirect("ConsultationAvisHAS_SMR.jsp");
					
			}else if(!request.getParameter("tri").isEmpty()) {
				String nom = request.getParameter("tri");
				ArrayList<DTO_CIS_bdpm> medicaments = dao.getListeSortedSearchingMedicaments(nom,nb) ;
				if(medicaments.size()==0)
					session.setAttribute("avisSMRVide", 1);		
				session.setAttribute("avis", medicaments);
				his.ajouterLigneHist(date.format(now), personne.getAttributes().get("login"), "A Recherché "+nom);
				response.sendRedirect("ConsultationAvisHAS_SMR.jsp");
				
			}else {
				session.setAttribute("avisSMRVide", 1);
				response.sendRedirect("ConsultationAvisHAS_SMR.jsp");
			}
		}
		//Cas où le compte est désactivé
		else {
			session.invalidate();
			response.sendRedirect("Index.jsp");
		}		

	}
	

}
