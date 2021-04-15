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
 * Servlet implementation class GroupeGenerMed
 */
@WebServlet("/GroupeGenerMed")
public class GroupeGenerMed extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupeGenerMed() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * Recherche pour avoir la liste des groupes génériques
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOfactory dao = new DAOfactory();
		HttpSession session = request.getSession();
		session.setAttribute("groupesVide", null);
		int range = Integer.parseInt(request.getParameter("num"));
		
		DTO_Personnel connecte = (DTO_Personnel)session.getAttribute("PersonneConnectee");
		PersonnelDAO pdao = new PersonnelDAO();
		DTO_Personnel personne = pdao.getUtilisateur(connecte.getAttributes().get("login"));
		Date now = new Date();
		DateFormat date = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
		HistoriqueRecherche his = new HistoriqueRecherche();
		
		
		//cas où le compte n'est pas désactivé
		if(personne != null && personne.getAttributes().get("statut").contentEquals("autorise")) {
			if(!request.getParameter("nom").isEmpty()) {
				String nom = request.getParameter("nom");
				ArrayList<DTO_CIS_GENER_bdpm> groupes = dao.getListeSearchingGroupeGenerique(nom,range);
				if(groupes.size()==0)
					session.setAttribute("groupesVide", 1);		
				session.setAttribute("groupes", groupes);
				his.ajouterLigneHist(date.format(now), personne.getAttributes().get("login"), "A Recherché "+nom);
				response.sendRedirect("GroupeGenerique.jsp");
					
			}else if(!request.getParameter("tri").isEmpty()) {
				String nom = request.getParameter("tri");
				ArrayList<DTO_CIS_GENER_bdpm> groupes = dao.getListeSortedSearchingGroupeGenerique(nom,range);
				if(groupes.size()==0)
					session.setAttribute("groupesVide", 1);		
				session.setAttribute("groupes", groupes);
				his.ajouterLigneHist(date.format(now), personne.getAttributes().get("login"), "A Recherché "+nom);
				response.sendRedirect("GroupeGenerique.jsp");
				
			}else {
				session.setAttribute("groupesVide", 1);
				response.sendRedirect("GroupeGenerique.jsp");
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
	 * Recherche et affichage des médicaments appartenant à un groupe générique donné
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session =request.getSession();
		DTO_Personnel connecte = (DTO_Personnel)session.getAttribute("PersonneConnectee");
		PersonnelDAO pdao = new PersonnelDAO();
		DTO_Personnel personne = pdao.getUtilisateur(connecte.getAttributes().get("login"));
		
		//cas où le compte n'est pas désactivé
		if(personne != null && personne.getAttributes().get("statut").contentEquals("autorise")) {
			DAOfactory dao = new DAOfactory();
			int id = Integer.parseInt(request.getParameter("id"));
			session.setAttribute("MedGeneriques", null);
			ArrayList<DTO_CIS_bdpm> medicaments = dao.getListeSearchByIdGroupeGenerique(id);
			if(medicaments.size() != 0) {
				session.setAttribute("MedGeneriques", medicaments);
			}		
			response.sendRedirect("DetailsGroupe.jsp");
		}
		//Cas où le compte est désactivé
		else {
			session.invalidate();
			response.sendRedirect("Index.jsp");
		}		
	}

}
