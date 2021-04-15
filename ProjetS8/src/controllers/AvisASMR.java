package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.*;

/**
 * Servlet implementation class AvisASMR
 */
@WebServlet("/AvisASMR")
public class AvisASMR extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AvisASMR() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * Methode pour récuperer la liste des avis ASMR d'un médicament donné
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
			ArrayList<DTO_CIS_HAS_ASMR_bdpm> avis = dao.getAvisASMR(cis);
			session.setAttribute("avisASMR", avis);
			response.sendRedirect("AvisASMR.jsp");
		}
		//Cas où le compte est désactivé
		else {
			session.invalidate();
			response.sendRedirect("Index.jsp");
		}		
	}

}
