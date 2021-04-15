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
 * Servlet implementation class MdpOublier
 */
@WebServlet("/MdpOublier")
public class MdpOublier extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MdpOublier() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * permet de changer le mot de passe d'un utilisateur qui a oublier son mot de passe
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Initialisations
		HttpSession session = request.getSession();
		session.setAttribute("modifer", null);
		session.setAttribute("IdInvalide", null);
		session.setAttribute("MdpNonConform", null);
		PersonnelDAO dao = new PersonnelDAO();
		
		//Fichier de logs
		Enregistrement record = new Enregistrement();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
		String date = dateFormat.format(new Date());
		String ip = (String)InetAddress.getLocalHost().getHostAddress();
		
	    int id = Integer.parseInt(request.getParameter("ID"));
		
		String mdp = request.getParameter("password");
		String cmdp = request.getParameter("cpassword");
		
		if(cmdp.contentEquals(mdp)) {
			String crypt = BCrypt.hashpw(mdp, BCrypt.gensalt());
			if(dao.ChangeMdp(id, crypt)) {
				DTO_Personnel p = dao.getId(id);
				session.setAttribute("modifier", 1);
				record.ajouterLigne(date,p.getAttributes().get("login") , ip, "Mot de passe réinitialiser");
				response.sendRedirect("ReinitialisationMdp.jsp");
			}else if(!dao.IdValide(id)) {
				session.setAttribute("IdInvalide", 1);
				response.sendRedirect("ReinitialisationMdp.jsp");
			}
		}else {
			session.setAttribute("MdpNonConform", 1);
			response.sendRedirect("ReinitialisationMdp.jsp");
		}

	
	}

}