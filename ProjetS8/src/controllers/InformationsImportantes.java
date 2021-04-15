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
 * Servlet implementation class InformationsImportantes
 */
@WebServlet("/InformationsImportantes")
public class InformationsImportantes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InformationsImportantes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * Rechercher et afficher les médicaments dont on veut afficher les infos importantes
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOfactory dao = new DAOfactory();
		HttpSession session = request.getSession();
		int nb = Integer.parseInt(request.getParameter("num"));
		
		DTO_Personnel connecte = (DTO_Personnel)session.getAttribute("PersonneConnectee");
		PersonnelDAO pdao = new PersonnelDAO();
		DTO_Personnel personne = pdao.getUtilisateur(connecte.getAttributes().get("login"));
		Date now = new Date();
		DateFormat date = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
		HistoriqueRecherche his = new HistoriqueRecherche();
		
		//cas où le compte n'est pas désactivé
		if(personne != null && personne.getAttributes().get("statut").contentEquals("autorise")) {
			session.setAttribute("informationsVide", null);
			
			if(!request.getParameter("nom").isEmpty()) {
				String nom = request.getParameter("nom");
				ArrayList<DTO_CIS_bdpm> medicaments = dao.getListeSearchingMedicaments(nom,nb) ;
				if(medicaments.size()==0)
					session.setAttribute("informationsVide", 1);		
					session.setAttribute("informations", medicaments);
					his.ajouterLigneHist(date.format(now), personne.getAttributes().get("login"), "A Recherché "+nom);
					response.sendRedirect("InfosImportantes.jsp");
					
			}else if(!request.getParameter("tri").isEmpty()) {
				String nom = request.getParameter("tri");
				ArrayList<DTO_CIS_bdpm> medicaments = dao.getListeSortedSearchingMedicaments(nom,nb) ;
				if(medicaments.size()==0)
					session.setAttribute("informationsVide", 1);		
				session.setAttribute("informations", medicaments);
				his.ajouterLigneHist(date.format(now), personne.getAttributes().get("login"), "A Recherché "+nom);
				response.sendRedirect("InfosImportantes.jsp");
				
			}else {
				session.setAttribute("informationsVide", 1);
				response.sendRedirect("InfosImportantes.jsp");
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
	 * Recherc her et afficher les informations importantes sur un médicament donné
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
	        ArrayList<DTO_CIS_INFOS_bdpm> infos = dao.getListeInfoImportantes(cis);
	        session.setAttribute("infos", infos);
	        response.sendRedirect("AffichageInfos.jsp");
		}
		//Cas où le compte est désactivé
		else {
			session.invalidate();
			response.sendRedirect("Index.jsp");
		}
        
	}

}
