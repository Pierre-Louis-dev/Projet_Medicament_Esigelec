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
 * Servlet implementation class Inscription
 */
@WebServlet("/Inscription")
public class Inscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Inscription() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * Inscription
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Initialisations
		HttpSession session = request.getSession();
		session.setAttribute("inscrit", null);
		session.setAttribute("code", null);
		session.setAttribute("LoginInvalide", null);
		session.setAttribute("MdpNonConforme", null);
		PersonnelDAO dao = new PersonnelDAO();
		
		//Fichier de logs
		Enregistrement record = new Enregistrement();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
		String date = dateFormat.format(new Date());
		String ip = (String)InetAddress.getLocalHost().getHostAddress();
		
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String login = request.getParameter("login");
		String mdp = request.getParameter("password");
		String cmdp = request.getParameter("cpassword");
		String profession = request.getParameter("profession");
		if(cmdp.contentEquals(mdp)) {
			String crypt = BCrypt.hashpw(mdp, BCrypt.gensalt());
			if(dao.Inscription(nom, prenom, login, crypt, profession)) {
				session.setAttribute("inscrit", 1);
				DTO_Personnel nouv = dao.getUtilisateur(login);
                session.setAttribute("code",nouv);
				record.ajouterLigne(date, login, ip, "Inscription réussie");
			}else if(!dao.LoginValide(login)) {
				session.setAttribute("LoginInvalide", 1);
			}
		}else {
			session.setAttribute("MdpNonConforme", 1);
		}

		response.sendRedirect("Inscription.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
