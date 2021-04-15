package controllers;

import project.*;
import java.util.ArrayList;
import java.util.Date;
import java.io.IOException;
import java.text.DateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LiensPage
 */
@WebServlet("/LiensPage")
public class LiensPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LiensPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * Rechercher et afficher la liste des liens correspondants à un médicament donné 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session =request.getSession();
		DTO_Personnel connecte = (DTO_Personnel)session.getAttribute("PersonneConnectee");
		PersonnelDAO pdao = new PersonnelDAO();
		DTO_Personnel personne = pdao.getUtilisateur(connecte.getAttributes().get("login"));
		
		//cas où le compte n'est pas désactivé
		if(personne != null && personne.getAttributes().get("statut").contentEquals("autorise")) {
			DAOfactory dao = new DAOfactory();
			int cis = Integer.parseInt(request.getParameter("cis"));
			String codeDossier = dao.getCodeDossierHAS(cis);
			DTO_HAS_LiensPageCT_bdpm lien = dao.getLien(codeDossier);
			session.setAttribute("lienHAS", lien);
			session.setAttribute("fromSMR", true);
			response.sendRedirect("ListeLiens.jsp");
		}
		//Cas où le compte est désactivé
		else {
			session.invalidate();
			response.sendRedirect("Index.jsp");
				}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * Methode pour récuperer la liste des médicaments dans la barre de recherche de la vue de la page des liens
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		DAOfactory dao = new DAOfactory();
		
		DTO_Personnel connecte = (DTO_Personnel)session.getAttribute("PersonneConnectee");
		PersonnelDAO pdao = new PersonnelDAO();
		DTO_Personnel personne = pdao.getUtilisateur(connecte.getAttributes().get("login"));
		Date now = new Date();
		DateFormat date = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
		HistoriqueRecherche his = new HistoriqueRecherche();
		
		//cas où le compte n'est pas désactivé
		if(personne != null && personne.getAttributes().get("statut").contentEquals("autorise")) {
			session.setAttribute("lienHAS", null);
			session.setAttribute("lienMed", null);
			String med = request.getParameter("med");
			int nb = Integer.parseInt(request.getParameter("num"));
			ArrayList<DTO_CIS_bdpm> lienMed = dao.getListeSearchingMedicaments(med, nb);
			session.setAttribute("lienMed", lienMed);
			his.ajouterLigneHist(date.format(now), personne.getAttributes().get("login"), "A Recherché "+med);
			response.sendRedirect("ListeLiens.jsp");
		}
		//Cas où le compte est désactivé
		else {
			session.invalidate();
			response.sendRedirect("Index.jsp");
		}
			
	}

}
