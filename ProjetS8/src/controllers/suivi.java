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
 * Servlet implementation class suivi
 */
@WebServlet("/suivi")
public class suivi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public suivi() {
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
		
		
		
		session.setAttribute("MauvaisId", null);
		session.setAttribute("refuse", null);
		session.setAttribute("attente", null);
		
		session.setAttribute("activ", null);
		int id = Integer.parseInt(request.getParameter("ID"));
		
		
		
		
		if(dao.IdValide(id)) {
			DTO_Personnel connecte = dao.getId(id);
			if(connecte.getAttributes().get("statut").contentEquals("autorise")) {
				session.setAttribute("activ", 1);
				
				
				response.sendRedirect("Suividemande.jsp");
			}else if(connecte.getAttributes().get("statut").contentEquals("refuse")) {
				session.setAttribute("refuse", 1);
				
				response.sendRedirect("Suividemande.jsp");
			}else if(connecte.getAttributes().get("statut").contentEquals("en attente")) {
				session.setAttribute("attente", 1);
				
				response.sendRedirect("Suividemande.jsp");
			}
			
		}else {
			session.setAttribute("MauvaisId", 1);
			
			response.sendRedirect("Suividemande.jsp");
		}
		
	}

	

}