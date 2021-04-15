package controllers;

import project.*;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Test
 */
@WebServlet("/Test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Test() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * Rechercher et afficher les présentations disponibles pour un médicament donné
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session =request.getSession();
		int nom = Integer.parseInt(request.getParameter("cis"));
		DAOfactory dao =new DAOfactory();
		if(session.getAttribute("actif")==null) {
			ArrayList<DTO_CIS_CIP_bdpm> presentations = dao.getPresentations(nom);
			session.setAttribute("presentations", presentations);
			response.sendRedirect("Presentations.jsp");
		}else {
			DTO_Personnel connecte = (DTO_Personnel)session.getAttribute("PersonneConnectee");
			PersonnelDAO pdao = new PersonnelDAO();
			DTO_Personnel personne = pdao.getUtilisateur(connecte.getAttributes().get("login"));
			//cas où le compte n'est pas désactivé
			if(personne != null && personne.getAttributes().get("statut").contentEquals("autorise")) {
				ArrayList<DTO_CIS_CIP_bdpm> presentations = dao.getPresentations(nom);
				session.setAttribute("presentations", presentations);
				response.sendRedirect("Presentations.jsp");
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
	 * Rechercher et afficher les groupes génériques auxquels appartient le médicament donné
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
			ArrayList<DTO_CIS_GENER_bdpm> groupeMed = dao.getGroupeByCisMedicament(cis);
			session.setAttribute("groupeMed", groupeMed);
			response.sendRedirect("MedtoGroup.jsp");
		}
		//Cas où le compte est désactivé
		else {
			session.invalidate();
			response.sendRedirect("Index.jsp");
		}		
	}

}
